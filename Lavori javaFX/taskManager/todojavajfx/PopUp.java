
package todojavajfx;

import java.io.File;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class PopUp {
    private final static Button daFare;
    private final static Button inCorso;
    private final static Button fatto;
    private final static Stage stage;
    private static VBox layout;
    private static HBox layoutBottoni;
    
    static{
        stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        
        /*Icona dello stage*/
        File programIcon = new File(System.getProperty("user.dir") + "\\src\\res\\logo.png");
        Image applicationIcon = new Image(programIcon.toURI().toString());
        stage.getIcons().add(applicationIcon);
       
        daFare = new Button("Da fare");
        daFare.setMinWidth(60);
        inCorso = new Button("In corso");
        inCorso.setMinWidth(60);
        fatto = new Button("Fatto");
        fatto.setMinWidth(60);
    }
    
    public static void Display(ElementoFx e){
        Label testo = new Label();
        testo.setText(e.getElemento().getTesto());
        layout = new VBox();
        layout.setSpacing(20);
        layout.setAlignment(Pos.CENTER);
        layoutBottoni = new HBox();
        
        layoutBottoni.getChildren().addAll(daFare, inCorso, fatto);
        layout.getChildren().addAll(layoutBottoni);
        layout.getChildren().add(testo);
        //layoutBottoni.setStyle("-fx-background-color: rgb(255,255,255);");
        layoutBottoni.setAlignment(Pos.CENTER);
        layoutBottoni.setSpacing(15);
        
        daFare.setOnAction((event) -> {
            e.rimuoviElemento();
            Menu.toDo.aggiungiElemento(e.getElemento());
            stage.close();
        });
        inCorso.setOnAction((event) -> {
            e.rimuoviElemento();
            Menu.doing.aggiungiElemento(e.getElemento());
            stage.close();
        });
        fatto.setOnAction((event) -> {
            e.rimuoviElemento();
            Menu.done.aggiungiElemento(e.getElemento());
            stage.close();
        });
        
        stage.setScene(new Scene(layout, 250, 100));
        stage.show();
    }
}
