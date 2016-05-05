package jeu;

/**
 *
 * @author Podoko
 */
public interface Case {
    
    // Indique si la case est prise par un joueur

    /**
     *
     * @return
     */
        public boolean remplie();
    
    // Si la case est remplie, indique si c'est par le joueur blanc
    //   (second joueur), sinon lève une exception

    /**
     *
     * @return
     */
        public boolean blanche();
    
    // Indique s'il est possible de jouer sur cette case

    /**
     *
     * @return
     */
        public boolean jouable();
}
