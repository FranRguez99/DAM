package ACT4;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<String> listaFicheros = new ArrayList<>();
        List<HiloCuenta> listaHilos = new ArrayList<>();
        listaFicheros.add(args[0]);
        listaFicheros.add(args[1]);
        listaFicheros.add(args[2]);
        listaFicheros.add(args[3]);

        listaFicheros.forEach(s -> listaHilos.add(new HiloCuenta(s)));
        listaHilos.forEach(Thread::start);
    }
}
