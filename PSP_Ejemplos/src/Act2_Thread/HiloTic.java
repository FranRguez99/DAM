package Act2_Thread;


public class HiloTic extends Thread{

    @Override
    public void run(){
        while(true){
            System.out.print("TIC");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
