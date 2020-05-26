/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karzal.javafxgame.menu;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.VBox;


public class MenuBox extends Pane{
    
    private VBox box;
    
    public MenuBox(int width, int height){
        Rectangle bg = new Rectangle(width, height);
        bg.setFill(Colors.MENU_BG);
        
        box = new VBox(3);
        box.setTranslateX(470);
        box.setTranslateY(300);
        box.setSpacing(20);
        
        getChildren().addAll(bg, box);
    }
    
    public void addItems(MenuItem... items){
        for(MenuItem item: items){
            addItem(item);
        }
    }
    
    public void addItem(MenuItem item){
        box.getChildren().add(item);
    }
}
