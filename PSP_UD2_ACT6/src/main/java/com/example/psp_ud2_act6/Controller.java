package com.example.psp_ud2_act6;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Button buttonStart;

    @FXML
    private ProgressBar pBar1;

    @FXML
    private ProgressBar pBar2;

    @FXML
    private ProgressBar pBar3;

    @FXML
    private Text priority1;

    @FXML
    private Text priority2;

    @FXML
    private Text priority3;

    @FXML
    private Slider slider1;

    @FXML
    private Slider slider2;

    @FXML
    private Slider slider3;

    @FXML
    private Text winner;


    @FXML
    void start(ActionEvent event) {
        // Reinicia las barras de progreso
        pBar1.setProgress(0);
        pBar2.setProgress(0);
        pBar3.setProgress(0);
        // Crea un hilo para cada barra
        Horse h1 = new Horse(pBar1);
        Horse h2 = new Horse(pBar2);
        Horse h3 = new Horse(pBar3);
        // Ejecuta los hilos
        h1.start();
        h2.start();
        h3.start();
    }

    private void setSlider(Slider slider2, Text priority2) {
        slider2.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                slider2.setValue(Math.floor(slider2.getValue()));
                priority2.setText("Prioridad: " + String.format("%.0f", slider2.getValue()));
            }
        });
    }

    /**
     * Incrementa la barra de proceso un 1% cada vez
     *
     * @param pBar barra a incrementar
     */
    synchronized void increment(ProgressBar pBar) {
        pBar.setProgress(pBar.getProgress() + 0.01);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setSlider(slider1, priority1);
        setSlider(slider2, priority2);
        setSlider(slider3, priority3);
    }


}
