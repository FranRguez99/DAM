package safa.fran.psp_ud2_act9;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private TextField tfHilo1;
    @FXML
    private TextField tfHilo2;
    @FXML
    private Button buttonComenzar;

    Hilo hilo1;
    Hilo hilo2;


    @FXML
    void comenzar(ActionEvent event) throws InterruptedException {
        hilo1 = new Hilo("Hilo 1", 1000, tfHilo1);
        hilo2 = new Hilo("Hilo 2", 2000, tfHilo2);
        hilo1.start();
        hilo2.start();
        buttonComenzar.setDisable(true);
    }

    @FXML
    void finalizar(ActionEvent event) {
        System.out.println("El hilo 1 tiene el valor: " + hilo1.getCuenta());
        System.out.println("El hilo 2 tiene el valor: " + hilo2.getCuenta());
        hilo1.interrupt();
        hilo2.interrupt();
    }

    @FXML
    void interrumpir1() {
        hilo1.setInterrumpir(true);
    }

    @FXML
    void interrumpir2(ActionEvent event) {
        hilo2.setInterrumpir(true);
    }

}
