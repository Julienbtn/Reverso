package jeu.ia;

import java.util.ArrayList;
import java.util.Random;
import jeu.Plateau;

public class IntelligenceHasard extends IntelligenceBase{

    public IntelligenceHasard(Plateau plate) {
        super(plate);
    }
    
    
    @Override
    public int mouvement() throws NoFreeCaseException {
        
        Random rnd  = new Random();
        ArrayList<Integer> jouables = casesJouables();
        if(jouables.isEmpty())
            System.out.println("On a un soucis je crois");
        
        return jouables.get(rnd.nextInt(jouables.size()));
    }
    
}
