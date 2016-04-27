package jeu;

import java.util.ArrayList;
import jeu.core.TableauCase;

public interface Plateau {
    
    public Plateau copie();                 // Renvoie copie du plateau

    public Case[] getDamier();              // Tableau contenant les cases du tableau (ligne par ligne)
    public void jouer(int idCase);          // Tente de jouer à la case indiquée
                                            // Lance une exception si impossible
    
    
    public boolean tourBlanc();             // Retourne true si c'est au joueur blanc de jouer
    public boolean passe();                 // Retourne true si un joueur vient de passer son tour
    
    public int scoreBlanc();                // Retourne le score actuel des blancs
    public int scoreNoir();                 // Retourne le score actuel des noirs
    
    public boolean victoireBlanc();         // Renvoie true si le joueur blanc a gagné
    public boolean termine();               // Renvoie true si jeu terminé
    public int nbCasesLibres();               // Renvoie le nombre de cases vides
}
