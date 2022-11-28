import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SinEspacios {
    /**
     * Lee el contenido de un fichero de texto y elimina los espacios del mismo
     */
    public static void leeFichero() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/Ej1.txt")); // Abrimos el fichero
        String aux;
        // Bucle para eliminar los espacios
        while ((aux = br.readLine()) != null){
            System.out.print(aux.replace(" ", ""));
        }
        br.close();
    }

}
