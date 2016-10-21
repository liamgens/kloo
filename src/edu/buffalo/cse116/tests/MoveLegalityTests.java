package edu.buffalo.cse116.tests;

import edu.buffalo.cse116.code.Board;
import edu.buffalo.cse116.code.Tile;
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
 * These tests below cover everything needed for the first stage move legality & some more
 *
 */

public class MoveLegalityTests {

    /**
     * Test that your project correctly identifies as legal a move that only goes horizontally for as many squares as the die roll
     */
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

    /**
     * Test that your project correctly identifies as legal a move that only goes vertically for as many squares as the die roll
     */
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

    /**
     * Test that your project correctly identifies as legal a move that goes horizontally & vertically for as many squares as the die roll
     */
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

    /**
     * Test that your project correctly identifies as legal a move that goes through a door and into a room
     */
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

    /**
     * Test that your project correctly identifies as legal a move that just uses the a secret passageway
     */
    @Test
    public void secretPassage(){
        //Create the Board and the User (Mrs. Peacock)
        Board b = new Board();
        User p1 = new User(b, 3);

        //Set the current roll to 6
        b.set_currentRoll(6);

        //Move 3 tiles North
        p1.makeMove(7,23);
        p1.makeMove(7,22);
        p1.makeMove(7,21);

        //Move 3 tiles West
        p1.makeMove(6,21);
        p1.makeMove(5,21);
        p1.makeMove(4,21);

        //Set the current roll to 6
        b.set_currentRoll(6);

        //Move 3 tiles West
        p1.makeMove(3,21);
        p1.makeMove(2,21);
        p1.makeMove(1,21);
        p1.makeMove(0,21);

        //Move 2 tiles South
        p1.makeMove(0,22);
        p1.makeMove(0,23);

        //Set the current roll to 6
        b.set_currentRoll(6);

        //Move 1 tile South to the secret passage in the Conservatory
        assertTrue(p1.makeMove(0,24));

        //Check to see if the user is now in the Lounge
        Tile t = b.getTile(p1.get_posX(), p1.get_posY());
        assertEquals(4, t.get_parentRoom());

    }

    /**
     * Test that your project correctly identifies as illegal a move that goes more squares than the die roll
     */
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

    /**
     * Test that your project correctly identifies as illegal a move that goes diagonally
     */
    @Test
    public void diagonalMoves(){
        //Create the Board and the User (Miss Scarlett)
        Board b = new Board();
        User p1 = new User(b, 0);

        //Set the current roll to 1
        b.set_currentRoll(1);

        //Attempts to move diagonally
        assertFalse(p1.makeMove(1,5));

        //Check the position is still correct
        assertEquals(0, p1.get_posX());
        assertEquals(6, p1.get_posY());
    }
    /**
     * Test that your project correctly identifies as illegal a move that is not contiguous
     */
    @Test
    public void contiguousMoves(){
        //Create the Board and the User (Mrs. White)
        Board b = new Board();
        User p1 = new User(b, 1);

        //Set the current roll to 2
        b.set_currentRoll(2);

        //Attempt to move 2 tiles South
        assertFalse(p1.makeMove(18, 2));

        //Attempt to move 4 tiles South
        assertFalse(p1.makeMove(18,4));


    }

    /**
     * Test that your project correctly identifies as illegal a move that goes through a wall
     */
    @Test
    public void throughAWall(){
        //Create the Board and the User (Colonel Mustard)
        Board b = new Board();
        User p1 = new User(b, 5);

        //Set the current roll to 1
        b.set_currentRoll(1);

        //Attempt move 1 tile South - Hitting the Conservatory Door
        assertFalse(p1.makeMove(0,20));

        //Check the position is still correct
        assertEquals(0, p1.get_posX());
        assertEquals(19, p1.get_posY());

    }

    /**
     * These tests are not required, but are some other things that
     * should be tested.
     */

    /**
     *Tests to see if the user can move from a room to a hallway without a door
     */
    @Test
    public void roomToHallway(){
        //Create the Board and the User (Professor Plum)
        Board b = new Board();
        User p1 = new User(b, 4);

        //Set the current roll to 6
        b.set_currentRoll(6);

        //Move into the room
        p1.makeMove(24,5);
        p1.makeMove(23,5);
        p1.makeMove(22,5);
        p1.makeMove(21,5);
        p1.makeMove(21,4);
        p1.makeMove(21,3);

        //Set the current roll to 3
        b.set_currentRoll(3);

        //Move to the edge of the room
        p1.makeMove(20,3);

        //Attempt to move through from the room to the hallway through the wall
        assertFalse(p1.makeMove(19,3));

    }

    /**
     * Tests to see if the user can move to a tile outside of the board
     */
    @Test
    public void outOfBoardTest(){
        //Create the Board and the User (Miss Scarlett)
        Board b = new Board();
        User p1 = new User(b, 0);

        //Set the current roll to 1
        b.set_currentRoll(1);

        //Attempt to move out of the board
        assertFalse(p1.makeMove(-1,6));

    }

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
