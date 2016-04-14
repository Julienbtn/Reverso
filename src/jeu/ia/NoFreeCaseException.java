package jeu.ia;

public class NoFreeCaseException extends Exception{

    public NoFreeCaseException() { super( "No free case on the game board"); }
    public NoFreeCaseException(String message) { super(message); }
    public NoFreeCaseException(String message, Throwable cause) { super(message, cause); }
    public NoFreeCaseException(Throwable cause) { super(cause); }

}
