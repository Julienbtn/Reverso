package jeu.ia;

import java.util.ArrayList;
import java.util.Random;
import jeu.Plateau;

/**
 * Intelligence artificielle min-max.
 * 
 * <p>Cette intelligence propose des coups en cherchant à obtenir le plus de cases
 * après un certain nombre de coups.</p>
 * 
 * @see IntelligenceBase
 */
public class IntelligenceMinMax extends IntelligenceBase{
    
    /**
     * Nombre de coups anticipés par l'ia
     */
    private int prof;
    
    /**
     * Initialise l'ia en la configurant pour prévoir le nombre de coups à anticiper.
     * 
     * @param plate Plateau sur lequel jouera l'ia
     * @param profondeur Nombre de coups qu'anticipera l'ia
     */
    public IntelligenceMinMax(Plateau plate, int profondeur) {
        super(plate);
        prof = profondeur;
    }
    
    
    /**
     * {@inheritDoc}
     * 
     * @return 
     */
    @Override
    public int mouvement() throws NoFreeCaseException {
        int maxval=-65;
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
            return scoreIa(plate);
        int minval=65;
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
            return scoreIa(plate);
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
