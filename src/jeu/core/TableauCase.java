package jeu.core;

/**
 * Coeur des mécaniques de Reverso, implémente les fonctions pour jouer, retourner
 * mettre à jour les pièces sur le plateau.
 * 
 */
public class TableauCase{
    /**
     * Nombre de colonnes sur le plateau
     */
    private final int nombreColonnes;
    
    /**
     * Nombre de lignes sur le plateau
     */
    private final int nombreLignes;
    /**
     * Tableau contenant les cases du plateau
     */
    private CaseR[] tab;
    
    /**
     * Crée un damier et le rempli les 4 cases du centre
     * 
     * @param dimY nombre de lignes 
     * @param dimX nombre de colonnes 
     */
    
    public TableauCase(int dimY, int dimX) {
        this.nombreColonnes = dimX;
        this.nombreLignes = dimY;
        this.tab = new CaseR[dimX * dimY];
        CaseR case1;
        for(int i = 0; i<dimX*dimY;i++){
            if (i==27 ||i==36)
                case1 = new CaseR(true);
            else if (i==28 ||i==35)
                case1 = new CaseR(false);
            else
                case1 = new CaseR();
            tab[i] = case1;
        }
    }
    
    /**
     * Donne le nombre de lignes du tableau
     * @return 
     */
    public int getNombreLignes() {
        return nombreLignes;
    }
    
    /**
     * Donne le nombre de colonnes du tableau
     * @return 
     */
    public int getNombreColonnes() {
        return nombreColonnes;
    }

    /**
     * Renvoie la case contenue à la colonne x et ligne y du tableau
     * 
     * @param colonne numéro de la colonne
     * @param ligne numéro de la ligne
     * @return Case contenue à la position demandée
     */
    public CaseR getCase(int colonne, int ligne){
        return tab[ligne*this.nombreColonnes + colonne];
    }
    
    /**
     * Renvoie la case contenue à la position [colonne, ligne] du tableau
     * 
     * @param choix tableau [colonne, ligne] contenant les coordonnées du la case
     * demandée
     * @return Case contenue à la position demandée
     */
    public CaseR getCase(int[] choix){
        return tab[choix[1]*this.nombreColonnes + choix[0]];
    }

    
    /**
     * Remplace le contenu du tableau de case par un nouveau passé en argument.
     * 
     * @param tab nouveau tableau de cases contenu par le TableauCase
     * @warning À n'utiliser que si vous savez ce que vous faites
     */
    public void setTab(CaseR[] tab){
        this.tab = tab;
    }
    
    // Parcours toutes les cases du tableau, et met à true
    // le boolean joauble sur les cases jouables
    // Paramètre joueur : vrai si blanc doit jouer, faux si au tour noir
    /**
     * Met à jour l'état "jouable" des cases du tableau en fonction du joueur 
     * passé en argument. Indique également si aucune case n'est jouable par
     * le jouer indiqué
     * 
     * @param joueur true si on cherche à mettre à true les cases jouables par le 
     * joueur blanc, false si on veut mettre à true celles jouables par le joueur noir
     * @return true si aucune case n'est jouable
     */
    public boolean chercherCase(boolean joueur){
        boolean fini = true;
        for (int x =0; x<nombreColonnes;x++)
            for (int y =0; y<nombreColonnes;y++){
                getCase(x,y).setJouable(false);
                if(getCase(x,y).isVide())
                    if(peutJouer(x,y, joueur)){
                        fini = false;
                        getCase(x,y).setJouable(true);
                    }
            }
        return fini;
    }
    
    
    /**
     * indique si la case demandée et vide ou non
     * 
     * @param colonne Colonne de la case indiquée
     * @param ligne Ligne de la case indiquée
     * @return true si les coordonnées sont valides et la case est occupée
     */
    public boolean nonVide(int colonne, int ligne){
        return (colonne>=0&&colonne<nombreColonnes&&ligne>=0&&
                ligne<nombreLignes&&!getCase(colonne,ligne).isVide());
    }
    
