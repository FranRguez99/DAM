package Ejemplos_T3;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Act7_Cliente {

    public static void main(String[] args) throws IOException {
        Socket cliente = new Socket("localhost", 6000);

        InetAddress i = cliente.getInetAddress();
        System.out.println("Puerto: " + cliente.getLocalPort());
        System.out.println("Puerto remoto: " + cliente.getPort());

        cliente.close();
    }

}
