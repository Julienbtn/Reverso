package jeu.ia;

import jeu.Plateau;

public class IntelligenceFusion extends IntelligenceBase{
    private IntelligenceValuationMaxIA iaLilian;
    private IntelligenceDiff iaAxel;
    
    public IntelligenceFusion(Plateau plate) {
        super(plate);
        iaLilian=new IntelligenceValuationMaxIA(plate);
        iaAxel = new IntelligenceDiff(plate);
    }
    
    @Override
    public int mouvement() throws NoFreeCaseException {
        if(plateau.nbCasesLibres()<20)
            return iaAxel.mouvement();
        else
            return iaLilian.mouvement();
    }
}
