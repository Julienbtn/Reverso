package jeu;

/**
 * Interface du jeu Reverso
 * 
 */
public interface Plateau {


    /**
     * Renvoie copie du plateau
     * 
     * @return une à l'identique du plateau et de tous ses éléments
     */
    public Plateau copie();
    
     
    /**
     * Tableau contenant les cases du tableau (ligne par ligne)
     * 
     * @return tableau des cases du damier avec tous les pions dedans
     */
    public Case[] getDamier();
    

    /**
     * Tente de jouer à la case indiquée
     * 
     * <p>Gère le cas où l'on tente de jouer dans une case où il n'est pas possible
     * de jouer, met à jour l'état du jeu (tour suivant, passe si besoin, cases 
     * libres, etc)</p>
     * @param idCase identifiant de la case à jouer
     */
    public void jouer(int idCase);
    
    // 
    /**
     * Indique si le tour est au joueur blanc
     * 
     * @return true si c'est au joueur blanc de jouer
     */
    public boolean tourBlanc();
    
    /** 
     * indique si un joueur vient de passer son tour
     * 
     * @return true si le dernier joueur vient de passer son tour
     */
    public boolean passe();
    


    /**
     * Donne le score actuel des blancs
     * @return score des blancs
     */
    public int scoreBlanc();
    
    /**
     * Retourne le score actuel des noirs
     * 
     * @return score des noirs
     */
    public int scoreNoir();
    
    /** 
     * indique si le joueur blanc a gagné
     * 
     * @return true si les blancs ont gagné
     */
    public boolean victoireBlanc();
    

    /**
     * indique si jeu est terminé
     * 
     * @return true si le jeu est terminé
     */
    public boolean termine();
    

    /**
     * Renvoie le nombre de cases vides restantes sur le damier
     * 
     * @return nombre de cases vides
     */
    public int nbCasesLibres();
}
