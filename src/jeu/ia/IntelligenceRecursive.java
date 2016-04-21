package jeu.ia;
import static java.lang.Math.*;
import java.util.ArrayList;
import jeu.Plateau;

public class IntelligenceRecursive extends IntelligenceBase{
    private int niv;
    public IntelligenceRecursive(Plateau plate, int niveauRecu) {
        super(plate);
        niv=niveauRecu;
    }
    
    public int scoreMin(ArrayList<Integer> file, Plateau copie, int scoremin){
        if(file.isEmpty())
            return scoremin;
        Plateau futur = simuler(copie, file.get(0));
        file.remove(0);
        return scoreMin(file,copie,min((plateau.tourBlanc() ? futur.scoreBlanc() : futur.scoreNoir()),scoremin));
    }

    
    @Override
    public int mouvement() throws NoFreeCaseException {
        return coupIa(plateau,niv)[0];
    }
    
    public int[] coupIa(Plateau plate, int recu) throws NoFreeCaseException{
        Plateau copie;
        int[] meilleurCoup = new int[2];
        meilleurCoup[1]=-1;
        ArrayList<Integer> jouables;
        jouables = casesJouables(plate);
        int score;
        for(int i=0;i<jouables.size();i++){
            copie = simuler(plate, jouables.get(i));
            if(recu ==0)
                score = scoreMin(casesJouables(copie),copie,65);
            else
                score = coupIa(copie, recu -1)[1];
            if(score>meilleurCoup[1]){
                meilleurCoup[1] = score;
                meilleurCoup[0] = jouables.get(i);
            }
        }
        return meilleurCoup;
    }
}
