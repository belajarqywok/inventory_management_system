package com.lestarieragemilang;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import com.lestarieragemilang.Utilities.Redirect;
import com.lestarieragemilang.Utilities.CacheService;
import com.lestarieragemilang.Entities.CustomerEntity;
import com.lestarieragemilang.Repositories.CustomerRepositories;

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
    void customerActionButton (MouseEvent event) {
        CustomerEntity entity = new CustomerEntity();

        entity.setCustomerId(customerIdField.getText());
        entity.setCustomerName(customerNameField.getText());
        entity.setCustomerContact(customerContactField.getText());
        entity.setCustomerAddress(customerAddressField.getText());
        entity.setCustomerEmail(customerEmailField.getText());

        if (customerActionButton.getText().equals("Update")) {
            this.updateCustomerRepository(entity);

        } else if (customerActionButton.getText().equals("Tambah")) {
            this.createCustomerRepository(entity);
        }

        Redirect.page("customer", anchorPane, getClass());
        CacheService.clear();
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

        } else {
            customerActionButton.setText("Tambah");
        }

        inputHandler();
    }
}
