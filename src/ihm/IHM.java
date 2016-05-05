// package de l'interface Homme-Machine
package ihm;

import jeu.Plateau;

public abstract class IHM {
    // Classe abstraite qui implémente les différentes classes de l'interface graphique

    /**
     *
     */
        protected Plateau plate;
    // on utilise un plateau de jeu

    /**
     *
     * @param plateau
     */
        public void setPlateau(Plateau plateau){ plate = plateau; }
    // cette méthode permet d'initialiser le plateau par le plateau passé en paramètre 

    /**
     *
     */
        public abstract void draw();
    // cette méthode gère l'affichage des composants de l'interface graphique
}
