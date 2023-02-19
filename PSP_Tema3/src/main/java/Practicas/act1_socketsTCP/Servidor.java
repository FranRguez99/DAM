package Practicas.act1_socketsTCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main (String[] args) throws IOException {
        DataOutputStream dos = null;
        DataInputStream dis = null;

        ServerSocket servidor = new ServerSocket(6000);
        Socket cliente = null;
        String cadena;
        System.out.println("Esperando cliente...");

        cliente = servidor.accept();

        while (true){
            // ENTRADA
            dis = new DataInputStream(cliente.getInputStream());

            cadena = dis.readUTF();
            if (cadena.equals("*")){
                break;
            }

            System.out.println("Cadena: " + cadena + " recibida");
            int numCaracteres = 0;
            for (int i = 0; i < cadena.length(); i++) {
                if (cadena.charAt(i)!=32) {
                    numCaracteres++;
                }
            }

            // SALIDA
            dos = new DataOutputStream(cliente.getOutputStream());
            dos.writeInt(numCaracteres);
            System.out.println("Enviado: " + numCaracteres);
        }

        dos.close();
        dis.close();
        cliente.close();
        servidor.close();
    }

}
