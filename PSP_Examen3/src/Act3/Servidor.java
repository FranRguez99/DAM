package Act3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) throws IOException {
        ServerSocket servidor = new ServerSocket(6000);
        System.out.println("Servidor iniciado.");
        int numCliente = 1;
        while(true){
            Socket cliente = new Socket();
            cliente = servidor.accept();

            HiloServidor hilo = new HiloServidor(cliente, numCliente);
            hilo.start();
            numCliente++;
        }

    }

}
