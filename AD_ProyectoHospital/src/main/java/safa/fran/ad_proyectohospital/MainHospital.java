package safa.fran.ad_proyectohospital;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainHospital extends Application {
    @Override
    public void start(Stage stage) throws IOException {


        FXMLLoader fxmlLoader = new FXMLLoader(MainHospital.class.getResource("view_hospital.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 400);
        // todo añadir icon a la aplicación

        stage.setTitle("Hospital Universitario Virgen del Rocío");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}