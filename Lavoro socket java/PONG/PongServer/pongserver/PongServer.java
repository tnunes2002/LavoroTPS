package pongserver;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PongServer {
    public static final int PORT = 50000;
    public static final int WIDTH = 500;
    public static final int HEIGHT = 700;
    public static final int BALL_RADIUS = 20;
    
    public static int xPlayer1;
    public static int xPlayer2;
    public static double xBall;
    public static int yPlayer2;
    public static int yPlayer1;
    public static double yBall;
    public static int punteggio1, punteggio2;
    
    public static double ySpeed = 0.00001;
    public static double xSpeed = 0.00001;
    
    public static void main(String[] args) throws IOException{
       ServerSocket serverSocket = new ServerSocket(PORT);
       System.out.println("Server startato " + serverSocket);
       
       //creazione dei due giocatori e della palla
       xPlayer1 = 210;
       xPlayer2 = 210;
       xBall = WIDTH/2;
       yBall = HEIGHT/2;
       yPlayer1 = 600;
       yPlayer2 = 50;
       
       Socket clientSocketPlayer1 = null;
       Socket clientSocketPlayer2 = null;
       
       BufferedReader in = null;
       PrintWriter out = null;
       
       BufferedReader in1 = null;
       PrintWriter out1 = null;
       try{
           clientSocketPlayer1 = serverSocket.accept();
           System.out.println("Connessione accetata: " + clientSocketPlayer1);
           
           //creazione stream di input
           InputStreamReader isr = new InputStreamReader(clientSocketPlayer1.getInputStream());
           in = new BufferedReader(isr);
           
           //creazione stream di output
           OutputStreamWriter osw = new OutputStreamWriter(clientSocketPlayer1.getOutputStream());
           BufferedWriter bw = new BufferedWriter(osw);
           out = new  PrintWriter(bw, true);
           out.println("1");
           
           clientSocketPlayer2 = serverSocket.accept();
           System.out.println("Connessione accettata: " + clientSocketPlayer2);
           //creazione stream di input
           InputStreamReader isr1 = new InputStreamReader(clientSocketPlayer2.getInputStream());
           in1 = new BufferedReader(isr1);
           
           //creazione stream di output
           OutputStreamWriter osw1 = new OutputStreamWriter(clientSocketPlayer2.getOutputStream());
           BufferedWriter bw1 = new BufferedWriter(osw1);
           out1 = new  PrintWriter(bw1, true);        
           out.println("2");
           
           Reciver reciver = new Reciver(in);
           Thread thread = new Thread(reciver);
           thread.start();
           
           ArrayList<PrintWriter> outs = new ArrayList<PrintWriter>();
           outs.add(out);
           outs.add(out1);
           
           Sender sender = new Sender(outs);
           Thread thread1 = new Thread(sender);
           thread1.start();
           
           Reciver reciver1 = new Reciver(in1);
           Thread thread2 = new Thread(reciver1);
           thread2.start();
           
           System.out.println("inizia la partita");
           
           
           //Ottengo il tempo in nanosecondi corrente
            long lastTime = System.nanoTime();
            //specifico quanti ticks voglio in un secondo
            final double ticks = 15D;
            //Ottengo quanti secondi devono passare per far passare un tick
            double ns = 1000000000 / ticks;    
            double delta = 0;

            while(true){
                //aggiorno il tempo corrente
                long now = System.nanoTime();
                //calcolo il tempo trascorso dallo scorso tick
                delta += (now - lastTime) / ns;
                //aggoirno l'ultima volta che ha aggiornato
                lastTime = now;
                //continua fino a quando delta è minore di 1
                if(delta >= 1){
                    update();                   
                }
            }
       }catch(IOException e){
           System.out.println(e);
       }
        
       System.out.println("dentro il main");
    }
    
    public static void update(){
        xBall += xSpeed;
        yBall += ySpeed;
        
        if(xPlayer1 < 0)
            xPlayer1 = 0;
        if(xPlayer2 < 0)
            xPlayer2 = 0;
        if(xPlayer2 > WIDTH - 80)
            xPlayer2 = WIDTH - 80;
        if(xPlayer1 > WIDTH - 80)
            xPlayer1 = WIDTH - 80;
        
        if( ((yBall < yPlayer2 + 20 && yBall + 20 > yPlayer2) && xBall + BALL_RADIUS >= xPlayer2 && xBall <= xPlayer2 + 80)){
            ySpeed *= -1;
            yBall = yPlayer2 + 21;
            ySpeed *= 1.05;
        }
        
        if(((xBall + BALL_RADIUS > xPlayer1) && yBall + BALL_RADIUS >= yPlayer1 && yBall < yPlayer1 + 20 && xBall <= xPlayer1 + 80)){
            ySpeed *= -1;
            yBall = yPlayer1 - 21;
            ySpeed *= 1.05;
        }
        
        if(xBall< 0){
            xSpeed *= -1;
            yBall += 5;
        }
        if(xBall + BALL_RADIUS > WIDTH){
            xSpeed *= -1;
            yBall -= 5;
        }
              
        if(yBall < 0 || yBall + 20 >  HEIGHT){
            if(yBall<0)
                punteggio1++;
            else
                punteggio2++;
            
            xBall = WIDTH/2;
            yBall = HEIGHT/2;           
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(PongServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
      
    }
    
}
