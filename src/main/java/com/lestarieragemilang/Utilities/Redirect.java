package com.lestarieragemilang.Utilities;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

public class Redirect {

  public static void page(String page, AnchorPane anchorPane, Class<?> callerClass) {
    Parent root = null;
    try {
      FXMLLoader loader = new FXMLLoader(
        callerClass.getResource(page + ".fxml")
      );

      root = loader.load();

    } catch (IOException ex) { ex.printStackTrace(); }
      if (root != null && anchorPane != null) {
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(root);
      } else {
        System.err.println("Root or anchorPane is null.");
      }
  }
  
}

