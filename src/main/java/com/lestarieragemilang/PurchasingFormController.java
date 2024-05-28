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

import com.lestarieragemilang.Entities.PurchasingEntity;
import com.lestarieragemilang.Repositories.PurchasingRepositories;



/**
 *  Purchasing Form Controller
 */
public class PurchasingFormController extends PurchasingRepositories {
  
  @FXML
  private AnchorPane anchorPane;

}
