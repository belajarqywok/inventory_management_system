package com.lestarieragemilang;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static final String LOGIN_FXML = "login";

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML(LOGIN_FXML), 1200, 650);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();

        final double[] xOffset = new double[1];
        final double[] yOffset = new double[1];

        scene.setOnMousePressed(event -> {
            xOffset[0] = event.getSceneX();
            yOffset[0] = event.getSceneY();
        });

        scene.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xOffset[0]);
            stage.setY(event.getScreenY() - yOffset[0]);
        });

        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        if (fxmlExists(fxml)) {
            scene.setRoot(loadFXML(fxml));
        } else {
            throw new IOException("FXML file does not exist: " + fxml);
        }
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    private static boolean fxmlExists(String fxml) {
        URL resource = App.class.getResource(fxml + ".fxml");
        return resource != null;
    }

    public static void main(String[] args) {
        launch();
    }

}