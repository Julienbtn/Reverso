// package de l'interface Homme-Machine gérant l'interface graphique
package ihm.gui;

import java.awt.*;
import javax.swing.JPanel;

public class Pion extends JPanel {

    // Constructeur d'un pion avec sa couleur placée en paramètre
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
    
    // Méthode qui permet de dessiner le pion 
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