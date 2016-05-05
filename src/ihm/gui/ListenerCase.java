// package de l'interface Homme-Machine gérant l'interface graphique
package ihm.gui;
    
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Classe permettant la gestion des clics sur les cases du damier de l'ihm
 * 
 */
public class ListenerCase implements MouseListener{
    
    /**
     * Id de la case écoutée par le mouseListener
     */
    private final int id;
    /**
     * Lien vers l'ihm à laquelle appartient la case
     */
    private final Fenetre fenetre;

    
    /**
     * Constructeur de l'écouteur
     * @param id id de la case écoutée
     * @param f fenetre dans laquelle se trouve la case
     */
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
    /**
     * Appelle notifie la fenetre que la case a été cliquée, la fenetre joue 
     * ensuite en fonction du jeu qu'elle contient et de la case jouée
     * @param arg0 
     */
    @Override
    public void mousePressed(MouseEvent arg0) {
        fenetre.clicCase(id); 
    }

    // méthode qui lance l'action après que la souris (relaché) de la souris
    @Override
    public void mouseReleased(MouseEvent arg0) {  
    }  
}