package com.mycompany.clinica.presentation.view.viewfx;

import com.mycompany.clinica.infrastructure.di.DependencyInjector;
import java.lang.reflect.InvocationTargetException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        DependencyInjector injector = new DependencyInjector();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainView.fxml"));
        loader.setControllerFactory(param -> {
            if (param == MainViewFx.class) {
                return new MainViewFx(injector);
            }
            try {
                return param.getDeclaredConstructor().newInstance();
            } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        });
        Parent root = loader.load();
        Scene scene = new Scene(root, 1350, 750);
        stage.setScene(scene);
        stage.setTitle("Sistema de Gestión - Clínica Praxell");
        stage.setMinHeight(700);
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
