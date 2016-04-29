package jeu.ia;

import java.util.ArrayList;
import java.util.Random;
import jeu.Plateau;

public class IntelligenceMinMax extends IntelligenceBase{
    private int prof;
    public IntelligenceMinMax(Plateau plate, int profondeur) {
        super(plate);
        prof = profondeur;
    }
    
    @Override
    public int mouvement() throws NoFreeCaseException {
        int maxval=-65;
        Random rnd  = new Random();
        Plateau copie;
        int val;
        ArrayList<Integer> positionsMeilleurCoup = new ArrayList<>();
        ArrayList<Integer> jouables= casesJouables();
        for(int i=0;i<jouables.size();i++){
            copie =simuler(plateau, jouables.get(i));
            val=iaMin(copie,prof);
            if(val==maxval)
                positionsMeilleurCoup.add(jouables.get(i));
            else if(val>maxval){
                maxval = val;
                positionsMeilleurCoup.clear();
                positionsMeilleurCoup.add(jouables.get(i));
            }
        }
        return positionsMeilleurCoup.get(rnd.nextInt(positionsMeilleurCoup.size()));
    }
    
    public int iaMin(Plateau plate, int prof) throws NoFreeCaseException{
        if(prof<1||plate.termine())
            return scoreIa(plate);
        int minval=65;
        Plateau copie;
        int val;
        ArrayList<Integer> jouables= casesJouables(plate);
        for(int i=0;i<jouables.size();i++){
            copie =simuler(plate, jouables.get(i));
            val=iaMax(copie,prof-1);
            if(val<minval)
                minval=val;
        }
        return minval;
    }
    
    public int iaMax(Plateau plate, int prof) throws NoFreeCaseException{
        if(prof<1||plate.termine())
            return scoreIa(plate);
        int maxval=-65;
        Plateau copie;
        int val;
        ArrayList<Integer> jouables= casesJouables(plate);
        for(int i=0;i<jouables.size();i++){
            copie =simuler(plate, jouables.get(i));
            val=iaMin(copie,prof-1);
            if(val>maxval)
                maxval=val;
        }
        return maxval;
    }
                
}
