package jeu.core;

import jeu.Case;

public class CaseR implements Case{
    private boolean jouable;
    private Pion contenu;
    
    public CaseR(){
        jouable = false;
        contenu = null;
    }
    
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
        if(contenu ==null)
            return true;
        else
            return false;
    }
        
    public boolean isBlanc(){
        return (!isVide() && contenu.isBlanc());
    }
    
    public boolean isNoir(){
        return (!isVide() && !contenu.isBlanc());
    }
    
    public boolean isBool(boolean joueur){
        if(joueur)
            return isBlanc();
        else
            return isNoir();
    }
    
    public void jouer(boolean blanc){
        contenu = new Pion(blanc);
    }
    
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
	

