package edu.buffalo.cse116.code;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class TurnQueue {
    private LinkedList<Integer> _turns;
    
    /**
     * Creates a Turn queue.
     * @param numberOfPlayers Takes in the number of players
     */

    public TurnQueue(int numberOfPlayers) throws InvalidNumberOfPlayersException{
        if (numberOfPlayers >=3 && numberOfPlayers <= 6) {
            _turns = new LinkedList<Integer>();
            for (int i = 0; i<numberOfPlayers; i++)
                _turns.add(i);
        } else {
            throw new InvalidNumberOfPlayersException();
        }
    } 

    //TODO if Queue is empty, then gameover because no other players are left in the game
    
    /**
     * Tests to see if the Queue is empty.
     * @return true if the Queue is empty, else it's false.
     */
    public boolean isEmpty(){
        return (_turns.size()==0);
    }

    /**
     * Adds an element to the front of the Queue.
     * @param user Adds the player to the end of the Queue .
     */
    public void enqueue(int user){
        _turns.add(user);
    }

    /**
     * Removes an element from the front of the Queue.
     * @return
     */
    public int dequeue(){
        int next = _turns.get(1);
        _turns.remove(0);
        return next;
    }

    /**
     * Returns the value of the first item in the TurnQueue.
     * @return
     */
    public Integer peek(){
        return _turns.get(0);
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
    
    //TODO Add a method that returns the SIZE (not length) of the Queue, ignoring nulls
}
