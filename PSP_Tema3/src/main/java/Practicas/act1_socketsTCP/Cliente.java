package Practicas.act1_socketsTCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main (String[] args) throws IOException {
        DataOutputStream dos = null;
        DataInputStream dis = null;
        Socket cliente = new Socket("localhost", 6000);

        Scanner sc = new Scanner(System.in);
        String cadena;

        while (true){
            System.out.println("Introduce la cadena:");
            cadena = sc.nextLine();

            // SALIDA
            dos = new DataOutputStream(cliente.getOutputStream());
            dos.writeUTF(cadena);

            if (cadena.equals("*")){
                break;
            }

            System.out.println("Cadena: " + cadena + " enviada");

            // ENTRADA
            dis = new DataInputStream(cliente.getInputStream());
            System.out.println("Recibido: " + dis.readInt());
        }

        dos.close();
        dis.close();
        cliente.close();

    }

}
