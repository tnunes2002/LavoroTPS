/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karzal.javafxgame.window;

import com.karzal.javafxgame.entites.Player;
import com.karzal.javafxgame.leveldata.Levels;
import com.karzal.javafxgame.menu.Menu;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Game extends Application{
   public static final String TITLE = "GAME";
   public static final int WIDTH = 1200;
   public static final int HEIGHT = 900;
   
   private Menu menu;
   private Game game;
   private Pane root;
   private Pane gameRoot = new Pane();
   
   private Player player;
  
   public static int levelWidth;
   public static ArrayList<Node> platforms = new ArrayList<Node>();   
   private boolean running = false;
   private boolean paused;
   
   private HashMap<KeyCode, Boolean> keys = new HashMap<KeyCode, Boolean>();
   
   public Game(){
       menu = new Menu();
       paused = true;
   }
   
   public void init(Stage window){
       if(running) return;
       
       running = true;
       root = new Pane();
       
       Scene scene = new Scene(root, WIDTH, HEIGHT);
       scene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
       scene.setOnKeyReleased(event -> keys.put(event.getCode(), false));
       
       levelWidth = Levels.LEVEL1[0].length() * 64;
       
       for(int i = 0; i < Levels.LEVEL1.length; i++){
           String line = Levels.LEVEL1[i];
           for(int j = 0; j < line.length(); j++){
               switch(line.charAt(j)){
                   case '0':                   
                       break;
                   case '1':
                       Node platform = createEntity(j * 64, i*64, 64, 64, Color.BLUE);
                       platforms.add(platform);
                       break;
                   case '4':
                       break;
               }
           }
       }
       
       player = new Player(
               100,
               60, 
               new Image(new File("C:\\Users\\YOUNESS\\Documents\\NetBeansProjects\\JAVAFX\\src\\resources\\room\\sheet2.png").toURI().toString()),
               gameRoot);
       
       player.getCollisionRect().setTranslateY(400);
       player.getCollisionRect().translateXProperty().addListener((obs, old, newValue) -> {
           int offset = newValue.intValue();
           
           if(offset > 600 && offset < levelWidth - 600){
               gameRoot.setLayoutX(-(offset - 600));
           }
       });
       
       Rectangle bg = new Rectangle(WIDTH, HEIGHT);
       bg.setFill(Color.BLACK);
       root.getChildren().addAll(bg, gameRoot);
       window.setScene(scene);   
       window.setTitle(TITLE);
       run();
   }
   
   private Node createEntity(int x, int y, int w, int h, Color color){
       Rectangle entity = new Rectangle(w, h);
       entity.setTranslateX(x);
       entity.setTranslateY(y);
       entity.setFill(color);
       
       gameRoot.getChildren().add(entity);
       return entity;
   }
   
   public void run(){  
       AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();

            }
       };
       timer.start();
   }
   
   public void setPaused(boolean paused){
       this.paused = paused;
   }
   
   public void update(){
       player.update(keys);
       
       if(player.getCollisionRect().getTranslateY() > HEIGHT){
           player.getCollisionRect().setTranslateX(0);
           player.getCollisionRect().setTranslateY(400);
           player.getSprite().setTranslateX(0);
           player.getSprite().setTranslateY(player.getCollisionRect().getTranslateY());
           gameRoot.setLayoutX(-(player.getCollisionRect().getTranslateX()));
       }
           
   }
   
   public static void main(String[] args){
       launch(args);
   }

    @Override
    public void start(Stage window){
        game = new Game();
        
        Scene scene = new Scene(game.menu.getMenuLayout(window, WIDTH, HEIGHT, game), WIDTH, HEIGHT);  
        window.setTitle(TITLE);
        window.setScene(scene);
        window.show();
    }
}
