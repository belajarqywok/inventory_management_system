package com.lestarieragemilang;

import java.util.Map;
import java.util.HashMap;

import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonType;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import com.lestarieragemilang.Utilities.Redirect;
import com.lestarieragemilang.Utilities.CacheService;

import com.lestarieragemilang.Entities.StockEntity;
import com.lestarieragemilang.Repositories.StockRepositories;



/**
 *  Stock Form Handler
 */
public class StockForm extends StockRepositories {
  
  @FXML
  private AnchorPane anchorPane;

  @FXML
  private Button stockActionButton;



  @FXML
  private TextField stockIdField;

  @FXML
  private TextField categoryIdField;

  @FXML
  private TextField stockSellPriceField;

  @FXML
  private TextField stockPurchasePriceField;

  @FXML
  private TextField stockSizeField;

  @FXML
  private TextField stockAmountField;

  @FXML
  private TextField stockUnitField;


  @FXML
  void clearButton (MouseEvent event) {
    stockIdField.setText("");
    categoryIdField.setText("");
    stockSellPriceField.setText("");
    stockPurchasePriceField.setText("");
    stockSizeField.setText("");
    stockAmountField.setText("");
    stockUnitField.setText("");

    CacheService.clear();
  }


  @FXML
  void backButton (MouseEvent event) {
    Redirect.page("stock", anchorPane, getClass());
    CacheService.clear();
  }


  @FXML
  void stockActionButton(MouseEvent event) {
    StockEntity entity = new StockEntity();

    entity.setStockId(stockIdField.getText());
    entity.setCategoryId(categoryIdField.getText());
    entity.setStockSellPrice(stockSellPriceField.getText());
    entity.setStockPurchasePrice(stockPurchasePriceField.getText());
    entity.setStockSize(stockSizeField.getText());
    entity.setStockAmount(stockAmountField.getText());
    entity.setStockUnit(stockUnitField.getText());

    // Update Stock Data
    if (stockActionButton.getText().equals("Update")) {
      Map<String, Object> response = this
        .updateStockRepository(entity);

      if ((boolean) response.get("result")) {
        Alert confirmationDialog = new Alert(Alert.AlertType.INFORMATION);
        confirmationDialog.getDialogPane().setPrefSize(450, 250);

        confirmationDialog.setTitle("Berhasil Memperbarui Data.");
        confirmationDialog.setHeaderText((String) response.get("message"));
    
        confirmationDialog.getButtonTypes()
          .setAll(ButtonType.YES);
    
        confirmationDialog.showAndWait().ifPresent(buttonType -> {
          if (buttonType == ButtonType.YES) {
            Redirect.page("stock", anchorPane, getClass());
            CacheService.clear();
          }
        });
    
      } else {
        Alert confirmationDialog = new Alert(Alert.AlertType.ERROR);
        confirmationDialog.getDialogPane().setPrefSize(450, 250);

        confirmationDialog.setTitle("Gagal Memperbarui Data.");
        confirmationDialog.setHeaderText((String) response.get("message"));
    
        confirmationDialog.getButtonTypes().setAll(ButtonType.YES);
        confirmationDialog.showAndWait();
      }


    // Add Stock Data
    } else if (stockActionButton.getText().equals("Tambah")) {
      Map<String, Object> response = this
        .createStockRepository(entity);

      if ((boolean) response.get("result")) {
        Alert confirmationDialog = new Alert(Alert.AlertType.INFORMATION);
        confirmationDialog.getDialogPane().setPrefSize(450, 250);

        confirmationDialog.setTitle("Berhasil Menambahkan Data.");
        confirmationDialog.setHeaderText((String) response.get("message"));

        confirmationDialog.getButtonTypes()
          .setAll(ButtonType.YES);

        confirmationDialog.showAndWait().ifPresent(buttonType -> {
          if (buttonType == ButtonType.YES) {
            Redirect.page("stock", anchorPane, getClass());
            CacheService.clear();
          }
        });

      } else {
        Alert confirmationDialog = new Alert(Alert.AlertType.ERROR);
        confirmationDialog.getDialogPane().setPrefSize(450, 250);

        confirmationDialog.setTitle("Gagal Menambahkan Data.");
        confirmationDialog.setHeaderText((String) response.get("message"));

        confirmationDialog.getButtonTypes().setAll(ButtonType.YES);
        confirmationDialog.showAndWait();
      }
    }
  }


  private void inputHandler() {
    String stockId = (String) CacheService.get("stockId");
    if (stockId != null) {
      stockIdField.setText(stockId);
      categoryIdField.setText((String) CacheService.get("categoryId"));
      stockSellPriceField.setText((String) CacheService.get("stockSellPrice"));
      stockPurchasePriceField.setText((String) CacheService.get("stockPurchasePrice"));
      stockSizeField.setText((String) CacheService.get("stockSize"));
      stockAmountField.setText((String) CacheService.get("stockAmount"));
      stockUnitField.setText((String) CacheService.get("stockUnit"));
    }
  }


  @FXML
  public void initialize() {
    if (CacheService.get("stockId") != null) {
      stockActionButton.setText("Update");
      stockIdField.setEditable(false);

    } else {
      stockActionButton.setText("Tambah");
    }

    inputHandler();
  }
}
