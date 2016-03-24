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
    
    public void start(){
        drawJeu();
    }
}
