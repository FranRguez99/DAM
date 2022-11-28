/**
 * @author Francisco Rodríguez Espinosa
 */

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class FileControl {

    // Lista en la que guardaremos los nombres
    public List<String> listPersons;
    HashMap<String, Integer> dniChecker;

    public void initialize(String fileName) throws IOException {
        readFile(fileName);
        dniChecker = new HashMap<>(); // Diccionario que contiene la combinación de números y letras del DNI
        dniChecker.put("T", 0);
        dniChecker.put("R", 1);
        dniChecker.put("W", 2);
        dniChecker.put("A", 3);
        dniChecker.put("G", 4);
        dniChecker.put("M", 5);
        dniChecker.put("Y", 6);
        dniChecker.put("F", 7);
        dniChecker.put("P", 8);
        dniChecker.put("D", 9);
        dniChecker.put("X", 10);
        dniChecker.put("B", 11);
        dniChecker.put("N", 12);
        dniChecker.put("J", 13);
        dniChecker.put("Z", 14);
        dniChecker.put("S", 15);
        dniChecker.put("Q", 16);
        dniChecker.put("V", 17);
        dniChecker.put("H", 18);
        dniChecker.put("L", 19);
        dniChecker.put("C", 20);
        dniChecker.put("K", 21);
        dniChecker.put("E", 22);
    }

    /**
     * Función que lee el fichero, inicializa la lista y carga los datos en la misma de manera que cada línea de fichero
     * es un elemento.
     *
     * @param fileName nombre del fichero .txt que deseamos cargar en la lista.
     */
    public void readFile(String fileName) throws IOException {
        BufferedReader br = null;
        try { // Abrimos el fichero para lectura
            br = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException fnfe) {
            System.out.println("El fichero no existe");
        }
        String buffer; // Buffer para ir guardando las líneas que se leen en el fichero
        listPersons = new ArrayList<>(); // Inicializamos la lista
        while ((buffer = br.readLine()) != null) {
            listPersons.add(buffer); // Añadimos los nombres al fichero
        }
        try {
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Función que escribe en el fichero los datos de nuestra aplicación
     *
     * @param fileName Fichero en el que vamos a escribir los datos
     */
    public void writeFile(String fileName) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, false)); // Conexión con el fichero
        listPersons.forEach(name -> { // Bucle que escribe todos los nombres de la lista en nuestro fichero
            try {
                bw.write(name + "\n"); // Escribimos cada nombre en una línea independiente
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        try {
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Comprueba si el DNI dado tiene el formato correcto
     *
     * @param dni DNI introducido
     * @throws DNIException excepción en caso de que el DNI sea erroneo
     */
    public void dniCheck(String dni) throws DNIException {
        if (dni.length() == 9) { // Comprueba la longitud de la cadena
            try {
                // Comprueba que los 8 primeros caracteres son numéricos
                Integer aux = Integer.valueOf(dni.substring(0, 8));
                String letter = String.valueOf(dni.charAt(8));
                if (aux % 23 != dniChecker.get(letter)) { // Comprueba la letra en el diccionario
                    throw new DNIException("DNI Erroneo");
                }
            } catch (NumberFormatException nfe) {
                throw new DNIException("DNI Erroneo");
            }
        } else {
            throw new DNIException("DNI Erroneo");
        }
    }

    /**
     * Añade una nueva persona a nuestra lista
     *
     * @param name    Nombre de la persona
     * @param surname Apellidos de la persona
     * @param age     Edad de la persona
     * @param dni     Documento nacional de identidad de la persona
     */
    public void addPerson(String name, String surname, String age, String dni) {
        listPersons.add(name + ";" + surname + ";" + age + ";" + dni);
    }

    /**
     * Muestra por pantalla la lista de personas registradas en nuestra aplicación
     */
    public void printPersons() {
        int i = 1;
        for (String person : listPersons) {
            System.out.println("PERSONA " + i);
            String[] fields = person.split(";");
            System.out.println("Nombre: " + fields[0] + "\nApellidos: " + fields[1] + "\nEdad: " + fields[2] +
                    "\nDNI: " + fields[3] + "\n");
            i++;
        }
    }

    /**
     * Modifica los datos de una persona del listado
     * @param sc Escáner para leer los datos introducidos por teclado
     */
    public void modifyPerson(Scanner sc) throws PersonNotFound {
        System.out.print("\nIntroduzca el DNI de la persona a modificar: ");
        String dni = sc.next();
        String aux = null;
        Integer position = null;
        for (int i = 0; i < listPersons.size(); i++) { // Comprobamos si el DNI introducido existe
            if (listPersons.get(i).split(";")[3].equals(dni)) {
                aux = listPersons.get(i);
                position = i;
            }
        }
        if (aux != null) { // Si ha encontrado a una persona, realiza el cambio
            System.out.println("\nDato que desea cambiar");
            System.out.println("1) Nombre");
            System.out.println("2) Apellidos");
            System.out.println("3) Edad");
            System.out.println("4) DNI");
            System.out.print("Introduzca opción: ");
            Integer option = Integer.parseInt(sc.next());
            if (option == 1) { // Cambio de nombre
                System.out.print("\nIntroduzca nuevo nombre: ");
                String name = sc.next();
                aux = aux.replace(aux.split(";")[0], name);
                listPersons.set(position, aux);
                System.out.println("Nombre modificado correctamente");
            } else if(option == 2){ // Cambia el apellido
                System.out.print("\nIntroduzca nuevo apellido: ");
                String surname = sc.next();
                aux = aux.replace(aux.split(";")[1], surname);
                listPersons.set(position, aux);
                System.out.println("Apellido modificado correctamente");
            } else if(option == 3){ // Cambia la edad
                System.out.print("\nIntroduzca nueva edad: ");
                String age = sc.next();
                aux = aux.replace(aux.split(";")[2], age);
                listPersons.set(position, aux);
                System.out.println("Edad modificada correctamente");
            } else if (option == 4){ // Cambiar DNI
                System.out.print("\nIntroduzca nuevo DNI: ");
                dni = sc.next();
                try{
                    dniCheck(dni);
                    aux = aux.replace(aux.split(";")[1], dni);
                    listPersons.set(position, aux);
                    System.out.println("DNI modificado correctamente");
                } catch (DNIException dnie) {
                    System.out.println("El DNI introducido es erróneo");
                }

            } else {
                System.out.println("La opción introducida no es correcta");
            }
        } else {
            throw new PersonNotFound("Persona no encontrada");
        }

    }

    /**
     * Elimina una persona de la lista de personas registradas
     * @param dni DNI de la persona a eliminar
     */
    public void deletePerson(String dni) throws PersonNotFound {
        for (int i = 0; i < listPersons.size(); i++){
            if(listPersons.get(i).split(";")[3].equals(dni)){ // Buscamos a la persona en la lista con su DNI
                listPersons.remove(i);
                System.out.println("Persona eliminada con éxito");
                break;
            } else if (i == listPersons.size()-1) {
                throw new PersonNotFound("Persona no encontrada");
            }
        }
    }

}
