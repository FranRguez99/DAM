package Ejemplos_T3;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Act15_Cliente {

    public static void main(String[] args) throws IOException {
        Socket cliente = new Socket("localhost", 11111);

        // Salida
        ObjectOutputStream oos = new ObjectOutputStream(cliente.getOutputStream());
        // Entrada
        ObjectInputStream ois = new ObjectInputStream(cliente.getInputStream());
    }

}

