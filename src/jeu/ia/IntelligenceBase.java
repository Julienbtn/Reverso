package jeu.ia;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jeu.Case;
import jeu.Plateau;
import jeu.core.Jeu;

public abstract class IntelligenceBase implements reverso.Entree{
    
    protected Plateau plateau;
    
    public IntelligenceBase(Plateau plate){
        plateau = plate;
    }
    
    protected ArrayList<Integer> casesJouables() throws NoFreeCaseException{
        return casesJouables(plateau);
    }
    
    protected ArrayList<Integer> casesJouables(Plateau p) throws NoFreeCaseException{
        
        Case[] damier = p.getDamier();
        ArrayList<Integer> jouables = new ArrayList<>();
        
        for (int i=0; i<64; i++){
            if (damier[i].jouable())
                jouables.add(i);
            
        }
        
        if (jouables.size()<1)
            throw new NoFreeCaseException();
        return jouables;
    }
    
    
    protected Plateau simuler(Plateau source, int coup){
        Plateau res = source.copie();
        res.jouer(coup);
        
        return res;
    }
    
    public abstract int mouvement() throws NoFreeCaseException;
    
    public int[] choix(Jeu j){
        int[] val = new int[2];
        try {
            int temp = mouvement();
            val[0] = temp%8;
            val[1] = (temp - val[0])/8;
        } catch (NoFreeCaseException ex) {
            Logger.getLogger(IntelligenceBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return val;
    }
    
    
}
