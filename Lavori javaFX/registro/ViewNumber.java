
package registro;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class ViewNumber {
    public static Stage window;
    public static void display(Contact c, int index){
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Contatto n " + index);
        VBox root = new VBox();
        root.setSpacing(15);
        root.setPadding(new Insets(10, 50, 10, 50));
        
        Label contact = new Label("Contatto n " + index);
        contact.setPadding(new Insets(0, 0, 15, 0));
        contact.setAlignment(Pos.CENTER);
        Label name = new Label   ("Nome:    " + c.getName());
        Label surname = new Label("Cognome: " + c.getSurname());
        Label number = new Label ("Numero:  " + c.getNumber());
        
        root.getChildren().addAll(contact, name, surname, number);
        window.setScene(new Scene(root));
        window.show();
    }
}
