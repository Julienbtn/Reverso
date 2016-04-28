/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.ia;

import java.util.ArrayList;
import jeu.Plateau;


/**
 * Intelligence artificielle min-max.
 * 
 * <p>Cette intelligence propose des coups en cherchant à obtenir le plus de cases
 * après un certain nombre de coups.</p>
 * 
 * @see IntelligenceBase
 */
public class IntelligenceDiff extends IntelligenceBase {
    
    /**
     * Nombre de coups anticipés par l'ia. 5 par défaut.
     */
    private int profRec;
    
    /**
     * Initialise l'ia en la configurant pour prévoir le nombre de coups par défaut.
     * 
     * @param plate Plateau sur lequel jouera l'ia
     */
    public IntelligenceDiff(Plateau plate) {
        this(plate, 5);
    }
    
    
    /**
     * Initialise l'ia en la configurant pour prévoir le nombre de coups indiqués
     * @param plate Plateau sur lequel jouera l'ia.
     * @param i Nombre de coups à anticiper par l'ia.
     */
    public IntelligenceDiff(Plateau plate, int i) {
        super(plate);
        profRec = i;
    }
    
    
    /**
     * Indique le meilleur coup de l'ennemi de l'ia et le score de l'ia après ce coup.
     * 
     * @param prof Nombre de coups restant à anticiper
     * @param plate Plateau à partir duquel il faut 
     * @return Tableau contenant l'indice de la meilleure case à jouer pour 
     * l'ennemi de l'ia et le score final de l'ia après ce coup.
     * @throws NoFreeCaseException si aucune case n'est jouable sur le plateau passé.
     */
    private int[] meilleurJo(int prof, Plateau plate) throws NoFreeCaseException{
        
        ArrayList<Integer> cases = casesJouables(plate);
        Plateau simul;
        int[] best = null;
        
        // pour toutes les cases jouables sur le plateau donné, simuler un coup.
        // et récupérer le score de l'ia après ce coup.
        for (Integer id : cases) {
            simul = simuler(plate, id);
            int[] score = {id, scoreIa(simul)};
            
            if (best == null) // Initialisation si besoin
                best = score;
            
            // Si on a simué assez de coups à l'avance.
            // Si le coup simulé est pire pour l'ia que les précedents simulés
            // on le sauvegarde
            if (prof < 1){
                if (score[1] <= best[1])
                    best = score;
                
            }else{ // Encore des tours à jouer
                // Si c'est encore à l'ia de jouer, le score sera celui si on rejoue
                // Sinon ce sera celui si l'autre joue bien
                if (!simul.termine()) // Vérifier aussi si c'est pas déjà terminé
                    score = tourIa(simul)? meilleurIa(prof-1, simul) : meilleurJo(prof-1, simul);
                
                // Si le coup simulé est pire pour l'ia que les précedents simulés
                // on le sauvegarde
                if (score[1] <= best[1])
                    best[1] = score[1];
            }
        }
        
        return best;
    }
    
    
    
    
    private int[] meilleurIa(int prof, Plateau plate) throws NoFreeCaseException{
        // System.out.println("Inoir " + prof);
        ArrayList<Integer> cases = casesJouables(plate);
        // System.out.println("IA " + prof +" Poss : " + cases.size());
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
                
                if (score[1] >= best[1]){
                    best[1] = score[1];
                }
            }
            
            
        }
        return best;
    }
    
    
    
    
    
    
    
    
    @Override
    public int mouvement() throws NoFreeCaseException {
        return meilleurIa(profRec, plateau)[0];
    }
    
}
