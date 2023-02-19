package Soluciones.act3_objetosUDP;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Cliente {

    // Envía al servidor el id del alumno
    // Bucle infinito hasta introducir *
    // Recibe el objeto y muestra los datos

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        DatagramSocket socketCliente = new DatagramSocket();
        System.out.println("Cliente iniciado...");

        Scanner kb = new Scanner(System.in);
        String idAlumno;
        Alumno alumnoRecibido = new Alumno();

        // Datos del servidor al que enviar el mensaje
        InetAddress IPServidor = InetAddress.getLocalHost();
        int puerto = 6000;

        while (true) {
            System.out.print("Introduce el id del alumno: ");
            idAlumno = kb.nextLine();

            // ENVÍO EL IDALUMNO AL SERVIDOR
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream salida = new ObjectOutputStream(baos);
            salida.writeUTF(idAlumno);
            salida.close();

            byte[] bytes = baos.toByteArray();
            DatagramPacket paqueteEnvio = new DatagramPacket(bytes, bytes.length, IPServidor, puerto);
            socketCliente.send(paqueteEnvio);

            if (idAlumno.equals("*")) {
                break;
            }

            System.out.println("Envío al servidor el id de alumno: " + idAlumno);

            // RECIBO DATAGRAMA DEL SERVIDOR
            byte[] bytesRecibidos = new byte[1024];
            DatagramPacket paqueteRecibido = new DatagramPacket(bytesRecibidos, bytesRecibidos.length);
            socketCliente.receive(paqueteRecibido);

            // convierte los bytes a objeto
            ByteArrayInputStream bais = new ByteArrayInputStream(bytesRecibidos);
            ObjectInputStream entrada = new ObjectInputStream(bais);
            alumnoRecibido = (Alumno) entrada.readObject();
            entrada.close();

            System.out.println("Recibo del servidor: " + alumnoRecibido.getNombre() + " con id " + alumnoRecibido.getIdalumno() + " del curso " + alumnoRecibido.getCurso().getId() + " con descripcion '" + alumnoRecibido.getCurso().getDescripcion() + "' y con nota " + alumnoRecibido.getNota());
        }

        // CIERRA SOCKET
        System.out.println("Cierro conexión...");
        socketCliente.close();

    }

}
