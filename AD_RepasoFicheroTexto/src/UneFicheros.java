import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Francisco Rodríguez Espinosa
 */

public class UneFicheros {

    /**
     * Lee dos ficheros de texto y une su contenido en un nuevo fichero de texto
     * @param ruta1 ruta del primer fichero
     * @param ruta2 ruta del segundo fichero
     * @param destino ruta del fichero de destino
     */
    public static void leeFichero(String ruta1, String ruta2, String destino) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(ruta1)); // Abre el fichero 1 para lectura
        List<String> lista1 = new ArrayList<>();
        String aux;
        // Bucle que guarda todas las líneas del fichero en una lista
        while ((aux = br.readLine()) != null){
            lista1.add(aux);
        }
        br.close();

        br = new BufferedReader(new FileReader(ruta2)); // Abre el fichero 2 para lectura
        List<String> lista2 = new ArrayList<>();
        // Mismo bucle que el anterior
        while ((aux = br.readLine()) != null){
            lista2.add(aux);
        }
        br.close();
        // Crea un nuevo fichero en la ruta especificada para guardar el contenido de los anteriores
        BufferedWriter bw = new BufferedWriter(new FileWriter(destino+"/"+ruta1.replace("src/",
                "").replace(".txt","")+"_"+ruta2.replace("src/",""),
                false));
        // Bucles que escriben las listas anteriormente guardadas en el nuevo fichero
        lista1.forEach(name -> {
            try {
                bw.write(name+"\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        lista2.forEach(name -> {
            try {
                bw.write(name+"\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        try {
            bw.close(); // Cerramos la conexión con el fichero
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