    // Recherche si une case est jouable, test tous les vecteurs aux
    // alentours, renvoie vrai si le joueur (true = blanc) peut jouer
    /**
     * Recherche si une case est jouable par le joueur passé en argument
     * 
     * @param colonne colonne de la case à jouer
     * @param ligne ligne de la case à jouer
     * @param joueur true si joueur blanc, false si joueur noir
     * @return true si la case est jouable, false sinon
     */
    public boolean peutJouer(int colonne, int ligne, boolean joueur){
        // On initialise le vecteur
        int cx = colonne-1;
        int cy = ligne-1;
        // Si le pion à coté de la case est un pion adverse...
        if(nonVide(cx,cy)&&getCase(cx,cy).isBool(!joueur)){
            // Temps qu'il y a des pions adverses on se déplace
            while(nonVide(cx,cy)&&getCase(cx,cy).isBool(!joueur)){
                cx--;
                cy--;
            }
            // Si après les pions adverses il y a un pion du joueur,
            // on peut manger !
            if(nonVide(cx, cy)&&getCase(cx,cy).isBool(joueur))
                return true;
        }
        cx=colonne;
        cy=ligne-1;
        if(nonVide(cx,cy)&&getCase(cx,cy).isBool(!joueur)){
            while(nonVide(cx,cy)&&getCase(cx,cy).isBool(!joueur)){
                cy--;
            }
            if(nonVide(cx, cy)&&getCase(cx,cy).isBool(joueur))
                return true;
        }
        cx=colonne+1;
        cy=ligne-1;
        if(nonVide(cx,cy)&&getCase(cx,cy).isBool(!joueur)){
            while(nonVide(cx,cy)&&getCase(cx,cy).isBool(!joueur)){
                cx++;
                cy--;
            }
            if(nonVide(cx, cy)&&getCase(cx,cy).isBool(joueur))
                return true;
        }
        cx=colonne-1;
        cy=ligne;
        if(nonVide(cx,cy)&&getCase(cx,cy).isBool(!joueur)){
            while(nonVide(cx,cy)&&getCase(cx,cy).isBool(!joueur)){
                cx--;
            }
            if(nonVide(cx, cy)&&getCase(cx,cy).isBool(joueur))
                return true;
        }
        cx=colonne+1;
        cy=ligne;
        if(nonVide(cx,cy)&&getCase(cx,cy).isBool(!joueur)){
            while(nonVide(cx,cy)&&getCase(cx,cy).isBool(!joueur)){
                cx++;
            }
            if(nonVide(cx, cy)&&getCase(cx,cy).isBool(joueur))
                return true;
        }
        cx=colonne-1;
        cy=ligne+1;
        if(nonVide(cx,cy)&&getCase(cx,cy).isBool(!joueur)){
            while(nonVide(cx,cy)&&getCase(cx,cy).isBool(!joueur)){
                cx--;
                cy++;
            }
            if(nonVide(cx, cy)&&getCase(cx,cy).isBool(joueur))
                return true;
        }
        cx=colonne;
        cy=ligne+1;
        if(nonVide(cx,cy)&&getCase(cx,cy).isBool(!joueur)){
            while(nonVide(cx,cy)&&getCase(cx,cy).isBool(!joueur)){
                cy++;
            }
            if(nonVide(cx, cy)&&getCase(cx,cy).isBool(joueur))
                return true;
        }
        cx=colonne+1;
        cy=ligne+1;
        if(nonVide(cx,cy)&&getCase(cx,cy).isBool(!joueur)){
            while(nonVide(cx,cy)&&getCase(cx,cy).isBool(!joueur)){
                cx++;
                cy++;
            }
            if(nonVide(cx, cy)&&getCase(cx,cy).isBool(joueur))
                return true;
        }
        return false;
    }
    
