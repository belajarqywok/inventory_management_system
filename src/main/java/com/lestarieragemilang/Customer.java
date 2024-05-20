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

import com.lestarieragemilang.Entities.CustomerEntity;
import com.lestarieragemilang.Repositories.CustomerRepositories;


/**
 *  Customer Handler
 */
public class Customer extends CustomerRepositories {

  @FXML
  private TableView<CustomerEntity> customersTable;

  @FXML
  private TableColumn<CustomerEntity, String> customerId;

  @FXML
  private TableColumn<CustomerEntity, String> customerName;

  @FXML
  private TableColumn<CustomerEntity, String> customerContact;

  @FXML
  private TableColumn<CustomerEntity, String> customerAddress;

  @FXML
  private TableColumn<CustomerEntity, String> customerEmail;
  

  @FXML
  private AnchorPane anchorPane;

  @FXML
  private Label featureLabel;

  @FXML
  private TextField searchTextField;

  @FXML
  private Button editCustomer;

  @FXML
  private Button addCustomer;

  @FXML
  private Button deleteCustomer;


  @FXML
  private BorderPane bp;

  private ObservableList<CustomerEntity> data;


  @FXML
  void searchCustomerAction(MouseEvent event) {
    addCustomer.setVisible(true);
    editCustomer.setVisible(false);
    deleteCustomer.setVisible(false);

    CacheService.clear();

    data = FXCollections.observableArrayList();

    List<Object[]> searchCustomers = this
      .searchCustomersRepository(searchTextField.getText());

    for (Object[] rowData : searchCustomers) {
      CustomerEntity entity = new CustomerEntity();

      entity.setCustomerId((String) rowData[0]);
      entity.setCustomerName((String) rowData[1]);
      entity.setCustomerContact((String) rowData[2]);
      entity.setCustomerAddress((String) rowData[3]);
      entity.setCustomerEmail((String) rowData[4]);

      data.add(entity);
      customersTable.setItems(data);
    }
  }


  @FXML
  void addCustomerAction (MouseEvent event) {
    Redirect.page("customerForm", anchorPane, getClass());
  }


  @FXML
  void editCustomerAction (MouseEvent event) {
    Redirect.page("customerForm", anchorPane, getClass());
  }


  @FXML
  void deleteCustomerAction (MouseEvent event) {
    Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
    confirmationDialog.getDialogPane().setPrefSize(450, 250);
    
    confirmationDialog.setTitle("Konfirmasi");
    confirmationDialog.setHeaderText("hapus Customer");
    confirmationDialog.setContentText("Apakah anda yakin ?.");

    confirmationDialog.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

    confirmationDialog.showAndWait().ifPresent(buttonType -> {
        if (buttonType == ButtonType.YES) {
            deleteCustomerRepository((String) CacheService.get("customerId"));
            Redirect.page("customer", anchorPane, getClass());
        }
    });
  }


  private void connectAndReadFromDatabase() {
    data = FXCollections.observableArrayList();

    List<Object[]> getCustomers = this
      .getCustomersRepository();

    for (Object[] rowData : getCustomers) {
      CustomerEntity entity = new CustomerEntity();

      entity.setCustomerId((String) rowData[0]);
      entity.setCustomerName((String) rowData[1]);
      entity.setCustomerContact((String) rowData[2]);
      entity.setCustomerAddress((String) rowData[3]);
      entity.setCustomerEmail((String) rowData[4]);

      data.add(entity);
    }
  }


  private void clickHandler () {
    EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        addCustomer.setVisible(true);
        editCustomer.setVisible(false);
        deleteCustomer.setVisible(false);

        CacheService.clear();
      }
    };

    anchorPane.setOnMouseClicked(handler);
    featureLabel.setOnMouseClicked(handler);
    searchTextField.setOnMouseClicked(handler);
  }


  @FXML
  public void initialize() {
    customerId.setCellValueFactory(
      new PropertyValueFactory<CustomerEntity, String>("customerId"));
    customerName.setCellValueFactory(
      new PropertyValueFactory<CustomerEntity, String>("customerName"));
    customerContact.setCellValueFactory(
      new PropertyValueFactory<CustomerEntity, String>("customerContact"));
    customerAddress.setCellValueFactory(
      new PropertyValueFactory<CustomerEntity, String>("customerAddress"));
    customerEmail.setCellValueFactory(
      new PropertyValueFactory<CustomerEntity, String>("customerEmail"));

    CacheService.clear();
    connectAndReadFromDatabase();
    clickHandler();

    customersTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        if (event.getClickCount() == 1) {
          CustomerEntity selectedCustomer = customersTable
            .getSelectionModel().getSelectedItem();

          if (selectedCustomer != null) {
            CacheService.put("customerId", selectedCustomer.getCustomerId());
            CacheService.put("customerName", selectedCustomer.getCustomerName());
            CacheService.put("customerContact", selectedCustomer.getCustomerContact());
            CacheService.put("customerAddress", selectedCustomer.getCustomerAddress());
            CacheService.put("customerEmail", selectedCustomer.getCustomerEmail());

            addCustomer.setVisible(false);
            editCustomer.setVisible(true);
            deleteCustomer.setVisible(true);
          }
        }
      }
    });

    customersTable.setItems(data);
  }
}
