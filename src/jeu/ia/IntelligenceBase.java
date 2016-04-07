/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.ia;

import java.util.ArrayList;
import jeu.Case;
import jeu.Plateau;

/**
 *
 * @author Podoko
 */
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
    
    
}
