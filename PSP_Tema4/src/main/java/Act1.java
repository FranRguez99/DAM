import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.IOException;

public class Act1 {

    public static void main(String[] args){
        // Nos conectamos al servidor
        FTPClient cliente = new FTPClient();
        String servFTP = "127.0.0.1";

        System.out.println("Conectado a: " + servFTP);
        String usuario = "admin";
        String clave = "admin";

        try{
            cliente.connect(servFTP);
            cliente.enterLocalPassiveMode(); // Modo pasivo

            if (cliente.login(usuario, clave)){
                System.out.println("Conexión establecida");
            } else {
                System.out.println("Error de conexión");
                cliente.disconnect();
                System.exit(1);
            }
            System.out.println("Directorio actual: " + cliente.printWorkingDirectory());

            FTPFile[] files = cliente.listFiles();
            System.out.println("Ficheros en el directorio: " + files.length);

            String tipos[] = {"Fichero", " Directorio", "Enlace simb."};

            for (int i = 0; i< files.length; i++){
                System.out.println("\t" + files[i].getName() + " => " + tipos[files[i].getType()]);
            }

            for (FTPFile file : files){
                if (cliente.changeWorkingDirectory(file.getName())){
                    System.out.println("Directorio actual: " + cliente.printWorkingDirectory());
                    for (int i = 0; i< files.length; i++){
                        System.out.println("\t" + files[i].getName() + " => " + tipos[files[i].getType()]);
                    }
                    cliente.changeToParentDirectory();
                }
            }

            if (cliente.logout()){
                System.out.println("Desconectado");
            } else {
                System.out.println("Error de desconexión");
            }

            cliente.disconnect();

        } catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

}
