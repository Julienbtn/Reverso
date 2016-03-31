/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.ia;

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
    
    public abstract int mouvement() throws NoFreeCaseException;
    
    
}
