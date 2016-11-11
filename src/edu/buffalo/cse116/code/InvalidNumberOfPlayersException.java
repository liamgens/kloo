package edu.buffalo.cse116.code;

/**
 * Created by thund on 11/10/2016.
 */
public class InvalidNumberOfPlayersException extends RuntimeException{
    public InvalidNumberOfPlayersException() {
        super("Not A Valid Number!");
    }
}
