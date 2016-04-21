package ihm.gui;

import java.awt.*;
import java.awt.event.*;
import static java.lang.Math.max;
import static java.lang.Math.min;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import jeu.*;
import jeu.core.Jeu;
import jeu.ia.*;


public class Fenetre extends JFrame {
    private final JPanel container_nord;
    private final JPanel container_centre;
    private final JPanel container_sud;

    private Plateau plateau;
    private Case[] damier;
    private int[] score;
    private IntelligenceBase iablanc;
    private IntelligenceBase ianoir;


    public Fenetre(Plateau plateau){

        this.plateau = plateau;
        this.damier = plateau.getDamier();
        ianoir = null;
        iablanc = null;
        score = new int[2]; score[0]=0; score[1]=0;
        
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
        barremenu();
        
        //Milieu de la fenêtre
        GridLayout grille = new GridLayout(8,8);
        container_centre.setLayout(grille);
        

        for (int i = 0; i<64; i++)
        {
            CaseG caseA = new CaseG(damier[i]);
            caseA.addMouseListener(new ListenerCase(i,this));
            container_centre.add(caseA);
        }
        container_centre.repaint();
        
        this.getContentPane().add(container_nord,BorderLayout.NORTH);
        this.getContentPane().add(container_centre,BorderLayout.CENTER);
        this.getContentPane().add(container_sud,BorderLayout.SOUTH);
        actualiser();

        this.setVisible(true); // rend visible la fenêtre
    }

    
    public void barremenu(){
        String[] choix = {"Joueur", "ia1"};
        JButton reload = new JButton("Rejouer");
        JButton valider = new JButton("Jouer");
        JComboBox blanc = new JComboBox(choix);
        JComboBox noir = new JComboBox(choix);
        blanc.setPreferredSize(new Dimension(100, 20));
        noir.setPreferredSize(new Dimension(100, 20));
        container_nord.add(reload);
        container_nord.add(new JLabel("   Blanc :"));
        container_nord.add(blanc);
        container_nord.add(new JLabel("   Noir :"));
        container_nord.add(noir);
        container_nord.add(new JLabel("   "));
        container_nord.add(valider);
        valider.addActionListener((ActionEvent e) -> {
            rejouer();
            switch(getChoix(blanc.getSelectedItem().toString(),choix)){
                case 1: iablanc=null; break;
                case 2: iablanc=new IntelligenceHasard(plateau); break;
                default: iablanc=null; break;
            }
            switch(getChoix(noir.getSelectedItem().toString(),choix)){
                case 1: ianoir=null; break;
                case 2: ianoir=new IntelligenceHasard(plateau); break;
                default: ianoir=null; break;
            }
            actualiser();
        });
        reload.addActionListener((ActionEvent e) -> {
            rejouer();
            actualiser();
        });
    }
    
    public int getChoix(String str, String[] tab){
        for(int i=0;i<tab.length;i++)
            if(str.equals(tab[i]))
                return i+1;
        return -1;
    }


    public void clicCase(int id){
        if (damier[id].jouable()){
            if((plateau.tourBlanc()&& iablanc == null) || (!plateau.tourBlanc()&& ianoir == null)){
                plateau.jouer(id);
                actualiser();
            }
        }
    }
    
    public void rejouer(){
        this.plateau = new Jeu();
        this.damier = plateau.getDamier();
    }

    public void actualiser(){
        String str;
        damier = plateau.getDamier();
        Component[] cases = container_centre.getComponents();
        for (int i=0; i<64; i++){
            ((CaseG) cases[i]).update(damier[i],plateau.tourBlanc());
        }
        
        container_sud.removeAll();
        if(plateau.termine()){
            str=scorefin();}
        else{
            str ="Scores : Blanc : "+plateau.scoreBlanc()+" Noir : "+plateau.scoreNoir()+"    ";
            str+=(plateau.passe()?"Encore a":"A")+"u tour de : "+(plateau.tourBlanc()? "blanc    " : "noir    ");
        }
        container_sud.add(new JLabel(str));    
        str="Victoires : Blanc : "+score[0]+" Noir : "+score[1];
        container_sud.add(new JLabel(str)); 
        
        revalidate();
        repaint();
        if (!plateau.termine()){
            try {
                if(plateau.tourBlanc()&& iablanc !=null)
                    joueria(true);
                if(!plateau.tourBlanc()&& ianoir!=null)
                    joueria(false);
            } catch (NoFreeCaseException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Si ça s'affiche voir avec Axel et ses exceptions bizarres !");
            }
        }
    }
    
    public String scorefin(){
        String str;
            int res[] = new int[2];
            if(plateau.tourBlanc()){
                res[0]=plateau.scoreBlanc();
                res[1]=64-res[0];
            }
            else{
                res[1]=plateau.scoreNoir();
                res[0]=64-res[1];
            }
            if (res[1]>res[0]){
                str = "Noir gagne ";
                score[1]++;
            }
            else if (res[1]<res[0]){
                str = "Blanc gagne ";
                score[0]++;
            }
            else
                str="Match nul ";
            str+=""+max(res[1],res[0])+" à "+min(res[1],res[0]);
        return str;
    }

    public void joueria(boolean blanc) throws NoFreeCaseException{
        if (blanc)
            plateau.jouer(iablanc.mouvement());
        else
            plateau.jouer(ianoir.mouvement());
        actualiser();
    }
    
}