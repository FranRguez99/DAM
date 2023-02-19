package com.example.psp_examen3_2;

import java.net.Socket;

public class HiloServidor extends Thread{
    Socket socket;

    public HiloServidor(Socket cliente) {
        this.socket = cliente;
    }
}
