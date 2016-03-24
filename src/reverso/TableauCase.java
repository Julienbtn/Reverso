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
    
}

