package reverso;

import jeu.core.Jeu;
import ihm.Fenetre;


public class Reverso {

    public static void main(String[] args) {
        // lancer de la fenetre
        Jeu j = new Jeu();
        Fenetre fen = new Fenetre(j.getDamier());
        fen.repaint(5);


        // Jeu en mode console qui fonctionne
        Clavier c = new Clavier();
        j.start(c);

    }
}
