// package de l'interface Homme-Machine gérant l'interface graphique
package ihm.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import static java.lang.Math.max;
import static java.lang.Math.min;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import jeu.*;
import jeu.core.Jeu;
import jeu.ia.*;

public class Fenetre extends JFrame{
    // définition des différents composants de la fenêtre
    private final JPanel container_nord;
    private final JPanel container_centre;
    private final JPanel container_sud;
    private JFrame victoire;

    /**
     * Plateau contenant la partie affichée par l'ihm
     */
    private Plateau plateau;
    /**
     * Scorces des joueurs [blanc, noir]
     */
    private final int[] score;
    /**
     * Intelligenge artificielle pour le joueur blanc si il y en a une
     * si null, joueur normal
     */
    private IntelligenceBase iablanc;
    /**
     * Intelligence artificielle pour le joueur noir
     * si null, joueur normal
     */
    private IntelligenceBase ianoir;
    
    /**
     * Timer faisant régulièrement jouer les intelligences artificielles si besoin
     */
    private final Timer timer;

    
    /**
     * Constructeur de la fenêtre avec un plateau passé en paramètre
     * 
     * @param plateau Jeu à gerer et afficher par la fenêtre
     */
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
        
        // Définition des différents containers de la fenêtre 
        container_nord = new JPanel();
        container_centre = new JPanel();
        container_sud = new JPanel();
        
        //Haut de la fenêtre
        barremenu();
        
        //Milieu de la fenêtre
        GridLayout grille = new GridLayout(8,8);
        container_centre.setLayout(grille);
        
        // Créations des cases, ajout d'un listener pour les cliques dessus
        // puis ajout de la case dans la grille du terrain.
        for (int i = 0; i<64; i++)
        {
            CaseG caseA = new CaseG(plateau.getDamier()[i]);
            caseA.addMouseListener(new ListenerCase(i,this));
            container_centre.add(caseA);
        }
        container_centre.repaint();
        
        // On place les différents container dans la fenêtre 
        this.getContentPane().add(container_nord,BorderLayout.NORTH);
        this.getContentPane().add(container_centre,BorderLayout.CENTER);
        this.getContentPane().add(container_sud,BorderLayout.SOUTH);
        actualiser();

        this.setVisible(true); // rend visible la fenêtre
        
        
        // Implémentation du timer qui fait jouer les ias toutes les 10ms
        timer = new Timer(10, (ActionEvent ae) -> {
            
            if (!this.plateau.termine()){
                try {
                    // Si c'est au blanc de jouer et si c'est une ia qui le gere
                    // jouer pour lui
                    if(this.plateau.tourBlanc() && iablanc != null)
                        this.joueria(true);
                    // Sinon, si c'est au noir de jouer et si c'est une ia qui 
                    // le gere jouer pour lui
                    else if(!this.plateau.tourBlanc() && ianoir != null)
                        this.joueria(false);
                } catch (NoFreeCaseException ex) {
                    Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            // Actualisation de la fênetre après avoir joué
            this.actualiser();
        });
        
        timer.setRepeats(true);
    }

    
    /**
     * méthode qui crée la barre de menu placé en haut de la fenêtre 
     */
    private void barremenu(){

        String[] choix = {"Joueur","Aléatoire","Valuation","MinMax",
            "Fusion","ValuationRecursive"};
        JButton valider = new JButton("Jouer");
        JComboBox blanc = new JComboBox(choix);
        JComboBox noir = new JComboBox(choix);
        blanc.setPreferredSize(new Dimension(100, 20));
        noir.setPreferredSize(new Dimension(100, 20));
        JCheckBox reini = new JCheckBox("Réini");
        container_nord.add(new JLabel("Blanc :"));
        container_nord.add(blanc);
        container_nord.add(new JLabel(" Noir :"));
        container_nord.add(noir);
        container_nord.add(new JLabel(" "));
        container_nord.add(reini);
        container_nord.add(new JLabel(" "));
        container_nord.add(valider);
        valider.addActionListener((ActionEvent e) -> {
            if (reini.isSelected()){
                reini.setSelected(false);
                score[0]=score[1]=0;
            }
            if (victoire != null)
                victoire.dispose();
            this.plateau = new Jeu();
            iablanc = choixMenu(getChoix(blanc.getSelectedItem().toString(),choix));
            ianoir = choixMenu(getChoix(noir.getSelectedItem().toString(),choix));
            actualiser();
            if (ianoir != null)
                timer.start();
        });
    }
    
    /**
     * Crée et renvoie une intelligence artificielle ou null en fonction du champ 
     * sélectionné dans la barre de menu.
     * 
     * @param choix identifiant dans la barre de menu de l'ia sélectionnée
     * @return l'intelligence artificielle sélectionnée ou null
     */
    private IntelligenceBase choixMenu(int choix){
        switch(choix){
            case 1: return null;
            case 2: return new IntelligenceHasard(plateau);
            case 3: return new IntelligenceValuation(plateau);
            case 4: return new IntelligenceMinMax(plateau,4);
            case 5: return new IntelligenceFusion(plateau,3);
            case 6: return new IntelligenceValuationRecursive(plateau,3);
            default: System.out.println("Erreur choix ia");return null;
        }
    }
    
    
    /**
     * fonction qui retourne le choix du type d'utilisateur sélectionné dans 
     * la barre de menu
     * 
     * @param str Contenu du champ sélectionné dans la barre de menu
     * @param tab contenu des champs de la barre de menu
     * @return numéro du champ sélectionné ou -1 en cas d'erreur
     */
    private int getChoix(String str, String[] tab){
        for(int i=0;i<tab.length;i++)
            if(str.equals(tab[i]))
                return i+1;
        return -1;
    }

    
    /**
     * méthode qui permet de jouer sur la case cliquée
     * 
     * <p>Appelée par un ListenerCase quand une personne clique sur une case 
     * du damier. Si la case est jouable et si le joueur qui doit jouer n'est 
     * pas une intelligence artificielle, alors la case est jouée.</p>
     * 
     * @param id identifiant de la case cliquée / à jouer.
     */
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

    
    /**
     * méthode qui actualise la fenêtre
     */
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
    
    /**
     * Méthode qui affiche le score final en bas et qui lance 
     * une popup avec une image en fonction du joueur gagnant
     * 
     * @return message affuiché dans la fenêtre affichée
     */
    private String scorefin(){
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

    
    /**
     * méthode qui fait jouer l'ia.
     * @param blanc true s'il faut jouer pour le joueur blanc, false sinon
     * @throws NoFreeCaseException si aucune case n'est jouable sur le plateau
     */
    private void joueria(boolean blanc) throws NoFreeCaseException{
        if (blanc)
            jouerpouria(iablanc.mouvement());
        else
            jouerpouria(ianoir.mouvement());
        if (plateau.termine() || 
                        (plateau.tourBlanc() && iablanc == null || !plateau.tourBlanc() && ianoir == null))
            // Arrête le timer si ce n'est plus à une ia de jouer 
            // (sera relancé si besoin)
            timer.stop();
    }
    
    /**
     * méthode qui permet de jouer un coup désigné par une ia
     * @param id identifiant de la case à jouer
     */ 
    private void jouerpouria(int id){
        if (plateau.getDamier()[id].jouable())
            if((plateau.tourBlanc()&& iablanc != null) || (!plateau.tourBlanc()&& ianoir != null))
                plateau.jouer(id);
    }


}
