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
    
    public boolean isVide(){
        if(contenu ==null)
            return true;
        else
            return false;
    }
        
    public boolean isBlanc(){
        return (!isVide() && contenu.isBlanc());
    }
    
    public boolean isNoir(){
        return (!isVide() && !contenu.isBlanc());
    }
    
    public boolean isBool(boolean joueur){
        if(joueur)
            return isBlanc();
        else
            return isNoir();
    }
    
    public void drawCase(int x, int y){
        if (jouable)
            System.out.print("?");
        else if (contenu != null)
            contenu.draw();
        else 
            System.out.print("-");
        System.out.print(" ");
    }
    
    public void jouer(boolean blanc){
        contenu = new Pion(blanc);
    }
    
    
    // Implémentation interface IA !
    
    public boolean remplie(){
        return !isVide();
    }
    public boolean blanche(){
        return isBlanc();
    }
    public boolean jouable(){
        return isJouable();
    }
}
	

