package com.lestarieragemilang;

import java.io.IOException;

import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;

import javafx.scene.layout.AnchorPane;

import com.lestarieragemilang.Utilities.Redirect;
import com.lestarieragemilang.Repositories.AuthRepositories;

public class Login extends AuthRepositories {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    TextField username;

    @FXML
    PasswordField password;

    @FXML
    Button loginButton;

    @FXML
    private void btnMasuk() throws IOException {
      boolean validation = loginRepo(
        username.getText(), password.getText());

      if (validation) {
        Redirect.page("dashboard", anchorPane, getClass());
      } else {
        Alert confirmationDialog = new Alert(Alert.AlertType.ERROR);
        confirmationDialog.getDialogPane().setPrefSize(450, 250);

        confirmationDialog.setTitle("Login Gagal");
        confirmationDialog.setHeaderText("Username Atau Password Anda Salah !!!...");
    
        confirmationDialog.getButtonTypes().setAll(ButtonType.YES);
        confirmationDialog.showAndWait();
      }

    }

    @FXML
    private void btnKeluar() {
        System.exit(0);
    }
}