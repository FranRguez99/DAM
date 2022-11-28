package Actividades._10;

import java.util.Scanner;

//Hola

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca número: ");
        int Tipo = sc.nextInt();
        HiloNumerosLetras hnl = new HiloNumerosLetras(Tipo);
        Thread t = new Thread(hnl);
        t.start();
    }

}
