public class Cola {

    String letra;
    boolean disponible = false;

    public synchronized String get() throws InterruptedException {
        while (!disponible){
            wait();
        }
        disponible = false;
        notify();
        return letra;
    }

    public synchronized  void put(String l) throws InterruptedException {
        while (disponible){
            wait();
        }
        letra = l;
        disponible = true;
        notify();
    }

}
