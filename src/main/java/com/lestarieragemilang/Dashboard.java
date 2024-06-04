package com.lestarieragemilang;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import com.lestarieragemilang.Utilities.Redirect;

import javax.swing.JOptionPane;

import com.lestarieragemilang.Utilities.CacheService;

public class Dashboard {

    @FXML
    private BorderPane bp;

    @FXML
    private AnchorPane setScene;

    @FXML
    private Button stockNavBtn;

    @FXML
    private Button supplierNavBtn;

    @FXML
    private Button categoryNavBtn;

    @FXML
    private Button customerNavBtn;

    @FXML
    private Button purchasingNavBtn;

    @FXML
    private Button penjualanNavBtn;

    @FXML
    private Button penerimaanNavBtn;

    @FXML
    private Button reportNavBtn;


    @FXML
    void setSceneKategoriBesi(MouseEvent event) {
        Redirect.page("category", setScene, getClass());
        CacheService.clear();
    }

    @FXML
    void setSceneLaporan(MouseEvent event) {
        Redirect.page("laporan", setScene, getClass());
        CacheService.clear();
    }

    @FXML
    void setScenePelanggan(MouseEvent event) {
        Redirect.page("customer", setScene, getClass());
        CacheService.clear();
    }

    @FXML
    void setScenePembelianBesi(MouseEvent event) {
        Redirect.page("purchasing", setScene, getClass());
        CacheService.clear();
    }

    @FXML
    void setScenePenerimaanBesi(MouseEvent event) {
        Redirect.page("penerimaan", setScene, getClass());
        CacheService.clear();
    }

    @FXML
    void setScenePenjualanBesi(MouseEvent event) {
        Redirect.page("penjualan", setScene, getClass());
        CacheService.clear();
    }

    @FXML
    void setSceneStokBesi(MouseEvent event) {
        Redirect.page("stock", setScene, getClass());
        CacheService.clear();
    }

    @FXML
    void setSceneSupplierBesi(MouseEvent event) {
        Redirect.page("supplier", setScene, getClass());
        CacheService.clear();
    }

    @FXML
    public void initialize() {
        // // Stock Button
        // stockNavBtn.setOnMouseEntered(event -> {
        //     stockNavBtn.setStyle(
        //         "-fx-font-size: 20px; -fx-background-color: #5C5470; -fx-cursor: hand; -fx-font-weight: bold;"
        //     );
        // });

        // stockNavBtn.setOnMouseExited(event -> {
        //     stockNavBtn.setStyle(
        //         "-fx-font-size: 18px; -fx-background-color: #5C5470; -fx-cursor: hand; -fx-font-weight: normal;"
        //     );
        // });


        // // Supplier Button
        // supplierNavBtn.setOnMouseEntered(event -> {
        //     supplierNavBtn.setStyle(
        //         "-fx-font-size: 20px; -fx-background-color: #5C5470; -fx-cursor: hand; -fx-font-weight: bold;"
        //     );
        // });

        // supplierNavBtn.setOnMouseExited(event -> {
        //     supplierNavBtn.setStyle(
        //         "-fx-font-size: 18px; -fx-background-color: #5C5470; -fx-cursor: hand; -fx-font-weight: normal;"
        //     );
        // });


        // // Category Button
        // categoryNavBtn.setOnMouseEntered(event -> {
        //     categoryNavBtn.setStyle(
        //         "-fx-font-size: 20px; -fx-background-color: #5C5470; -fx-cursor: hand; -fx-font-weight: bold;"
        //     );
        // });

        // categoryNavBtn.setOnMouseExited(event -> {
        //     categoryNavBtn.setStyle(
        //         "-fx-font-size: 18px; -fx-background-color: #5C5470; -fx-cursor: hand; -fx-font-weight: normal;"
        //     );
        // });


        // // Customer Button
        // customerNavBtn.setOnMouseEntered(event -> {
        //     customerNavBtn.setStyle(
        //         "-fx-font-size: 20px; -fx-background-color: #5C5470; -fx-cursor: hand; -fx-font-weight: bold;"
        //     );
        // });

        // customerNavBtn.setOnMouseExited(event -> {
        //     customerNavBtn.setStyle(
        //         "-fx-font-size: 18px; -fx-background-color: #5C5470; -fx-cursor: hand; -fx-font-weight: normal;"
        //     );
        // });


        // // Purchasing Button
        // purchasingNavBtn.setOnMouseEntered(event -> {
        //     purchasingNavBtn.setStyle(
        //         "-fx-font-size: 20px; -fx-background-color: #5C5470; -fx-cursor: hand; -fx-font-weight: bold;"
        //     );
        // });

        // purchasingNavBtn.setOnMouseExited(event -> {
        //     purchasingNavBtn.setStyle(
        //         "-fx-font-size: 18px; -fx-background-color: #5C5470; -fx-cursor: hand; -fx-font-weight: normal;"
        //     );
        // });


        // // Penjualan Button
        // penjualanNavBtn.setOnMouseEntered(event -> {
        //     penjualanNavBtn.setStyle(
        //         "-fx-font-size: 20px; -fx-background-color: #5C5470; -fx-cursor: hand; -fx-font-weight: bold;"
        //     );
        // });

        // penjualanNavBtn.setOnMouseExited(event -> {
        //     penjualanNavBtn.setStyle(
        //         "-fx-font-size: 18px; -fx-background-color: #5C5470; -fx-cursor: hand; -fx-font-weight: normal;"
        //     );
        // });


        // // Penerimaan Button
        // penerimaanNavBtn.setOnMouseEntered(event -> {
        //     penerimaanNavBtn.setStyle(
        //         "-fx-font-size: 20px; -fx-background-color: #5C5470; -fx-cursor: hand; -fx-font-weight: bold;"
        //     );
        // });

        // penerimaanNavBtn.setOnMouseExited(event -> {
        //     penerimaanNavBtn.setStyle(
        //         "-fx-font-size: 18px; -fx-background-color: #5C5470; -fx-cursor: hand; -fx-font-weight: normal;"
        //     );
        // });


        // // Report Button
        // reportNavBtn.setOnMouseEntered(event -> {
        //     reportNavBtn.setStyle(
        //         "-fx-font-size: 20px; -fx-background-color: #5C5470; -fx-cursor: hand; -fx-font-weight: bold;"
        //     );
        // });

        // reportNavBtn.setOnMouseExited(event -> {
        //     reportNavBtn.setStyle(
        //         "-fx-font-size: 18px; -fx-background-color: #5C5470; -fx-cursor: hand; -fx-font-weight: normal;"
        //     );
        // });
        

        Redirect.page("stock", setScene, getClass());
        CacheService.clear();
    }

    @FXML
    private void exitApp() {
        int dialogResult = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin ingin keluar?", "Peringatan", JOptionPane.YES_NO_OPTION);
        if (dialogResult == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
    
}
