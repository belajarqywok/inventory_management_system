package com.lestarieragemilang;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import com.lestarieragemilang.Utilities.Redirect;
import com.lestarieragemilang.Utilities.CacheService;

import com.lestarieragemilang.Entities.StockEntity;
import com.lestarieragemilang.Repositories.StockRepositories;
 


/**
 *  Stock Controller
 */
public class StockController extends StockRepositories {

	@FXML
  private TableView<StockEntity> stocksTable;


  @FXML
  private TableColumn<StockEntity, String> stockId;

  @FXML
  private TableColumn<StockEntity, String> categoryId;

  @FXML
  private TableColumn<StockEntity, String> stockSellPrice;

  @FXML
  private TableColumn<StockEntity, String> stockPurchasePrice;

	@FXML
  private TableColumn<StockEntity, String> stockSize;

	@FXML
  private TableColumn<StockEntity, String> stockAmount;

	@FXML
  private TableColumn<StockEntity, String> stockUnit;



	@FXML
  private AnchorPane anchorPane;

  @FXML
  private Label featureLabel;

  @FXML
  private TextField searchTextField;

  @FXML
  private Button editStock;

  @FXML
  private Button addStock;

  @FXML
  private Button deleteStock;


	@FXML
  private BorderPane bp;

  private ObservableList<StockEntity> data;


	@FXML
  void searchStockAction(MouseEvent event) {
    addStock.setVisible(true);
    editStock.setVisible(false);
    deleteStock.setVisible(false);

    CacheService.clear();

    data = FXCollections.observableArrayList();

    List<Object[]> searchStocks = this
      .searchStocksRepository(searchTextField.getText());

    for (Object[] rowData : searchStocks) {
      StockEntity entity = new StockEntity();

			entity.setStockId((String) rowData[0]);
      entity.setCategoryId((String) rowData[1]);
			entity.setStockSellPrice((String) rowData[2]);
			entity.setStockPurchasePrice((String) rowData[3]);
			entity.setStockSize((String) rowData[4]);
			entity.setStockAmount((String) rowData[5]);
			entity.setStockUnit((String) rowData[6]);

      data.add(entity);
      stocksTable.setItems(data);
    }
  }


	@FXML
  void addStockAction (MouseEvent event) {
    Redirect.page("stockForm", anchorPane, getClass());
  }


  @FXML
  void editStockAction (MouseEvent event) {
    Redirect.page("stockForm", anchorPane, getClass());
  }



	@FXML
  void deleteStockAction (MouseEvent event) {
    Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
    confirmationDialog.getDialogPane().setPrefSize(450, 250);
    
    confirmationDialog.setTitle("Konfirmasi");
    confirmationDialog.setHeaderText("hapus Stok");
    confirmationDialog.setContentText("Apakah anda yakin ?.");

    confirmationDialog.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

    confirmationDialog.showAndWait().ifPresent(buttonType -> {
        if (buttonType == ButtonType.YES) {
            deleteStockRepository((String) CacheService.get("stockId"));
            Redirect.page("stock", anchorPane, getClass());
        }
    });
  }


	private void connectAndReadFromDatabase() {
    data = FXCollections.observableArrayList();

    List<Object[]> getStocks = this
      .getStocksRepository();

    for (Object[] rowData : getStocks) {
      StockEntity entity = new StockEntity();

      entity.setStockId((String) rowData[0]);
      entity.setCategoryId((String) rowData[1]);
			entity.setStockSellPrice((String) rowData[2]);
			entity.setStockPurchasePrice((String) rowData[3]);
			entity.setStockSize((String) rowData[4]);
			entity.setStockAmount((String) rowData[5]);
			entity.setStockUnit((String) rowData[6]);

      data.add(entity);
    }
  }


	private void clickHandler () {
    EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        addStock.setVisible(true);
        editStock.setVisible(false);
        deleteStock.setVisible(false);

        CacheService.clear();
      }
    };

    anchorPane.setOnMouseClicked(handler);
    featureLabel.setOnMouseClicked(handler);
    searchTextField.setOnMouseClicked(handler);
  }


	@FXML
  public void initialize() {
		stockId.setCellValueFactory(
      new PropertyValueFactory<StockEntity, String>("stockId"));
    categoryId.setCellValueFactory(
      new PropertyValueFactory<StockEntity, String>("categoryId"));
		stockSellPrice.setCellValueFactory(
      new PropertyValueFactory<StockEntity, String>("stockSellPrice"));
		stockPurchasePrice.setCellValueFactory(
      new PropertyValueFactory<StockEntity, String>("stockPurchasePrice"));
		stockSize.setCellValueFactory(
      new PropertyValueFactory<StockEntity, String>("stockSize"));
		stockAmount.setCellValueFactory(
			new PropertyValueFactory<StockEntity, String>("stockAmount"));
		stockUnit.setCellValueFactory(
			new PropertyValueFactory<StockEntity, String>("stockUnit"));

    CacheService.clear();
    connectAndReadFromDatabase();
    clickHandler();

    stocksTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        if (event.getClickCount() == 1) {
          StockEntity selectedStock = stocksTable
            .getSelectionModel().getSelectedItem();

          if (selectedStock != null) {
            CacheService.put("stockId", selectedStock.getStockId());
            CacheService.put("categoryId", selectedStock.getCategoryId());
            CacheService.put("stockSellPrice", selectedStock.getStockSellPrice());
            CacheService.put("stockPurchasePrice", selectedStock.getStockPurchasePrice());
						CacheService.put("stockSize", selectedStock.getStockSize());
						CacheService.put("stockAmount", selectedStock.getStockAmount());
						CacheService.put("stockUnit", selectedStock.getStockUnit());

            addStock.setVisible(false);
            editStock.setVisible(true);
            deleteStock.setVisible(true);
          }
        }
      }
    });

    stocksTable.setItems(data);
  }
}

