package jeu.ia;

import java.util.ArrayList;
import jeu.Case;
import jeu.Plateau;


/**
* Classe abstraite représentant une intelligence artificielle pour le jeu Reverso.
* 
* <p>Toute autre IA devrait hériter de cette classe afin d'être facilement utilisable.</p>
* 
* @see Plateau
*/
public abstract class IntelligenceBase{
    
    protected Plateau plateau;
    
    
    /**
    * Initialise l'intelligence atificielle.
    * 
    * @param plate le Plateau pour lequel l'intelligence artificielle va 
    * chercher les coups possibles.
    */
    public IntelligenceBase(Plateau plate){
        plateau = plate;
    }
    
    /**
     * Récupère la liste des cases jouables sur le Plateau que l'ia a sauvegardé.
     * 
     * @return la liste des indexs des sur lesquelles il est possible de jouer 
     * sur le plateau sauvegardé pas l'ia dans son état actuel.
     * @throws NoFreeCaseException si la plateau ne contient aucune case jouable.
     */
    protected ArrayList<Integer> casesJouables() throws NoFreeCaseException{
        return casesJouables(plateau);
    }
    
    /**
     * Récupère la liste des cases jouables sur le Plateau passé en paramètre.
     * 
     * @param plate Plateau dont on cherche à récupérer les cases jouables
     * @return liste des cases jouables pour le tour actuel sur le plateau.
     * @throws NoFreeCaseException s'il n'y a aucune case jouable sur le Plateau 
     * passé
     */
    protected ArrayList<Integer> casesJouables(Plateau plate) throws NoFreeCaseException{
        
        Case[] damier = plate.getDamier();
        ArrayList<Integer> jouables = new ArrayList<>();
        
        for (int i=0; i<64; i++)
            if (damier[i].jouable())
                jouables.add(i);
        
        if (jouables.isEmpty())
            throw new NoFreeCaseException();
        return jouables;
    }
    
    /**
     * Renvoie le score de l'ia (le joueur à qui il est le tour sur le plateau 
     * en mémoire) selon le plateau actuel.
     * 
     * <p>Récupère le score du joueur blanc sur le tableau passé si c'est au joueur 
     * blanc de jouer sur le plateau sauvegardé par l'ia, le score du joueur noir 
     * sinon.</p>
     * 
     * @param plate plateau sur lequel on charche à avoir le score de l'ia.
     * @return le score de l'intelligence artificielle sur le plateau passé.
     */
    protected int scoreIa(Plateau plate){
        return (plateau.tourBlanc()) ? plate.scoreBlanc() : plate.scoreNoir();
    }
    
    /**
     * Renvoir le score de l'ennemi de l'ia.
     * 
     * @param plate Plateau sur lequel on souhaite obtenir le score de l'ennemi 
     * de l'ia
     * @return Score de l'ennemi de l'ia
     */
    protected int scoreJo(Plateau plate){
        return (plateau.tourBlanc()) ? plate.scoreNoir() : plate.scoreBlanc();
    }
    
    /**
     * Indique si sur le plateau passé, le tour est à l'ia ou à son adversaire.
     * 
     * @param plate Plateau sur lequel on cherche à savoir qui a le tour.
     * @return True si c'est au tour de l'ia, False sinon.
     */
    protected boolean tourIa(Plateau plate){
        return plate.tourBlanc() == plateau.tourBlanc();
    }
    
    /**
     * Joue le coup indiqué et renvoie le plateau une fois le coup joué.
     * 
     * <p>Cette fonctione crée une copie du Plateau passé en paramètre pour simuler
     * le coup dessus, il ne modifie donc pas le Plateau initial.</p>
     * 
     * @param source Plateau sur lequel on souhaite simuler le coup
     * @param coup Indice de la case sur laquelle jouer.
     * @return Le Plateau résultat une fois le coup simulé.
     */
    protected Plateau simuler(Plateau source, int coup){
        Plateau res = source.copie();
        res.jouer(coup);
        
        return res;
    }
    
    /**
     * Propose une case à jouer pour le joueur à c'est le tour.
     * @return l'indice de la case sur laquelle l'ia propose de jouer.
     * @throws NoFreeCaseException S'il n'y a aucune case sur laquelle l'ia peut joueur.
     */
    public abstract int mouvement() throws NoFreeCaseException;
}
