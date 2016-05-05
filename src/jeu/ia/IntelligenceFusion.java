package jeu.ia;

import jeu.Plateau;

/**
 * Intelligence artificielle faisant la fusion entre une IntelligenceValuationRecursive
 * et une IntelligenceMinMax.
 * 
 * <p>Cette intelligence propose, au début du jeu, les coups que proposerait
 * une ia par valuation récursive puis propose les coups par min-max récursif.</p>
 * 
 * @see IntelligenceBase
 * @see IntelligenceValuationRecursive
 * @see IntelligenceMinMax
 */
public class IntelligenceFusion extends IntelligenceBase{
    /**
     * Intelligence par valuation récursive utilisée pour les premiers coups du jeu.
    */
    private IntelligenceValuationRecursive iaVal;
    /**
     * Intelligence par nombre total de cases, utilisée pour les derniers coups du jeu.
     */
    private IntelligenceMinMax iaMinMax;
    
    /**
     * Initialise l'ia en construisant deux ia plus simples
     * 
     * @param plate Plateau sur lequel jouera l'ia
     * @param prof Nombre de coups à anticiper
     */
    public IntelligenceFusion(Plateau plate,int prof) {
        super(plate);
        iaVal=new IntelligenceValuationRecursive(plate,prof);
        iaMinMax = new IntelligenceMinMax(plate,prof);
    }
    
    
    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public int mouvement() throws NoFreeCaseException {
        if(plateau.nbCasesLibres()<20)
            return iaMinMax.mouvement();
        else
            return iaVal.mouvement();
    }
}
