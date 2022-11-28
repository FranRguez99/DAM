package Examen;

public class Main {

    public static void main(String[] args){
        HiloPares hp = new HiloPares();
        hp.start();
        for (int i = 1; i <=10; i++){
            if(i%2 != 0){
                System.out.println(i);
            }
        }

    }

}
