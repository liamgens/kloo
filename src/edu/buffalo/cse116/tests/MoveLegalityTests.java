package edu.buffalo.cse116.tests;

import edu.buffalo.cse116.code.Board;
import edu.buffalo.cse116.code.User;
import org.junit.*;

import static org.junit.Assert.*;

/**
 * Created by liamgens on 10/20/16.
 */

/**
 * README -- Users make moves a tile at a time. They can only make as many moves as what they roll. Our board holds the current roll,
 * so when a user is out of moves, the next user must roll. Rolls are managed through the board, so you have to call roll on the board. As of right now,
 * the user doesn't hold the roll, and turns are manual, but this will all be implemented in stage 2.
 *
 * Our Board is an ArrayList of Tile objects that are generated into a 25x25 grid. Each tile holds an X,Y coordinate, starting with 0,0 in the top left
 * and 24,24 in the bottom right. Under the drawable package (edu.buffalo.cse116.drawable) is a layout of what the board looks like for visual representation.
 *
 * Rooms
 */

public class MoveLegalityTests {
    /**
     * Tests to see if the starting point for the Player is correct
     */
    @Test
    public void startingPointTest(){
        Board b = new Board();
        b.addDefaultDoors();
        b.addDefaultRooms();
        User p1 = new User(b, 0);
        assertEquals(0, p1.get_posX());
        assertEquals(6, p1.get_posY());
    }

    /**
     * Tests to see if moves in the vertical direction work
     */
    @Test
    public void verticalMoves(){
        //DIE ROLL IS RANDOM, SO I SET A CONSTANT FOR TESTING -- RANDOM DOES WORK THOUGH, JUST CAN'T TEST
        Board b = new Board();
        b.addDefaultDoors();
        b.addDefaultRooms();
        User p1 = new User(b, 3);

        System.out.println(p1.get_posX() + " " + p1.get_posY());

        b.set_currentRoll(3);

        //Move 3 tiles and then can't move anymore && Move Vertically
        assertTrue(p1.makeMove(7,23));
        assertTrue(p1.makeMove(7,22));
        assertTrue(p1.makeMove(7,21));
        assertFalse(p1.makeMove(7,20));

        //check that tile is still correct
        assertEquals(7, p1.get_posX());
        assertEquals(21, p1.get_posY());

    }

    //Checks if moves in the horizontal direction work
    @Test
    public void horizontalMoves(){
        Board b = new Board();
        b.addDefaultDoors();
        b.addDefaultRooms();
        User p1 = new User(b, 4);

        b.set_currentRoll(3);

        //Move 3 tiles and then can't move anymore && Move Horizontally
        assertTrue(p1.makeMove(23,6));
        assertTrue(p1.makeMove(22,6));
        assertTrue(p1.makeMove(21,6));
    }

    //Confirms that diagonal moves fail
    @Test
    public void diagonalMoves(){
        Board b = new Board();
        b.addDefaultDoors();
        b.addDefaultRooms();
        User p1 = new User(b, 0);

        //checks an illegal move
        b.set_currentRoll(3);
        assertFalse(p1.makeMove(1,19));

    }

    @Test
    //Checks Hallway >> Door
    public void hallwayToDoor(){
        Board b = new Board();
        b.addDefaultDoors();
        b.addDefaultRooms();
        User p1 = new User(b, 2);

        b.set_currentRoll(4);
        p1.makeMove(17,23);
        p1.makeMove(17,22);
        p1.makeMove(17,21);
        assertTrue(p1.makeMove(16,21));

    }

    @Test
    public void hallwayToWall(){
        Board b = new Board();
        b.addDefaultDoors();
        b.addDefaultRooms();
        User p1 = new User(b, 2);

        b.set_currentRoll(3);
        p1.makeMove(17,23);
        p1.makeMove(17,22);
        assertFalse(p1.makeMove(16,22));

    }

    //
    @Test
    public void roomToHallway(){
        Board b = new Board();
        b.addDefaultDoors();
        b.addDefaultRooms();
        User p1 = new User(b, 4);

        b.set_currentRoll(6);
        p1.makeMove(24,5);
        p1.makeMove(23,5);
        p1.makeMove(22,5);
        p1.makeMove(21,5);
        assertTrue(p1.makeMove(21,4));

        b.set_currentRoll(3);

        assertTrue(p1.makeMove(20,4));
        assertFalse(p1.makeMove(20,5));

    }

    //this method checks Hallway >> Door >> Room >> Door >> Hallway
    @Test
    public void enteringARoom(){
        Board b = new Board();
        b.addDefaultDoors();
        b.addDefaultRooms();
        User p1 = new User(b, 4);

        b.set_currentRoll(6);
        p1.makeMove(24,5);
        p1.makeMove(23,5);
        p1.makeMove(22,5);
        p1.makeMove(21,5);
        assertTrue(p1.makeMove(21,4));

        b.set_currentRoll(3);
        assertTrue(p1.makeMove(21,3));
        assertTrue(p1.makeMove(21,4));
        assertTrue(p1.makeMove(21,5));

    }

    //The method checks non-contiguous moves
    @Test
    public void checkMoreThanOneMove(){
        Board b = new Board();
        b.addDefaultDoors();
        b.addDefaultRooms();

        User p1 = new User(b, 5);
        assertFalse(p1.makeMove(2,19));

        p1 = new User(b, 0);
        assertFalse(p1.makeMove(18,4));

    }

    //FIXME test multiple characters
    //FIXME test going out of board
    //FIXME test secret passages(transport & transport correctly

}
