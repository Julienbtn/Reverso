// package de l'interface Homme-Machine gérant l'interface graphique
package ihm.gui; 

import java.awt.*;
import javax.swing.*;
import jeu.Case;

public class CaseG extends JPanel {
    
    // Constructeur qui transforme une case du jeu en case graphique
    public CaseG(Case c){
        setLayout(new GridLayout(1,0));
        initCouleur();
        update(c,false,false);
    }
    
    // méthode qui modifie la case passée en paramètre en fonction de sa couleur et si c'est une ia 
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
    
    // fonction qui retourne une case graphique en fonction de ses coordonnées x et y 
    public CaseG getCase(int i, int j){
        return (CaseG) getComponent(j+i*8);
    }
    
    // fonction qui retourne une case graphique en fonction de placement dans le plateau
    public CaseG getCase(int i){
        return (CaseG) getComponent(i);
    }
    
    // méthode d'initialisation de la couleur du plateau ainsi que des bordures
    private void initCouleur(){
        setBackground(new Color(0,102,51));
        setForeground(new Color(0,102,51));
        setBorder(BorderFactory.createLineBorder(Color.black));
        }
    
    // fonction qui crée un pion en fonction de la couleur passée en paramètre
    private Pion creerPion(Couleur couleur){
        Pion pion = new Pion(couleur);
        return pion;
    }
    
    // méthode qui permet de dessiner le composant 
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
    
