/**
 * Sample Skeleton for 'primary.fxml' Controller Class
 */

package com.d4n73.indovinanumero;

import com.d4n73.indovinanumero.model.Model;
import java.net.URL;
import java.security.InvalidParameterException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;


public class PrimaryController {
    
    private Model model; //il controller deve sapere chii controlla il programma
    
    //setter per impostare il modello
    public void setModel(Model model){
        this.model = model;
    }
    
    
     

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="layoutVite"
    private HBox layoutVite; // Value injected by FXMLLoader

    @FXML // fx:id="btnNuovaPartita"
    private Button btnNuovaPartita; // Value injected by FXMLLoader

    @FXML // fx:id="txtVite"
    private TextField txtVite; // Value injected by FXMLLoader

    @FXML // fx:id="txtProva"
    private TextField txtProva; // Value injected by FXMLLoader

    @FXML // fx:id="btnProva"
    private Button btnProva; // Value injected by FXMLLoader

    @FXML // fx:id="txtRisultato"
    private TextArea txtRisultato; // Value injected by FXMLLoader

    @FXML
    void handleNuovaPartita(ActionEvent event) {
        this.model.nuovaPartita();
        
        this.txtVite.setText(Integer.toString(this.model.getTMAX())); // andiamo a trasformare int in string e lo settiamo come tentativi
        this.btnNuovaPartita.setDisable(this.model.isInGioco()); // andiamo a disabilitare il bottone 
        this.btnProva.setDisable(false);
        this.txtProva.setDisable(false);
        this.txtRisultato.clear();
        
    }

    @FXML
    void handleTentativo(ActionEvent event) {
        String ts = txtProva.getText();
        
        int tentativo;
        try{
            tentativo = Integer.parseInt(ts);
        }catch(NumberFormatException e){
            txtRisultato.appendText("[ERRORE] Devi digitare un numero!\n");
            txtProva.clear();
            return;
        }
        txtProva.clear();
        
        int result;
        try{
            result = model.tentativo(tentativo);
        }catch(IllegalStateException se){
             this.txtRisultato.appendText(se.getMessage());
             this.btnNuovaPartita.setDisable(this.model.isInGioco());
             this.btnProva.setDisable(!(this.model.isInGioco()));
             this.txtProva.setDisable(!(this.model.isInGioco()));
             return;
        }catch(InvalidParameterException pe){
             this.txtRisultato.appendText(pe.getMessage());
             return;
        }
        
        
        
       
        this.txtVite.setText(Integer.toString(this.model.getTMAX() - this.model.getTentativiFatti()));
        
        if(result == 0){
            txtRisultato.appendText("[VITTORIA] Hai indovinato il numero con " + this.model.getTentativiFatti() + " tentativi!!\n");
            this.btnNuovaPartita.setDisable(this.model.isInGioco());
             this.layoutVite.setDisable(!(this.model.isInGioco()));

        }else if(result < 0){
            txtRisultato.appendText("[MESSAGGIO] Il numero è piu alto del numero inserito.(" + tentativo + ")\n");
        }else{
             txtRisultato.appendText("[MESSAGGIO] Il numero è piu basso del numero inserito.(" + tentativo + ")\n");
        }

        
        
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert layoutVite != null : "fx:id=\"layoutVite\" was not injected: check your FXML file 'primary.fxml'.";
        assert btnNuovaPartita != null : "fx:id=\"btnNuovaPartita\" was not injected: check your FXML file 'primary.fxml'.";
        assert txtVite != null : "fx:id=\"txtVite\" was not injected: check your FXML file 'primary.fxml'.";
        assert txtProva != null : "fx:id=\"txtProva\" was not injected: check your FXML file 'primary.fxml'.";
        assert btnProva != null : "fx:id=\"btnProva\" was not injected: check your FXML file 'primary.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'primary.fxml'.";

    }
}
