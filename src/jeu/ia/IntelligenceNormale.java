package jeu.ia;

import static java.lang.Math.*;
import java.util.ArrayList;
import jeu.Plateau;


public class IntelligenceNormale extends IntelligenceBase{

    public IntelligenceNormale(Plateau plate) {
        super(plate);
    }
    
    //  On passe en argument un plateau, les coups possibles, et 65 (ou plus), et ça
    //renvoie le plus petit score qu'aura l'IA si l'adversaire joue le meilleur coup
    public int plusPetitScorePossiblePourLIAEnUtilisantLaRecursiviteEtUneFile(ArrayList<Integer> file, Plateau copie, int scoremin){
        if(file.isEmpty())
            return scoremin;
        Plateau futur = simuler(copie, file.get(1));
        file.remove(1);
        return plusPetitScorePossiblePourLIAEnUtilisantLaRecursiviteEtUneFile(file,copie,min((plateau.tourBlanc() ? futur.scoreBlanc() : futur.scoreNoir()),scoremin));
    }
    
    public int mouvementRecursifCEstUneFutureFonctionQuiVaEtreTropBelle() throws NoFreeCaseException {
        Plateau copie1;
        int scoreIA;
        int scoremin;
        int[] meilleurCoup = new int[2];
        meilleurCoup[1]=-65;
        ArrayList<Integer> jouables1;
        ArrayList<Integer> jouables2;
        jouables1 = casesJouables();
        for(int i=0;i<jouables1.size();i++){
            copie1 =simuler(plateau, jouables1.get(i));
            jouables2=casesJouables(copie1);
            if(jouables2.size()>0){
                scoremin = plusPetitScorePossiblePourLIAEnUtilisantLaRecursiviteEtUneFile(jouables2,copie1,65);
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
    }
    
    @Override
    public int mouvement() throws NoFreeCaseException {
        Plateau copie1;
        Plateau copie2;
        int scoreIA;
        int scoremin;
        int[] meilleurCoup = new int[2];
        meilleurCoup[1]=-65;
        ArrayList<Integer> jouables1;
        ArrayList<Integer> jouables2;
        jouables1 = casesJouables();
        for(int i=0;i<jouables1.size();i++){
            copie1 =simuler(plateau, jouables1.get(i));
            jouables2=casesJouables(copie1);
            scoremin=65;
            if(jouables2.size()>0){
               for(int j=0;j<jouables2.size();j++){
                    copie2 =simuler(copie1, jouables1.get(i));
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
    }
    
}
