package reverso;

import jeu.core.Jeu;
import jeu.ia.*;
import ihm.Fenetre;


public class Reverso {

    public static void main(String[] args) {
        // lancer de la fenetre

        Jeu j = new Jeu();
        Fenetre fen = new Fenetre(j);


        // Jeu en mode console qui fonctionne
        /*
        Clavier c = new Clavier();
        IntelligenceHasard ia1; 
        IntelligenceNormale ia2; 
        IntelligenceRecursive ia3;
        while(true){
            ia1= new IntelligenceHasard(j);
            ia2= new IntelligenceNormale(j);
            ia3 = new IntelligenceRecursive(j,1);
            j.start(c,ia1);
            c.pause();
            j= new Jeu();
        }
*/
    }
}
