import java.util.ArrayList;

public class Descifrador {

    // Atributos
    ArrayList<String> mensajeCifrado = new ArrayList<>();
    String mensajeDescifrado;
    ArrayList<String> codigoCifrado, codigoDescifrado;

    public Descifrador(){
        codigoCifrado = new ArrayList<>();
        codigoDescifrado = new ArrayList<>();
        codigoCifrado.add("g");
        codigoCifrado.add("k");
        codigoCifrado.add("l");
        codigoCifrado.add("n");
        codigoCifrado.add("p");
        codigoCifrado.add("r");
        codigoCifrado.add("t");
        codigoCifrado.add("v");
        codigoCifrado.add("z");
        codigoCifrado.add("&");
        codigoCifrado.add("x");
        codigoCifrado.add("y");
        codigoCifrado.add("s");
        codigoCifrado.add("o");
        codigoCifrado.add("m");
        codigoCifrado.add("q");
        codigoCifrado.add("i");
        codigoCifrado.add("h");
        codigoCifrado.add("f");
        codigoCifrado.add("d");
        codigoCifrado.add("b");
        codigoCifrado.add("a");
        codigoCifrado.add("c");
        codigoCifrado.add("e´");

        codigoDescifrado.add("A");
        codigoDescifrado.add("B");
        codigoDescifrado.add("C");
        codigoDescifrado.add("D");
        codigoDescifrado.add("E");
        codigoDescifrado.add("F");
        codigoDescifrado.add("G");
        codigoDescifrado.add("I");
        codigoDescifrado.add("L");
        codigoDescifrado.add("M");
        codigoDescifrado.add("N");
        codigoDescifrado.add("O");
        codigoDescifrado.add("P");
        codigoDescifrado.add("Q");
        codigoDescifrado.add("R");
        codigoDescifrado.add("S");
        codigoDescifrado.add("T");
        codigoDescifrado.add("V");
        codigoDescifrado.add("X");
        codigoDescifrado.add("Z");
        codigoDescifrado.add("1");
        codigoDescifrado.add("2");
        codigoDescifrado.add("3");
        codigoDescifrado.add("4");

    }

    public synchronized void addLetra(String letra) throws InterruptedException {
        if (letra.equals("#")){
            descifra();
        } else{
            mensajeCifrado.add(letra);
        }
    }

    public void descifra() throws InterruptedException {
        Integer contador = 0;
        Integer indexAux = 0;
        Integer index;
        System.out.print("El mensaje cifrado es: ");
        for (String letra: mensajeCifrado){
            System.out.print(letra);
            index = codigoCifrado.indexOf(letra) + indexAux;
            if(index > 24){
                index = index - 24;
            }
            mensajeDescifrado = mensajeDescifrado + codigoDescifrado.get(index);
            contador++;
            if (contador == 10){
                indexAux += 2;
                contador = 0;
            }
            wait(1000);
        }
        System.out.println(mensajeDescifrado);
    }

}
