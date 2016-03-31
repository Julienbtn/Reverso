package reverso;

public class Jeu {
    private TableauCase plateau;
    private boolean tourBlanc;

    public Jeu(){
        plateau = new TableauCase(8,8,this);
        tourBlanc = false;
    }
    
    public void drawJeu(){
        plateau.drawPlateau();
    }
    
    public void start(Clavier c){
        do {
        plateau.chercherCase(tourBlanc);
        drawJeu();
        jouer(c.choix(this));
        tourBlanc = !tourBlanc;
        } while (true);
    }
    
    public TableauCase getPlateau(){
        return plateau;
    }
    
    public void jouer(int[] choix){
        plateau.jouer(choix, tourBlanc);
    }
}
