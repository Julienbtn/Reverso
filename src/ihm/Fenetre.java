package ihm;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import jeu.*;

 
public class Fenetre extends JFrame implements ActionListener {
  private JPanel container_nord;
  private JPanel container_centre;
  private JPanel container_sud;
  
  
  public Fenetre(Plateau plateau){
    this.setTitle("Reverso"); //titre
    this.setSize(500, 500); // taille de la fenetre (500x500)
    this.setLocationRelativeTo(null); // centré 
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //bouton croix activé (quitte le processus lorsqu'on appuye dessus            
    this.setResizable(true); // possibilité de modifier la taille de la fenêtre
    this.setAlwaysOnTop(true); // la fenêtre reste devant
    container_nord = new JPanel();
    container_centre = new JPanel();
    container_sud = new JPanel();
    
    //Haut de la fenêtre
    this.getContentPane().add(container_nord,BorderLayout.NORTH);
    JButton bouton = new JButton("Recommencer");
    container_nord.add(bouton);
    
    //Grille et scores
    this.getContentPane().add(container_centre,BorderLayout.CENTER);
    this.getContentPane().add(container_sud,BorderLayout.SOUTH);
    actualise(plateau);
    
    this.setVisible(true); // rend visible la fenêtre
}

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void actualise(Plateau plateau){
        container_centre.removeAll();
        GridLayout grille = new GridLayout(8,8);
        container_centre.setLayout(grille);
        for (int i = 0 ; i<64;i++)
        {
            CaseG caseA = new CaseG(plateau.getDamier()[i]);
            caseA.addMouseListener(new ListenerCase(plateau.getDamier()[i],plateau,i,this));
            container_centre.add(caseA);
        }
        
        container_sud.removeAll();
        container_sud.add(new JLabel("Blanc : "+plateau.scoreBlanc()));
        container_sud.add(new JLabel("Noir : "+plateau.scoreNoir()));
        if(plateau.termine())
            container_sud.add(new JLabel("FINI"));
        else
            container_sud.add(new JLabel("Au tour de : "+(plateau.tourBlanc()? "blanc" : "noir")));
        if(plateau.passe())
            container_sud.add(new JLabel("Tour passé"));
        revalidate();
        repaint();
    }
    
}
