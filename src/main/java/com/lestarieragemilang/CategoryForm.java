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

import com.lestarieragemilang.Entities.CategoryEntity;
import com.lestarieragemilang.Repositories.CategoryRepositories;



/**
 *  Category Form
 */
public class CategoryForm extends CategoryRepositories {
  
  @FXML
  private AnchorPane anchorPane;

  @FXML
  private Button categoryActionButton;



  @FXML
  private TextField categoryIdField;

  @FXML
  private TextField categoryBrandField;

  @FXML
  private TextField categoryTypeField;

  @FXML
  private TextField categoryDescriptionField;


  @FXML
  void clearButton (MouseEvent event) {
    categoryIdField.setText("");
    categoryBrandField.setText("");
    categoryTypeField.setText("");
    categoryDescriptionField.setText("");

    CacheService.clear();
  }


  @FXML
  void backButton (MouseEvent event) {
    Redirect.page("category", anchorPane, getClass());
    CacheService.clear();
  }


  @FXML
  void categoryActionButton(MouseEvent event) {
    CategoryEntity entity = new CategoryEntity();

    entity.setCategoryId(categoryIdField.getText());
    entity.setCategoryBrand(categoryBrandField.getText());
    entity.setCategoryType(categoryTypeField.getText());
    entity.setCategoryDescription(categoryDescriptionField.getText());

    // Update Category Data
    if (categoryActionButton.getText().equals("Update")) {
      Map<String, Object> response = this
        .updateCategoryRepository(entity);

      if ((boolean) response.get("result")) {
        Alert confirmationDialog = new Alert(Alert.AlertType.INFORMATION);
        confirmationDialog.getDialogPane().setPrefSize(450, 250);

        confirmationDialog.setTitle("Berhasil Memperbarui Data.");
        confirmationDialog.setHeaderText((String) response.get("message"));
    
        confirmationDialog.getButtonTypes()
          .setAll(ButtonType.YES);
    
        confirmationDialog.showAndWait().ifPresent(buttonType -> {
          if (buttonType == ButtonType.YES) {
            Redirect.page("category", anchorPane, getClass());
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
    } else if (categoryActionButton.getText().equals("Tambah")) {
      Map<String, Object> response = this
        .createCategoryRepository(entity);

      if ((boolean) response.get("result")) {
        Alert confirmationDialog = new Alert(Alert.AlertType.INFORMATION);
        confirmationDialog.getDialogPane().setPrefSize(450, 250);

        confirmationDialog.setTitle("Berhasil Menambahkan Data.");
        confirmationDialog.setHeaderText((String) response.get("message"));

        confirmationDialog.getButtonTypes()
          .setAll(ButtonType.YES);

        confirmationDialog.showAndWait().ifPresent(buttonType -> {
          if (buttonType == ButtonType.YES) {
            Redirect.page("category", anchorPane, getClass());
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
    String categoryId = (String) CacheService.get("categoryId");
    if (categoryId != null) {
      categoryIdField.setText(categoryId);
      categoryBrandField.setText((String) CacheService.get("categoryBrand"));
      categoryTypeField.setText((String) CacheService.get("categoryType"));
      categoryDescriptionField.setText((String) CacheService.get("categoryDescription"));
    }
  }


  @FXML
  public void initialize() {
    if (CacheService.get("categoryId") != null) {
      categoryActionButton.setText("Update");
      categoryIdField.setEditable(false);

    } else {
      categoryActionButton.setText("Tambah");
    }

    inputHandler();
  }
}
