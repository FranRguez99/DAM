package Ejemplos_T3;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Act14_Cliente {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket cliente = new Socket("localhost", 12345);

        Scanner sc = new Scanner(System.in);
        System.out.print("Introduzca un número: ");
        int numero = Integer.parseInt(sc.next());
        Act14_Numeros resultado = new Act14_Numeros();
        resultado.setNumero(numero);

        // Salida
        ObjectOutputStream oos = new ObjectOutputStream(cliente.getOutputStream());
        // Entrada
        ObjectInputStream ois = new ObjectInputStream(cliente.getInputStream());


        // Envío de objeto
        oos.writeObject(resultado);
        System.out.println("Enviado: "+ resultado.getNumero());

        // Recibimos el objeto
        resultado = (Act14_Numeros) ois.readObject();
        System.out.println("Recibido: " + resultado);

        // Cerramos la conexión
        ois.close();
        oos.close();
        cliente.close();
    }

    public static Act14_Numeros creaNumero(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduzca un número: ");
        int numero = Integer.parseInt(sc.next());
        Act14_Numeros resultado = new Act14_Numeros();
        resultado.setNumero(numero);
        return resultado;
    }

}
