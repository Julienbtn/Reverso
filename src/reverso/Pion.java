package reverso;


public class Pion {
    private boolean blanc;

    public Pion(boolean blanc){
        this.blanc = blanc;
    }
    
    public void draw(){
        if (blanc)
            System.out.print("O");
        else
            System.out.print("X");
    }
}
