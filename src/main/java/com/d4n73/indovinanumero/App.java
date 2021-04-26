package com.d4n73.indovinanumero;

import java.io.IOException;

import com.d4n73.indovinanumero.model.Model;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.image.Image;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static PrimaryController controller; // creaiamo un raferance al controller 
   
    @Override
    public void start(Stage stage) throws IOException {
        
        //istanza oggetto del modello
        Model model = new Model();
        
        scene = new Scene(loadFXML("primary"));
        
        // di standard non possiamo perche non abbiamo un referance al un controller
        controller.setModel(model);
        
        stage.setScene(scene);
        
        stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        stage.setTitle("Indovina il Numero");
        
        stage.show(); // apri l app (sipario)
        
        
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        // andiamo a modificare il metodo in modo da avere un referance per il controoller
        Parent risultato; 
        
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        //return fxmlLoader.load(); 
        risultato = fxmlLoader.load(); // andiamo a mettere un raferance in risultato
        
        controller = fxmlLoader.getController(); // consente di accedere al raference del controller 
        
        return risultato;
    }

    public static void main(String[] args) {
        launch();
    }

}