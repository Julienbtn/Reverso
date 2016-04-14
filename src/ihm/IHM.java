package ihm;

import jeu.Plateau;

public abstract class IHM {
    
    protected Plateau plate;
    
    public void setPlateau(Plateau plateau){ plate = plateau; }
    
    public abstract void draw();
    
}
