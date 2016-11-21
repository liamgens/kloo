package edu.buffalo.cse116.code;

import java.util.ArrayList;
import java.util.LinkedList;

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

    /**
     * If only one player is left in the Queue, he/she is declared the winner.
     * @return
     */
 /*   public User soleSurvivor(){
        User winner = null;
        if(get_listOfPlayers().size()== 1){
            winner = _listOfPlayers.get(0);
            return winner;
        }
        return winner;
    }*/
    
}