    /**
     * Tente de jouer sur la case indiquée et retourne tous les pions qui doivent 
     * être retournés par ce coup
     * 
     * @param choix coordonnées [colonne,ligne] de la case à jouer
     * @param joueur true pour jouer en blanc, false pour jouer en noir
     */
    public void jouer(int[] choix, boolean joueur){
        int cx = choix[0]-1;
        int cy = choix[1]-1;
        if(nonVide(cx,cy)&&getCase(cx,cy).isBool(!joueur)){
            while(nonVide(cx,cy)&&getCase(cx,cy).isBool(!joueur)){
                cx--;
                cy--;
            }
            // Si on peut manger de ce coté :
            if(nonVide(cx, cy)&&getCase(cx,cy).isBool(joueur)){
                // On mange !!!
                cx = choix[0]-1;
                cy = choix[1]-1;
                do{
                    getCase(cx,cy).jouer(joueur);
                    cx--;
                    cy--;
                } while(getCase(cx,cy).isBool(!joueur));
            }
        }
        cx=choix[0];
        cy=choix[1]-1;
        if(nonVide(cx,cy)&&getCase(cx,cy).isBool(!joueur)){
            while(nonVide(cx,cy)&&getCase(cx,cy).isBool(!joueur)){
                cy--;
            }
            if(nonVide(cx, cy)&&getCase(cx,cy).isBool(joueur)){
                cx = choix[0];
                cy = choix[1]-1;
                do{
                    getCase(cx,cy).jouer(joueur);
                    cy--;
                } while(getCase(cx,cy).isBool(!joueur));
            }
        }
        cx=choix[0]+1;
        cy=choix[1]-1;
        if(nonVide(cx,cy)&&getCase(cx,cy).isBool(!joueur)){
            while(nonVide(cx,cy)&&getCase(cx,cy).isBool(!joueur)){
                cx++;
                cy--;
            }
            if(nonVide(cx, cy)&&getCase(cx,cy).isBool(joueur)){
                cx = choix[0]+1;
                cy = choix[1]-1;
                do{
                    getCase(cx,cy).jouer(joueur);
                    cx++;
                    cy--;
                } while(getCase(cx,cy).isBool(!joueur));
            }
        }
        cx=choix[0]-1;
        cy=choix[1];
        if(nonVide(cx,cy)&&getCase(cx,cy).isBool(!joueur)){
            while(nonVide(cx,cy)&&getCase(cx,cy).isBool(!joueur)){
                cx--;
            }
            if(nonVide(cx, cy)&&getCase(cx,cy).isBool(joueur)){
                cx = choix[0]-1;
                do{
                    getCase(cx,cy).jouer(joueur);
                    cx--;
                } while(getCase(cx,cy).isBool(!joueur));
            }
        }
        cx=choix[0]+1;
        cy=choix[1];
        if(nonVide(cx,cy)&&getCase(cx,cy).isBool(!joueur)){
            while(nonVide(cx,cy)&&getCase(cx,cy).isBool(!joueur)){
                cx++;
            }
            if(nonVide(cx, cy)&&getCase(cx,cy).isBool(joueur)){
                cx = choix[0]+1;
                do{
                    getCase(cx,cy).jouer(joueur);
                    cx++;
                } while(getCase(cx,cy).isBool(!joueur));
            }
        }
        cx=choix[0]-1;
        cy=choix[1]+1;
        if(nonVide(cx,cy)&&getCase(cx,cy).isBool(!joueur)){
            while(nonVide(cx,cy)&&getCase(cx,cy).isBool(!joueur)){
                cx--;
                cy++;
            }
            if(nonVide(cx, cy)&&getCase(cx,cy).isBool(joueur)){
                cx = choix[0]-1;
                cy = choix[1]+1;
                do{
                    getCase(cx,cy).jouer(joueur);
                    cx--;
                    cy++;
                } while(getCase(cx,cy).isBool(!joueur));
            }
        }
        cx=choix[0];
        cy=choix[1]+1;
        if(nonVide(cx,cy)&&getCase(cx,cy).isBool(!joueur)){
            while(nonVide(cx,cy)&&getCase(cx,cy).isBool(!joueur)){
                cy++;
            }
            if(nonVide(cx, cy)&&getCase(cx,cy).isBool(joueur)){
                cx = choix[0];
                cy = choix[1]+1;
                do{
                    getCase(cx,cy).jouer(joueur);
                    cy++;
                } while(getCase(cx,cy).isBool(!joueur));
            }
        }
        cx=choix[0]+1;
        cy=choix[1]+1;
        if(nonVide(cx,cy)&&getCase(cx,cy).isBool(!joueur)){
            while(nonVide(cx,cy)&&getCase(cx,cy).isBool(!joueur)){
                cx++;
                cy++;
            }
            if(nonVide(cx, cy)&&getCase(cx,cy).isBool(joueur)){
                cx = choix[0]+1;
                cy = choix[1]+1;
                do{
                    getCase(cx,cy).jouer(joueur);
                    cx++;
                    cy++;
                } while(getCase(cx,cy).isBool(!joueur));
            }
        }
        getCase(choix[0],choix[1]).jouer(joueur);
    }
    
    
    /**
     * indique si au moins une case du plateau est jouable
     * @return true si au moins une case du plateau est jouable
     */
    public boolean caseJouable(){
        for (int x =0; x<nombreColonnes;x++)
            for (int y =0; y<nombreColonnes;y++)
                if(getCase(x,y).isJouable())
                    return true;
        return false;
    }
    
    // Compte les points en fin de partie.
    // Si des cases sont vides, elles appartiennent au dernier joueur 
    // qui a posé un pion (true pour blanc, false pour noir)
    /**
     * Compte les points en fin de partie
     * 
     * <p>Si des cases sont vides, elles appartiennent au dernier joueur 
     * qui a posé un pion (true pour blanc, false pour noir)
     * 
     * @param joueur true pour blanc, false pour noir
     * @return nombre de points du joueur indiqué
     */
    public int comptePoints(boolean joueur){
        int c = 0;
        for (int x =0; x<nombreColonnes;x++)
            for (int y =0; y<nombreColonnes;y++)
                if(getCase(x,y).isBool(joueur))
                    c++;
        return c;
    }
    
    /**
     * Fait ce que ça dit
     * 
     * @return Renvoie une copie du tableau de cases contenu par l'objet
     */
    public CaseR[] copieTab(){
        CaseR[] copie;
        copie = new CaseR[nombreColonnes * nombreLignes];
        for(int i=0; i<nombreColonnes*nombreLignes;i++){
            copie[i]=tab[i].copieCase();
        }
        return copie;
    }
    
    
    /**
     * Copie l'objet actuel
     * 
     * @return une copie l'objet actuel
     */
    public TableauCase copieTout(){
        TableauCase copie;
        copie = new TableauCase(nombreLignes, nombreColonnes);
        copie.setTab(copieTab());
        return copie;
    }
    
    
    /**
     * Compte les cases vides (comme le nom l'indique)
     * 
     * @return nombre de cases vides sur le plateau
     */
    public int compteCasesVides(){
        int ret = 0;
        for(int i=0;i<63;i++)
            if(tab[i].isVide())
                ret ++;
        return ret;
    }
}

