package ihm.gui;
    
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class ListenerCase implements MouseListener{
    
    private final int id;



    private final Fenetre fenetre;

    public ListenerCase(int id, Fenetre f) {



        this.id = id;
        this.fenetre = f;

    }


    @Override
    public void mouseClicked(MouseEvent arg0) {
    
    }


    @Override
    public void mouseEntered(MouseEvent arg0) {
        
    }


    @Override
    public void mouseExited(MouseEvent arg0) {
        
    }


    @Override
    public void mousePressed(MouseEvent arg0) {
        fenetre.clicCase(id); 
    }


    @Override
    public void mouseReleased(MouseEvent arg0) {
        
    }  

}
