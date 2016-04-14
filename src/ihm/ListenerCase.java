package ihm;
    
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import jeu.Case;
import jeu.Plateau;


public class ListenerCase implements MouseListener{
    
    private Case case1;
    private Plateau plateau;
    private int id;
    private Fenetre f;

    public ListenerCase(Case case1, Plateau plateau, int id, Fenetre f) {
        super();
        this.case1 = case1;
        this.plateau = plateau;
        this.id = id;
        this.f = f;
    }


    public void mouseClicked(MouseEvent arg0) {
    
    }


    public void mouseEntered(MouseEvent arg0) {
        
    }


    public void mouseExited(MouseEvent arg0) {
        
    }


    @Override
    public void mousePressed(MouseEvent arg0) {
        if(case1.jouable()){
            plateau.jouer(id);
            f.actualise(plateau);
        }  
    }


    public void mouseReleased(MouseEvent arg0) {
        
    }  

}
