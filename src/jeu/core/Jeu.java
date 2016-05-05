package jeu.core;

import jeu.Plateau;

/**
 * jeu de reverso implémentant l'interface Plateau
 * 
 * @see Plateau
 */
public class Jeu implements Plateau{
    /**
     * Demier du jeu contenant les cases et les pions sur chacune d'elle, coeur du jeu
     */
    private TableauCase plateau;
    /**
     * Indique s'il est au tour du blanc de jouer
     */
    private boolean tourBlanc;
    
    // Implémentation interface IA !
    /**
     * Indique si le dernier joueur vient de passer son tour
     */
    private boolean passe;
    /**
     * Indique si la partie est terminée
     */
    private boolean fini;
  
    
    /**
     * Constructeur de base, crée un plateau déjà prêt, quatre cases occupées au mileu
     */
    public Jeu(){
        plateau = new TableauCase(8,8);
        tourBlanc = false;
        passe = false;
        fini = false;
        plateau.chercherCase(tourBlanc);
    }
    
    
    /**
     * Constructeur utilisé pour copier le jeu (voir fonction copie)
     * 
     * @param tourBlanc indique si le tour doit être au blanc ou non
     * @param passe indique si le dernier joueur est supposé avoir passé
     * @param fini indique si la partie est déjà terminée
     */
    public Jeu(boolean tourBlanc, boolean passe, boolean fini){
        plateau = new TableauCase(8,8);
        this.tourBlanc = tourBlanc;
        this.passe = passe;
        this.fini = fini;
    }
    
    /**
     * Permet d'obternir directement le damier utilisé par le jeu et son contenu
     * 
     * @return le plateau utilisé par le jeu
     */
    public TableauCase getPlateau(){
        return plateau;
    }
    
    /**
     * Pause un pion aux coordonnées [colonne,ligne] retourne les cases à retourner
     * et met à jour les infos sur le jeu.
     * 
     * @param choix coordonnées (colonne, ligne) de la case à jouer.
     */
    public void jouer(int[] choix){
        plateau.jouer(choix, tourBlanc);
        tourBlanc = !tourBlanc;
        passe = false;
    }
    
    /**
     * Indique si une case au moins est jouable par le joueur actuel
     * 
     * @return true si une case est jouable, false sinon
     */
    public boolean caseJouable(){
        return plateau.caseJouable();
    }
    
    
    
    //      Implémentation interface IA !
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Jeu copie(){
        Jeu copie;
        copie = new Jeu(tourBlanc, passe, fini);
        copie.plateau = plateau.copieTout();
        return copie;
    }
    
    
    /**
     * {@inheritDoc}
     */
    @Override
    public CaseR[] getDamier(){
        return plateau.copieTab();
    }
    
    
    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean tourBlanc(){
        return tourBlanc;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean passe(){
        return passe;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int scoreBlanc(){
        return plateau.comptePoints(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int scoreNoir(){
        return plateau.comptePoints(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean victoireBlanc(){
        if (termine())
            if (scoreBlanc()>scoreNoir())
                return true;
        return false;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean termine(){
        return fini;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int nbCasesLibres(){
        return plateau.compteCasesVides();
    }
}
