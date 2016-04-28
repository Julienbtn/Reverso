package jeu.ia;

import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Random;
import jeu.Case;
import jeu.Plateau;

public class IntelligenceValuationRecursive extends IntelligenceBase{
    // Grille de valeur des cases
    private int[] valeurs;
    int prof;
    
    public IntelligenceValuationRecursive(Plateau plate, int prof) {
        super(plate);
        valeurs = creerGrille();
        this.prof = prof;
    }
    
    // Constructeur de la grille
    public int[] creerGrille(){
        int[] grille = new int[64];
        // On crée un patron correspondant au coin supérieur gauche
        int[] patron = new int[16];
        patron[0]=1000;//angle
        patron[1]=patron[4]=-75;//bords contres angle
        patron[2]=patron[8]=20;//bords proches angle
        patron[3]=patron[12]=8;//bords centre
        patron[5]=-200;//diag contre angle
        patron[6]=patron[7]=patron[9]=patron[13]=-10;//cases proches bord
        patron[10]=patron[11]=patron[14]=2;//cases autour centre
        patron[15]=20;//case du centre
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
    
    // Calcul les points qu'à l'IA en utilisant la grille
    // Si il y a 4 cases libres ou moints, on calcule le nombre de pions
    public int calculerPoints(Plateau plate,boolean blanc){
        int score = 0;
        // Si le joueur possède un angle les cases autour sont avantageuses
        boolean hautgauche,hautdroit,basgauche,basdroit;
        hautgauche=possedeCase(plate.getDamier()[0],blanc);
        hautdroit=possedeCase(plate.getDamier()[7],blanc);
        basgauche=possedeCase(plate.getDamier()[56],blanc);
        basdroit=possedeCase(plate.getDamier()[63],blanc);
        // Si l'angle est pris la valeur de la case à coté en ligne
        // devient abs(case diag) et inversement
        int valdiag =abs(valeurs[9]);
        int valligne=abs(valeurs[1]);
        // Si ce n'est pas les 2 derniers coups on regarde la grille
        if(plate.nbCasesLibres()>3){
            for(int i= 0;i<64;i++){
                if(possedeCase(plate.getDamier()[i],blanc)){
                     switch(i){
                        case 1:case 8:score+=(hautgauche?valdiag:valeurs[i]);break;
                        case 9:score+=(hautgauche?valligne:valeurs[i]);break;
                        case 6:case 15:score+=(hautdroit?valdiag:valeurs[i]);break;
                        case 14:score+=(hautdroit?valligne:valeurs[i]);break;
                        case 48:case 57:score+=(basgauche?valdiag:valeurs[i]);break;
                        case 49:score+=(basgauche?valligne:valeurs[i]);break;
                        case 55:case 62:score+=(basdroit?valdiag:valeurs[i]);break;
                        case 54:score+=(basdroit?valligne:valeurs[i]);break;
                        default:score+=valeurs[i];break;
                    }
                }
            }
        }
        else { //Sinon on compte les pions
            score = (blanc?plate.scoreBlanc():plate.scoreNoir());
        }
        return score;
    }
    
    // Renvoie vrai si le joueur possède la case
    public boolean possedeCase(Case c, boolean joueur){
        return c.remplie() && c.blanche()==joueur;
    }
    
    public int[] mouvementRecu(Plateau p,int recu) throws NoFreeCaseException{
        Random rnd = new Random();
        Plateau copie;
        ArrayList<Integer> jouables= casesJouables(p);
        ArrayList<int[]> listeBest = new ArrayList<>();
        // On enregistre tous les meilleurs coups dans un tableau pour
        // en retourner un au hasard
        // ATTENTION : On stock des pointeurs, donc si on modifie et
        // ajoute la variable best à chaque fois chaque case aura la
        // même valeur
        int[] best = {-1,-99999};
        if(recu>=1)
            best[1] = 99999;
        int score;
        for(int i=0;i<jouables.size();i++){
            // On simule un coup et on calcule 
            // les points DU JOUEUR POUR QUI ON SIMULE
            copie = simuler(p, jouables.get(i));
            score = calculerPoints(copie, p.tourBlanc());
            // Si la récu est fini, ça simule le plus gros score
            if (recu < 1){
                if (score > best[1]){
                    best[0]=jouables.get(i);
                    best[1] = score;
                    listeBest.clear();
                    listeBest.add(best);
                }
                else if(score==best[1]){
                    listeBest.add(new int[] {jouables.get(i),score});
                }
            }
            // Sinon on essaye de donner le plus petit score à l'adversaire
            // (qui joue exactement comme nous)
            else{
                score = mouvementRecu(copie, recu -1)[0];
                if (score < best[1]){
                    best[0]=jouables.get(i);
                    best[1] = score;
                    listeBest.clear();
                    listeBest.add(best);
                }
                else if(score==best[1]){
                    listeBest.add(new int[] {jouables.get(i),score});
                }
            }
        }
        return listeBest.get(rnd.nextInt(listeBest.size()));
    }
        
    // L'ia renvoie la case sur laquelle elle va jouer
    @Override
    public int mouvement() throws NoFreeCaseException {
        return mouvementRecu(plateau, prof)[0];
    }
}
