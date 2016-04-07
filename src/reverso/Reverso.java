package reverso;

import ihm.Fenetre;
import jeu.core.Jeu;
import jeu.ia.*;


public class Reverso {

    public static void main(String[] args) {
        // lancer de la fenetre
        //Fenetre fen = new Fenetre();
        
        // Jeu en mode console qui fonctionne
        Jeu j = new Jeu();
        Clavier c = new Clavier();
        IntelligenceHasard ia1 = new IntelligenceHasard(j);
        IntelligenceNormale ia2 = new IntelligenceNormale(j);
        j.start(c,ia2);
    }
}
