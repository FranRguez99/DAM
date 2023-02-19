package Soluciones.act4_objetosTCP_hilos;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) throws IOException {
        ServerSocket servidor = new ServerSocket(33333);
        int numCliente = 0;
        System.out.println("Servidor iniciado...");

        while (true) {
            numCliente ++;
            Socket cliente = new Socket();
            cliente = servidor.accept();
            HiloServidor2 hilo = new HiloServidor2(cliente, numCliente);
            hilo.start();
        }
    }
}
