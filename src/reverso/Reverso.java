package reverso;

import ihm.Fenetre;
import jeu.core.Jeu;
import jeu.ia.IntelligenceHasard;


public class Reverso {

    public static void main(String[] args) {
        // lancer de la fenetre
        //Fenetre fen = new Fenetre();
        
        // Jeu en mode console qui fonctionne
        Jeu j = new Jeu();
        Clavier c = new Clavier();
        IntelligenceHasard ia = new IntelligenceHasard(j);
        j.start(c,ia);
    }
}
