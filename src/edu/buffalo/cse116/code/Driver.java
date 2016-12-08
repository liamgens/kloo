package edu.buffalo.cse116.code;

import java.util.Scanner;

/**
 * Created by liamgens on 11/21/16.
 */
public class Driver {
    public static void main(String[]args){

        Scanner scan = new Scanner(System.in);
        System.out.print("Enter number of players (3-6): ");
        int numberOfPlayers = scan.nextInt();

        Gui gui = new Gui(numberOfPlayers);
        Board b = gui.get_board();

        String winningHand = new String();
        for(Card c : b.get_envelope()){
            winningHand += c.get_title() + ", ";
        }

        System.out.println("The winning hand is: " + winningHand.substring(0,winningHand.length()-2));

    }
}
