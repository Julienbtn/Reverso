package jeu.core;

// Un pion est juste blanc ou pas blanc
public class Pion {
    private boolean blanc;

    public Pion(boolean blanc){
        this.blanc = blanc;
    }
    
    public boolean isBlanc(){
        return blanc;
    }
}
