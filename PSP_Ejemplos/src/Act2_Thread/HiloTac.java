package Act2_Thread;

public class HiloTac extends Thread{
    @Override
    public void run(){
        while(true){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("-TAC");
            try {
                Thread.sleep(950);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
