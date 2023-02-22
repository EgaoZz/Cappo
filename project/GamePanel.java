package project;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    
    private boolean IsRun=true;
    private int FPS = 60; 
    private Thread thread;
    private character c;
    public GamePanel(KeyHandle key) {
        super();
        c= new character(key);

        thread=new Thread(this);
        thread.start();
    }

    public void run(){

        double drawInterval = 1000000000/FPS; // 1 giây/60 
        double nextDrawTime = System.nanoTime()+drawInterval;
        long timer =0;
        int count=0;
        while(IsRun){

            update();
            repaint();
            try{
                double remainingTime = nextDrawTime - System.nanoTime();
                timer+=remainingTime;
                
                // sleep chạy theo mili giây
                remainingTime/=1000000;

                if (remainingTime<0){
                    remainingTime=0;
                }
                Thread.sleep((long)remainingTime);
                nextDrawTime+=drawInterval;
                count++;
                if (timer >= 1000000000){
                    // System.out.println("FPS: "+count);
                    timer=0;
                    count=0;
                }
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    private void update(){
        c.update();

    }

    public void paintComponent( Graphics g){
        super.paintComponent(g);
        c.draw((Graphics2D) g);

        
    }


    

}  
