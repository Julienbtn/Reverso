package reverso;


public class TableauCase{
    private Jeu jeu;
    private int dimX;
    private int dimY;
    private Case[] tab;
    
    public TableauCase(int dimY, int dimX,Jeu jeu) {
        this.dimX = dimX;
        this.dimY = dimY;
        this.jeu = jeu;
        this.tab = new Case[dimX * dimY];
        Case case1;
        for(int i = 0; i<dimX*dimY;i++){
            if (i==27 ||i==36)
                case1 = new Case(true);
            else if (i==28 ||i==35)
                case1 = new Case(false);
            else
                case1 = new Case();
            tab[i] = case1;
        }
    }
    
    public int getDimY() {
        return dimY;
    }
    public int getDimX() {
        return dimX;
    }

    public Case getCase(int x, int y){
        return tab[y*this.dimX + x];
    }
    
    public void drawPlateau(){
        for (int x =0; x<dimX;x++){
            for (int y =0; y<dimX;y++){
                getCase(x,y).drawCase(x, y);
            }
        }
        System.out.println();
    }
    
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
    
    public boolean nonVide(int cx, int cy){
        return (cx>=0&&cx<dimX&&cy>=0&&cy<dimY&&!getCase(cx,cy).isVide());
    }
    
    public boolean peutJouer(int x, int y, boolean joueur){
        int cx = x-1;
        int cy = y-1;
        if(nonVide(cx,cy)&&getCase(cx,cy).isBool(!joueur)){
            while(nonVide(cx,cy)&&getCase(cx,cy).isBool(!joueur)){
                cx--;
                cy--;
            }
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
}

