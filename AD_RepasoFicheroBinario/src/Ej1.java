import javax.swing.*;
import java.io.*;
import java.util.Scanner;

/**
 * @author Francisco Rodríguez Espinosa
 */

public class Ej1 {

    /**
     * Pide por teclado la ruta del fichero, además de una cantidad de números aleatorios. Luego los escribe en un
     * fichero binario sin borrar los ya previamente escritos
     */
    public static void ejecutar(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe la ruta del fichero");
        String ruta= sc.next();
        System.out.println("Escribe el numero de numeros aleatorios");
        int num=Integer.parseInt(sc.next());
        // Conexión con el archivo binario
        try(DataOutputStream dos=new DataOutputStream (new FileOutputStream(ruta, true));
            DataInputStream dis=new DataInputStream(new FileInputStream(ruta))){

            Ej1.escribeFichero(dos, num);
            Ej1.leeFichero(dis);

        }catch(EOFException e){
            System.out.println("Fin del fichero");
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error: "+e.getMessage() , "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Escribe en el fichero binario los números generados
     * @param dos conexión con el fichero binario
     * @param num cantidad de números a escribir
     */
    public static void escribeFichero (DataOutputStream dos, int num) throws IOException {

        for (int i=0;i<num;i++){
            int numero= numAleatorio();
            dos.writeInt(numero);
        }
        dos.flush();

    }

    /**
     * Lee el fichero binario para extraer los números escritos en él
     * @param dis conexión con el fichero binario
     *
     */
    public static void leeFichero (DataInputStream dis) throws IOException{
        while(true){
            System.out.println(dis.readInt());
        }
    }

    /**
     * Generador de números aleatorios
     * @return número generado
     */
    public static int numAleatorio(){
        return (int)Math.floor(Math.random()*101);
    }

}
