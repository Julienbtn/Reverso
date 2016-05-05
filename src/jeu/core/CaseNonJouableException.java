package jeu.core;

/**
 *
 * @author Podoko
 */
public class CaseNonJouableException extends Exception {
    
    /**
     *
     */
    public CaseNonJouableException() { super( "Case can't be played"); }

    /**
     *
     * @param message
     */
    public CaseNonJouableException(String message) { super(message); }

    /**
     *
     * @param message
     * @param cause
     */
    public CaseNonJouableException(String message, Throwable cause) { super(message, cause); }
    public CaseNonJouableException(Throwable cause) { super(cause); }
    
}
