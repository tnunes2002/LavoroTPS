
package registro;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


class AddContact {
    public static Stage window;
    public static void display(){
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Aggiungi contatto");
        VBox root = new VBox();
        root.setPadding(new Insets(10, 50, 10, 50));
        root.setSpacing(15);
        
        Label nameLabel = new Label("Nome:");
        Label surnameLabel = new Label("Cognome:");
        Label numberLabel = new Label("Numero:");
        
        TextField name = new TextField();
        
        TextField surname = new TextField();
        
        TextField number = new TextField();
        
        Button submit = new Button();
        submit.setText("Aggiungi");
        submit.setMinWidth(150);
        submit.setOnAction((event) -> {
            if(!name.getText().isEmpty() && !surname.getText().isEmpty() && !name.getText().isEmpty()){
                addContact(new Contact(number.getText(), name.getText(), surname.getText()));  
            }  
            window.close();
        });
        
        root.getChildren().addAll(nameLabel, name, surnameLabel, surname, numberLabel, number, submit);
        window.setScene(new Scene(root));
        window.showAndWait();       
    }
    
    public static void addContact(Contact c){
        Rubrica.contacts.add(c);
    }
}
