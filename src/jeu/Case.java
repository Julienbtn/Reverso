package jeu;

/**
 * Interface des cases pour le plateau Reverso
 * 
 */
public interface Case {
    
    // Indique si la case est prise par un joueur
    /**
     *
     * @return true si un pion se trouve dans la case
     */
    public boolean remplie();
    
    // Si la case est remplie, indique si c'est par le joueur blanc
    /**
     *
     * @return true si la case est occupée par un pion blanc
     */
    public boolean blanche();
    
    // Indique s'il est possible de jouer sur cette case
    /**
     *
     * @return true s'il est possible de jouer sur la case
     */
    public boolean jouable();
}
