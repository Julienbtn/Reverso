package jeu;

/**
 *
 * @author Podoko
 */
public interface Plateau {
    // Renvoie copie du plateau

    /**
     *
     * @return
     */
        public Plateau copie();
    
     // Tableau contenant les cases du tableau (ligne par ligne)

    /**
     *
     * @return
     */
        public Case[] getDamier();
    
    // Tente de jouer à la case indiquée, lance une exception si impossible
    public void jouer(int idCase);

    // Retourne true si c'est au joueur blanc de jouer

    /**
     *
     * @return
     */
        public boolean tourBlanc();
    
    // Retourne true si un joueur vient de passer son tour
    public boolean passe();
    
    // Retourne le score actuel des blancs

    /**
     *
     * @return
     */
        public int scoreBlanc();
    
    // Retourne le score actuel des noirs
    public int scoreNoir();
    
    // Renvoie true si le joueur blanc a gagné
    public boolean victoireBlanc();
    
    // Renvoie true si jeu terminé

    /**
     *
     * @return
     */
        public boolean termine();
    
    // Renvoie le nombre de cases vides

    /**
     *
     * @return
     */
        public int nbCasesLibres();
}
