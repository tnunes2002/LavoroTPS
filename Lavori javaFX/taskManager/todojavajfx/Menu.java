/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todojavajfx;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class Menu extends TabPane{
    public static Liste toDo;
    public static Liste doing;
    public static Liste done;
    
    public Menu(){
        setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
         
        /*tab delle cose da fare*/
        ScrollPane scrolltoDo = new ScrollPane();
        scrolltoDo.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        toDo = new Liste("Da fare", scrolltoDo);
        scrolltoDo.setContent(toDo); 
        scrolltoDo.setFitToWidth(true);
        
        /*tab delle cose in corso*/
        ScrollPane scrolltoDoing = new ScrollPane();
        doing = new Liste("In corso", scrolltoDoing);
        scrolltoDoing.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrolltoDoing.setContent(doing);
        scrolltoDoing.setFitToWidth(true);
        
        /*tab delle cose già fatte*/        
        ScrollPane scrolltoDone = new ScrollPane();
        scrolltoDone.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        done = new Liste("Fatto", scrolltoDone);
        scrolltoDone.setContent(done);
        scrolltoDone.setFitToWidth(true);
        
        Aggiungi add = new Aggiungi();
        
        /*aggiungo le mie liste nelle tab*/
        Tab daFare = new Tab("Da fare");
        daFare.setContent(scrolltoDo);
        Tab inCorso = new Tab("In corso");
        inCorso.setContent(scrolltoDoing);
        Tab fatto = new Tab("Fatto");
        fatto.setContent(scrolltoDone);
        Tab aggiungi = new Tab("+");
        aggiungi.setContent(add);
        
        /*stile*/
        getStyleClass().add("floating");
        setStyle("-fx-background-color: rgb(255,99,71);" );
        daFare.setOnSelectionChanged((event) -> {
            if(daFare.isSelected())
                setStyle("-fx-background-color: rgb(240 , 20,0);" );
        });
        inCorso.setOnSelectionChanged((event) -> {
            if(inCorso.isSelected())
                setStyle("-fx-background-color: rgb(255,99,71);" );
        });
        fatto.setOnSelectionChanged((event) -> {
            if(fatto.isSelected())
                setStyle("-fx-background-color: rgb(0,99,71);" );
        });
        aggiungi.setOnSelectionChanged((event) -> {
            if(aggiungi.isSelected()){
               setStyle("-fx-background-color: rgb(215,215,215);" ); 
            }
        });
        
        getTabs().addAll(daFare, inCorso, fatto, aggiungi);
    }
}
