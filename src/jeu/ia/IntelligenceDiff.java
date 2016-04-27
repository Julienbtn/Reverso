/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.ia;

import java.util.ArrayList;
import jeu.Plateau;

/**
 *
 * @author Podoko
 */
public class IntelligenceDiff extends IntelligenceBase {

    public IntelligenceDiff(Plateau plate) {
        super(plate);
    }
    
    private int[] meilleurJo(int prof, Plateau plate) throws NoFreeCaseException{
        ArrayList<Integer> cases = casesJouables(plate);
        System.out.println("\tJO " + prof +" Poss : " + cases.size());
        Plateau simul;
        int[] best = null;
        
        for (Integer id : cases) {
            simul = simuler(plate, id);
            int[] score = {id, scoreIa(simul)};
            if (best == null)
                best = score;
            
            if (prof < 1){
                if (score[1] <= best[1])
                    best = score;
                
            }else{ // Encore des tours à jouer
                // Si c'est encore à l'ia de jouer, le score sera celui si on rejoue
                // Sinon ce sera celui si l'autre joue bien
                if (!simul.termine()) // Vérifier aussi si c'est pas déjà terminé
                    score = tourIa(simul)? meilleurIa(prof-1, simul) : meilleurJo(prof-1, simul);
                
                if (score[1] <= best[1])
                    best = score;
            }
        }
        
        return best;
    }
    
    
    
    
    private int[] meilleurIa(int prof, Plateau plate) throws NoFreeCaseException{
        
        ArrayList<Integer> cases = casesJouables(plate);
        System.out.println("IA " + prof +" Poss : " + cases.size());
        Plateau simul;
        int[] best = null;
        
        for (Integer id : cases) {
            // Simulation du coup et résultat de la simulation
            simul = simuler(plate, id);
            int[] score = {id, scoreIa(simul)};
            if (best == null)
                best = score;
                
            // Si on doit renvoyer
            if (prof < 1){
                if (score[1] >= best[1])
                    best = score;
                
            }else{ // Encore des tours à jouer
                // Si c'est encore à l'ia de jouer, le score sera celui si on rejoue
                // Sinon ce sera celui si l'autre joue bien
                if (!simul.termine()) // Vérifier aussi si c'est pas déjà terminé
                    score = tourIa(simul)? meilleurIa(prof-1, simul) : meilleurJo(prof-1, simul);
                
                if (score[1] >= best[1])
                    best = score;
            }
            
            
        }
        
        return best;
    }
    
    
    
    
    
    
    
    
    @Override
    public int mouvement() throws NoFreeCaseException {
        System.out.println("Appel");
        return meilleurIa(1, plateau)[0];
    }
    
}
