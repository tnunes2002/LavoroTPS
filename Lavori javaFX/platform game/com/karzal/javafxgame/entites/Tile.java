package com.karzal.javafxgame.entites;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Tile{
    public static final int TILE_SIZE = 64;
    
    private final ImageView image;
    private int x;
    private int y;
    
    public Tile(Image image, int x, int y, Pane gameRoot){
        this.image =  new ImageView(image);
        this.image.setFitWidth(TILE_SIZE);
        this.image.setFitHeight(TILE_SIZE);
        this.x = x;
        this.y = y;
        this.image.setTranslateX(x);
        this.image.setTranslateY(y);
        
        gameRoot.getChildren().add(this.image);
    }
}
