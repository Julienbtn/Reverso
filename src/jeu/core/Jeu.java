package jeu.core;

import jeu.Plateau;

public class Jeu implements Plateau{
    private TableauCase plateau;
    private boolean tourBlanc;
    
    // Implémentation interface IA !
    private boolean passe;
    private boolean fini;
  
    
    // Constructeur de base, crée un plateau déjà prêt
    public Jeu(){
        plateau = new TableauCase(8,8);
        tourBlanc = false;
        passe = false;
        fini = false;
        plateau.chercherCase(tourBlanc);
    }
    
    // Constructeur utilisé pour copier le jeu (voir fonction copie)
    public Jeu(boolean tourBlanc, boolean passe, boolean fini){
        plateau = new TableauCase(8,8);
        this.tourBlanc = tourBlanc;
        this.passe = passe;
        this.fini = fini;
    }
    
    public TableauCase getPlateau(){
        return plateau;
    }
    
    // Pause un pion aux coordonnées [colonne,ligne]
    public void jouer(int[] choix){
        plateau.jouer(choix, tourBlanc);
        tourBlanc = !tourBlanc;
        passe = false;
    }
    
    public boolean caseJouable(){
        return plateau.caseJouable();
    }
    
    //      Implémentation interface IA !
    // Copie le jeu actuel
    @Override
    public Jeu copie(){
        Jeu copie;
        copie = new Jeu(tourBlanc, passe, fini);
        copie.plateau = plateau.copieTout();
        return copie;
    }
    // Renvoie UNE COPIE du damier seulement
    @Override
    public CaseR[] getDamier(){
        return plateau.copieTab();
    }
    // Joue dans une case et gère le tour qui passe/la fin du jeu
    // Fonction également utilisée pour les coups du joueur
    @Override
    public void jouer(int idCase){
        if (idCase<0 ||idCase>=64)
            throw new IndexOutOfBoundsException();
        int[] choix = new int[2];
        choix[0] = idCase%8;
        choix[1] = (idCase - choix[0])/8;
        // On met le pion (pas de vérification si la case st jouable
        // il faut le faire avant
        jouer(choix);
        // On recherche les coups possibles pour le joueur suivant
        plateau.chercherCase(tourBlanc);
        if(!caseJouable()){
            // Si l'autre joueur ne peut pas jouer, on recherche les coups
            // possible pour le joueur qui vient de jouer
            plateau.chercherCase(!tourBlanc);
            if(!caseJouable()){
                // Si personne ne peut jouer, le jeu est fini
                fini = true;
            }
            else{
                // Sinon le joueur qui ne peut pas jouer passe son tour
                tourBlanc = !tourBlanc;
                passe = true;
            }
        }
    }
    @Override
    public boolean tourBlanc(){
        return tourBlanc;
    }
    @Override
    public boolean passe(){
        return passe;
    }
    @Override
    public int scoreBlanc(){
        return plateau.comptePoints(true);
    }
    @Override
    public int scoreNoir(){
        return plateau.comptePoints(false);
    }
    @Override
    public boolean victoireBlanc(){
        if (termine())
            if (scoreBlanc()>scoreNoir())
                return true;
        return false;
    }
    @Override
    public boolean termine(){
        return fini;
    }
    @Override
    public int nbCasesLibres(){
        return plateau.compteCasesVides();
    }
}
