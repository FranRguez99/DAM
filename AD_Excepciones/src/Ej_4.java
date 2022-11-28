import java.util.HashMap;
import java.util.Scanner;

/**
 * Crea un programa que controle el formato del DNI, 8
 * dígitos y una letra. Además, debes controlar el sistema de
 * verificación de la letra. Lanza excepciones con el mensaje
 * más adecuado según el caso.
 *
 * @author Francisco Rodríguez Espinosa
 */

public class Ej_4 {

    static HashMap <String, Integer> compruebaDNI;

    /**
     * Inicializamos las variables necesarias para la aplicación
     */
    public static void inicializar(){
        compruebaDNI = new HashMap<>(); // Diccionario que contiene la combinación de números y letras del DNI
        compruebaDNI.put("T",0);
        compruebaDNI.put("R",1);
        compruebaDNI.put("W",2);
        compruebaDNI.put("A",3);
        compruebaDNI.put("G",4);
        compruebaDNI.put("M",5);
        compruebaDNI.put("Y",6);
        compruebaDNI.put("F",7);
        compruebaDNI.put("P",8);
        compruebaDNI.put("D",9);
        compruebaDNI.put("X",10);
        compruebaDNI.put("B",11);
        compruebaDNI.put("N",12);
        compruebaDNI.put("J",13);
        compruebaDNI.put("Z",14);
        compruebaDNI.put("S",15);
        compruebaDNI.put("Q",16);
        compruebaDNI.put("V",17);
        compruebaDNI.put("H",18);
        compruebaDNI.put("L",19);
        compruebaDNI.put("C",20);
        compruebaDNI.put("K",21);
        compruebaDNI.put("E",22);
    }

    /**
     * Comprueba si el DNI dado tiene el formato correcto
     * @param dni DNI introducido
     * @throws DNIException excepción en caso de que el DNI sea erroneo
     */
    public static void verDNI(String dni) throws DNIException {

        if (dni.length() == 9) { // Comprueba la longitud de la cadena
            try {
                // Comprueba que los 8 primeros caracteres son numéricos
                Integer aux = Integer.valueOf(dni.substring(0, 8));
                String letra = String.valueOf(dni.charAt(8));
                if(aux%23 != compruebaDNI.get(letra)){ // Comprueba la letra en el diccionario
                    throw new DNIException("DNI Erroneo");
                }
            } catch (NumberFormatException nfe){
                throw new DNIException("DNI Erroneo");
            }
        } else {
            throw new DNIException("DNI Erroneo");
        }
    }

    /**
     * Ejecuta la aplicación
     */
    public static void ejecutar(){
        inicializar();
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduzca el DNI: ");
        String dni = sc.next();
        try {
            verDNI(dni);
        } catch (DNIException dnie) {
            System.out.println("Excepción DNI capturada");
        } finally {
            System.out.println("\nFin del ejercicio 4");
        }
    }
}
