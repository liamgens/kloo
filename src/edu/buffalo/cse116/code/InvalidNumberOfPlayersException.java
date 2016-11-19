package edu.buffalo.cse116.code;

/**
 * Throws a RuntimeException when the number of players playing the game is less than 3.
 * Created by thund on 11/10/2016.
 */
public class InvalidNumberOfPlayersException extends RuntimeException{
    public InvalidNumberOfPlayersException() {
        super("Not A Valid Number!");
    }
}
