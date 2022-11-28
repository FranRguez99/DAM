/**
 * Acceso a datos: Ejercicio de excepciones #2
 * @author Francisco Rodríguez Espinosa
 * @version 1.0
 */

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, PersonNotFound {
        String fileName =  "src/data.txt";
        Menu m = new Menu();
        m.menu(fileName);
    }
}