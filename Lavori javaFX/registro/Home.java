/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registro;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Home {
    public static VBox Home(Stage window){
        VBox root = new VBox();
        Button view = new Button("Visualizza Contatti");
        view.setPrefWidth(150);
        view.setOnAction((event) -> {
            ViewContacts.display(Rubrica.contacts);
        });
        
        Button add = new Button("Cambia rubrica");
        add.setPrefWidth(150);
        add.setOnAction((event) -> {
            Rubrica.openFile();
        });
        
        root.setSpacing(15);
        root.setPadding(new Insets(50, 10, 50, 10));
        root.setAlignment(Pos.CENTER);
        root.setPrefWidth(250);
        root.getChildren().addAll(view, add);
        
        return root;
    }
}
