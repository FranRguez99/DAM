package com.example.psp_examen3_2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    ServidorController servidorController;

    public Servidor() {
        this.servidorController = new ServidorController();
    }

    void comenzar() throws IOException {
        ServerSocket servidor = new ServerSocket(6000);
        System.out.println("Servidor iniciado.");

        while (true) {
            Socket cliente = new Socket();
            cliente = servidor.accept();

            HiloServidor hilo = new HiloServidor(cliente);
            hilo.start();
        }

    }
}

