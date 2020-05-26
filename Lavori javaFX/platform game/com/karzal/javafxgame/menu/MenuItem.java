
package com.karzal.javafxgame.menu;

import javafx.util.Duration;
import javafx.animation.FillTransition;
import javafx.geometry.Pos;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


public class MenuItem extends StackPane{
    private Text text;
    private Rectangle selection;
    
    public MenuItem(String name, int width){
        setAlignment(Pos.CENTER);
        
        text = new Text(name);
        text.setScaleX(2);
        text.setScaleY(2);
        text.setFill(Colors.TEXT_COLOR);
        text.setTranslateX(5);
          
        selection = new Rectangle(width - 45, 30);
        selection.setFill(Colors.SELECTED_BG_COLOR);
        selection.setStroke(Color.BLACK);
        selection.setVisible(false);
        
        GaussianBlur blur = new GaussianBlur(5);
        selection.setEffect(blur);
        
        getChildren().addAll(selection, text);
        
        setOnMouseEntered(e -> {
            onSelect();
        });

        setOnMouseExited(e -> {
            onDeselect();
        });

        setOnMousePressed(e -> {
            text.setFill(Colors.PRESSED_TEXT_COLOR);
        });
    }

    private void onSelect() {
        text.setFill(Color.BLACK);
        selection.setVisible(true);
    }

    private void onDeselect() {
        text.setFill(Color.WHITE);
        selection.setVisible(false);    
    }

    public void setOnAction(Runnable action) {
        setOnMouseClicked(e -> {
            FillTransition ft = new FillTransition(Duration.seconds(0.45), selection,
                    Colors.SELECTED_BG_COLOR, Colors.MENU_BG);
            ft.setOnFinished(e2 -> action.run());
            ft.play();
        });
    }
}
