package jeu.ia;

import java.util.ArrayList;
import jeu.Case;
import jeu.Plateau;

public abstract class IntelligenceBase{
    
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
        
        for (int i=0; i<64; i++)
            if (damier[i].jouable())
                jouables.add(i);
        
        if (jouables.isEmpty())
            throw new NoFreeCaseException();
        return jouables;
    }
    
    protected int scoreIa(Plateau plate){
        return (plateau.tourBlanc()) ? plate.scoreBlanc() : plate.scoreNoir();
    }
    
    protected int scoreJo(Plateau plate){
        return (plateau.tourBlanc()) ? plate.scoreNoir() : plate.scoreBlanc();
    }
    
    protected boolean tourIa(Plateau plate){
        return plate.tourBlanc() == plateau.tourBlanc();
    }
    
    
    protected Plateau simuler(Plateau source, int coup){
        Plateau res = source.copie();
        res.jouer(coup);
        
        return res;
    }
    
    public abstract int mouvement() throws NoFreeCaseException;
}
