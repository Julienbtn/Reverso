package jeu;

public interface Case {
    
    public boolean remplie();   // Indique si la case est prise par un joueur
    public boolean blanche();   // Si la case est remplie, indique si c'est par le joueur blanc (second joueur)
                                // Sinon, lève une exception
    public boolean jouable();   // Indique s'il est possible de jouer sur cette case

}
