// package de l'interface Homme-Machine
package ihm;

import jeu.Plateau;

/**
 * classe abstraite décrivant les interfaces homme-machine pour reverso
 * 
 * @see Plateau
 */
public abstract class IHM {
    
    /**
    * Objet implémentant toutes les fonctions spécifiées par l'interface Plateau
    *
    * @see Plateau
    */
    protected Plateau plate;
        
    
    /**
     * Permet de modifier le plateau de jeu utilisé par l'ihm, devrait mettre 
     * automatiquement l'affichage à jour.
     * @param plateau Nouveau plateau à utiliser pour l'affichage
     */
    public void setPlateau(Plateau plateau){ plate = plateau; }
        
    
    /**
     * Met à jour si besoin de l'affichage du plateau
     */
    public abstract void draw();
    // cette méthode gère l'affichage des composants de l'interface graphique
}
