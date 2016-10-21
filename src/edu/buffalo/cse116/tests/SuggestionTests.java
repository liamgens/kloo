package edu.buffalo.cse116.tests;

import edu.buffalo.cse116.code.Board;
import edu.buffalo.cse116.code.User;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by liamgens on 10/20/16.
 */
public class SuggestionTests {
    @Test
    public void test1(){
        Board b = new Board();
        b.addDefaultDoors();
        b.addDefaultRooms();
        User p1 = new User(b, "Professor Plum");
        User p2 = new User(b, "Miss Scarlet");
        User p3 = new User(b, "Colonel Mustard");
        User p4 = new User(b, "Colonel Mustard");
        User p5 = new User(b, "Colonel Mustard");
        User p6 = new User(b, "Colonel Mustard");



        b.set_currentRoll(6);
        p1.makeMove(24,5);
        p1.makeMove(23,5);
        p1.makeMove(22,5);
        p1.makeMove(21,5);
        p1.makeMove(21,3);



    }

}
