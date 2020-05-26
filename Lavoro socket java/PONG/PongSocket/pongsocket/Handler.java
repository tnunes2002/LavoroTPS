/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pongsocket;

import java.io.BufferedReader;
import java.io.IOException;

public class Handler implements Runnable{
    private final BufferedReader in;
    private PongSocket game;
    
    public Handler(BufferedReader in, PongSocket game){
        this.in = in;
        this.game = game;
        System.out.println("all'interno di true");
    }
    
    @Override
    public void run() {
        while(true){
            try{             
                String stringa = in.readLine();
                String[] valori =  stringa.split(",");
                try{
                    
                    game.updateValues(Integer.parseInt(valori[0]), Integer.parseInt(valori[1]), Integer.parseInt(valori[2]), Integer.parseInt(valori[3]), valori[4]);              
                    
                }catch(ArrayIndexOutOfBoundsException e){
                    System.out.println("errorcino");
                }
                  
            }catch(IOException e){
                System.out.println(e);
            }
            
        }
    }
    
}
