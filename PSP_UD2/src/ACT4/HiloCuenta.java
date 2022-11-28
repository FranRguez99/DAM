package ACT4;

import java.io.*;

public class HiloCuenta extends Thread{

    long t_comienzo, t_fin;
    int count;
    String fichero;

    public HiloCuenta(String fichero) {
        this.fichero = fichero;
    }

    @Override
    public void run() {
        t_comienzo = System.currentTimeMillis();
        File file = new File(fichero);
        FileInputStream fis;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        count = 0;
        byte[] bytesArray = new byte[(int)file.length()];
        try {
            fis.read(bytesArray);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String s = new String(bytesArray);
        String [] data = s.split(" ");
        for (int i=0; i<data.length; i++) {
            count++;
        }


        t_fin = System.currentTimeMillis();
        long total = t_fin - t_comienzo;
        System.out.println("El proceso ha tardado " + total + " milisegundos. El número de palabras es " + count);
    }
}
