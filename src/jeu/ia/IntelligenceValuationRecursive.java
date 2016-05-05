package jeu.ia;

import java.util.ArrayList;
import java.util.Random;
import jeu.Plateau;

/**
 * Intelligence artificielle récursive par valuation de cases.
 * 
 * <p>Cette intelligence associe une valeur à chaque case du plateau et propose
 * un coup de façon à obtenir le meilleur nombre de points en plusieurs coups.</p>
 * 
 * @see IntelligenceValuation
 */
public class IntelligenceValuationRecursive extends IntelligenceValuation{
    // Grille de valeur des cases
    /**
     * Nombre de coups à anticiper
     */
    int prof;
    
    /**
    * Initialise l'intelligence atificielle et crée le tableau des valeurs associées
    * aux cases
    * 
    * @param plate le Plateau pour lequel l'intelligence artificielle va 
    * chercher les coups possibles.
    * @param prof nombre de coups à anticiper
    */
    public IntelligenceValuationRecursive(Plateau plate, int prof) {
        super(plate);
        this.prof = prof;
    }
   
    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public int mouvement() throws NoFreeCaseException {
        int maxval=-65000;
        Random rnd  = new Random();
        Plateau copie;
        int val;
        ArrayList<Integer> positionsMeilleurCoup = new ArrayList<>();
        ArrayList<Integer> jouables= casesJouables();
        for(int i=0;i<jouables.size();i++){
            copie =simuler(plateau, jouables.get(i));
            val=iaMin(copie,prof);
            if(val==maxval)
                positionsMeilleurCoup.add(jouables.get(i));
            else if(val>maxval){
                maxval = val;
                positionsMeilleurCoup.clear();
                positionsMeilleurCoup.add(jouables.get(i));
            }
        }
        return positionsMeilleurCoup.get(rnd.nextInt(positionsMeilleurCoup.size()));
    }
    
    
    /** 
     * Détermine le score minimal possible pour le nombre de coups indiqué
     * 
     * @param plate Plateau à analyser pour déterminer le pire score
     * @param prof Nombre de coups à anticiper
     * @return Score minimal possible en prof coups 
     * @throws NoFreeCaseException Si aucune case n'est libre sur le plateau passé
     */
    public int iaMin(Plateau plate, int prof) throws NoFreeCaseException{
        if(prof<1||plate.termine())
            return calculerPoints(plate, plateau.tourBlanc());
        int minval=65000;
        Plateau copie;
        int val;
        ArrayList<Integer> jouables= casesJouables(plate);
        for(int i=0;i<jouables.size();i++){
            copie =simuler(plate, jouables.get(i));
            val=iaMax(copie,prof-1);
            if(val<minval)
                minval=val;
        }
        return minval;
    }
    
    /** 
     * Détermine le score maximal possible pour le nombre de coups indiqué
     * 
     * @param plate Plateau à analyser pour déterminer le pire score
     * @param prof Nombre de coups à anticiper
     * @return Score maximal possible en prof coups 
     * @throws NoFreeCaseException Si aucune case n'est libre sur le plateau passé
     */
    public int iaMax(Plateau plate, int prof) throws NoFreeCaseException{
        if(prof<1||plate.termine())
            return calculerPoints(plate, plateau.tourBlanc());
        int maxval=-65;
        Plateau copie;
        int val;
        ArrayList<Integer> jouables= casesJouables(plate);
        for(int i=0;i<jouables.size();i++){
            copie =simuler(plate, jouables.get(i));
            val=iaMin(copie,prof-1);
            if(val>maxval)
                maxval=val;
        }
        return maxval;
    }
}
