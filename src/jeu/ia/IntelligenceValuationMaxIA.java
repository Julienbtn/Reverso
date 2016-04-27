package jeu.ia;

import static java.lang.Math.abs;
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
    
    public void coinPris(int angle){
        // Si on prend un angle, on change la valeur des cases adjacentes,
        // en prenant en priorité les cases sur le bord.
        int valdiag =abs(valeurs[9]);
        int valligne=abs(valeurs[1]);
        if(angle==0){
            valeurs[1]=valeurs[8]=valdiag;
            valeurs[9]=valligne;
        }
        else if(angle==7){
            valeurs[6]=valeurs[15]=valdiag;
            valeurs[14]=valligne;
        }
        else if(angle==56){
            valeurs[48]=valeurs[57]=valdiag;
            valeurs[49]=valligne;
        }
        else if(angle==63){
            valeurs[62]=valeurs[55]=valdiag;
            valeurs[54]=valligne;
        }
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
        switch(jouables.get(meilleurCoup[0])){
            case 0: coinPris(0);break;
            case 7: coinPris(7);break;
            case 56: coinPris(56);break;
            case 63: coinPris(63);break;
        }
        return jouables.get(meilleurCoup[0]);
    }
}
