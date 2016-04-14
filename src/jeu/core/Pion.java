package jeu.core;


public class Pion {
    private boolean blanc;

    public Pion(boolean blanc){
        this.blanc = blanc;
    }
    
    public boolean isBlanc(){
        return blanc;
    }
}
