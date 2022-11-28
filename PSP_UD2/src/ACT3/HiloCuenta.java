package ACT3;

import java.io.*;

public class HiloCuenta extends Thread{

    long t_comienzo, t_fin;
    int caracteres;
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
        InputStreamReader ir = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(ir);
        caracteres = 0;
        String data;
        while(true){
            try {
                if (!((data = br.readLine()) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            caracteres += data.length();
        }
        t_fin = System.currentTimeMillis();
        long total = t_fin - t_comienzo;
        System.out.println("El proceso ha tardado " + total + " milisegundos. El número de caracteres es " + caracteres);
    }
}
