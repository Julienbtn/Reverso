package reverso;

import jeu.core.Jeu;


public class Reverso {

    public static void main(String[] args) {
        // lancer de la fenetre
        //Fenetre fen = new Fenetre();
        
        // Jeu en mode console qui fonctionne
        Jeu j = new Jeu();
        Clavier c = new Clavier();
        j.start(c);
    }
}
