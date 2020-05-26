/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pongsocket;

import javafx.application.Application;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.net.*;
import java.io.*;
import javafx.scene.text.Font;

public class PongSocket extends Application {
    public static final int WIDTH = 500;
    public static final int HEIGHT = 700;
    public static final int PLAYER_WIDTH = 80;
    public static final int PLAYER_HEIGHT = 20;
    public static final int BALL_RADIUS = 20;
    public static final int PORT = 50000;
    
    private int posX, posY;
    private int posXEnemy, posYEnemy;
    private int posXBall, posYBall;
    private String punteggio = "0-0";
    
    private String moveRight, moveLeft;
    public int giocatore;
    
    InetAddress addr;
    Socket socket = null;
    BufferedReader in = null, stdIn = null;
    PrintWriter out = null;
    
    @Override
    public void start(Stage primaryStage) {
        
         try{
             addr = InetAddress.getByName("127.0.0.1");
         }catch(IOException e){
             System.out.println(e);
         }
         
         try{
             socket = new Socket(addr, PORT);
             InputStreamReader isr = new InputStreamReader(socket.getInputStream());
             in = new BufferedReader(isr);
             
             OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
             BufferedWriter bw = new BufferedWriter(osw);
             out = new PrintWriter(bw, true);
             
             stdIn = new BufferedReader(new InputStreamReader(System.in));
             String userInput = in.readLine();
             
             if(userInput.equals("1")){
                 moveLeft = "1";
                 moveRight = "2";
                 giocatore = 1;
             }else {
                 moveLeft = "3";
                 moveRight = "4";
                 giocatore = 2;
             }
         }catch(IOException e){
             System.out.println(e);
         }
        
         
        Handler handler = new Handler(in, this);
        Thread thread = new Thread(handler);
        thread.start();
        
        
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        Timeline tl = new Timeline(new KeyFrame(Duration.millis(10), e -> run(gc)));
        tl.setCycleCount(Timeline.INDEFINITE);
        StackPane root = new StackPane();
        root.getChildren().add(canvas);
        //funzione per iniziallizare le variabili;
        init();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.LEFT){
                out.println(moveLeft);    
            } else if (event.getCode() == KeyCode.RIGHT) {
                //richiedo di andare verso destra
                
                out.println(moveRight);  
            }
       });
        
        primaryStage.setTitle("SOCKET");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        tl.play();
    }
    public void init(){
        posX = 210;
        posY = 600;
        
        posXEnemy = 210;
        posYEnemy = 50;
        
        posXBall = WIDTH/2;
        posYBall = HEIGHT/2;
    }
    
    public void run(GraphicsContext gc){
        //coloro lo sfondo di nero
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, WIDTH, HEIGHT);
        
        //coloro la palla
        gc.setFill(Color.WHITE);
        gc.fillOval(posXBall, posYBall, BALL_RADIUS, BALL_RADIUS);
        
        //disegno le palette dei giocatori
        gc.fillRect(posX, posY, PLAYER_WIDTH, PLAYER_HEIGHT);
        gc.fillRect(posXEnemy, posYEnemy, PLAYER_WIDTH, PLAYER_HEIGHT);
        
        //disegno il testo
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("Verdana", 32.0));
        gc.fillText(punteggio, 420, 350);
    }
    public void updateValues(int xPos, int xPosEnemy, int xBall, int yBall, String punteggio){
        this.posX = xPos;
        this.posXBall = xBall;
        this.posYBall = yBall;
        this.posXEnemy = xPosEnemy;
        this.punteggio = punteggio;
    }
    public static void main(String[] args) {
        launch(args);
    }
}