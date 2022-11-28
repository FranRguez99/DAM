import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Francisco Rodríguez Espinosa
 */

public class RutaFichero {

    /**
     * Pide por teclado la ruta de un fichero, a continuación pide un mensaje, y escribe ese mensaje en la ruta
     * especificada cambiando las mayúsculas por minúsculas y viceversa
     */
    public static void escribeFichero() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca la ruta del archivo:");
        String ruta = sc.next();
        BufferedWriter bw = new BufferedWriter(new FileWriter(ruta, false)); // Abre el fichero para escritura
        System.out.println("Introduzca el mensaje:");
        String mensaje = sc.next();
        StringBuilder mensajeMod = new StringBuilder();
        char aux;
        // Bucle que cambia todas las letras entre mayúsculas y minúsculas
        for (int i = 0; i < mensaje.length(); i++){
            aux = mensaje.charAt(i);
            if(Character.isUpperCase(aux)){
                aux = Character.toLowerCase(aux);
            } else if (Character.isLowerCase(aux)) {
                aux = Character.toUpperCase(aux);
            }
            mensajeMod.append(aux);
        }
        bw.write(String.valueOf(mensajeMod)); // Escribe el mensaje en el fichero
        bw.close();
    }

}
