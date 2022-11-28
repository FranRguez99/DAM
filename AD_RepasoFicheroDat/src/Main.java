import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        escribeDatos();
        leeDatos();
        System.out.println("1) Leer diccionario\t2) Buscar palabra:"); // Opciones
        Scanner sc = new Scanner(System.in);
        int opcion = Integer.parseInt(sc.next()); // Introducción de la opción
        if (opcion == 1){
            for (Diccionario d : dicc){
                System.out.println(d);
            }
        } else if (opcion == 2) {
            System.out.println("Palabra a buscar:");
            String palabra = sc.next();
            for (Diccionario d : dicc){
                if (d.getPalabra().equals(palabra)){
                    System.out.println(d.getDefinicion());
                }
            }
        }

    }

    static List<Diccionario> dicc; // Variable que almacena el diccionario localmente

    /**
     * Escribe los datos del diccionario dentro de un fichero .dat
     */
    public static void escribeDatos() {
        List<Diccionario> aux = new ArrayList<>();
        aux.add(new Diccionario("Mesa", "Tabla sobre 4 patas"));
        aux.add(new Diccionario("Coche", "Vehículo de 4 ruedas"));
        try{
            FileOutputStream writeData = new FileOutputStream("src/Ej3.dat"); // Conexión con el fichero
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

            writeStream.writeObject(aux);
            writeStream.flush();
            writeStream.close();

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lee el diccionario a partir de un fichero binario
     */
    public static void leeDatos(){
        try{
            FileInputStream readData = new FileInputStream("src/Ej3.dat");
            ObjectInputStream readStream = new ObjectInputStream(readData);

            dicc = (List<Diccionario>) readStream.readObject();

        }catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}