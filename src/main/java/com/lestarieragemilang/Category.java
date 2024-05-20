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

import com.lestarieragemilang.Entities.CategoryEntity;
import com.lestarieragemilang.Repositories.CategoryRepositories;


/**
 *  Category Handler
 */
public class Category extends CategoryRepositories {

  @FXML
  private TableView<CategoryEntity> categoriesTable;

  @FXML
  private TableColumn<CategoryEntity, String> categoryId;

  @FXML
  private TableColumn<CategoryEntity, String> categoryBrand;

  @FXML
  private TableColumn<CategoryEntity, String> categoryType;

  @FXML
  private TableColumn<CategoryEntity, String> categoryDescription;


  @FXML
  private AnchorPane anchorPane;

  @FXML
  private Label featureLabel;

  @FXML
  private TextField searchTextField;

  @FXML
  private Button editCategory;

  @FXML
  private Button addCategory;

  @FXML
  private Button deleteCategory;


  @FXML
  private BorderPane bp;

  private ObservableList<CategoryEntity> data;


  @FXML
  void searchCategoryAction(MouseEvent event) {
    addCategory.setVisible(true);
    editCategory.setVisible(false);
    deleteCategory.setVisible(false);

    CacheService.clear();

    data = FXCollections.observableArrayList();

    List<Object[]> searchCategories = this
      .searchCategoriesRepository(searchTextField.getText());

    for (Object[] rowData : searchCategories) {
      CategoryEntity entity = new CategoryEntity();

      entity.setCategoryId((String) rowData[0]);
      entity.setCategoryBrand((String) rowData[1]);
      entity.setCategoryType((String) rowData[2]);
      entity.setCategoryDescription((String) rowData[3]);

      data.add(entity);
      categoriesTable.setItems(data);
    }
  }


  @FXML
  void addCategoryAction (MouseEvent event) {
    Redirect.page("categoryForm", anchorPane, getClass());
  }


  @FXML
  void editCategoryAction (MouseEvent event) {
    Redirect.page("categoryForm", anchorPane, getClass());
  }


  @FXML
  void deleteCategoryAction (MouseEvent event) {
    Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
    confirmationDialog.getDialogPane().setPrefSize(450, 250);
    
    confirmationDialog.setTitle("Konfirmasi");
    confirmationDialog.setHeaderText("hapus kategori");
    confirmationDialog.setContentText("Apakah anda yakin ?.");

    confirmationDialog.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

    confirmationDialog.showAndWait().ifPresent(buttonType -> {
        if (buttonType == ButtonType.YES) {
            deleteCategoryRepository((String) CacheService.get("categoryId"));
            Redirect.page("category", anchorPane, getClass());
        }
    });
  }


  private void connectAndReadFromDatabase() {
    data = FXCollections.observableArrayList();

    List<Object[]> getCategories = this
      .getCategoriesRepository();

    for (Object[] rowData : getCategories) {
      CategoryEntity entity = new CategoryEntity();

      entity.setCategoryId((String) rowData[0]);
      entity.setCategoryBrand((String) rowData[1]);
      entity.setCategoryType((String) rowData[2]);
      entity.setCategoryDescription((String) rowData[3]);

      data.add(entity);
    }
  }


  private void clickHandler () {
    EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        addCategory.setVisible(true);
        editCategory.setVisible(false);
        deleteCategory.setVisible(false);

        CacheService.clear();
      }
    };

    anchorPane.setOnMouseClicked(handler);
    featureLabel.setOnMouseClicked(handler);
    searchTextField.setOnMouseClicked(handler);
  }


  @FXML
  public void initialize() {
    categoryId.setCellValueFactory(
      new PropertyValueFactory<CategoryEntity, String>("categoryId"));
    categoryBrand.setCellValueFactory(
      new PropertyValueFactory<CategoryEntity, String>("categoryBrand"));
    categoryType.setCellValueFactory(
      new PropertyValueFactory<CategoryEntity, String>("categoryType"));
    categoryDescription.setCellValueFactory(
      new PropertyValueFactory<CategoryEntity, String>("categoryDescription"));

    CacheService.clear();
    connectAndReadFromDatabase();
    clickHandler();

    categoriesTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        if (event.getClickCount() == 1) {
          CategoryEntity selectedCategory = categoriesTable
            .getSelectionModel().getSelectedItem();

          if (selectedCategory != null) {
            CacheService.put("categoryId", selectedCategory.getCategoryId());
            CacheService.put("categoryBrand", selectedCategory.getCategoryBrand());
            CacheService.put("categoryType", selectedCategory.getCategoryType());
            CacheService.put("categoryDescription", selectedCategory.getCategoryDescription());

            addCategory.setVisible(false);
            editCategory.setVisible(true);
            deleteCategory.setVisible(true);
          }
        }
      }
    });

    categoriesTable.setItems(data);
  }
}
