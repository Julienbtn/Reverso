package reverso;

import jeu.core.Jeu;


public class Reverso {

    public static void main(String[] args) {
        Jeu j = new Jeu();
        Clavier c = new Clavier();
        j.start(c);
    }
}
