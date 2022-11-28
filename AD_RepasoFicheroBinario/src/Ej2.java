import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Francisco Rodríguez Espinosa
 */

public class Ej2 {

    /**
     * Pide por teclado los datos para crear un nuevo objeto de clase vehículo
     * @return vehículo creado
     */
    public static Vehiculo creaVehiculo() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca la matrícula:");
        String matricula = sc.next();
        System.out.println("Introduzca la marca:");
        String marca = sc.next();
        System.out.println("Introduzca la capacidad del depósito:");
        Double deposito = Double.valueOf(sc.next());
        System.out.println("Introduzca el modelo:");
        String modelo = sc.next();
        return new Vehiculo(matricula, marca, deposito, modelo);
    }

    /**
     * Escribe en el fichero binario los vehículos
     * @param dos conexión con el fichero binario
     * @param vehiculo vehículo a escribir
     */
    public static void escribeFichero(DataOutputStream dos, Vehiculo vehiculo) throws IOException {
        PrintWriter writer = new PrintWriter("src/Ej2.bin"); // Escriba para el fichero
        writer.print("");
        writer.close();
        dos.writeUTF(vehiculo.getMatricula());
        dos.writeUTF(vehiculo.getMarca());
        dos.writeDouble(vehiculo.getDeposito());
        dos.writeUTF(vehiculo.getModelo());
    }

    /**
     * Lee los vehículos almacenados en el fichero binario
     * @param dis conexión con el fichero
     * @param listaVehiculos lista en la que se almacenan los vehículos en la aplicación
     */
    public static void leeFichero(DataInputStream dis, List<Vehiculo> listaVehiculos) {
        try {
            while (true) {
                listaVehiculos.add(new Vehiculo(dis.readUTF(), dis.readUTF(), dis.readDouble(), dis.readUTF()));
            }
        } catch (IOException ignore){

        }

    }

    /**
     * Menú de nuestra aplicación
     */
    public static void menu() throws IOException, ClassNotFoundException {
        DataOutputStream dos = new DataOutputStream(new FileOutputStream("src/Ej2.bin", true));
        DataInputStream dis = new DataInputStream(new FileInputStream("src/Ej2.bin"));
        List<Vehiculo> listaVehiculos = new ArrayList<>();
        leeFichero(dis, listaVehiculos);
        Boolean salir = false;
        Scanner sc = new Scanner(System.in);
        int opcion;
        while (!salir) {
            System.out.println("    MENÚ");
            System.out.println("   ------");
            System.out.println("1) Añadir vehículo");
            System.out.println("2) Mostrar vehículos");
            System.out.println("3) Modifica un vehículo");
            System.out.println("4) Salir");
            System.out.print("Opción: ");
            opcion = Integer.parseInt(sc.next());
            switch (opcion) { // Menú
                case 1: // Añadir vehículo

                    Vehiculo v = creaVehiculo();
                    listaVehiculos.add(v);
                    escribeFichero(dos, v);
                    break;
                case 2: // Mostrar los vehículos almacenados
                    for (Vehiculo ve : listaVehiculos) {
                        System.out.println(ve);
                    }
                    break;
                case 3: // Modificar algunos de los vehículos
                    System.out.print("Matrícula del vehículo a modificar: ");
                    String matricula = sc.next();
                    int opcion2;
                    System.out.println("1) Modificar marca");
                    System.out.println("2) Modificar depósito");
                    System.out.println("3) Modificar modelo");
                    System.out.println("4) Borrar");
                    System.out.print("Opción:");
                    opcion2 = Integer.parseInt(sc.next());
                    switch (opcion2) { // Submenú para modificar los vehículos
                        case 1: // Modificar marca
                            System.out.print("Introduzca nueva marca: ");
                            String marca = sc.next();
                            for (Vehiculo vAux : listaVehiculos) {
                                if (vAux.getMatricula().equals(matricula)) {
                                    vAux.setMarca(marca);
                                }
                            }
                            break;
                        case 2: // Modificar depósito
                            System.out.print("Introduzca nuevo depósito: ");
                            Double deposito = Double.valueOf(sc.next());
                            for (Vehiculo vAux : listaVehiculos) {
                                if (vAux.getMatricula().equals(matricula)) {
                                    vAux.setDeposito(deposito);
                                }
                            }
                            break;
                        case 3: // Modificar modelo
                            System.out.print("Introduzca nuevo modelo: ");
                            String modelo = sc.next();
                            for (Vehiculo vAux : listaVehiculos) {
                                if (vAux.getMatricula().equals(matricula)) {
                                    vAux.setModelo(modelo);
                                }
                            }
                            break;
                        case 4: // Borrar el vehículo
                            listaVehiculos.removeIf(vAux -> vAux.getMatricula().equals(matricula));
                            break;
                    }
                    break;
                case 4: // Salir
                    System.out.println("Hasta luego");
                    salir = true;
                    break;
                default:
                    System.out.println("Introduzca un número del 1 al 4");
            }
        }

    }

}
