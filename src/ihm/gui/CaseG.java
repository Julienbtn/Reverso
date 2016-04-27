package ihm.gui;
 
import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import jeu.Case;


public class CaseG extends JPanel {
        

    public CaseG(Case c){
        setLayout(new GridLayout(1,0));
        initCouleur();
        update(c,false,false);
    }
    
    public void update(Case c, boolean blanc, boolean ia){
        initCouleur();
        removeAll();
        if (c.remplie())
        {
            if (c.blanche())
                add(creerPion(Couleur.BLANC));
            else 
                add(creerPion(Couleur.NOIR));
        }
        else if (c.jouable()&&!ia)
        {
            if(blanc){
                setBackground(Color.LIGHT_GRAY);
                setForeground(Color.BLUE);
            }
            else{
                setBackground(Color.DARK_GRAY);
                setForeground(Color.BLUE);
            }
            
        }
    }
    
    public CaseG getCase(int i, int j){
        return (CaseG) getComponent(j+i*8);
    }

    public CaseG getCase(int i){
        return (CaseG) getComponent(i);
    }
        
    private void initCouleur(){
        setBackground(new Color(0,102,51));
        setForeground(new Color(0,102,51));
        setBorder(BorderFactory.createLineBorder(Color.black));
            
    }
    
    private Pion creerPion(Couleur couleur){
        Pion pion = new Pion(couleur);
        return pion;
    }
    
    @Override
    public void paintComponent(Graphics g){
        Paint paint;
        Graphics2D g2d;
        if (g instanceof Graphics2D) {
            g2d = (Graphics2D) g;
        }
        else {
            System.out.println("Error");
            return;
        }
        paint = new GradientPaint(0,0, getBackground(), getWidth(), getHeight(), getForeground());
        g2d.setPaint(paint);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
    
