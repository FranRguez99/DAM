package Ejemplos_T3;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Act14_Servidor {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket servidor = new ServerSocket(12345);
        System.out.println("Esperando a cliente...");
        Socket cliente = servidor.accept();
        System.out.println("HA LLEGAO");

        // Entrada
        ObjectInputStream ois = new ObjectInputStream(cliente.getInputStream());
        // Salida
        ObjectOutputStream oos = new ObjectOutputStream(cliente.getOutputStream());

        // Recibimos el objeto
        Act14_Numeros numero = (Act14_Numeros) ois.readObject();
        numero.setCuadrado((long) numero.getNumero() *numero.getNumero());
        numero.setCubo((long) (numero.getNumero()*numero.getNumero()*numero.getNumero()));

        // Lo enviamos de vuelta
        oos.writeObject(numero);
        System.out.println("Enviado: "+ numero.getNumero());

        // Cerramos la conexión
        ois.close();
        oos.close();
        cliente.close();

    }

}
