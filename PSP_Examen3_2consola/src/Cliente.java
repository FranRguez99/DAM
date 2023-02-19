import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main (String[] args) throws IOException {
        System.out.println("Cliente iniciado");

        Socket cliente = new Socket("localhost", 6000);
        Scanner sc = new Scanner(System.in);
        String numero;
        String respuesta;

        DataOutputStream salida;
        DataInputStream entrada = null;

        do {
            System.out.println("Introduzca un número:");
            numero = sc.nextLine();


            salida  = new DataOutputStream(cliente.getOutputStream());
            salida.writeUTF(numero);

            if (!numero.equals("*")){
                entrada = new DataInputStream(cliente.getInputStream());
                respuesta = entrada.readUTF();
                System.out.println("El número es: " + respuesta);
            }

        } while (!numero.equals("*"));

        salida.close();
        entrada.close();
        sc.close();
        cliente.close();
    }

}
