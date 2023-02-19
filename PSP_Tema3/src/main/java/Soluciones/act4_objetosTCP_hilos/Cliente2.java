package Soluciones.act4_objetosTCP_hilos;


import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Cliente2 {

    // Envía una cadena al servidor con el botón Enviar
    // El botón Limpiar limpia los dos campos
    // El botón salir envía un * al servidor y finaliza la ejecución

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        String host = "localhost";
        int puerto = 33333;
        Socket cliente = new Socket(host, puerto);

        Scanner in = new Scanner(System.in);
        DataOutputStream salida;
        ObjectInputStream entrada = null;

        String strIdProfEnviado;
        Profesor profesorRecibido = new Profesor();

        do {
            System.out.print("Introduce el id del profesor: ");
            strIdProfEnviado = in.nextLine();
            salida = new DataOutputStream(cliente.getOutputStream());
            salida.writeUTF(strIdProfEnviado);
            if (!strIdProfEnviado.equals("*")) {
                entrada = new ObjectInputStream(cliente.getInputStream());
                profesorRecibido = (Profesor) entrada.readObject();
                System.out.println("Nombre: " + profesorRecibido.getNombre() + ", Especialidad: " + profesorRecibido.getEsp().getId() + " - " + profesorRecibido.getEsp().getNombreEspe());
                for (Asignatura element : profesorRecibido.getAsignaturas()) {
                    System.out.println("\tAsignatura: " + element.getId() + " - " + element.getNombreAsig());
                }
            }
        } while (!strIdProfEnviado.equals("*"));

        salida.close();
        entrada.close();
        in.close();
        cliente.close();
    }

}
