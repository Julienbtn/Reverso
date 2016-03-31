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
        boolean boucle = true;
        do {
        plateau.chercherCase(tourBlanc);
        drawJeu();
        if(caseJouable()){
            jouer(c.choix(this));
            tourBlanc = !tourBlanc;
        }
        else {
            plateau.chercherCase(!tourBlanc);
            if(!caseJouable()){
                boucle = false;
            }
            else{
                System.out.println("Tu peux pas jouer, dommage");
                tourBlanc = !tourBlanc;
            }
        }
        
        } while (boucle);
        System.out.println("FINI");
    }
    
    public TableauCase getPlateau(){
        return plateau;
    }
    
    public void jouer(int[] choix){
        plateau.jouer(choix, tourBlanc);
    }
    
    public boolean caseJouable(){
        return plateau.caseJouable();
    }
}
