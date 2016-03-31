/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.ia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import jeu.Case;
import jeu.Plateau;

/**
 *
 * @author Podoko
 */
public class IntelligenceHasard extends IntelligenceBase{

    public IntelligenceHasard(Plateau plate) {
        super(plate);
    }
    
    
    @Override
    public int mouvement() throws NoFreeCaseException {
        
        Random rnd  = new Random();
        
        Case[] damier = plateau.getDamier();
        ArrayList<Integer> libres = new ArrayList<>();
        
        Case place;
        for (int i=0; i<64; i++){
            place = damier[i];
            if (place.jouable())
                libres.add(i);
            
        }
        
        if (libres.size()<1)
            throw new NoFreeCaseException();
        
        return libres.get(rnd.nextInt(libres.size()));
    }
    
}
