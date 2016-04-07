package jeu.core;

import jeu.core.Jeu;
import jeu.core.CaseR;


public class TableauCase{
    private Jeu jeu;
    private int dimX;
    private int dimY;
    private CaseR[] tab;
    
    public TableauCase(int dimY, int dimX,Jeu jeu) {
        this.dimX = dimX;
        this.dimY = dimY;
        this.jeu = jeu;
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
    
    public void drawPlateau(){
        System.out.println("  A B C D E F G H");
        for (int x =0; x<dimX;x++){
            System.out.print(x+1 +" ");
            for (int y =0; y<dimX;y++)
                getCase(x,y).drawCase(x, y);
            System.out.println();
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
    
    public void jouer(int[] choix, boolean joueur){
        int cx = choix[0]-1;
        int cy = choix[1]-1;
        if(nonVide(cx,cy)&&getCase(cx,cy).isBool(!joueur)){
            while(nonVide(cx,cy)&&getCase(cx,cy).isBool(!joueur)){
                cx--;
                cy--;
            }
            if(nonVide(cx, cy)&&getCase(cx,cy).isBool(joueur)){
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
    
    public boolean caseJouable(){
        for (int x =0; x<dimX;x++)
            for (int y =0; y<dimX;y++)
                if(getCase(x,y).isJouable())
                    return true;
        return false;
    }
    
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
    
}

