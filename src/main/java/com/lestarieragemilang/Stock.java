package com.lestarieragemilang;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lestarieragemilang.Entities.Product;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Stock {

    @FXML
    private TableColumn<Product, String> hargaBeli;

    @FXML
    private TableColumn<Product, String> hargaJual;

    @FXML
    private TableColumn<Product, String> idKategori;

    @FXML
    private TableColumn<Product, String> jenis;

    @FXML
    private TableColumn<Product, String> merek;

    @FXML
    private TableColumn<Product, String> satuan;

    @FXML
    private TableColumn<Product, String> stok;

    @FXML
    private TableView<Product> tabelDatabaseStokBesi;

    @FXML
    private TableColumn<Product, String> ukuran;

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost/inventory";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";

    Connection conn = null;
    private ObservableList<Product> data;

    public void connectAndReadFromDatabase() {
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Execute a query
            String sql = "SELECT * FROM steel_stocks";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            // Extract data from result set and store it in an ObservableList
            data = FXCollections.observableArrayList();
            while(rs.next()){
                // Assuming you have a Product class with a constructor that matches the columns in your database
                data.add(new Product(rs.getString("harga_beli"), rs.getString("harga_jual"), rs.getString("id_kategori"), rs.getString("jenis"), rs.getString("merek"), rs.getString("satuan"), rs.getString("stok"), rs.getString("ukuran")));
            }

            // Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch(SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if(conn!=null)
                    conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }

    @FXML
    public void initialize() {
        hargaBeli.setCellValueFactory(new PropertyValueFactory<Product, String>("harga_beli"));
        hargaJual.setCellValueFactory(new PropertyValueFactory<Product, String>("harga_jual"));
        idKategori.setCellValueFactory(new PropertyValueFactory<Product, String>("id_kategori"));
        jenis.setCellValueFactory(new PropertyValueFactory<Product, String>("jenis"));
        merek.setCellValueFactory(new PropertyValueFactory<Product, String>("merek"));
        satuan.setCellValueFactory(new PropertyValueFactory<Product, String>("satuan"));
        stok.setCellValueFactory(new PropertyValueFactory<Product, String>("stok"));
        ukuran.setCellValueFactory(new PropertyValueFactory<Product, String>("ukuran"));

        // Call the method to connect to the database and read data
        connectAndReadFromDatabase();

        // Add the data to the table
        tabelDatabaseStokBesi.setItems(data);
    }
}

