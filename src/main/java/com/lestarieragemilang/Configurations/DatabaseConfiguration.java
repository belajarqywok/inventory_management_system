package com.lestarieragemilang.Configurations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class DatabaseConfiguration {

    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/iron_sales_db";
    private static final String USER = "root";
    private static final String PASS = "";

    private Connection connection;

    public DatabaseConfiguration() {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            showErrorMessage(e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            showErrorMessage(e.getMessage());
        }
    }

    private void showErrorMessage(String errorMessage) {
        Platform.runLater(() -> {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Failed to connect to the database");
            alert.setContentText(errorMessage);
            alert.showAndWait();
        });
    }
}