package Ejemplos_T3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Act7_Socket {

    public static void main(String[] args) throws IOException {
        ServerSocket servidor = new ServerSocket(6000);

        System.out.println("Escuchando en: " +servidor.getLocalPort());

        Socket cliente1 = servidor.accept();
        System.out.println("Llega cliente 1");

        Socket cliente2 = servidor.accept();
        System.out.println("Llega cliente 2");

        servidor.close();
    }
}
