package jeu.core;

/**
 *
 * @author Podoko
 */
public class TableauCase{
    private int dimX;
    private int dimY;
    private CaseR[] tab;
    
    // Crée un damier et le rempli les 4 cases du centre
    public TableauCase(int dimY, int dimX) {
        this.dimX = dimX;
        this.dimY = dimY;
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
     *
     * @return
     */
    public int getDimY() {
        return dimY;
    }
    public int getDimX() {
        return dimX;
    }

    public CaseR getCase(int x, int y){
        return tab[y*this.dimX + x];
    }
    
    public CaseR getCase(int[] choix){
        return tab[choix[1]*this.dimX + choix[0]];
    }

    /**
     *
     * @param tab
     */
    public void setTab(CaseR[] tab){
        this.tab = tab;
    }
    
    // Parcours toutes les cases du tableau, et met à true
    // le boolean joauble sur les cases jouables
    // Paramètre joueur : vrai si blanc doit jouer, faux si au tour noir
    public boolean chercherCase(boolean joueur){
        boolean fini = true;
        for (int x =0; x<dimX;x++)
            for (int y =0; y<dimX;y++){
                getCase(x,y).setJouable(false);
                if(getCase(x,y).isVide())
                    if(peutJouer(x,y, joueur)){
                        fini = false;
                        getCase(x,y).setJouable(true);
                    }
            }
        return fini;
    }
    
    // Fonction de test des conditions, renvoie true si la case passée en
    // paramètre est sur le damier et contient un pion
    public boolean nonVide(int cx, int cy){
        return (cx>=0&&cx<dimX&&cy>=0&&cy<dimY&&!getCase(cx,cy).isVide());
    }
    
    // Recherche si une case est jouable, test tous les vecteurs aux
    // alentours, renvoie vrai si le joueur (true = blanc) peut jouer
    public boolean peutJouer(int x, int y, boolean joueur){
        // On initialise le vecteur
        int cx = x-1;
        int cy = y-1;
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
        cx=x;
        cy=y-1;
        if(nonVide(cx,cy)&&getCase(cx,cy).isBool(!joueur)){
            while(nonVide(cx,cy)&&getCase(cx,cy).isBool(!joueur)){
                cy--;
            }
            if(nonVide(cx, cy)&&getCase(cx,cy).isBool(joueur))
                return true;
        }
        cx=x+1;
        cy=y-1;
        if(nonVide(cx,cy)&&getCase(cx,cy).isBool(!joueur)){
            while(nonVide(cx,cy)&&getCase(cx,cy).isBool(!joueur)){
                cx++;
                cy--;
            }
            if(nonVide(cx, cy)&&getCase(cx,cy).isBool(joueur))
                return true;
        }
        cx=x-1;
        cy=y;
        if(nonVide(cx,cy)&&getCase(cx,cy).isBool(!joueur)){
            while(nonVide(cx,cy)&&getCase(cx,cy).isBool(!joueur)){
                cx--;
            }
            if(nonVide(cx, cy)&&getCase(cx,cy).isBool(joueur))
                return true;
        }
        cx=x+1;
        cy=y;
        if(nonVide(cx,cy)&&getCase(cx,cy).isBool(!joueur)){
            while(nonVide(cx,cy)&&getCase(cx,cy).isBool(!joueur)){
                cx++;
            }
            if(nonVide(cx, cy)&&getCase(cx,cy).isBool(joueur))
                return true;
        }
        cx=x-1;
        cy=y+1;
        if(nonVide(cx,cy)&&getCase(cx,cy).isBool(!joueur)){
            while(nonVide(cx,cy)&&getCase(cx,cy).isBool(!joueur)){
                cx--;
                cy++;
            }
            if(nonVide(cx, cy)&&getCase(cx,cy).isBool(joueur))
                return true;
        }
        cx=x;
        cy=y+1;
        if(nonVide(cx,cy)&&getCase(cx,cy).isBool(!joueur)){
            while(nonVide(cx,cy)&&getCase(cx,cy).isBool(!joueur)){
                cy++;
            }
            if(nonVide(cx, cy)&&getCase(cx,cy).isBool(joueur))
                return true;
        }
        cx=x+1;
        cy=y+1;
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
    
    // Renvoie true si au moins une case du plateau est jouable

    /**
     *
     * @return
     */
        public boolean caseJouable(){
        for (int x =0; x<dimX;x++)
            for (int y =0; y<dimX;y++)
                if(getCase(x,y).isJouable())
                    return true;
        return false;
    }
    
    // Compte les points en fin de partie.
    // Si des cases sont vides, elles appartiennent au dernier joueur 
    // qui a posé un pion (true pour blanc, false pour noir)

    /**
     *
     * @param joueur
     * @return
     */
        public int comptePoints(boolean joueur){
        int c = 0;
        for (int x =0; x<dimX;x++)
            for (int y =0; y<dimX;y++)
                if(getCase(x,y).isBool(joueur))
                    c++;
        return c;
    }
    
    public CaseR[] copieTab(){
        CaseR[] copie;
        copie = new CaseR[dimX * dimY];
        for(int i=0; i<dimX*dimY;i++){
            copie[i]=tab[i].copieCase();
        }
        return copie;
    }
    
    // Copie l'objet actuel
    public TableauCase copieTout(){
        TableauCase copie;
        copie = new TableauCase(dimY, dimX);
        copie.setTab(copieTab());
        return copie;
    }
    
    // Compte les cases vides (comme le nom l'indique)

    /**
     *
     * @return
     */
        public int compteCasesVides(){
        int ret = 0;
        for(int i=0;i<63;i++)
            if(tab[i].isVide())
                ret ++;
        return ret;
    }
}

