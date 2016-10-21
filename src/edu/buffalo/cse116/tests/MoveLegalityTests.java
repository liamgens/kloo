package edu.buffalo.cse116.tests;

import edu.buffalo.cse116.code.Board;
import edu.buffalo.cse116.code.User;
import org.junit.*;

import static org.junit.Assert.*;

/**
 * Created by liamgens on 10/20/16.
 */

/**
 * Users make moves a tile at a time. They can only make as many moves as what they roll. Our board holds the current roll,
 * so when a user is out of moves, the next user must roll. Rolls are managed through the board, so you have to call roll on the board. As of right now,
 * the user doesn't hold the roll, and turns are manual, but this will all be implemented in stage 2.
 * FOR TESTING PURPOSES OUR ROLLS ARE SET MANUALLY -- RANDOM ROLLS DO WORK
 *
 * Our Board is an ArrayList of Tile objects that are generated into a 25x25 grid. Each tile holds an X,Y coordinate, starting with 0,0 in the top left
 * and 24,24 in the bottom right. Under the "other" package (edu.buffalo.cse116.other) is a layout of what the board looks like for visual representation.
 *
 * Rooms are just tiles that have an int value representing their room or if they are a hallway. Our "Walls" are just the outer tiles of the room. A user is
 * allowed to go on those walls if they are already in the room, but not if they are in a hallway. Essentially the "Walls are the outer edge of the outer tiles
 * of a room.
 *
 * The User can only enter a room through a door which is a predetermined tile from the outer tiles of the room.
 *
 * The User takes in the board and an int value for which character they are.
 *
 * These tests below cover everything needed for the first stage move legality.
 *
 * You can find more info in the readme file under the "other" package
 */

public class MoveLegalityTests {

    @Test
    public void horizontalMoves(){
        //Create the Board and the User (Professor Plum)
        Board b = new Board();
        User p1 = new User(b, 4);

        //Set the current roll to 6
        b.set_currentRoll(6);

        //Move 3 tiles West
        assertTrue(p1.makeMove(23,6));
        assertTrue(p1.makeMove(22,6));
        assertTrue(p1.makeMove(21,6));

        //Move 3 tiles East
        assertTrue(p1.makeMove(22,6));
        assertTrue(p1.makeMove(23,6));
        assertTrue(p1.makeMove(24,6));

    }

    @Test
    public void verticalMoves(){
        //Create the Board and the User (Mrs. Peacock)
        Board b = new Board();
        User p1 = new User(b, 3);

        //Set the current roll to 6
        b.set_currentRoll(6);

        //Move 3 tiles North
        assertTrue(p1.makeMove(7,23));
        assertTrue(p1.makeMove(7,22));
        assertTrue(p1.makeMove(7,21));

        //Move 3 tiles South
        assertTrue(p1.makeMove(7,22));
        assertTrue(p1.makeMove(7,23));
        assertTrue(p1.makeMove(7,24));
    }

    //FIXME
    @Test
    public void horizontalAndVerticalMoves(){
        //Create the Board and the User (Miss Scarlett)
        Board b = new Board();
        User p1 = new User(b, 0);

        //Set the current roll to 6
        b.set_currentRoll(6);

        //Move 2 tiles East
        assertTrue(p1.makeMove(1,6));
        assertTrue(p1.makeMove(2,6));

        //Move 1 tile North
        assertTrue(p1.makeMove(2,5));

        //Move 2 tiles West
        assertTrue(p1.makeMove(1,5));
        assertTrue(p1.makeMove(0,5));

        //Move 1 tile South
        assertTrue(p1.makeMove(0,6));


    }

    @Test
    public void enteringARoom(){
        //Create the Board and the User (Professor Plum)
        Board b = new Board();
        User p1 = new User(b, 4);

        //Set the current roll to 6
        b.set_currentRoll(6);

        //Move to tile right outside of Lounge door
        p1.makeMove(24,5);
        p1.makeMove(23,5);
        p1.makeMove(22,5);
        p1.makeMove(21,5);

        //Move from the Hallway to the Lounge door
        assertTrue(p1.makeMove(21,4));

        //Move from the Lounge door into the Lounge
        assertTrue(p1.makeMove(21,3));

        //Set the current roll to 2
        b.set_currentRoll(2);

        //Move from the Lounge to the Lounge door
        assertTrue(p1.makeMove(21,4));

        //Move from the Lounge door to the Hallway
        assertTrue(p1.makeMove(21,5));

    }

    //FIXME
    @Test
    public void secretPassage(){

    }

    @Test
    public void dieRoll(){
        //Create the Board and the User (Professor Plum)
        Board b = new Board();
        User p1 = new User(b, 2);

        //Set the current roll to 4
        b.set_currentRoll(4);

        //Move 3 tiles North
        p1.makeMove(17,23);
        p1.makeMove(17,22);
        p1.makeMove(17,21);

        //Move 1 tile East
        p1.makeMove(18,21);

        //Attempt to move 1 tile South
        assertFalse(p1.makeMove(18,22));

        //Check the position is still correct
        assertEquals(18, p1.get_posX());
        assertEquals(21, p1.get_posY());

    }

    @Test
    public void diagonalMoves(){
        //Create the Board and the User (Miss Scarlett)
        Board b = new Board();
        User p1 = new User(b, 0);

        //Attempts to move diagonally
        b.set_currentRoll(1);
        assertFalse(p1.makeMove(1,5));

        //Check the position is still correct
        assertEquals(0, p1.get_posX());
        assertEquals(6, p1.get_posY());
    }

    //FIXME
    @Test
    public void contiguousMoves(){
        //Create the Board and the User ()
        Board b = new Board();
        User p1 = new User(b, 4);

    }

    //FIXME
    @Test
    public void throughAWall(){
        //Create the Board and the User (Colonel Mustard)
        Board b = new Board();
        User p1 = new User(b, 5);

    }










































    @Test
    //Checks Hallway >> Door
    public void hallwayToDoor(){
        Board b = new Board();
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



    //The method checks non-contiguous moves
    @Test
    public void checkMoreThanOneMove(){
        Board b = new Board();

        User p1 = new User(b, 5);
        assertFalse(p1.makeMove(2,19));

        p1 = new User(b, 0);
        assertFalse(p1.makeMove(18,4));

    }

    //FIXME test multiple characters
    //FIXME test going out of board
    //FIXME test secret passages(transport & transport correctly
    //FIXME check that rolls DON'T decrement on illegal move

    /**
     * Tests to see if the starting point for the Player is correct
     */
    @Test
    public void startingPointTest(){
        Board b = new Board();
        User p1 = new User(b, 0);
        assertEquals(0, p1.get_posX());
        assertEquals(6, p1.get_posY());
    }

}
