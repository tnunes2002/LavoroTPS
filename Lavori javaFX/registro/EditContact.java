package registro;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EditContact {
    public static Stage window;
    public static void display(Contact contact){
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Modifica contatto");
        VBox root = new VBox();
        root.setPadding(new Insets(10, 50, 10, 50));
        root.setSpacing(15);
        
        Label nameLabel = new Label("Nome:");
        Label surnameLabel = new Label("Cognome:");
        Label numberLabel = new Label("Numero:");
        
        TextField name = new TextField();
        name.setPromptText(contact.getName());
        
        TextField surname = new TextField();
        surname.setPromptText(contact.getSurname());
        
        TextField number = new TextField();
        number.setPromptText(contact.getNumber());
        
        Button submit = new Button();
        submit.setText("Modifica");
        submit.setMinWidth(150);
        submit.setOnAction((event) -> {
            if(!name.getText().isEmpty())
                contact.setName(name.getText());
            
            if(!surname.getText().isEmpty())
                contact.setSurname(surname.getText());
            
            if(!number.getText().isEmpty())
                contact.setNumber(number.getText());
            
            window.close();
        });
        
        root.getChildren().addAll(nameLabel, name, surnameLabel, surname, numberLabel, number, submit);
        window.setScene(new Scene(root));
        window.showAndWait();
        
        ViewContacts.writeContacts();
    }
}
