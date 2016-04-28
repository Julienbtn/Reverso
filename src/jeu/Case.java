package jeu;

public interface Case {
    
    // Indique si la case est prise par un joueur
    public boolean remplie();
    
    // Si la case est remplie, indique si c'est par le joueur blanc
    //   (second joueur), sinon lève une exception
    public boolean blanche();
    
    // Indique s'il est possible de jouer sur cette case
    public boolean jouable();
}
