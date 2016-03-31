package reverso;

public class Jeu {
    private TableauCase plateau;
    private boolean tourBlanc;
    private boolean passe;
    private boolean fini;

    public Jeu(){
        plateau = new TableauCase(8,8,this);
        tourBlanc = false;
        passe = false;
        fini = false;
    }
    
    public void drawJeu(){
        plateau.drawPlateau();
        System.out.println("Vous avez "+plateau.comptePoints(tourBlanc)+" pions.");
        System.out.println("L'adversaire en a "+plateau.comptePoints(!tourBlanc)+".");
    }
    
    public void start(Clavier c){
        boolean boucle = true;
        do {
            plateau.chercherCase(tourBlanc);
            drawJeu();
            if(caseJouable()){
                jouer(c.choix(this));
                tourBlanc = !tourBlanc;
                passe = false;
            }
            else {
                plateau.chercherCase(!tourBlanc);
                if(!caseJouable()){
                    boucle = false;
                    fini = true;
                }
                else{
                    System.out.println("Tu peux pas jouer, dommage");
                    tourBlanc = !tourBlanc;
                    passe = true;
                }
            }
        } while (boucle);
        fin();
    }
    
    public void fin(){
        System.out.println("FINI\n");
        System.out.println("O : "+plateau.comptePoints(true));
        System.out.println("X : "+plateau.comptePoints(false));
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
    
    public boolean isTourBlanc(){
        return tourBlanc;
    }
    
    
    
    // Implémentation interface IA !
    
    public Case[] getDamier(){
        return plateau.getTab();
    }
    public void jouer(int idCase){
        int[] choix = new int[2];
        choix[1] = idCase%8;
        choix[0] = (idCase - choix[1])/8;
        jouer(choix);
    }
    public boolean tourBlanc(){
        return tourBlanc;
    }
    public boolean passe(){
        return passe;
    }
    public int scoreBlanc(){
        return plateau.comptePoints(true);
    }
    public int scoreNoir(){
        return plateau.comptePoints(false);
    }
    public boolean victoireBlanc(){
        if (termine())
            if (scoreBlanc()>scoreNoir())
                return true;
        return false;
    }
    public boolean termine(){
        return fini;
    }
}
