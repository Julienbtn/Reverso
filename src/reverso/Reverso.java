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
        IntelligenceHasard ia1; 
        IntelligenceNormale ia2; 
        IntelligenceRecursive ia3;
        int ca = 0;
        while(true){
            ia1= new IntelligenceHasard(j);
            ia2= new IntelligenceNormale(j);
            ia3 = new IntelligenceRecursive(j,ca);
            j.start(ia1,ia3);
            System.out.println("IA recu de niveau "+ca);
            ca++;
            c.pause();
            j= new Jeu();
        }
        

    }
}
