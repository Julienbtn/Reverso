package jeu.ia;

import java.util.ArrayList;
import java.util.Random;
import jeu.Plateau;

/**
 * Intelligence artificielle aléatoire.
 * 
 * <p>Cette intelligence propose des coups aléatoires à chaque coup demandé.</p>
 * 
 * @see IntelligenceBase
 */
public class IntelligenceHasard extends IntelligenceBase{

    /**
     * {@inheritDoc}
     */
    public IntelligenceHasard(Plateau plate) {
        super(plate);
    }
    
    
    
    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public int mouvement() throws NoFreeCaseException {
        
        Random rnd  = new Random();
        ArrayList<Integer> jouables = casesJouables();
        if(jouables.isEmpty())
            System.out.println("On a un soucis je crois");
        
        return jouables.get(rnd.nextInt(jouables.size()));
    }
    
}
