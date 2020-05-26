/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todojavajfx;

import java.util.ArrayList;
import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class Liste extends VBox{
    private Label titolo;
    private ArrayList<Elemento> elementi;
    private ScrollPane scrollPane;
    
    public Liste(String titolo, ScrollPane scrollPane){
        this.scrollPane = scrollPane;
        this.titolo = new Label(titolo);
        this.titolo.setMinWidth(200);
        elementi = new ArrayList<>();
        
        /*elementi.add(new Elemento("Studiare", "12 novembre 2020"));
        elementi.add(new Elemento("Finire il programma", "4 aprire 2012"));
        elementi.add(new Elemento("Giocare a lol", "5 gennaio 2010"));
        elementi.add(new Elemento("Aumentare a 120 wpm", "30 dicembre 2929"));
        elementi.add(new Elemento("Mangiare se possibile", "5 settembre 2014"));*/
        
        aggiornaElementi();
        
        setSpacing(15);
        setPadding(new Insets(20, 20, 20, 20));
        setStyle("-fx-background-color: rgb(220,220,220);" + " -fx-background-radius: 5.0; ");
        setMinSize(400, 400);
    }
    
    public void rimuoviElemento(ElementoFx e){
        FadeTransition ft = new FadeTransition(Duration.millis(1000), e);
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        ft.play();
        
        ft.setOnFinished((event) -> {
            /*rimozione dell'elemento*/
            this.elementi.remove(e.getElemento());
            getChildren().remove(e);
        });
              
    }
    
    public void aggiungiElemento(Elemento e){
        getChildren().add(new ElementoFx(e, this));
    }
    
    public void aggiornaElementi(){
        for(Elemento e: elementi){
            getChildren().add(new ElementoFx(e, this));
        }
        scrollPane.setContent(this);
    }
}   

