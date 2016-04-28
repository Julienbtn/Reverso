// package de l'interface Homme-Machine gérant l'interface graphique
package ihm.gui;
    
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ListenerCase implements MouseListener{
    // Les attributs sont l'id de la case cliquée et la fenêtre sur laquelle on a cliqué
    private final int id;
    private final Fenetre fenetre;

    // Constructeur de l'écouteur
    public ListenerCase(int id, Fenetre f) {
        this.id = id;
        this.fenetre = f;
    }

    // méthode qui lance l'action apès le clic (appuyé et relaché) de la souris
    @Override
    public void mouseClicked(MouseEvent arg0) {
    }

    // méthode qui lance l'action lorsque l'utilisateur presse (reste appuyé) de la souris
    @Override
    public void mouseEntered(MouseEvent arg0) {  
    }

    // méthode qui lance l'action après que la souris est quitté le composant
    @Override
    public void mouseExited(MouseEvent arg0) {
    }

    // méthode qui lance l'action lorsque l'utilisateur presse (appuyé) de la souris
    @Override
    public void mousePressed(MouseEvent arg0) {
        fenetre.clicCase(id); 
    }

    // méthode qui lance l'action après que la souris (relaché) de la souris
    @Override
    public void mouseReleased(MouseEvent arg0) {  
    }  
}