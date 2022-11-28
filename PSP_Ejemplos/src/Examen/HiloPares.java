package Examen;

public class HiloPares extends Thread{

    Integer suma = 0;

    @Override
    public void run(){
        for (int i = 1; i <=10; i++){
            if(i%2 == 0){
                System.out.println(i);
                suma = suma+i;
            }
        }
        System.out.println(suma);
    }
}
