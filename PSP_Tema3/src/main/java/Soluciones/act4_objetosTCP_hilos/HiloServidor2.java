package Soluciones.act4_objetosTCP_hilos;


import java.io.*;
import java.net.Socket;

public class HiloServidor2 extends Thread {

    // Cuando se conecte un cliente se muestra la IP y el puerto
    // Cuando se desconecte que se notifique
    // Recibe una cadena y si no es un * se la devuelve en mayúsculas

    DataInputStream entrada;
    ObjectOutputStream salida;
    Socket socket = null;
    int numCliente;

    Asignatura asig1 = new Asignatura(1, "Matemáticas");
    Asignatura asig2 = new Asignatura(2, "Naturales");
    Asignatura asig3 = new Asignatura(3, "Lengua");
    Asignatura asig4 = new Asignatura(3, "Inglés");
    Asignatura asig5 = new Asignatura(3, "Geografía");
    Asignatura listaAsig1 [] = {asig1, asig2, asig5};
    Asignatura listaAsig2 [] = {asig3, asig4, asig1};
    Asignatura listaAsig3 [] = {asig2, asig5, asig3};

    Especialidad esp1 = new Especialidad(1, "Especialidad 1");
    Especialidad esp2 = new Especialidad(2, "Especialidad 2");
    Especialidad esp3 = new Especialidad(3, "Especialidad 3");

    Profesor profesor1 = new Profesor(1, "Alex", listaAsig1, esp1);
    Profesor profesor2 = new Profesor(2, "Maria José", listaAsig2, esp1);
    Profesor profesor3 = new Profesor(3, "Jorge", listaAsig3, esp2);
    Profesor profesor4 = new Profesor(4, "Jose Ignacio", listaAsig1, esp2);
    Profesor profesor5 = new Profesor(5, "Estrella", listaAsig3, esp3);
    Profesor listaProfesores[] = {profesor1, profesor2, profesor3, profesor4, profesor5};

    public HiloServidor2(Socket socket, int numCliente) throws IOException {
        this.socket = socket;
        this.numCliente = numCliente;
    }

    public void run() {
        int idProfRecibido = 0;
        String strIdProfRecibido = "";
        System.out.println("Cliente " + numCliente + " conectado");

        Profesor profesor = new Profesor();

        while (!strIdProfRecibido.equals("*")) {

            try {
                entrada = new DataInputStream(socket.getInputStream());
                strIdProfRecibido = entrada.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(!strIdProfRecibido.equals("*")) {
                idProfRecibido = Integer.parseInt(strIdProfRecibido);
                System.out.println("\t Consultando id: " + idProfRecibido + ", solicitado por cliente: " + numCliente);

                for (Profesor element: listaProfesores) {
                    if (element.getIdprofesor() == idProfRecibido) {
                        profesor = element;
                    }
                }

                try {
                    salida = new ObjectOutputStream(socket.getOutputStream());
                    salida.writeObject(profesor);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        System.out.println("EL CLIENTE " + numCliente + " HA FINALIZADO");
        System.out.println("FIN CON: " + socket.toString() + " DEL CLIENTE: " + numCliente);

        try {
            salida.close();
            entrada.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
