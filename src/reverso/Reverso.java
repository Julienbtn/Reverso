package reverso;

import jeu.core.Jeu;
import jeu.ia.*;
import ihm.Fenetre;


public class Reverso {

    public static void main(String[] args) {
        // lancer de la fenetre

        Jeu j = new Jeu();
        Fenetre fen = new Fenetre(j.getDamier());


        // Jeu en mode console qui fonctionne
        Clavier c = new Clavier();
        IntelligenceHasard ia1 = new IntelligenceHasard(j);
        IntelligenceNormale ia2 = new IntelligenceNormale(j);
        IntelligenceRecursive ia3 = new IntelligenceRecursive(j,70);
        
        j.start(c,ia3);

    }
}
