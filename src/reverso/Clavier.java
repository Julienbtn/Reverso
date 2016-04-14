package reverso;

import jeu.core.Jeu;
import java.util.Scanner;

public class Clavier implements Entree {
    Scanner in;
    
    public Clavier(){
        in = new Scanner(System.in);
    }
    
    public int[] choix(Jeu j){
        String c;
        int[] choix;
        boolean boucle = true;
        do{
            if(j.isTourBlanc())
                System.out.print("O : ");
            else
                System.out.print("X : ");
            System.out.println("où jouer? (exemple : B2)");
            c = in.nextLine();
            choix = lireChoix(c);
            if (choix[0] != -1){
                boucle = !j.getPlateau().getCase(choix).isJouable();
            }
        }while (boucle);
        return choix;
    }
    
    public int[] lireChoix(String c){
        int[] val = new int[2];
        if (c.length()==2){
            if(c.toCharArray()[0] >='A' && c.toCharArray()[0] <='H')
                val[1] = c.toCharArray()[0] - 'A';
            else if(c.toCharArray()[0] >='a' && c.toCharArray()[0] <='h')
                val[1] = c.toCharArray()[0] - 'a';
            else
                val[0] = -1;
            if(c.toCharArray()[1]>='1' && c.toCharArray()[1]<='8')
                val[0]=c.toCharArray()[1]-'1';
            else
                val[0] = -1;
        }
        else val[0] = -1;
        return val;
    }
    
    public int choixMenu(int max){
        String c;
        int choix = -1;
        boolean boucle = true;
        do{
            System.out.println("Votre choix?");
            c = in.nextLine();
            if(c.toCharArray().length ==1 && c.toCharArray()[0] >='0' && c.toCharArray()[0] <='9'){
                choix = Integer.parseInt(c);
                if (choix>0 && choix<=max){
                    boucle = false;
                }
            }
        }while (boucle);
        return choix;
    }
    
    public void pause(){
        System.out.println("Appuyer sur Entrée pour rejouer");
        in.nextLine();
    }
    
}
