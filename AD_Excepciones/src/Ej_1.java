import java.util.Scanner;

/**
 * Creación de un programa que lance la excepción
 * correspondiente cuando el número cero es el divisor.
 *
 * @author Francisco Rodríguez Espinosa
 */

public class Ej_1 {

    /**
     * Divide dos números dados como parámetro
     * @param a número 1
     * @param b número 2
     * @return resultado
     */
    public static Integer divide(Integer a, Integer b) throws ArithmeticException{
        Integer res;
        res = a/b;
        return res;
    }

    /**
     * Ejecuta la aplicación
     */
    public static void ejecutar(){
        Scanner sc = new Scanner(System.in);
        try{
            System.out.print("Introduzca el dividendo: ");
            Integer a = Integer.valueOf(sc.next());
            System.out.print("Introduzca el divisor: ");
            Integer b = Integer.valueOf(sc.next());
            Integer res = divide(a,b);
            System.out.println("El resultado es : " + res);
        }catch (ArithmeticException e){
            System.out.println("Error de cálculo: NO se puede dividir por 0");
        } finally {
            System.out.println("\nFin del ejercicio 1");
        }
    }

}
