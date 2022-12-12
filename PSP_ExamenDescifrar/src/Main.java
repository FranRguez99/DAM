public class Main {

    public static void main(String[] args) throws InterruptedException {
        Cola c = new Cola();
        Descifrador d = new Descifrador();
        Productor p = new Productor(c, "Productor");
        Consumidor c1 = new Consumidor(c, "Consumidor 1", d);
        Consumidor c2 = new Consumidor(c, "Consumidor 2", d);
        p.start();
        c1.start();
        c2.start();
    }




}
