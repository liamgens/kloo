package edu.buffalo.cse116.code;

import sun.util.resources.cldr.st.CalendarData_st_LS;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class TurnQueue {
    private LinkedList<User> _playersQueue;
    public ArrayList<User> _listOfPlayers;
    private Board _board;
    
    /**
     * Creates a Turn queue.
     * @param numberOfPlayers Takes in the number of players
     */

    public TurnQueue(int numberOfPlayers, Board board) throws InvalidNumberOfPlayersException {
        _board = board;
        if (numberOfPlayers >= 3 && numberOfPlayers <= 6) {
            _playersQueue = new LinkedList<User>();
            _listOfPlayers = new ArrayList<User>();
            for (int i = 0; i < numberOfPlayers; i++) {
                User u = new User(_board, i);
                _playersQueue.add(u);
                _listOfPlayers.add(u);
            }
        }else{
                throw new InvalidNumberOfPlayersException();
            }
        }

    /**
     * Tests to see if the Queue is empty.
     * @return true if the Queue is empty, else it's false.
     */
    public boolean isEmpty(){
        return (_playersQueue.size()==0);
    }

    /**
     * Adds an element to the front of the Queue.
     * @param user Adds the player to the end of the Queue .
     */
    public void enqueue(User user){
        _playersQueue.add(user);
    }

    /**
     * Removes an element from the front of the Queue.
     * @return
     */
    public User dequeue(){
        User next = _playersQueue.get(1);
        _playersQueue.remove(0);
        return next;
    }

    /**
     * Returns the value of the first item in the TurnQueue.
     * @return
     */
    public User peek(){
        return _playersQueue.get(0);
    }

    /**
     * Ends a user's turn by enqueuing the player into the end of the Queue and then dequeuing the user in the front of the queue.
     */
    public void endTurn(){
//		if(user.Accusation() == false){
//			dequeue();
//		}
        enqueue(peek());
        dequeue();
    }

    public ArrayList<User> get_listOfPlayers(){
        return _listOfPlayers;
    }
    
}
