/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.d4n73.indovinanumero.model;

import java.security.InvalidParameterException;

/**
 *
 * @author ddant
 */
public class Model {
    
    private final int NMAX = 100; // numero da indovinare massimo
    private final int TMAX = 8; // tentativi
    private int segreto; // numero segreto da indovinare
    private int tentativiFatti; // traccia dell numero dei tentativi
    private boolean inGioco = false; // vede se c'è una partita in corso

    public int getNMAX() {
        return NMAX;
    }

    public int getTMAX() {
        return TMAX;
    }

    public int getSegreto() {
        return segreto;
    }

    public int getTentativiFatti() {
        return tentativiFatti;
    }

    public boolean isInGioco() {
        return inGioco;
    }
    
    
    public void nuovaPartita(){
        this.segreto = (int) ((Math.random() * NMAX) + 1); // genera numero casuale tra 1 e varabile NMAX
        this.tentativiFatti = 0;
        this.inGioco = true;
    }
    
    public int tentativo(int tentativo){
        //restituisce: 
        // 0 vittoria
        // 1 alto
        //-1 basso 
        
        //controllo se la partita è in corso
        if(!this.inGioco){
            throw new IllegalStateException("[MESSAGGIO] La partita è finita.\n[MESSAGGIO] Hai perso... Il numero era: " + this.segreto + " Riprova\n");
        }
        
        // controllo se il numero inserito  è compreso nel range
        if(tentativo < 1 || tentativo > 100){
            throw new InvalidParameterException("[ERRORE] Il numero da inserire deve essere compreso tra 1 e 100!!\n");
        }
        
        // a queto punto il numero sarà valido
        // quindi andiamo ad incrementare i tentativi fatti
        this.tentativiFatti++;
        
        //controllo se non ho piu tentativi
        if(this.tentativiFatti == this.TMAX){
            this.inGioco = false; // finisce la partita
        }
        
        
        // controllo il tentativo inserito
        if(tentativo == this.segreto){
            this.inGioco = false; // finisce la partita
            return 0;
        }else
        if(tentativo < this.segreto){
            return -1;
        }else{
            return 1;
        }
        
        
        
    }
    
    
    
    
    
}
