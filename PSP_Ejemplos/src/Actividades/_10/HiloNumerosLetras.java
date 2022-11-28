package Actividades._10;

public class HiloNumerosLetras implements Runnable{
    int Tipo;

    public HiloNumerosLetras(int Tipo){
        this.Tipo = Tipo;
    }

    @Override
    public void run() {
        if (Tipo == 1){
            for (int i = 1; i<=30; i++){
                System.out.print(i + " ");
            }
        } else if (Tipo == 2) {
            for (int i = 97; i<=122; i++){
                System.out.print((char)i + " ");
            }
        }
    }
}
