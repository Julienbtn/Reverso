package jeu.ia;

import jeu.Plateau;

public class IntelligenceFusion extends IntelligenceBase{
    private IntelligenceValuationRecursive iaVal;
    private IntelligenceMinMax iaMinMax;
    
    public IntelligenceFusion(Plateau plate,int prof) {
        super(plate);
        iaVal=new IntelligenceValuationRecursive(plate,prof);
        iaMinMax = new IntelligenceMinMax(plate,prof);
    }
    
    @Override
    public int mouvement() throws NoFreeCaseException {
        if(plateau.nbCasesLibres()<20)
            return iaMinMax.mouvement();
        else
            return iaVal.mouvement();
    }
}
