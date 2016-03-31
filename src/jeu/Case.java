/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu;

/**
 *
 * @author Podoko
 */
public interface Case {
    
    public boolean remplie();   // Indique si la case est prise par un joueur
    public boolean blanche();   // Si la case est remplie, indique si c'est par le joueur blanc (second joueur)
                                // Sinon, lève une exception
    public boolean jouable();   // Indique s'il est possible de jouer sur cette case
    
}
