package jeu.core;

public class CaseNonJouableException extends Exception {
    
    public CaseNonJouableException() { super( "Case can't be played"); }
    public CaseNonJouableException(String message) { super(message); }
    public CaseNonJouableException(String message, Throwable cause) { super(message, cause); }
    public CaseNonJouableException(Throwable cause) { super(cause); }
    
}
