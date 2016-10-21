package edu.buffalo.cse116.tests;

import edu.buffalo.cse116.code.Board;
import edu.buffalo.cse116.code.User;
import org.junit.*;

import static org.junit.Assert.*;

/**
 * Created by liamgens on 10/20/16.
 */



public class MoveLegalityTests {
    //Checks if the user's starting point is correct
    @Test
    public void startingPointTest(){
        Board b = new Board();
        b.addDefaultDoors();
        b.addDefaultRooms();
        User p1 = new User(b, 0);

        assertEquals(0, p1.get_posX());
        assertEquals(6, p1.get_posY());
    }

    //Checks if moves in the vertical direction work
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

}
