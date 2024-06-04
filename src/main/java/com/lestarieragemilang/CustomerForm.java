package com.lestarieragemilang;

import java.util.Map;

import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonType;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import com.lestarieragemilang.Utilities.Redirect;
import com.lestarieragemilang.Utilities.CacheService;

import com.lestarieragemilang.Entities.CustomerEntity;
import com.lestarieragemilang.Repositories.CustomerRepositories;



/**
 *  Customer Form
 */
public class CustomerForm extends CustomerRepositories {

  @FXML
  private AnchorPane anchorPane;

  @FXML
  private Button customerActionButton;

  @FXML
  private TextField customerIdField;

  @FXML
  private TextField customerNameField;

  @FXML
  private TextField customerContactField;

  @FXML
  private TextField customerAddressField;

  @FXML
  private TextField customerEmailField;


  @FXML
  void clearButton (MouseEvent event) {
    customerIdField.setText("");
    customerNameField.setText("");
    customerContactField.setText("");
    customerAddressField.setText("");
    customerEmailField.setText("");

    CacheService.clear();
  }


  @FXML
  void backButton (MouseEvent event) {
    Redirect.page("customer", anchorPane, getClass());
    CacheService.clear();
  }


  @FXML
  void customerActionButton(MouseEvent event) {
    CustomerEntity entity = new CustomerEntity();

    entity.setCustomerId(customerIdField.getText());
    entity.setCustomerName(customerNameField.getText());
    entity.setCustomerContact(customerContactField.getText());
    entity.setCustomerAddress(customerAddressField.getText());
    entity.setCustomerEmail(customerEmailField.getText());

    // Update Customer Data
    if (customerActionButton.getText().equals("Update")) {
      Map<String, Object> response = this
        .updateCustomerRepository(entity);

      if ((boolean) response.get("result")) {
        Alert confirmationDialog = new Alert(Alert.AlertType.INFORMATION);
        confirmationDialog.getDialogPane().setPrefSize(450, 250);

        confirmationDialog.setTitle("Berhasil Memperbarui Data.");
        confirmationDialog.setHeaderText((String) response.get("message"));
    
        confirmationDialog.getButtonTypes()
          .setAll(ButtonType.YES);
    
        confirmationDialog.showAndWait().ifPresent(buttonType -> {
          if (buttonType == ButtonType.YES) {
            Redirect.page("customer", anchorPane, getClass());
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


        // Add Customer Data
    } else if (customerActionButton.getText().equals("Tambah")) {
      Map<String, Object> response = this
        .createCustomerRepository(entity);

      if ((boolean) response.get("result")) {
        Alert confirmationDialog = new Alert(Alert.AlertType.INFORMATION);
        confirmationDialog.getDialogPane().setPrefSize(450, 250);

        confirmationDialog.setTitle("Berhasil Menambahkan Data.");
        confirmationDialog.setHeaderText((String) response.get("message"));

        confirmationDialog.getButtonTypes()
          .setAll(ButtonType.YES);

        confirmationDialog.showAndWait().ifPresent(buttonType -> {
          if (buttonType == ButtonType.YES) {
            Redirect.page("customer", anchorPane, getClass());
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
    String customerId = (String) CacheService.get("customerId");
    if (customerId != null) {
      customerIdField.setText(customerId);
      customerNameField.setText((String) CacheService.get("customerName"));
      customerContactField.setText((String) CacheService.get("customerContact"));
      customerAddressField.setText((String) CacheService.get("customerAddress"));
      customerEmailField.setText((String) CacheService.get("customerEmail"));
    }
  }


  @FXML
  public void initialize() {
    if (CacheService.get("customerId") != null) {
      customerActionButton.setText("Update");
      customerIdField.setEditable(false);

    } else {
      customerActionButton.setText("Tambah");
    }

    inputHandler();
  }
}
