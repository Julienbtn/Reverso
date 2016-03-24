package reverso;

public class Case{
    private boolean jouable;
    private Pion contenu;
    
    public Case(){
        jouable = false;
        contenu = null;
    }
    
    public Case(boolean blanc){
        jouable = false;
        contenu = new Pion(blanc);
    }

    public void setJouable(boolean jouable) {
        this.jouable = jouable;
    }
    
    public boolean isJouable() {
        return jouable;
    }
    
    public void drawCase(int x, int y){
        if(y == 0)
            System.out.println();
        if (contenu != null)
            contenu.draw();
        else 
            System.out.print("-");
        System.out.print(" ");
    }
}
	

