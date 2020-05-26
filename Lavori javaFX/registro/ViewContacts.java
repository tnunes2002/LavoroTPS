
package registro;

import com.sun.corba.se.spi.ior.WriteContents;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


class ViewContacts {
    private static Stage window;
    private static ListView contactList;
    private static int index = 0;
    
    public static void display(ArrayList<Contact> contacts){
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        HBox root = new HBox();
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setSpacing(15);
        
        contactList = new ListView();
        contactList.setPrefHeight(200);
        contactList.getSelectionModel().selectedItemProperty().addListener((observable) -> {
            index = contactList.getSelectionModel().getSelectedIndex();
        });
        
        for(Contact c : contacts){
            contactList.getItems().add(c.getName() + " " + c.getSurname());
        }
        
        VBox vbox = new VBox();
        vbox.setSpacing(15);
        vbox.setPadding(new Insets(10, 50, 10, 10));
        vbox.setAlignment(Pos.CENTER);
        
        Button view = new Button();
        view.setMinWidth(120);
        view.setText("Visualizza");
        view.setOnAction((event) -> {
            ViewNumber.display(contacts.get(index), index + 1);
        });
        
        Button edit = new Button();
        edit.setMinWidth(120);
        edit.setText("Modifica");
        edit.setOnAction((event) -> {
            EditContact.display(contacts.get(index));
            update();
        });
        
        Button add = new Button();
        add.setPrefWidth(120);
        add.setText("Aggiungi contatto");
        add.setOnAction((event) -> {
            AddContact.display();
            ViewContacts.writeContacts();
            update();
        });
        
        Button remove = new Button();
        remove.setMinWidth(120);
        remove.setText("Rimuovi contatto");
        remove.setOnAction((event) -> {
            Rubrica.contacts.remove(index);
            update();
            writeContacts(); 
        });
        
        Button back = new Button();
        back.setText("Indietro");
        back.setPrefWidth(120);
        back.setOnAction((event) -> {
            window.close();
        });
        
        vbox.getChildren().addAll(view, edit, add, remove, back);
        
        root.getChildren().addAll(contactList, vbox);
        
        window.setScene(new Scene(root));
        window.setTitle("Lista di contatti");
        window.show();
    }
    
    public static void writeContacts(){
        try{
            Rubrica.out = new FileOutputStream(Rubrica.file);
            Rubrica.oor = new ObjectOutputStream(Rubrica.out);
        }catch(IOException e){
            System.out.println(e);
        }

        for(Contact c: Rubrica.contacts){
            BinaryWriteRead.WriteContact(Rubrica.oor, c);
        }
        try{
            Rubrica.out.close();
        Rubrica.oor.close();
        }catch(IOException e){
            System.err.println(e);
        }  
    }
    
    public static void update(){
        contactList.getItems().clear();
        
        for(Contact c : Rubrica.contacts){
            contactList.getItems().add(c.getName() + " " + c.getSurname());
        }       
    }
}
