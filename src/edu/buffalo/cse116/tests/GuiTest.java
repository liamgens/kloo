package edu.buffalo.cse116.tests;

import edu.buffalo.cse116.code.Board;
import edu.buffalo.cse116.code.Gui;

import java.util.Scanner;


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
}
