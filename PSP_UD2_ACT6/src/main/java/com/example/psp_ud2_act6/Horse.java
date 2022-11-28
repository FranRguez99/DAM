package com.example.psp_ud2_act6;

import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class Horse extends Thread{

    ProgressBar pBar;
    Controller controller = new Controller();

    public Horse(ProgressBar pBar) {
        this.pBar = pBar;
    }

    @Override
    public void run(){
        while((int)(pBar.getProgress()*100)<100) {
                controller.increment(pBar);
                try {
                    sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

        }
    }

}
