/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pongserver;

import java.io.BufferedReader;
import java.io.IOException;


public class Reciver implements Runnable{
    private BufferedReader in;
    
    public Reciver(BufferedReader in){
        this.in = in;
    }
    
    public void run(){
        while(true){
            try{
                String stringa = in.readLine();
                System.out.println("ricevuta la stringa: " + stringa);
                if(stringa.equals("1"))
                    PongServer.xPlayer1 -= 10;
                else if(stringa.equals("2"))
                    PongServer.xPlayer1 += 10;
                else if(stringa.equals("3"))
                    PongServer.xPlayer2 -= 10;
                else if(stringa.equals("4"))
                    PongServer.xPlayer2 += 10;
            } catch(IOException e){
                System.out.println(e);
            }
        }
    }
}
