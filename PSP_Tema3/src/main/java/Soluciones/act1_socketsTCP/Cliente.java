package Soluciones.act1_socketsTCP;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) throws IOException {
        DataOutputStream flujoSalida;
        DataInputStream flujoEntrada = null;
        String host = "localhost";
        int port = 6000;
        String cadena;
        Socket cliente = new Socket(host, port);
        Scanner kb = new Scanner(System.in);

        while (true) {
            System.out.print("Introduce la cadena: ");
            cadena = kb.nextLine();

            // FLUJO SALIDA
            OutputStream salida = cliente.getOutputStream();
            flujoSalida = new DataOutputStream(salida);
            flujoSalida.writeUTF(cadena);

            if (cadena.equals("*")) {
                break;
            }

            System.out.println("Cadena: '" + cadena + "' enviada");

            //FLUJO ENTRADA
            InputStream entrada = cliente.getInputStream();
            flujoEntrada = new DataInputStream(entrada);
            System.out.println("Recibido: " + flujoEntrada.readInt());
        }

        flujoSalida.close();
        flujoEntrada.close();
        cliente.close();
    }

}
