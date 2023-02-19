package Act3;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("Cliente iniciado.");
        Socket cliente = new Socket("localhost", 6000);
        Scanner sc = new Scanner(System.in);

        DataOutputStream salida;
        ObjectInputStream entrada = null;

        String nombre;
        Mugiwara mugiwara;

        do{
            System.out.println("Nombre del mugiwara a consultar: ");
            nombre = sc.nextLine();

            salida  = new DataOutputStream(cliente.getOutputStream());
            salida.writeUTF(nombre);

            if(!nombre.equals("*")){
                entrada = new ObjectInputStream(cliente.getInputStream());
                mugiwara = (Mugiwara) entrada.readObject();
                try{
                    System.out.println("Nombre: " + mugiwara.getNombre());
                    System.out.println("Rol: " + mugiwara.getRol());
                    System.out.println("Fruta: " + mugiwara.getAkumaNoMi().getNombre());
                    System.out.println("\tDatos de la fruta:\n\t\tDescripción: " + mugiwara.getAkumaNoMi().getDescripcion() +
                            "\n\t\tApariencia: " + mugiwara.getAkumaNoMi().getApariencia());
                    System.out.println("Barco: " + mugiwara.getBarco().getBarco());
                    System.out.println("\tDatos del barco:\n\t\tTripulantes: " + mugiwara.getBarco().getTripulantes() +
                            "\n\t\tTipo: " + mugiwara.getBarco().getTipo());
                } catch (Exception e){
                    System.out.println("El mugiwara no se encuentra en nuestro registro");
                }

            }
        } while (!nombre.equals("*"));

        salida.close();
        entrada.close();
        sc.close();
        cliente.close();

    }

}
