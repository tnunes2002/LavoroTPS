package todojavajfx;

import java.io.File;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ElementoFx extends HBox{
    private Elemento elemento;
    private Testo testo;
    private VBox layout;
    private ImageView settings;
    private Label delte;
    private Liste lista;
    
    public ElementoFx(Elemento elemento, Liste lista){
        /*style*/
        setMinHeight(70);
        setPadding(new Insets(10, 0, 0, 0));
        setStyle("-fx-background-color: rgb(255,255,255);" + " -fx-background-radius: 5.0;");
        this.lista = lista; 
        
        /*testo*/
        this.elemento = elemento;
        testo = new Testo(elemento.getTesto(), elemento.getData());     
        testo.setMinWidth(300);
           
        /*inizializzo il tasto impostazioni*/
        settings = new ImageView();
        File settingsFile = new File(System.getProperty("user.dir") + "\\src\\res\\settings.png");
        Image settingsImage = new Image(settingsFile.toURI().toString());
        settings.setImage(settingsImage);
        settings.setFitHeight(30);
        settings.setFitWidth(30);
        
        /*inizializzo la x per eliminare il promemoria*/
        delte = new Label("X");
        delte.setFont(new Font(14));
        delte.setTextFill(Color.GREY);
        delte.setVisible(false);
        delte.setOnMouseClicked((event) -> {
            rimuoviElemento();
        });
        
        /*inizializzo il layout con l'immagine e la X*/
        layout = new VBox();
        layout.getChildren().add(delte);
        layout.setAlignment(Pos.TOP_RIGHT);
        layout.getChildren().add(settings);
        layout.setPadding(new Insets(0, 10, 0, 0));
        layout.setSpacing(5);
      
        /*gestione degli eventi collegati al mouse*/
        setOnMouseEntered((event) -> {
            delte.setVisible(true);
            testo.editTextSize(14);
            setPadding(new Insets(15, 0, 0, 0));
            setMinWidth(300);
            setMinHeight(80);
        });
        setOnMouseExited((event) -> {
            delte.setVisible(false);
            testo.editTextSize(11);
            setPadding(new Insets(10, 0, 0, 0));
            setMaxWidth(370);
            setMinHeight(70);
        });
        
        /*gestione del click sulla freccia*/
        settings.setOnMouseClicked((event) -> {
            System.out.println("CIAO");
            PopUp.Display(this);
        });
        
        setAlignment(Pos.CENTER);
        getChildren().addAll(testo, layout);
    }
    
    public Elemento getElemento(){
        return this.elemento;
    }
    
    public void rimuoviElemento(){
        lista.rimuoviElemento(this);
    }
}
