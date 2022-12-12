import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Productor extends Thread{
    // Atributos
    String textoCifrado;
    ArrayList<String> caracteres = new ArrayList<>();
    Cola c;
    String nombre;
    Integer longitud;

    public Productor(Cola c, String nombre) {
        this.c = c;
        this.nombre = nombre;
    }

    public void leeFichero(String fichero) throws IOException {
        String aux;

        File archivo = new File(fichero);
        FileReader lector = new FileReader(archivo);
        BufferedReader lectorBuffer = new BufferedReader(lector);

        while ((aux = lectorBuffer.readLine()) != null){
            textoCifrado = aux;
        }

    }

    public void separaLetras(){
        String[] letras = textoCifrado.split(" ");
        caracteres.addAll(Arrays.asList(letras));
        longitud = caracteres.size();
    }

    @Override
    public void run(){
        try {
            leeFichero("src/cifrado.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        separaLetras();
        for(String letra : caracteres){
            try {
                c.put(letra);
                //System.out.println("El productor escribe la letra: " + letra);
                sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
