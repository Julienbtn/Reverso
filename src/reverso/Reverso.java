package reverso;

import jeu.core.Jeu;
import ihm.Fenetre;
import jeu.ia.IntelligenceHasard;


public class Reverso {

    public static void main(String[] args) {
        // lancer de la fenetre

        Jeu j = new Jeu();
        Fenetre fen = new Fenetre(j.getDamier());


        // Jeu en mode console qui fonctionne
        Clavier c = new Clavier();

        //j.start(c);
        IntelligenceHasard ia = new IntelligenceHasard(j);
        j.start(c,ia);

    }
}
