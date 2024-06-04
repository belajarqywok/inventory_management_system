package com.lestarieragemilang;

import java.util.Map;
import java.sql.Date;
import java.time.LocalDate;

import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import com.lestarieragemilang.Utilities.Redirect;
import com.lestarieragemilang.Utilities.CacheService;

import com.lestarieragemilang.Entities.PurchasingEntity;
import com.lestarieragemilang.Repositories.PurchasingRepositories;



/**
 *  Purchasing Form
 */
public class PurchasingForm extends PurchasingRepositories {

  @FXML
  private AnchorPane anchorPane;

  @FXML
  private Button purchasingActionButton;

  @FXML
  private DatePicker purchasingDateField;

  @FXML
  private TextField purchasingIdField;

  @FXML
  private TextField categoryIdField;

  @FXML
  private TextField supplierIdField;

  @FXML
  private TextField purchasingAmountField;

  @FXML
  private TextField purchasingPriceField;


  @FXML
  void clearButton (MouseEvent event) {
    purchasingIdField.setText("");
    categoryIdField.setText("");
    supplierIdField.setText("");
    purchasingAmountField.setText("");
    purchasingPriceField.setText("");

    CacheService.clear();
  }


  @FXML
  void backButton (MouseEvent event) {
    Redirect.page("purchasing", anchorPane, getClass());
    CacheService.clear();
  }


  @FXML
  void purchasingActionButton(MouseEvent event) {
    PurchasingEntity entity = new PurchasingEntity();

    entity.setPurchasingId(purchasingIdField.getText());
    entity.setCategoryId(categoryIdField.getText());
    entity.setSupplierId(supplierIdField.getText());
    entity.setPurchasingAmount(purchasingAmountField.getText());
    entity.setPurchasingPrice(purchasingPriceField.getText());
    entity.setPurchasingDate(Date.valueOf(purchasingDateField.getValue()));

    // Update Purchasing Data
    if (purchasingActionButton.getText().equals("Update")) {
      Map<String, Object> response = this
        .updatePurchasingRepository(entity);

      if ((boolean) response.get("result")) {
        Alert confirmationDialog = new Alert(Alert.AlertType.INFORMATION);
        confirmationDialog.getDialogPane().setPrefSize(450, 250);

        confirmationDialog.setTitle("Berhasil Memperbarui Data.");
        confirmationDialog.setHeaderText((String) response.get("message"));
    
        confirmationDialog.getButtonTypes()
          .setAll(ButtonType.YES);
    
        confirmationDialog.showAndWait().ifPresent(buttonType -> {
          if (buttonType == ButtonType.YES) {
            Redirect.page("purchasing", anchorPane, getClass());
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


    // Add Purchasing Data
    } else if (purchasingActionButton.getText().equals("Tambah")) {
      Map<String, Object> response = this
        .createPurchasingRepository(entity);

      if ((boolean) response.get("result")) {
        Alert confirmationDialog = new Alert(Alert.AlertType.INFORMATION);
        confirmationDialog.getDialogPane().setPrefSize(450, 250);

        confirmationDialog.setTitle("Berhasil Menambahkan Data.");
        confirmationDialog.setHeaderText((String) response.get("message"));

        confirmationDialog.getButtonTypes()
          .setAll(ButtonType.YES);

        confirmationDialog.showAndWait().ifPresent(buttonType -> {
          if (buttonType == ButtonType.YES) {
            Redirect.page("purchasing", anchorPane, getClass());
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
    String purchasingId = (String) CacheService.get("purchasingId");
    if (purchasingId != null) {
      purchasingIdField.setText(purchasingId);
      categoryIdField.setText((String) CacheService.get("categoryId"));
      supplierIdField.setText((String) CacheService.get("supplierId"));
      purchasingAmountField.setText((String) CacheService.get("purchasingAmount"));
      purchasingPriceField.setText((String) CacheService.get("purchasingPrice"));
      
      Date purchasingDate = (Date) CacheService.get("purchasingDate");
      LocalDate localDate = purchasingDate.toLocalDate();
      purchasingDateField.setValue(localDate);
    }
  }


  @FXML
  public void initialize() {
    purchasingDateField.setValue(LocalDate.now());
    
    if (CacheService.get("purchasingId") != null) {
      purchasingActionButton.setText("Update");
      purchasingIdField.setEditable(false);

    } else {
      purchasingActionButton.setText("Tambah");
    }

    inputHandler();
  }
}
