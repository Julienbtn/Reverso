package jeu.core;

import java.util.logging.Level;
import java.util.logging.Logger;
import jeu.core.CaseR;
import jeu.Plateau;
import jeu.ia.*;
import reverso.Clavier;

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
    }
    
    public Jeu(boolean tourBlanc, boolean passe, boolean fini){
        plateau = new TableauCase(8,8);
        this.tourBlanc = tourBlanc;
        this.passe = passe;
        this.fini = fini;
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
    
    public void start(Clavier c, IntelligenceBase ia){
        boolean boucle = true;
        do {
            plateau.chercherCase(tourBlanc);
            drawJeu();
            if(caseJouable()){
                if(tourBlanc)
                    jouer(c.choix(this));
                else
                    try {
                        jouer(ia.mouvement());
                } catch (NoFreeCaseException ex) {
                    Logger.getLogger(Jeu.class.getName()).log(Level.SEVERE, null, ex);
                }
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
