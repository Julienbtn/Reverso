/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.ia;

/**
 *
 * @author Podoko
 */
public class NoFreeCaseException extends Exception{

    public NoFreeCaseException() { super( "No free case on the game board"); }
    public NoFreeCaseException(String message) { super(message); }
    public NoFreeCaseException(String message, Throwable cause) { super(message, cause); }
    public NoFreeCaseException(Throwable cause) { super(cause); }

}
