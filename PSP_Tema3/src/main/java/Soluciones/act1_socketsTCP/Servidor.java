package Soluciones.act1_socketsTCP;

/*Usando sockets TCP realiza un programa cliente que introduzca cadenas por
teclado hasta introducir un asterisco. Las cadenas se enviarán a un programa servidor.
El programa servidor aceptará la conexión de un único cliente y por cada cadena
recibida le devolverá al cliente el número de caracteres de la misma. El programa
servidor finalizará cuando reciba un asterisco como cadena*/

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) throws IOException {
        DataOutputStream flujoSalida = null;
        DataInputStream flujoEntrada = null;
        int port = 6000;
        ServerSocket servidor = new ServerSocket(port);
        Socket cliente = null;
        System.out.println("Esperando al cliente...");

        cliente = servidor.accept();

        while (true) {
            // FLUJO ENTRADA
            InputStream entrada = cliente.getInputStream();
            flujoEntrada = new DataInputStream(entrada);

            String cadenaRecibida = flujoEntrada.readUTF();

            if (cadenaRecibida.equals("*")) {
                break;
            }

            System.out.println("Cadena recibida: " + cadenaRecibida);
            int numCaracteres = 0;
            for (int i = 0; i < cadenaRecibida.length(); i++) {
                if (cadenaRecibida.charAt(i)!=32) {
                    numCaracteres++;
                }
            }

            // FLUJO SALIDA
            OutputStream salida = cliente.getOutputStream();
            flujoSalida = new DataOutputStream(salida);
            flujoSalida.writeInt(numCaracteres);
            System.out.println("Enviado: " + numCaracteres);
        }

        flujoEntrada.close();
        flujoSalida.close();
        cliente.close();
        servidor.close();

    }

}
