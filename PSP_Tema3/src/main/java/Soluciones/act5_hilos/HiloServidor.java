package Soluciones.act5_hilos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HiloServidor extends Thread {

    // Cuando se conecte un cliente se muestra la IP y el puerto
    // Cuando se desconecte que se notifique
    // Recibe una cadena y si no es un * se la devuelve en mayúsculas

    BufferedReader entrada;
    PrintWriter salida;
    Socket socket = null;

    public HiloServidor (Socket socket) throws IOException {
        this.socket = socket;
        salida = new PrintWriter(socket.getOutputStream(), true);
        entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void run() {
        String cadena = "";
        System.out.println("=>Conecta IP /" + socket.getInetAddress() + ", Puerto remoto: " + socket.getPort());

        while (!cadena.trim().equals("*")) {
            try {
                cadena = entrada.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(!cadena.trim().equals("*")) {
                salida.println(cadena.trim().toUpperCase());
            }
        }

        System.out.println("\t=>Desconecta IP /" + socket.getInetAddress() + ", Puerto remoto: " + socket.getPort());

        salida.close();
        try {
            entrada.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
