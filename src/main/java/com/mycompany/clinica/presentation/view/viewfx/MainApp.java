package com.mycompany.clinica.presentation.view.viewfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(
            getClass().getResource(
                "/MainView.fxml"
            )
        );

        Parent root = loader.load();
        Scene scene = new Scene(root, 1350, 750);
        stage.setScene(scene);
        stage.setTitle("Sistema de Gestión - Clínica Praxell");
        stage.setMinHeight(700);
        stage.setMaximized(true); // 👈 No maximizar automáticamente
        stage.show();
    }

    public static void main(String[] args) {
        launch(args); // 👈 AQUÍ inicia JavaFX
    }
}