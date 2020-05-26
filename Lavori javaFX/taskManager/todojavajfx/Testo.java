/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todojavajfx;

import java.io.File;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Testo extends VBox{
    private Label testo;
    private Label scadenza;
    private ImageView scadenzaImg;
    private HBox scadenzaLayout; //contiene sia il testo che l'immagine della scadenza

    public Testo(String testo, String scadenza){
        setSpacing(20);
        
        /*iniziallizzo il testo*/
        this.testo = new Label(testo);
        this.testo.setFont(new Font(11));
        
        /*iniziallizzo la data di scadenza*/
        this.scadenza = new Label(scadenza);
        this.scadenza.setFont(new Font(9));
        this.scadenza.setTextFill(Color.GREY);
        
        /*iniziallizzo l'immagine di scadenza*/
        File scadenzaFile = new File(System.getProperty("user.dir") + "\\src\\res\\clock.png");
        Image scadenzaImage = new Image(scadenzaFile.toURI().toString());
        scadenzaImg = new ImageView(scadenzaImage);
        scadenzaImg.setFitWidth(15);
        scadenzaImg.setFitHeight(15);
        
        /*aggiungo la scadenza e l'immagine di scadenza al HBox*/
        scadenzaLayout = new HBox();
        scadenzaLayout.setSpacing(15);
        scadenzaLayout.getChildren().addAll(this.scadenza, scadenzaImg);
        
        getChildren().addAll(this.testo, this.scadenzaLayout);
    }
    
    public void editTextSize(int size){
        this.testo.setFont(new Font(size));
    }
}
