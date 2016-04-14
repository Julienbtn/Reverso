package jeu.ia;
import static java.lang.Math.*;
import java.util.ArrayList;
import java.util.Random;
import jeu.Plateau;

public class IntelligenceRecursive extends IntelligenceBase{
    public IntelligenceRecursive(Plateau plate) {
        super(plate);
    }
    
    public int scoreMin(ArrayList<Integer> file, Plateau copie, int scoremin, int recu){
        if(file.isEmpty())
            return scoremin;
        Plateau futur = simuler(copie, file.get(0));
        file.remove(0);
        return minAdv(file,copie,min((plateau.tourBlanc() ? futur.scoreBlanc() : futur.scoreNoir()),scoremin), recu -1);
    }

    
    @Override
    public int mouvement() throws NoFreeCaseException {
        
    }
}
