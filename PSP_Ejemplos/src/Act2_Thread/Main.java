package Act2_Thread;

public class Main {

    public static void main(String[] args){
        HiloTic hTic = new HiloTic();
        HiloTac hTac = new HiloTac();

        hTic.start();
        hTac.start();
    }

}
