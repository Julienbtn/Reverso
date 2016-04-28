package jeu.core;

import jeu.Case;

public class CaseR implements Case{
    private boolean jouable;
    private Pion contenu;
    
    // Constructeur case vide, de base non jouable et sans contenu
    public CaseR(){
        jouable = false;
        contenu = null;
    }
    
    // Constructeur d'une case avec un pion
    public CaseR(boolean blanc){
        jouable = false;
        contenu = new Pion(blanc);
    }

    public void setJouable(boolean jouable) {
        this.jouable = jouable;
    }
    
    public boolean isJouable() {
        return jouable;
    }
    
    public boolean isVide(){
        return contenu ==null;
    }
        
    public boolean isBlanc(){
        return (!isVide() && contenu.isBlanc());
    }
    
    public boolean isNoir(){
        return (!isVide() && !contenu.isBlanc());
    }
    
    // Renvoie vrai si la case appartient au joueur passé en argument,
    // faux sinon (si elle est vide ou à l'adversaire)
    public boolean isBool(boolean joueur){
        if(joueur)
            return isBlanc();
        else
            return isNoir();
    }
    
    public void jouer(boolean blanc){
        contenu = new Pion(blanc);
    }
    
    // Copie la case
    public CaseR copieCase(){
        CaseR copie;
        if(contenu == null)
            copie = new CaseR();
        else
            copie = new CaseR(contenu.isBlanc());
        copie.setJouable(jouable);
        return copie;
    }
    
    
    // Implémentation interface IA !
    
    public boolean remplie(){
        return !isVide();
    }
    public boolean blanche(){
        return isBlanc();
    }
    public boolean jouable(){
        return isJouable();
    }
}
	

