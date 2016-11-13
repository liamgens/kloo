package edu.buffalo.cse116.tests;

import edu.buffalo.cse116.code.Board;
import edu.buffalo.cse116.code.Gui;

import java.util.Scanner;

import org.junit.Test;


/**
 * Created by liamgens on 10/24/16.
 */
public class GuiTest {
    public static void main(String[]args){

        Scanner scan = new Scanner(System.in);
        System.out.print("Enter number of players (3-6; there is no error handling here): ");
        int numberOfPlayers = scan.nextInt();



        Gui gui = new Gui(numberOfPlayers);
        Board b = gui.get_board();
        

        b.rollDice();

    }
    
    //TODO (might be redundant) Make sure board and pieces are generated test
    @Test
    /**
     * As a player, I want to be able to see:
     * the board,
     * where each player piece is on the board,
     */
    public void wherePlayersPieceGUITest() {
    	
    }
    
    //TODO Write GUI Move Legality Test
    @Test
    /**
     * As a player, I want to be able to:
     * input my move into the system,
     * only have legal moves accepted,
     * everyone is following the rules.
     */
    public void legalMovesOnlyGUITest(){
    	
    };
}
