package jeu.core;

/**
 * Un pion est juste blanc ou pas blanc, utilisé par CaseR
 * 
 * @see CaseR
 */
public class Pion {
    /**
     * Couleur du pion
     */
    private boolean blanc;
    
    /**
     * Crée un pion de la couleur indiquée
     * 
     * @param blanc true si pion blanc, false si pion noir
     */
    public Pion(boolean blanc){
        this.blanc = blanc;
    }
    
    /**
     * Indique si le pion est blanc
     * 
     * @return true si le pion est blanc, false sinon
     */
    public boolean isBlanc(){
        return blanc;
    }
}
