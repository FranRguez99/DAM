package Soluciones.act3_objetosUDP;

import java.io.*;
import java.lang.reflect.Array;
import java.net.*;

public class Servidor {

    // Array de 5 objetos tipo Alumno
    // Recibe del cliente el id del alumno y envía el objeto correspondiente

    public static void main(String[] args) throws IOException {

        DatagramSocket socketServidor = new DatagramSocket(6000);
        System.out.println("Servidor iniciado...");

        Curso curso1 = new Curso ("Primero", "DAM");
        Curso curso2 = new Curso ("Segundo", "DAM");

        Alumno alumno1 = new Alumno ("1", "Sara", curso1, 9);
        Alumno alumno2 = new Alumno ("2", "Eva", curso1, 7);
        Alumno alumno3 = new Alumno ("3", "Pedro", curso1, 6);
        Alumno alumno4 = new Alumno ("4", "Jose", curso2, 3);
        Alumno alumno5 = new Alumno ("5", "Alex", curso2, 8);
        Alumno listaAlumnos[] = {alumno1, alumno2, alumno3, alumno4, alumno5};

        while (true) {
            Alumno alumnoRecibido = new Alumno();

            // RECIBE DATAGRAMA DEL CLIENTE
            byte[] recibidos = new byte[1024];
            DatagramPacket paqueteRecibido = new DatagramPacket(recibidos, recibidos.length);
            socketServidor.receive(paqueteRecibido);

            // convertirmos el paquete a String
            ByteArrayInputStream bais = new ByteArrayInputStream(recibidos);
            ObjectInputStream entrada = new ObjectInputStream(bais);
            String idalumnoRecibido = entrada.readUTF();
            entrada.close();

            if (idalumnoRecibido.equals("*")) {
                break;
            }

            System.out.println("Recibo el id de alumno: " + idalumnoRecibido);

            // lo asociamos al objeto correspondiente
            for (Alumno element : listaAlumnos) {
                if (element.getIdalumno().equals(idalumnoRecibido)) {
                    alumnoRecibido = element;
                }
            }

            // DIRECCIÓN DE ORIGEN DEL MENSAJE
            InetAddress IPOrigen = paqueteRecibido.getAddress();
            int puerto = paqueteRecibido.getPort();

            // ENVÍA DATAGRAMA AL CLIENTE
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream salida = new ObjectOutputStream(baos);
            salida.writeObject(alumnoRecibido);
            salida.close();
            byte[] bytesEnviados = baos.toByteArray();

            DatagramPacket paqueteEnvio = new DatagramPacket(bytesEnviados, bytesEnviados.length, IPOrigen, puerto);
            socketServidor.send(paqueteEnvio);

            System.out.println("Envío al cliente: " + alumnoRecibido.getNombre() + " con id " + alumnoRecibido.getIdalumno() + " del curso " + alumnoRecibido.getCurso().getId() + " con descripcion '" + alumnoRecibido.getCurso().getDescripcion() + "' y con nota " + alumnoRecibido.getNota());
        }

        // CIERRA SOCKET
        System.out.println("Cerrando conexión...");
        socketServidor.close();

    }

}
