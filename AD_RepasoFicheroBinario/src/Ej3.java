import java.io.*;
import java.util.Scanner;

/**
 * @author Francisco Rodríguez Espinosa
 */

public class Ej3 {

    /**
     * Se introduce una ruta de origen y otra de destino, copia el fichero del origen a la ruta de destino
     */
    public static void ejecutar(){
        Scanner sc = new Scanner(System.in);

        //Pedimos las rutas
        System.out.print("Ruta de orígen: ");
        String origen = sc.next();
        System.out.print("Ruta de destino: ");
        String destino = sc.next();
        try(FileInputStream fis=new FileInputStream(origen);
            FileOutputStream fos=new FileOutputStream(destino)){

            //Creamos un array de bytes con el tamaño del fichero de origen
            byte byteA[]=new byte[fis.available()];

            //Copia todos los bytes del fichero al array
            fis.read(byteA);

            //Escribe todos los bytes en el fichero de destino
            fos.write(byteA);

        }catch(IOException e){
            System.out.println(e.getClass());
        }
    }

}
