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
    private JFrame victoire;

    private Plateau plateau;
    private int[] score;
    private IntelligenceBase iablanc;
    private IntelligenceBase ianoir;
    
    private Timer timer;


    public Fenetre(Plateau plateau){

        this.plateau = plateau;
        ianoir = null;
        iablanc = null;
        score = new int[2]; score[0]=0; score[1]=0;
        
        this.setTitle("Reverso"); //titre
        this.setSize(500, 500); // taille de la fenetre (500x500)
        this.setLocationRelativeTo(null); // centré 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //bouton croix activé (quitte le processus lorsqu'on appuye dessus            
        this.setResizable(true); // possibilité de modifier la taille de la fenêtre
        
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
            CaseG caseA = new CaseG(plateau.getDamier()[i]);
            caseA.addMouseListener(new ListenerCase(i,this));
            container_centre.add(caseA);
        }
        container_centre.repaint();
        
        this.getContentPane().add(container_nord,BorderLayout.NORTH);
        this.getContentPane().add(container_centre,BorderLayout.CENTER);
        this.getContentPane().add(container_sud,BorderLayout.SOUTH);
        actualiser();

        this.setVisible(true); // rend visible la fenêtre
        
        
        // Timer d'update
        timer = new Timer(10, (ActionEvent ae) -> {
            
            if (!this.plateau.termine()){
                try {
                    if(this.plateau.tourBlanc() && iablanc != null)
                        this.joueria(true);
                    else if(!this.plateau.tourBlanc() && ianoir != null)
                        this.joueria(false);
                } catch (NoFreeCaseException ex) {
                    Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Si ça s'affiche voir avec Axel et ses exceptions bizarres !");
                }
            }
            this.actualiser();
        });
        timer.setRepeats(true);
    }

    
    public void barremenu(){

        String[] choix = {"Joueur", "Bot Aléatoire", "Bot Axel", "Bot Lilian"};
        JButton valider = new JButton("Jouer");
        JComboBox blanc = new JComboBox(choix);
        JComboBox noir = new JComboBox(choix);
        blanc.setPreferredSize(new Dimension(100, 20));
        noir.setPreferredSize(new Dimension(100, 20));
        container_nord.add(new JLabel("   Blanc :"));
        container_nord.add(blanc);
        container_nord.add(new JLabel("   Noir :"));
        container_nord.add(noir);
        container_nord.add(new JLabel("   "));
        container_nord.add(valider);
        valider.addActionListener((ActionEvent e) -> {
            if (victoire != null)
                victoire.dispose();
            this.plateau = new Jeu();
            switch(getChoix(blanc.getSelectedItem().toString(),choix)){
                case -1: System.out.println("Erreur menu déroulant blanc");break;
                case 1: iablanc=null; break;
                case 2: iablanc=new IntelligenceHasard(plateau); break;
                case 3: iablanc=new IntelligenceDiff(plateau); break;
                case 4: iablanc=new IntelligenceValuationMaxIA(plateau); break;
                default: iablanc=null; break;
            }
            switch(getChoix(noir.getSelectedItem().toString(),choix)){
                case -1: System.out.println("Erreur menu déroulant noir");break;
                case 1: ianoir=null; break;
                case 2: ianoir=new IntelligenceHasard(plateau); break;
                case 3: ianoir=new IntelligenceDiff(plateau); break;
                case 4: ianoir=new IntelligenceValuationMaxIA(plateau); break;
                default: ianoir=null; break;
            }
            actualiser();
            if (ianoir != null)
                timer.start();
        });
    }
    
    public int getChoix(String str, String[] tab){
        for(int i=0;i<tab.length;i++)
            if(str.equals(tab[i]))
                return i+1;
        return -1;
    }


    public void clicCase(int id){
        if (plateau.getDamier()[id].jouable()){
            if((plateau.tourBlanc()&& iablanc == null) || (!plateau.tourBlanc()&& ianoir == null)){
                plateau.jouer(id);
                actualiser();
                if (!plateau.termine() && 
                        (plateau.tourBlanc() && iablanc != null || !plateau.tourBlanc() && ianoir != null))
                    timer.start();
            }
        }
    }

    public void actualiser(){
        String str;
        Component[] cases = container_centre.getComponents();
        for (int i=0; i<64; i++){
            boolean ia = (plateau.tourBlanc() && iablanc!=null) || (!plateau.tourBlanc() && ianoir!=null);
            ((CaseG) cases[i]).update(plateau.getDamier()[i],plateau.tourBlanc(),ia);
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
    }
    
    public String scorefin(){
        String str;
        victoire = new JFrame();
        victoire.setSize(220, 255);
        victoire.setLocationRelativeTo(this);
        victoire.setVisible(true);
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
            victoire.add(new JLabel( new ImageIcon("VictoireN.jpg")));
        }
        else if (res[1]<res[0]){
            str = "Blanc gagne ";
            score[0]++;
            victoire.add(new JLabel( new ImageIcon("VictoireB.jpg")));
        }
        else{
            str="Match nul ";
            victoire.add(new JLabel( new ImageIcon("Null.jpg")));
        }
        str+=""+max(res[1],res[0])+" à "+min(res[1],res[0]);
        JLabel textFin = new JLabel(str);
        textFin.setHorizontalAlignment(SwingConstants.CENTER);
        victoire.add(textFin,BorderLayout.NORTH);
        return str;
    }

    public void joueria(boolean blanc) throws NoFreeCaseException{
        if (blanc)
            jouerpouria(iablanc.mouvement());
        else
            jouerpouria(ianoir.mouvement());
        
        if (plateau.termine() || 
                        (plateau.tourBlanc() && iablanc == null || !plateau.tourBlanc() && ianoir == null))
                    timer.stop();
    }
    
    public void jouerpouria(int id){
        if (plateau.getDamier()[id].jouable())
            if((plateau.tourBlanc()&& iablanc != null) || (!plateau.tourBlanc()&& ianoir != null))
                plateau.jouer(id);
    }
    
    public Plateau getPlateau(){
        return plateau;
    }

}

