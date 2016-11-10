package edu.buffalo.cse116.code;

import java.util.LinkedList;
import java.util.Queue;

public class Turn {

    private LinkedList<Integer> turns;
    private int _userTurn;

    /**
     * Creates a Turn queue.
     */
    public void Turn(){
        turns = new LinkedList<Integer>();
        turns.add(0);
        turns.add(1);
        turns.add(2);
        turns.add(3);
        turns.add(4);
        turns.add(5);
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

    public void endTurn(int user){
//		if(user.Accusation() == false){
//			dequeue();
//		}
        dequeue();
        enqueue(user);
    }

}
