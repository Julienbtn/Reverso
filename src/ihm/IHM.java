/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm;

import jeu.Plateau;

/**
 *
 * @author Podoko
 */
public abstract class IHM {
    
    protected Plateau plate;
    
    public void setPlateau(Plateau plateau){ plate = plateau; }
    
    public abstract void draw();
    
}
