package Act3;


import java.io.*;
import java.net.Socket;

public class HiloServidor extends Thread {


    DataInputStream entrada;
    ObjectOutputStream salida;
    Socket socket;
    int numCliente;

    Barco b1 = new Barco("Thousand Sunny", "Mugiwaras", "Bergantín", 56, 39);
    Barco b2 = new Barco("Moby Dick", "Withebeard pirates", "Bergantín", 56, 39);

    AkumaNoMi a1 = new AkumaNoMi("Gomu Gomu no Mi", "Poder de goma", "Morada");
    AkumaNoMi a2 = new AkumaNoMi("Gura Gura no Mi", "Poder de terremoto", "Gris");
    AkumaNoMi a3 = new AkumaNoMi("Mera Mera no Mi", "Poder de fuego", "Naranja");

    Mugiwara m1 = new Mugiwara("Luffy", "Capitán", a1, b1);
    Mugiwara m2 = new Mugiwara("Shirohige", "Capitán", a2, b2);
    Mugiwara m3 = new Mugiwara("Ace", "Subcapitán", a3, b2);

    Mugiwara listaMugiwaras[] = {m1,m2,m3};

    public HiloServidor(Socket socket, int numCliente) {
        this.socket = socket;
        this.numCliente = numCliente;
    }

    public void run() {
        System.out.println("Se ha conectado el cliente: " + numCliente);

        String nombre = "";
        Mugiwara mugiwara;

        while (!nombre.equals("*")) {
            mugiwara = new Mugiwara();
            try {
                entrada = new DataInputStream(socket.getInputStream());
                nombre = entrada.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(!nombre.equals("*")) {
                System.out.println("\t El cliente " + numCliente + "busca al pirata: " + nombre);

                for (Mugiwara m : listaMugiwaras){
                    if (m.getNombre().equals(nombre)){
                        mugiwara = m;
                    }
                }

                try {
                    salida = new ObjectOutputStream(socket.getOutputStream());
                    salida.writeObject(mugiwara);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("Cliente " + numCliente + " desconectado");

        try {
            salida.close();
            entrada.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
