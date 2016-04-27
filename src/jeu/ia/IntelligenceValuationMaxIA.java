package jeu.ia;

import java.util.ArrayList;
import jeu.Plateau;

public class IntelligenceValuationMaxIA extends IntelligenceBase{
    private int[] valeurs;
    
    public IntelligenceValuationMaxIA(Plateau plate) {
        super(plate);
        valeurs = creerGrille();
    }
    
    public int[] creerGrille(){
        int[] grille = new int[64];
        // On crée un patron correspondant au coin supérieur gauche
        int[] patron = new int[16];
        patron[0]=500;
        patron[1]=patron[4]=-150;
        patron[2]=patron[8]=30;
        patron[3]=patron[12]=10;
        patron[5]=-250;
        patron[6]=patron[7]=patron[9]=patron[13]=0;
        patron[10]=1;
        patron[11]=patron[14]=2;
        patron[15]=16;
        //On place le patron dans la grille, en le retournant si nécessaire
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
                if(i<4){
                    if(j<4){
                        grille[8*i+j]=patron[4*i+j];}//coin sup gauche
                    else{
                        grille[8*i+j]=patron[4*i+(3-(j%4))];}//coin sup droit
                }
                else {
                    if(j<4){
                        grille[8*i+j]=patron[4*(3-(i%4))+j];}//coin inf gauche
                    else{
                        grille[8*i+j]=patron[4*(3-(i%4))+(3-(j%4))];}//coin inf droit
                }
        return grille;
    }
    
    public int calculerPoints(Plateau plate,boolean blanc){
        int score = 0;
        for(int i= 0;i<64;i++)
            if(plate.getDamier()[i].remplie())
                if(plate.getDamier()[i].blanche()==blanc)
                    score+=valeurs[i];
        return score;
    }
    
    
    @Override
    public int mouvement() throws NoFreeCaseException {
        Plateau copie;
        int scoreIA;
        int[] meilleurCoup = new int[2];
        meilleurCoup[1]=-3000;
        ArrayList<Integer> jouables;
        jouables = casesJouables();
        for(int i=0;i<jouables.size();i++){
            copie =simuler(plateau, jouables.get(i));
            scoreIA= calculerPoints(copie,plateau.tourBlanc());
            if(scoreIA>meilleurCoup[1]){
                meilleurCoup[1] = scoreIA;
                meilleurCoup[0] = i;
            }
        }
        return jouables.get(meilleurCoup[0]);
    }
}
