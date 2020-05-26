package todojavajfx;

import java.io.File;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ToDoJavajfx extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        /*TabPane*/
        Menu root = new Menu();
        
        /*Icona dello stage*/
        File programIcon = new File(System.getProperty("user.dir") + "\\src\\res\\logo.png");
        Image applicationIcon = new Image(programIcon.toURI().toString());
        primaryStage.getIcons().add(applicationIcon);
        
        /*Impostazioni dello Stage*/
        Scene scene = new Scene(root, 400, 400);        
        primaryStage.setTitle("To do");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
