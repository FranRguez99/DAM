import java.io.IOException;
import java.util.Scanner;

/**
 * @author Francisco Rodríguez Espinosa
 */

public class Menu {

    /**
     * Menú que controla nuestra aplicación
     */
    public void menu(String fileName) throws IOException, PersonNotFound {
        FileControl fc = new FileControl();
        fc.initialize(fileName);
        Scanner sc = new Scanner(System.in);
        Boolean exit = false;
        Integer option = null;
        while (!exit) {
            System.out.println("\n\tAplicación de control de excepciones");
            System.out.println("\t------------------------------------");
            System.out.println("\n1) Añadir persona");
            System.out.println("2) Ver listado de personas registradas");
            System.out.println("3) Modificar datos de una persona");
            System.out.println("4) Eliminar una persona");
            System.out.println("5) Salir");
            System.out.print("\nIntroduzca una opción: ");
            option = Integer.parseInt(sc.next());
            if (option == 1) { // Añadir persona
                Boolean exists = false; // Variable para controlar si el DNI ya existe en la lista de personas
                System.out.print("Introduzca el nombre de la persona: ");
                String name = sc.next();
                System.out.print("Introduzca sus apellidos: ");
                String surname = sc.next();
                System.out.print("Introduzca su edad: ");
                String age = sc.next();
                System.out.print("Introduzca su DNI: ");
                String dni = sc.next();
                try { // Comprobamos si su DNI tiene el formato correcto
                    fc.dniCheck(dni);
                    for (String person : fc.listPersons) { // Recorremos la lista de personas para ver si existe el DNI
                        if (person.split(";")[3].equals(dni)) {
                            exists = true;
                            break;
                        }
                    }
                    if (exists.equals(true)) { // Introducimos el nuevo usuario en caso de que no exista el DNI
                        System.out.println("El DNI ya está registrado");
                    } else {
                        fc.addPerson(name, surname, age, dni);
                        System.out.println("Persona añadida con éxito\n");
                    }
                } catch (DNIException dnie) {
                    System.out.println("El DNI introducido es erróneo");
                }

            } else if (option == 2) { // Ver listado de personas
                fc.printPersons();
            } else if (option == 3) { // Modificar persona
                try{
                    fc.modifyPerson(sc);
                } catch (PersonNotFound pnf){
                    System.out.println("El DNI introducido no se encuentra en la lista");
                }

            } else if (option == 4) { // Eliminar persona
                System.out.print("Introduzca DNI de la persona a eliminar: ");
                String dni = sc.next();
                try{
                    fc.deletePerson(dni);
                } catch (PersonNotFound pnf){
                    System.out.println("El DNI introducido no se encuentra en la lista");
                }
            } else if (option == 5) { // Salir
                fc.writeFile(fileName);
                exit = true;
            } else {
                System.out.println("Escriba un número de 1 a 5");
            }
        }
    }

}
