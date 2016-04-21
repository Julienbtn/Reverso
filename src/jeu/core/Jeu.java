package jeu.core;

import jeu.Plateau;

public class Jeu implements Plateau{
    private TableauCase plateau;
    private boolean tourBlanc;
    
    // Implémentation interface IA !
    private boolean passe;
    private boolean fini;
  

    public Jeu(){
        plateau = new TableauCase(8,8);
        tourBlanc = false;
        passe = false;
        fini = false;
        plateau.chercherCase(tourBlanc);
    }
    
    public Jeu(boolean tourBlanc, boolean passe, boolean fini){
        plateau = new TableauCase(8,8);
        this.tourBlanc = tourBlanc;
        this.passe = passe;
        this.fini = fini;
    }
    
    public TableauCase getPlateau(){
        return plateau;
    }
    
    public void jouer(int[] choix){
        plateau.jouer(choix, tourBlanc);
        tourBlanc = !tourBlanc;
        passe = false;
    }
    
    public boolean caseJouable(){
        return plateau.caseJouable();
    }
    
    // Implémentation interface IA !
    public Jeu copie(){
        Jeu copie;
        copie = new Jeu(tourBlanc, passe, fini);
        copie.plateau = plateau.copieTout();
        return copie;
    }
    public CaseR[] getDamier(){
        return plateau.copieTab();
    }
    public void jouer(int idCase){
        if (idCase<0 ||idCase>=64)
            throw new IndexOutOfBoundsException();
        int[] choix = new int[2];
        choix[0] = idCase%8;
        choix[1] = (idCase - choix[0])/8;
        jouer(choix);
        plateau.chercherCase(tourBlanc);
        if(!caseJouable()){
            plateau.chercherCase(!tourBlanc);
            if(!caseJouable()){
                fini = true;
            }
            else{
                tourBlanc = !tourBlanc;
                passe = true;
            }
        }
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
