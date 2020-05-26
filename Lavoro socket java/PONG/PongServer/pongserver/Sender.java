/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pongserver;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Sender implements Runnable{
    ArrayList<PrintWriter> outs;
    
    public Sender(ArrayList<PrintWriter> outs){
        this.outs = outs;
    }
    
    public void run(){
        while(true){
            for(PrintWriter out: outs){
                try {
                    out.println("" + PongServer.xPlayer1 +","+ PongServer.xPlayer2 + "," + (int)(PongServer.xBall) + "," + (int)(PongServer.yBall) + "," + PongServer.punteggio2 + "-" + PongServer.punteggio1);
                    
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Sender.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }   
    }
}
