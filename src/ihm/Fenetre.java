package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.*;
import jeu.Case;

 
public class Fenetre extends JFrame {
  private JButton bouton = new JButton("Recommencer");
  private JPanel container_nord = new JPanel();
  private JLabel parties_j1 = new JLabel("0");
  private JLabel parties_j2 = new JLabel("0");
  private JPanel container_centre = new JPanel();
  private GridLayout grille = new GridLayout(8,8);
  private JPanel container_sud = new JPanel();
  private JLabel scorej1 = new JLabel("0");
  private JLabel scorej2 = new JLabel("0");
  private JLabel tour = new JLabel("");
  
  
  public Fenetre( Case[] damier){
    this.setTitle("Reverso"); //titre
    this.setSize(500, 500); // taille de la fenetre (500x500)
    this.setLocationRelativeTo(null); // centré 
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //bouton croix activé (quitte le processus lorsqu'on appuye dessus            
    this.setResizable(true); // possibilité de modifier la taille de la fenêtre
    this.setAlwaysOnTop(true); // la fenêtre reste devant
    
    //Haut de la fenêtre
    this.getContentPane().add(container_nord,BorderLayout.NORTH);
    container_nord.add(new JLabel("Scores totales des parties"));
    container_nord.add(new JLabel("Joueur 1"));
    container_nord.add(parties_j1);
    container_nord.add(new JLabel("Joueur 2"));
    container_nord.add(parties_j2);
    container_nord.add(bouton);
    
    //Milieu de la fenêtre
    this.getContentPane().add(container_centre,BorderLayout.CENTER);
    GridLayout grille = new GridLayout(8,8);
    container_centre.setLayout(grille);
    for (int i = 0 ; i<64;i++)
    {
        container_centre.add(new CaseG(damier[i]));
    }
    
    
    container_centre.repaint();

    
    //Bas de la fenêtre
    this.getContentPane().add(container_sud,BorderLayout.SOUTH);
    container_sud.add(new JLabel("Scores : "));
    container_sud.add(new JLabel("Joueur 1"));
    container_sud.add(scorej1);
    container_sud.add(new JLabel("C'est au tour de"));
    container_sud.add(tour);
    container_sud.add(new JLabel("Joueur 2"));
    container_sud.add(scorej2);
    this.setVisible(true); // rend visible la fenêtre
}
}
