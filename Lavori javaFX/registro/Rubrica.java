package registro;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Rubrica extends Application {
    public static ArrayList<Contact> contacts = new ArrayList<>();
    public static FileOutputStream out;
    public static ObjectOutputStream oor;
    public static FileInputStream in;
    public static ObjectInputStream ois;
    public static String file;
    
    @Override
    public void start(Stage primaryStage) {
             
        Button btn = new Button();
        Label label = new Label("Seleziona il file da aprire");
        btn.setText("Apri");
        btn.setMinWidth(100);
        btn.setOnAction((ActionEvent event) -> {
            openFile();
            primaryStage.setScene(new Scene(Home.Home(primaryStage)));
        });
        
        VBox root = new VBox();
        root.setSpacing(15);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(label, btn);
        root.setPadding(new Insets(50, 10, 50, 10));
        
        Scene scene = new Scene(root, 250, 100);
        
        primaryStage.setTitle("Rubrica");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    public static void openFile(){
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);

        if(selectedFile != null){
            file = selectedFile.getAbsolutePath();
            System.err.println(file);
            try{
                /*lettura da file dei contatti*/
                in = new FileInputStream(file);
                ois = new ObjectInputStream(in);
                contacts = BinaryWriteRead.ReadContacts(ois);
                System.out.println(contacts.size());
                for(Contact c: contacts){
                    System.out.println(c.getName());
                    System.out.println(c.getSurname());
                }
            }catch(IOException e){
                System.out.println(e);
            }
        }
        else{
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Errore nell'apertura del file");
                alert.setContentText("Provare ad aprire il file nuovamente");
                alert.showAndWait();
            }
    }
}