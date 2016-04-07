/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.ia;

import java.util.ArrayList;
import java.util.Random;
import jeu.Plateau;

/**
 *
 * @author Podoko
 */
public class IntelligenceNormale extends IntelligenceBase{

    public IntelligenceNormale(Plateau plate) {
        super(plate);
    }
    
    
    
    
    @Override
    public int mouvement() throws NoFreeCaseException {
        Plateau copie1;
        Plateau copie2;
        int scoreIA;
        int scoremin;
        int[] meilleurCoup = new int[2];
        meilleurCoup[1]=-65;
        Random rnd  = new Random();
        ArrayList<Integer> jouables1;
        ArrayList<Integer> jouables2;
        jouables1 = casesJouables();
        for(int i=0;i<jouables1.size();i++){
            copie1 =simuler(plateau, jouables1.get(i));
            jouables2=casesJouables(copie1);
            scoremin=65;
            if(jouables2.size()>0){
               for(int j=0;j<jouables2.size();j++){
                    copie2 =simuler(plateau, jouables1.get(i));
                    scoreIA = (plateau.tourBlanc() ? copie2.scoreBlanc() : copie2.scoreNoir());
                    if(scoreIA < scoremin)
                        scoremin = scoreIA;
                }
               if(scoremin>meilleurCoup[1]){
                    meilleurCoup[1] = scoremin;
                    meilleurCoup[0] = i;
                }
            }
            else{
                scoreIA=(plateau.tourBlanc() ? copie1.scoreBlanc() : copie1.scoreNoir());
                if(scoreIA>meilleurCoup[1]){
                    meilleurCoup[1] = scoreIA;
                    meilleurCoup[0] = i;
                }
            }  
        }
        
        return jouables1.get(meilleurCoup[0]);
        
        //throw new UnsupportedOperationException("Not supported yet."); 
        //To change body of generated methods, choose Tools | Templates.
    }
    
}
