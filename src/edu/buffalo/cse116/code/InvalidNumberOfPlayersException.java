package edu.buffalo.cse116.code;

/**
 * Created by thund on 11/10/2016.
 */
public class InvalidNumberOfPlayersException extends RuntimeException{
    /**
     * An exception created when the number of players is less than 3.
     */
    public InvalidNumberOfPlayersException() {
        super("Not A Valid Number!");
    }
}
