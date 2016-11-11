package edu.buffalo.cse116.tests;

import edu.buffalo.cse116.code.Board;
import edu.buffalo.cse116.code.Gui;
import edu.buffalo.cse116.code.User;
import org.junit.Test;

import javax.swing.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


/**
 * Created by liamgens on 10/24/16.
 */
public class GuiTest {
    public static void main(String[]args){

        Scanner scan = new Scanner(System.in);
        int numberOfPlayers = scan.nextInt();



        Gui gui = new Gui(numberOfPlayers);
        Board b = gui.get_board();

        b.rollDice();

    }
}
