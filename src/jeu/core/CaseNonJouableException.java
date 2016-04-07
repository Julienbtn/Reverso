/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.core;

/**
 *
 * @author Podoko
 */
public class CaseNonJouableException extends Exception {
    
    public CaseNonJouableException() { super( "Case can't be played"); }
    public CaseNonJouableException(String message) { super(message); }
    public CaseNonJouableException(String message, Throwable cause) { super(message, cause); }
    public CaseNonJouableException(Throwable cause) { super(cause); }
    
}
