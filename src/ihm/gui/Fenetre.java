package ihm.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import jeu.*;
import jeu.core.Jeu;

 
public class Fenetre extends JFrame implements ActionListener {
  private final JPanel container_nord;
  private final JPanel container_centre;
  private final JPanel container_sud;
  
  private Plateau plateau;
  private Case[] damier;
  
  
  public Fenetre(Plateau plateau){
      
      this.plateau = plateau;
      this.damier = plateau.getDamier();
      
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
    bouton.addActionListener(new CustomMouseListener(this){

        @Override
        public void actionPerformed(ActionEvent ae){
            fenetre.recommencer();
        }});
    container_nord.add(bouton);
    

    
    //Milieu de la fenêtre
    this.getContentPane().add(container_centre,BorderLayout.CENTER);
    GridLayout grille = new GridLayout(8,8);
    container_centre.setLayout(grille);

    for (int i = 0; i<64; i++)
    {
        CaseG caseA = new CaseG(damier[i]);
        caseA.addMouseListener(new ListenerCase(i,this));
        container_centre.add(caseA);
    }
    container_centre.repaint();
    
    //Grille et scores
    this.getContentPane().add(container_centre,BorderLayout.CENTER);
    this.getContentPane().add(container_sud,BorderLayout.SOUTH);
    actualiser();
    
    this.setVisible(true); // rend visible la fenêtre
}

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public void clicCase(int id){
        if (damier[id].jouable()){
            plateau.jouer(id);
            actualiser();
        }
    }
    
    public void recommencer(){
        this.plateau = new Jeu();
        this.actualiser();
    }
    
    
    
    
    public void actualiser(){
        
        // Damier (centre)
        
        damier = plateau.getDamier();
        Component[] cases = container_centre.getComponents();
        
        for (int i=0; i<64; i++){
            ((CaseG) cases[i]).update(damier[i]);
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



class CustomMouseListener implements ActionListener{
    
    protected Fenetre fenetre;
    
    public CustomMouseListener(Fenetre f){
        fenetre = f;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
    }

}