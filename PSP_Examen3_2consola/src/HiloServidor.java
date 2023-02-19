import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class HiloServidor extends Thread{

    DataInputStream entrada;
    DataOutputStream salida;
    Socket socket;
    Scanner sc;

    public HiloServidor(Socket socket) {
        this.socket = socket;
        this.sc = new Scanner(System.in);
    }

    public void run(){
        String recibido = "";
        Integer random = new Random().nextInt(25);
        System.out.println("El número generado es " + random);

        while(!recibido.equals("*")){
            recibido = "";
            try{
                entrada = new DataInputStream(socket.getInputStream());
                recibido = entrada.readUTF();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (!recibido.equals("*")){
                Integer aux = Integer.parseInt(recibido);
                if (aux.equals(random)){
                    try {
                        salida = new DataOutputStream(socket.getOutputStream());
                        salida.writeUTF("Correcto");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                } else {
                    try {
                        salida = new DataOutputStream(socket.getOutputStream());
                        salida.writeUTF("Incorrecto");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            } else {
                System.exit(1);
            }
        }

        try {
            salida.close();
            entrada.close();
            socket.close();
            sc.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
