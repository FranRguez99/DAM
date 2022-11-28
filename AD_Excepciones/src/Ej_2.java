import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Creación de un programa que PROPAGUE y capture una
 * excepción del tipo FileNotFound.
 *
 * @author Francisco Rodríguez Espinosa
 */

public class Ej_2 {

    /**
     * Intenta leer un fichero dado como parámetro
     * @param f fichero
     */
    public static void leeFich(File f) throws FileNotFoundException {
        try{
            FileReader fr = new FileReader(f);
        } catch (FileNotFoundException fnfe){
            System.out.println("No se ha encontrado el fichero 1");
        } finally {
            System.out.println("\n Fin del método leeFich");
        }
    }

    /**
     * Ejecuta la aplicación
     */
    public static void ejecutar(){
        File f = new File("fichero.txt");
        try{
            leeFich(f);
        } catch (FileNotFoundException fnfe){
            System.out.println("No se ha encontrado el fichero 2");
        } finally {
            System.out.println("\n Fin del ejercicio 2");
        }
    }

}
