package Ejemplos_T2.Applet;

public class Act4_MyHilo extends Thread{

    private Act4_SolicitaSuspender suspender = new Act4_SolicitaSuspender();
    Boolean salir = false;
    Integer contador = 0;

    public void Suspende(){
        suspender.set(true);
    }

    public void Reanuda(){
        suspender.set(false);
    }

    public void Parar(){
        salir = true;
    }

    public Integer valorContador(){
        return contador;
    }

    public void run(){
        try{
            while(!salir){
                contador++;
                System.out.println(contador);
                sleep(1000);
                suspender.esperandoParaReanudar();
            }
        } catch (InterruptedException exception){

        }
    }

}
