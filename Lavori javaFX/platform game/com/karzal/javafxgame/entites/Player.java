
package com.karzal.javafxgame.entites;

import com.karzal.javafx.graphics.SpriteAnimation;
import com.karzal.javafxgame.window.Game;
import java.util.HashMap;
import javafx.animation.Animation;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Player {
    private final int height;
    private final int width;
    private int x, y;
    
    private final Rectangle collision;
    private final ImageView imageView;
    public SpriteAnimation animation;
    
    private boolean canJump = true;
    private boolean facingRight = true;
    private boolean isTouchingGround =  true;
    private int yVel = 0;
       
    public Player(
            int height,
            int width, 
            Image image,
            Pane gameRoot){
        this.height = height;
        this.width = width;
        this.imageView = new ImageView(image);
        imageView.setFitHeight(128);
        imageView.setFitWidth(80);
        animation = new SpriteAnimation(
                imageView,
                Duration.millis(1000),
                4, 4,
                0, 30,
                63, 110
        );
        
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();
        collision = new Rectangle(width, height);
        collision.setStroke(Color.GREEN);
        collision.setFill(Color.TRANSPARENT);
        //collision.setVisible(false);
        gameRoot.getChildren().addAll(collision, imageView);
    }
    
    public ImageView getSprite(){
        return imageView;
    }
    
    public Rectangle getCollisionRect(){
        return collision;
    }
    
    public int getWidth(){
        return this.width;
    }
    
    public int getHeight(){
        return this.height;
    }
    
    public void update(HashMap<KeyCode, Boolean> keys){
       if(isPressed(KeyCode.W, keys) && getCollisionRect().getTranslateY() >= 5){
           if(canJump){
              //animation.stop();
              canJump = false;
              isTouchingGround = false;
              animation.play();
              jump();
           }   
       }    
       if(isPressed(KeyCode.A, keys) && getCollisionRect().getTranslateX() >= 5){
           facingRight = false;
           
           animation.offsetY = 28 + 128;
           animation.play();
           getSprite().setScaleX(-1);
           movePlayerX(-5);
       }
       if(isPressed(KeyCode.D, keys) && getCollisionRect().getTranslateX() + 64 <= Game.levelWidth - 5){
           facingRight = true;
           
           animation.offsetY = 28 + 128;
           animation.play();
           getSprite().setScaleX(1);
           movePlayerX(5);
       }
       
       if(isTouchingGround && (!isPressed(KeyCode.D, keys) && !isPressed(KeyCode.W, keys) && !isPressed(KeyCode.A, keys))){
            animation.offsetY = 30;
       }
           
       if(yVel < 20){
           yVel += 1;
       }
       
       movePlayerY((int)yVel);
    }
    
   public boolean isPressed(KeyCode k, HashMap<KeyCode, Boolean> keys){
       return keys.getOrDefault(k, false);
   }
   
   private void jump(){
       yVel = -25;
   }
   public void movePlayerY(int value){
       boolean movingDown = value > 0;
       
       for(int i = 0; i < Math.abs(value); i++){
           for(Node platform: Game.platforms){
               if(getCollisionRect().getBoundsInParent().intersects(platform.getBoundsInParent())){
                   if(movingDown){
                       if(getCollisionRect().getTranslateY() + getHeight() == platform.getTranslateY()){
                           getCollisionRect().setTranslateY(getCollisionRect().getTranslateY() -1);
                           canJump = true;
                           isTouchingGround = true;
                           return;
                       }                
                       
                   }else{          
                       if(getCollisionRect().getTranslateY() == platform.getTranslateY() + Tile.TILE_SIZE) {
                           yVel = 0;
                           return;
                       }
                   }
               }    
           }         
           getCollisionRect().setTranslateY(getCollisionRect().getTranslateY() +(movingDown ? 1: -1));
           getSprite().setTranslateY(getCollisionRect().getTranslateY() - 15);
       }
       
       if(movingDown){
           canJump = false;
           isTouchingGround = false;
           
           //animation.offsetX = 0;
           //animation.offsetY = 270;
       }
           
   }
   
   public void movePlayerX(int value){
       boolean movingRight = value > 0;
       
       for(int i = 0; i < Math.abs(value); i++){
           for(Node platform: Game.platforms){
               if(getCollisionRect().getBoundsInParent().intersects(platform.getBoundsInParent())){
                   if(movingRight){
                       if(getCollisionRect().getTranslateX() + getWidth() == platform.getTranslateX()) return;
                       
                   }else{
                       if(getCollisionRect().getTranslateX() == platform.getTranslateX() + Tile.TILE_SIZE) return;
                   }
               }    
           }
           getCollisionRect().setTranslateX(getCollisionRect().getTranslateX() +(movingRight ? 1.5: -1.5));
           if(facingRight)
                getSprite().setTranslateX(getCollisionRect().getTranslateX());
           else
                getSprite().setTranslateX(getCollisionRect().getTranslateX() - 32);

       }
   }
   
}
