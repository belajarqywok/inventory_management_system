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

import com.lestarieragemilang.Entities.PurchasingEntity;
import com.lestarieragemilang.Repositories.PurchasingRepositories;



/**
 *  Purchasing
 */
public class Purchasing extends PurchasingRepositories {

  @FXML
  private TableView<PurchasingEntity> purchasingsTable;

  @FXML
  private TableColumn<PurchasingEntity, String> purchasingDate;

  @FXML
  private TableColumn<PurchasingEntity, String> purchasingId;

  @FXML
  private TableColumn<PurchasingEntity, String> categoryId;

  @FXML
  private TableColumn<PurchasingEntity, String> categoryBrand;

  @FXML
  private TableColumn<PurchasingEntity, String> categoryType;

  @FXML
  private TableColumn<PurchasingEntity, String> supplierId;

  @FXML
  private TableColumn<PurchasingEntity, String> supplierName;

  @FXML
  private TableColumn<PurchasingEntity, String> purchasingAmount;

  @FXML
  private TableColumn<PurchasingEntity, String> purchasingPrice;

  @FXML
  private TableColumn<PurchasingEntity, String> purchasingTotal;



  @FXML
  private AnchorPane anchorPane;

  @FXML
  private Label featureLabel;

  @FXML
  private TextField searchTextField;

  @FXML
  private Button editPurchasing;

  @FXML
  private Button addPurchasing;

  @FXML
  private Button deletePurchasing;




  @FXML
  private BorderPane bp;

  private ObservableList<PurchasingEntity> data;


  @FXML
  void searchStockAction(MouseEvent event) {
    addPurchasing.setVisible(true);
    editPurchasing.setVisible(false);
    deletePurchasing.setVisible(false);

    CacheService.clear();

    data = FXCollections.observableArrayList();

    List<Object[]> searchStocks = this
        .searchPurchasingsRepository(searchTextField.getText());

    for (Object[] rowData : searchStocks) {
      PurchasingEntity entity = new PurchasingEntity();

      entity.setPurchasingDate((String) rowData[0]);
      entity.setPurchasingId((String) rowData[1]);
      entity.setCategoryId((String) rowData[2]);
      entity.setCategoryBrand((String) rowData[3]);
      entity.setCategoryType((String) rowData[4]);
      entity.setSupplierId((String) rowData[5]);
      entity.setSupplierName((String) rowData[6]);
      entity.setPurchasingAmount((String) rowData[7]);
      entity.setPurchasingPrice((String) rowData[8]);
      entity.setPurchasingTotal((String) rowData[9]);

      data.add(entity);
      purchasingsTable.setItems(data);
    }
  }

  @FXML
  void addPurchasingAction(MouseEvent event) {
    Redirect.page("purchasingForm", anchorPane, getClass());
  }

  @FXML
  void editPurchasingAction(MouseEvent event) {
    Redirect.page("purchasingForm", anchorPane, getClass());
  }

  @FXML
  void deletePurchasingAction(MouseEvent event) {
    Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
    confirmationDialog.getDialogPane().setPrefSize(450, 250);

    confirmationDialog.setTitle("Konfirmasi");
    confirmationDialog.setHeaderText("Hapus Pembelian");
    confirmationDialog.setContentText("Apakah anda yakin ?.");

    confirmationDialog.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

    confirmationDialog.showAndWait().ifPresent(buttonType -> {
      if (buttonType == ButtonType.YES) {
        deletePurchasingRepository((String) CacheService.get("purchasingId"));
        Redirect.page("purchasing", anchorPane, getClass());
      }
    });
  }

  private void connectAndReadFromDatabase() {
    data = FXCollections.observableArrayList();

    List<Object[]> getStocks = this
        .getPurchasingsRepository();

    for (Object[] rowData : getStocks) {
      PurchasingEntity entity = new PurchasingEntity();

      entity.setPurchasingDate((String) rowData[0]);
      entity.setPurchasingId((String) rowData[1]);
      entity.setCategoryId((String) rowData[2]);
      entity.setCategoryBrand((String) rowData[3]);
      entity.setCategoryType((String) rowData[4]);
      entity.setSupplierId((String) rowData[5]);
      entity.setSupplierName((String) rowData[6]);
      entity.setPurchasingAmount((String) rowData[7]);
      entity.setPurchasingPrice((String) rowData[8]);
      entity.setPurchasingTotal((String) rowData[9]);

      data.add(entity);
    }
  }

  private void clickHandler() {
    EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        addPurchasing.setVisible(true);
        editPurchasing.setVisible(false);
        deletePurchasing.setVisible(false);

        CacheService.clear();
      }
    };

    anchorPane.setOnMouseClicked(handler);
    featureLabel.setOnMouseClicked(handler);
    searchTextField.setOnMouseClicked(handler);
  }

  @FXML
  public void initialize() {
    purchasingDate.setCellValueFactory(
        new PropertyValueFactory<PurchasingEntity, String>("purchasingDate"));
    purchasingId.setCellValueFactory(
        new PropertyValueFactory<PurchasingEntity, String>("purchasingId"));
    categoryId.setCellValueFactory(
        new PropertyValueFactory<PurchasingEntity, String>("categoryId"));
    categoryBrand.setCellValueFactory(
        new PropertyValueFactory<PurchasingEntity, String>("categoryBrand"));
    categoryType.setCellValueFactory(
        new PropertyValueFactory<PurchasingEntity, String>("categoryType"));
    supplierId.setCellValueFactory(
        new PropertyValueFactory<PurchasingEntity, String>("supplierId"));
    supplierName.setCellValueFactory(
        new PropertyValueFactory<PurchasingEntity, String>("supplierName"));
    purchasingAmount.setCellValueFactory(
        new PropertyValueFactory<PurchasingEntity, String>("purchasingAmount"));
    purchasingPrice.setCellValueFactory(
      new PropertyValueFactory<PurchasingEntity, String>("purchasingPrice"));
    purchasingTotal.setCellValueFactory(
        new PropertyValueFactory<PurchasingEntity, String>("purchasingTotal"));

    CacheService.clear();
    connectAndReadFromDatabase();
    clickHandler();

    purchasingsTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        if (event.getClickCount() == 1) {
          PurchasingEntity selectedStock = purchasingsTable
              .getSelectionModel().getSelectedItem();

          if (selectedStock != null) {
            CacheService.put("purchasingDate", selectedStock.getPurchasingDate());
            CacheService.put("purchasingId", selectedStock.getPurchasingId());
            CacheService.put("categoryId", selectedStock.getCategoryId());
            CacheService.put("categoryBrand", selectedStock.getCategoryBrand());
            CacheService.put("categoryType", selectedStock.getCategoryType());
            CacheService.put("supplierId", selectedStock.getSupplierId());
            CacheService.put("supplierName", selectedStock.getSupplierName());
            CacheService.put("purchasingAmount", selectedStock.getPurchasingAmount());
            CacheService.put("purchasingPrice", selectedStock.getPurchasingPrice());
            CacheService.put("purchasingTotal", selectedStock.getPurchasingTotal());

            addPurchasing.setVisible(false);
            editPurchasing.setVisible(true);
            deletePurchasing.setVisible(true);
          }
        }
      }
    });

    purchasingsTable.setItems(data);
  }
}
