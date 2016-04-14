package ihm.gui;

import java.awt.*;
import java.awt.event.*;
import static java.lang.Math.max;
import static java.lang.Math.min;
import javax.swing.*;
import jeu.*;
import jeu.core.Jeu;


public class Fenetre extends JFrame implements ActionListener {
    private final JPanel container_nord;
    private final JPanel container_centre;
    private final JPanel container_sud;

    private Plateau plateau;
    private Case[] damier;
    private boolean ianoir;
    private boolean iablanc;
    private int[] score;


    public Fenetre(Plateau plateau){

        this.plateau = plateau;
        this.damier = plateau.getDamier();
        ianoir = false;
        iablanc = false;
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

        this.getContentPane().add(container_nord,BorderLayout.NORTH);
        this.getContentPane().add(container_centre,BorderLayout.CENTER);
        this.getContentPane().add(container_sud,BorderLayout.SOUTH);
        actualiser();

        this.setVisible(true); // rend visible la fenêtre
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void barremenu(){
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Rejouer");
        JMenuItem jvj = new JMenuItem("2 joueurs");
        JMenuItem ia1 = new JMenuItem("IA lvl1");
        menu.add(jvj);
        menu.add(ia1);
        menuBar.add(menu);
        jvj.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent e)
                            {
                                    pvp();
                            }
                    });
        this.setJMenuBar(menuBar);
        ia1.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent e)
                            {
                                    ia1();
                            }
                    });
        this.setJMenuBar(menuBar);
    }


    public void clicCase(int id){
        if (damier[id].jouable()){
            if((plateau.tourBlanc()&& !iablanc) || (!plateau.tourBlanc()&& !ianoir)){
                plateau.jouer(id);
                actualiser();
            }
        }
    }

    public void pvp(){
        this.plateau = new Jeu();
        ianoir = false;
        iablanc = false;
        this.actualiser();
    }
    
    public void ia1(){
        this.plateau = new Jeu();
        this.actualiser();
    }

    public void actualiser(){
        String str;
        damier = plateau.getDamier();
        Component[] cases = container_centre.getComponents();
        for (int i=0; i<64; i++){
            ((CaseG) cases[i]).update(damier[i],plateau.tourBlanc());
        }
        
        container_sud.removeAll();
        if(plateau.termine())
            str=scorefin();
        else{
            str ="Blanc : "+plateau.scoreBlanc()+" Noir : "+plateau.scoreNoir();
            str+=" Au tour de : "+(plateau.tourBlanc()? "blanc" : "noir");
            if(plateau.passe())
            str+="Tour passé";
        }
        container_sud.add(new JLabel(str));
        
        container_nord.removeAll();
        str="Victoires : Blanc : "+score[0]+" Noir : "+score[1];
        container_nord.add(new JLabel(str));
        
        revalidate();
        repaint();
        if(plateau.tourBlanc()&&iablanc)
            joueria(true);
        if(!plateau.tourBlanc()&&ianoir)
            joueria(false);
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

    public void joueria(boolean blanc){

    }
    
}