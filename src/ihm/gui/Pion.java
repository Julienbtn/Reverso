package ihm.gui;

import java.awt.*;
import javax.swing.JPanel;


public class Pion extends JPanel {


    public Pion(Couleur couleur) {
        setOpaque(false);
        switch (couleur) {
        case BLANC :
            setForeground(Color.WHITE);
            setBackground(Color.LIGHT_GRAY);
            break;
        case NOIR :
            setForeground(Color.BLACK);
            setBackground(Color.DARK_GRAY);
            break;
        }
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
        g.fillOval(5, 5, getWidth()-10, getHeight()-10);

    }
}
