package br.edu.utfpr.pb.oo24s.aula3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        VBox root = (VBox) FXMLLoader.load(
                this.getClass().getResource("/fxml/FXMLPrincipal.fxml")
                );
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/style.css");
        
        stage.setTitle("Aula JavaFX - OO24S");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
