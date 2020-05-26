/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karzal.javafxgame.menu;

import com.karzal.javafxgame.window.Game;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Menu{
    public Pane getMenuLayout(Stage window, int width, int height, Game game){
        Pane root = new Pane();
        MenuBox menuBox = new MenuBox(width, height);
        
        MenuItem play = new MenuItem("PLAY", 250);
        play.setOnAction(() -> {
            game.init(window);
            game.setPaused(false);
        });
        
        menuBox.addItem(play);
     
        
        menuBox.addItem(new MenuItem("OPTIONS", 250));
        
        MenuItem exit = new MenuItem("EXIT", 250);
        exit.setOnAction(() -> {
            window.close();
            System.exit(0);
        });
        menuBox.addItem(exit);
        
        root.getChildren().addAll(menuBox);
        
        return root;
    }
    
    public void startGame(){
        
    }
    
}
