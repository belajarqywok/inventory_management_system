package com.lestarieragemilang;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import com.lestarieragemilang.Utilities.Redirect;
import com.lestarieragemilang.Utilities.CacheService;
import com.lestarieragemilang.Entities.SupplierEntity;
import com.lestarieragemilang.Repositories.SupplierRepositories;

public class SupplierForm extends SupplierRepositories {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button supplierActionButton;

    @FXML
    private TextField supplierIdField;

    @FXML
    private TextField supplierNameField;

    @FXML
    private TextField supplierContactField;

    @FXML
    private TextField supplierAddressField;

    @FXML
    private TextField supplierEmailField;

    @FXML
    void clearButton (MouseEvent event) {
        supplierIdField.setText("");
        supplierNameField.setText("");
        supplierContactField.setText("");
        supplierAddressField.setText("");
        supplierEmailField.setText("");

        CacheService.clear();
    }

    @FXML
    void backButton (MouseEvent event) {
        Redirect.page("supplier", anchorPane, getClass());
        CacheService.clear();
    }

    @FXML
    void supplierActionButton (MouseEvent event) {
        SupplierEntity entity = new SupplierEntity();

        entity.setSupplierId(supplierIdField.getText());
        entity.setSupplierName(supplierNameField.getText());
        entity.setSupplierContact(supplierContactField.getText());
        entity.setSupplierAddress(supplierAddressField.getText());
        entity.setSupplierEmail(supplierEmailField.getText());

        if (supplierActionButton.getText().equals("Update")) {
            this.updateSupplierRepository(entity);

        } else if (supplierActionButton.getText().equals("Tambah")) {
            this.createSupplierRepository(entity);
        }

        Redirect.page("supplier", anchorPane, getClass());
        CacheService.clear();
    }

    private void inputHandler() {
        String supplierId = (String) CacheService.get("supplierId");
        if (supplierId != null) {
            supplierIdField.setText(supplierId);
            supplierNameField.setText((String) CacheService.get("supplierName"));
            supplierContactField.setText((String) CacheService.get("supplierContact"));
            supplierAddressField.setText((String) CacheService.get("supplierAddress"));
            supplierEmailField.setText((String) CacheService.get("supplierEmail"));
        }
    }

    @FXML
    public void initialize() {
        if (CacheService.get("supplierId") != null) {
            supplierActionButton.setText("Update");

        } else {
            supplierActionButton.setText("Tambah");
        }

        inputHandler();
    }
}
