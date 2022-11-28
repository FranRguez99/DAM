package Ejemplos_T2.Applet;

import java.util.Scanner;

public class Act4_Main {

    public static void main(String[] args){
        Act4_MyHilo h = new Act4_MyHilo();
        Scanner sc = new Scanner(System.in);
        String letra;

        h.start();
        while(true){
            letra = sc.next();
            if(letra.equals("S")){
                h.Suspende();
            } else if (letra.equals("R")){
                h.Reanuda();
            } else if (letra.equals("*")){
                h.Parar();
                break;
            }
        }
    }

}
