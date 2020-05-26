package todojavajfx;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Aggiungi extends VBox{
    private final Label testo;
    private final Label data;
    private final Label daFare;
    private final Label inCorso;
    private final Label fatto;
    
    private TextField testoTF;
    private DatePicker dataDP;
    
    private final CheckBox daFareCB;
    private final CheckBox inCorsoCB;
    private final CheckBox fattoCB;
    private Button aggiungiBottone;
    
    public Aggiungi(){
        setSpacing(30);
        setAlignment(Pos.CENTER);
        
        testo = new Label();
        testo.setText("Testo:");
        data = new Label();
        data.setText("Data:");
        daFare = new Label();
        daFare.setText("da fare");
        inCorso = new Label();
        inCorso.setText("In corso");
        fatto = new Label();
        fatto.setText("Fatto");
        
        testoTF = new TextField();
        testoTF.setMinWidth(170);
        dataDP = new DatePicker();
        daFareCB = new CheckBox();
        inCorsoCB = new CheckBox();
        fattoCB = new CheckBox();
        aggiungiBottone = new Button("Aggiungi");
        
        HBox layoutCB = new HBox();
        layoutCB.setSpacing(15);
        layoutCB.getChildren().addAll(new VBox(daFare, daFareCB), 
                        new VBox(inCorso, inCorsoCB), 
                        new VBox(fatto, fattoCB));
        layoutCB.setAlignment(Pos.CENTER);
        
        HBox layoutTesto = new HBox();
        layoutTesto.setSpacing(10);
        layoutTesto.setAlignment(Pos.CENTER);
        layoutTesto.getChildren().addAll(testo, testoTF);
        
        HBox layoutData = new HBox();
        layoutData.setSpacing(10);
        layoutData.setAlignment(Pos.CENTER);
        layoutData.getChildren().addAll(data, dataDP);
        
        getChildren().addAll(
                layoutTesto, 
                layoutData, 
                layoutCB,
                aggiungiBottone);
        
        aggiungiBottone.setOnAction((event) -> {
            if(daFareCB.isSelected()){
                Menu.toDo.aggiungiElemento(new Elemento(testoTF.getText(), dataDP.getValue().toString()));
            }
            if(inCorsoCB.isSelected()){
               Menu.doing.aggiungiElemento(new Elemento(testoTF.getText(), dataDP.getValue().toString())); 
            }
            if(fattoCB.isSelected()){
                Menu.done.aggiungiElemento(new Elemento(testoTF.getText(), dataDP.getValue().toString()));
            }
            
            reset();
        });
    }
    
    private void reset(){
        testoTF.setText("");
        daFareCB.setSelected(false);
        inCorsoCB.setSelected(false);
        fattoCB.setSelected(false);
    }
}
