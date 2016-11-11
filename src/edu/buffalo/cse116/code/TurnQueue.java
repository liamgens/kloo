package edu.buffalo.cse116.code;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class TurnQueue {
    private LinkedList<Integer> turns;
    /**
     * Creates a Turn queue.
     */

    public TurnQueue(ArrayList<User> userList) throws InvalidNumberOfPlayersException{
        if(userList.size() >=3 && userList.size() <= 6){
            turns = new LinkedList<Integer>();
            for(int i = 0; i<userList.size(); i++){
                turns.add(i);
            }
        }else {
            throw new InvalidNumberOfPlayersException();
        }
    }


    /**
     * Tests to see if the Queue is empty.
     * @return true if the Queue is empty, else it's false.
     */
    public boolean isEmpty(){
        return (turns.size()==0);
    }

    /**
     * Adds an element to the front of the Queue.
     * @param user Adds the player to the end of the Queue .
     */
    public void enqueue(int user){
        turns.add(user);
    }

    /**
     * Removes an element from the front of the Queue.
     * @return
     */
    public int dequeue(){
        int next = turns.get(1);
        turns.remove(0);
        return next;
    }

    public Integer peek(){
        return turns.get(0);
    }

    public void endTurn(){
//		if(user.Accusation() == false){
//			dequeue();
//		}
        enqueue(peek());
        dequeue();
    }

}