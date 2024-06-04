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
import com.lestarieragemilang.Entities.SupplierEntity;
import com.lestarieragemilang.Repositories.SupplierRepositories;



/**
 *  Supplier
 */
public class Supplier extends SupplierRepositories {

  @FXML
  private TableView<SupplierEntity> suppliersTable;

  @FXML
  private TableColumn<SupplierEntity, String> supplierId;

  @FXML
  private TableColumn<SupplierEntity, String> supplierName;

  @FXML
  private TableColumn<SupplierEntity, String> supplierContact;

  @FXML
  private TableColumn<SupplierEntity, String> supplierAddress;

  @FXML
  private TableColumn<SupplierEntity, String> supplierEmail;



  @FXML
  private AnchorPane anchorPane;

  @FXML
  private Label featureLabel;

  @FXML
  private TextField searchTextField;

  @FXML
  private Button editSupplier;

  @FXML
  private Button addSupplier;

  @FXML
  private Button deleteSupplier;



  @FXML
  private BorderPane bp;

  private ObservableList<SupplierEntity> data;


  @FXML
  void searchSupplierAction(MouseEvent event) {
    addSupplier.setVisible(true);
    editSupplier.setVisible(false);
    deleteSupplier.setVisible(false);

    CacheService.clear();

    data = FXCollections.observableArrayList();

    List<Object[]> searchSuppliers = this
      .searchSuppliersRepository(searchTextField.getText());

    for (Object[] rowData : searchSuppliers) {
      SupplierEntity entity = new SupplierEntity();

      entity.setSupplierId((String) rowData[0]);
      entity.setSupplierName((String) rowData[1]);
      entity.setSupplierContact((String) rowData[2]);
      entity.setSupplierAddress((String) rowData[3]);
      entity.setSupplierEmail((String) rowData[4]);

      data.add(entity);
      suppliersTable.setItems(data);
    }
  }

  @FXML
  void addSupplierAction (MouseEvent event) {
    Redirect.page("supplierForm", anchorPane, getClass());
  }

  @FXML
  void editSupplierAction (MouseEvent event) {
    Redirect.page("supplierForm", anchorPane, getClass());
  }

  @FXML
  void deleteSupplierAction (MouseEvent event) {
    Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
    confirmationDialog.setTitle("Konfirmasi");
    confirmationDialog.setHeaderText("hapus Supplier");
    confirmationDialog.setContentText("Apakah anda yakin ?.");

    confirmationDialog.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

    confirmationDialog.showAndWait().ifPresent(buttonType -> {
        if (buttonType == ButtonType.YES) {
            deleteSupplierRepository((String) CacheService.get("supplierId"));
            Redirect.page("supplier", anchorPane, getClass());
        }
    });
  }

  private void connectAndReadFromDatabase() {
    data = FXCollections.observableArrayList();

    List<Object[]> getSuppliers = this
      .getSuppliersRepository();

    for (Object[] rowData : getSuppliers) {
      SupplierEntity entity = new SupplierEntity();

      entity.setSupplierId((String) rowData[0]);
      entity.setSupplierName((String) rowData[1]);
      entity.setSupplierContact((String) rowData[2]);
      entity.setSupplierAddress((String) rowData[3]);
      entity.setSupplierEmail((String) rowData[4]);

      data.add(entity);
    }
  }

  private void clickHandler () {
    EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        addSupplier.setVisible(true);
        editSupplier.setVisible(false);
        deleteSupplier.setVisible(false);

        CacheService.clear();
      }
    };

    anchorPane.setOnMouseClicked(handler);
    featureLabel.setOnMouseClicked(handler);
    searchTextField.setOnMouseClicked(handler);
  }

  @FXML
  public void initialize() {
    supplierId.setCellValueFactory(
      new PropertyValueFactory<SupplierEntity, String>("supplierId"));
    supplierName.setCellValueFactory(
      new PropertyValueFactory<SupplierEntity, String>("supplierName"));
    supplierContact.setCellValueFactory(
      new PropertyValueFactory<SupplierEntity, String>("supplierContact"));
    supplierAddress.setCellValueFactory(
      new PropertyValueFactory<SupplierEntity, String>("supplierAddress"));
    supplierEmail.setCellValueFactory(
      new PropertyValueFactory<SupplierEntity, String>("supplierEmail"));

    CacheService.clear();
    connectAndReadFromDatabase();
    clickHandler();

    suppliersTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        if (event.getClickCount() == 1) {
          SupplierEntity selectedSupplier = suppliersTable
            .getSelectionModel().getSelectedItem();

          if (selectedSupplier != null) {
            CacheService.put("supplierId", selectedSupplier.getSupplierId());
            CacheService.put("supplierName", selectedSupplier.getSupplierName());
            CacheService.put("supplierContact", selectedSupplier.getSupplierContact());
            CacheService.put("supplierAddress", selectedSupplier.getSupplierAddress());
            CacheService.put("supplierEmail", selectedSupplier.getSupplierEmail());

            addSupplier.setVisible(false);
            editSupplier.setVisible(true);
            deleteSupplier.setVisible(true);
          }
        }
      }
    });

    suppliersTable.setItems(data);
  }

}
