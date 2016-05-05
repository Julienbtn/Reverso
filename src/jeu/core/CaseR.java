package jeu.core;

import jeu.Case;

/**
 * Implémentation de l'interface Case pour Reverso
 * 
 * @see Case
 */
public class CaseR implements Case{
    /**
     * Indique si la case peut être jouée pour le coup suivant
     */
    private boolean jouable;
    /**
     * Pion contenu dans la case s'il y en a un, indique pricipalement la couleur de la case
     */
    private Pion contenu;
    
    
    /**
     * Constructeur case vide, de base non jouable et sans contenu
     */
    public CaseR(){
        jouable = false;
        contenu = null;
    }
    
    /**
     * Constructeur d'une case avec un pion 
     * 
     * @param blanc true si le pion dans la case doit être blanc, false sinon
     */
    public CaseR(boolean blanc){
        jouable = false;
        contenu = new Pion(blanc);
    }

    /**
     * rend la case jouable ou non
     * @param jouable true si la case est jouable, false sinon
     */
    public void setJouable(boolean jouable) {
        this.jouable = jouable;
    }
    
    /**
     * Indique si la case est jouable
     * 
     * @return true si la case est jouable, false sinon 
     */
    public boolean isJouable() {
        return jouable;
    }
    
    /**
     * Indique si la case est vide
     * 
     * @return true si la case est vide, false sinon 
     */
    public boolean isVide(){
        return contenu ==null;
    }
    
    /**
     * Indique si la case contient un pion blanc
     * 
     * @return true si la case contient un pion blanc, false sinon 
     */
    public boolean isBlanc(){
        return (!isVide() && contenu.isBlanc());
    }
    
    /**
     * Indique si la case contient un pion noir
     * 
     * @return true si la case contient un pion noir, false sinon 
     */
    public boolean isNoir(){
        return (!isVide() && !contenu.isBlanc());
    }
    

    /**
     * Indique si la case contient un pion du joueur indiqué
     * 
     * @param joueur true si on suppose que la case contient un pion blanc, 
     * false si on le suppose noir
     * @return true si la case contient un pion du joueur indiqué 
     */
    public boolean isBool(boolean joueur){
        if(joueur)
            return isBlanc();
        else
            return isNoir();
    }
    
    /**
     * Change/ajoute le pion contenu dans la case.
     * 
     * @param blanc indique si le pion doit appartenir au joueur blanc (true) 
     * ou noir (false)
     */
    public void jouer(boolean blanc){
        contenu = new Pion(blanc);
    }
    
    
    /**
     * Copie la case
     * 
     * @return copie exacte de la case et de son contenu
     */
    public CaseR copieCase(){
        CaseR copie;
        if(contenu == null)
            copie = new CaseR();
        else
            copie = new CaseR(contenu.isBlanc());
        copie.setJouable(jouable);
        return copie;
    }
    
    
    // Implémentation interface Case !
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remplie(){
        return !isVide();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean blanche(){
        return isBlanc();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean jouable(){
        return isJouable();
    }
}
	

