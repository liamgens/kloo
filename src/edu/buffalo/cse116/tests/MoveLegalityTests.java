package edu.buffalo.cse116.tests;

import edu.buffalo.cse116.code.Board;
import edu.buffalo.cse116.code.User;
import org.junit.*;

import static org.junit.Assert.*;

/**
 * Created by liamgens on 10/20/16.
 */
public class MoveLegalityTests {
    @Test
    public void horizontalMoves(){
        System.out.println("\nHorizontal Test\n");

        Board b = new Board();
        b.addDefaultDoors();
        b.addDefaultRooms();
        User p1 = new User(b, "Mrs. White");

        System.out.println("Current Roll: " + b.rollDice());

        assertEquals(0, p1.get_posX());
        assertEquals(6, p1.get_posY());
        System.out.println("Starting Position: " + p1.get_posX() + ", " + p1.get_posY());

        assertTrue(p1.makeMove(1,6));
        assertEquals(1, p1.get_posX());
        assertEquals(6, p1.get_posY());
        System.out.println("Move: "+ p1.get_posX() + ", " + p1.get_posY());

        assertTrue(p1.makeMove(0,6));
        assertEquals(0, p1.get_posX());
        assertEquals(6, p1.get_posY());
        System.out.println("Move: "+ p1.get_posX() + ", " + p1.get_posY());

        System.out.println("Rolls Left: " + b.get_currentRoll());


    }

    @Test
    public void verticalMoves(){
        System.out.println("\nVertical Test\n");
        Board b = new Board();
        b.addDefaultDoors();
        b.addDefaultRooms();
        User p1 = new User(b, "Mrs. Peacock");

        System.out.println("Current Roll: " + b.rollDice());

        



    }

    @Test
    public void hallwayToDoor(){

    }

}
