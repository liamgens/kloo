package edu.buffalo.cse116.code;

import java.util.ArrayList;

/**
 * @author liamgens
 */
public class User {

    private ArrayList<Card> _userCards; // Used to return cards
    private String[] CHARACTER_NAME = {"Miss Scarlett", "Professor Plum", "Mr. Green", "Mrs. Peacock", "Mrs. White", "Colonel Mustard"};
    private int _userTurn;
    private int _posX, _posY;
    private Board _board;
    //  stores users' current coordinates {int x, int y}
    private int[] _currentCoor;
    //  stores users coordinates at beginning of turn {int x, int y}, for comparison
    private int[] _startTurn;
    //  stores users coordinates at end of turn {int x, int y}
    private int[] _endTurn;
    // holds value if user decides to end turn, prompts: continue: yes or no (OPTIONAL)
    private boolean _decision;
 // Holds value to set off suggestion
    private boolean _trigger;
    // Hold value if the user wants to make a suggestion under certain circumstances
    private boolean _makeSuggestion;

    /**
     * Creates a User on the game board and assigns it a characterName from the CHARACTER_NAME Array.
     * _userCards holds the value of the Cards that the User's hand will contain.
     * _board is the game board.
     * _userTurn assigns the User when their turn will be. (Miss Scarlet always goes first.)
     * The setStartingPosition method sets the starting position of the User based on the User's name.
     * @param board The game board.
     * @param characterName an integer representing the character being created.
     */
    public User(Board board, int characterName){
        _userCards = new ArrayList<Card>();
        _board = board;
        setStartingPosition(CHARACTER_NAME[characterName]);
        _userTurn = characterName;
        _board.getTile(get_posX(),get_posY()).set_isOccupied(true);
    }
    
//    public Card showCard(Card show) { return show; }
//    public ArrayList<Card> get_userCards() { return _userCards; }

    // Getter
    public int get_userTurn() {
        return _userTurn;
    }
    /** Assigns hands to users*/
    public void set_userCards(ArrayList<Card> _userCards) {
        this._userCards = _userCards;
    }

    /**
     *A method that takes in opponent, suspect, weapon, and location as parameters.
     * Goes through every card in the opponent's hand to see if any of the suggested cards are in their hand.
     * Returns false if none of the cards are in their hands.
     * @param opponent
     * @param sus
     * @param wep
     * @param loc
     * @return
     */
    public boolean checkCards(ArrayList<Card> opponent, String sus, String wep, String loc) {
        for (Card c : opponent) {
            if (c.get_title() == sus) {
            	if (c.get_title() == wep || c.get_title() == loc) {
            		return true;
            	}
                return true;
            } else if (c.get_title() == wep) {
            	if (c.get_title() == sus || c.get_title() == loc) {
            		return true;
            	}
                return true;
            } else if (c.get_title() == loc) {
            	if (c.get_title() == sus || c.get_title() == wep) {
            		return true;
            	}
                return true;
            }
        }
        return false;
    }



    //Player Position
    public int get_posX() {
        return _posX;
    }

    public void set_posX(int _posX) {
        this._posX = _posX;
    }

    public int get_posY() {
        return _posY;
    }

    public void set_posY(int _posY) {
        this._posY = _posY;
    }
    
    /**
     * Uses two methods:
     * - get_posX();
     * - get_posY();
     * to create an array {x, y} of the current players coordinates
     * @return int[] of user coordinates {x,y}
     */
    public int[] get_userCoor() {
    	int x = get_posX(); 
    	int y = get_posX(); 
    	int[] coordinates = new int[2];
    	coordinates[0] = x;
    	coordinates[1] = y;
    	return coordinates;
    }
    
    /**
     * Call while user is moving to see if user coordinates lands on any special coordinates (i.e. door coordinates)
     * @return int[] _endTurn;
     */
    public int[] currentCoordinates() {
    	_currentCoor = get_userCoor();
    	return _currentCoor;
    }
    
    /**
     * After then Users first turn,
     * Call at the beginning of a user turn, stores coordinates {x, y} for comparison
     * @return int[] _startTurn;
     */
    public int[] startTurnCoordinates() {
    	_startTurn = get_userCoor();
    	return _startTurn;
    }
    
    /**
     * Call at the end of a user turn to stores coordinates {x, y} for comparison
     * @return int[] _endTurn;
     */
    public int[] endTurnCoordinates() {
    	_endTurn = get_userCoor();
    	return _endTurn;
    }
    /**
     * At the start of every turn call this method.
     * If users endTurn coordinate is different from users startTurn coordinate,
     * then user has been moved! This could be because of: suggestion or secret passage
     * @return true if user did move, false if user is at the same location
     */
    public boolean userMoved() {
    	// if coordinates are then same then false,
    	if ( _endTurn == _startTurn) {
    		return false;
    	} else {
    		// user moved to a new room! Call suggestion
    		return true;
    	}
    }
    
    /**
     * This method returns boolean if the user coordinates {x,y} is equal to the doors coordinates {x,y},
     *  which means that the user is entering a room.
     *  This will be used to trigger a the suggestion method if true.
     * @param int[] userCoor
     * @return _trigger if true, user at door coordinates, false if otherwise
     */
    public boolean enterDoor(int[] userCoor) {
    	int x = 0;
    	int y = 1;
    	boolean xBool = false;
    	boolean yBool = false;
    	
    	// Iterates through every array in 2Darray
    	for(int[] coor : Board.DOORCOOR) {
    			// checks if first value (x) in the array matches
    			if (coor[x] == userCoor[x]) {
    				xBool = true;
    			} 
    			// checks if second value (y) in the array matches
    			if (coor[y] == userCoor[y]) {
    				yBool = true;
    			}
    	}
    	// returns true if coordinates match
    	_trigger = (xBool && yBool);
    	return _trigger;
    }
    
    //TODO finish suggestion method
    /**
     * if user entered new room, fire suggestion method
     * if ended up in new room, fire option for suggestion
     */
    public void suggestion() {
    	// if entered through door
    	if (_trigger == true) {
    		/**
    		 * Prompts user for:
    		 * What User to move? Excluding yourself
    		 * User u1 or u2 etc..
    		 * What weapon to move?
    		 * Object weapon [1-6]
    		 * this.location (current room)
    		 */
    		
    		// if user was moved before start of turn, provide option to make suggestion
    	} else if (userMoved()) {
    		
    		/**
    		 * Prompt user to make a suggestion or not
    		 * _makeSuggestion = yes or no
    		 */
    		// if yes
    		if (_makeSuggestion == true) {
    			/**
        		 * Prompts user for:
        		 * What User to move?
        		 * What weapon to move?
        		 * this.location
        		 */
    		} else {
    			// check if can leave room
    			// if you can continue with turn
    			// otherwise: cry/end turn
    		}
    	}
    }
    
    //TODO finish method
    /**
     * If you entered new room or were moved to a new room from previous,
     * return room name
     * @return String roomName
     */
    public String whatRoom() {
    	String roomName = "";
    	if (_trigger == true) {
    		// get location of your coordinates and compare with room coordinates
    	}
    	if (userMoved()) {
    		// get location of your coordinates and compare with room coordinates
    	}
    	
    	return roomName;
    }
    
    //TODO Finish method to move otheruser because of suggestion
    /**
     * Moves a user to current Room because of suggestion 
     * @param otherUser
     */
    public void moveUserToRoom(User otherUser) {
    	// Get current users location
    	// Get otherUsers coordinates
    	// Get room location
    	// Change otherUsers location to room location
    }
    

    /**
     * Lets the player try to move to another tile
     * @param desiredX the x coor of Tile to travel to
     * @param desiredY the y coor of Tile to travel to
     * @return true if the player moves, false otherwise
     */
    public boolean makeMove(int desiredX, int desiredY) {
        if(_board.get_currentRoll() <= 0){
            _board.getTurnQueue().endTurn();
            _board.rollDice();
            _board.getGui().updateInfoPanel();
            return false;
        }
        Tile playersCurrentTile = _board.getTile(_posX, _posY);
        Tile desiredTile = _board.getTile(desiredX, desiredY);

        if (desiredTile == null) {
            return false;
        }

        if (desiredTile.equals(playersCurrentTile.getNorth(_board))
                || desiredTile.equals(playersCurrentTile.getSouth(_board)) || desiredTile.equals(playersCurrentTile.getEast(_board))
                || desiredTile.equals(playersCurrentTile.getWest(_board))) {

            int desiredTileRoom = desiredTile.get_parentRoom();
            int playersCurrentRoom = playersCurrentTile.get_parentRoom();

            //hallway -> hallway
            if (desiredTileRoom == -1 && playersCurrentRoom == -1 && !desiredTile.is_isOccupied()) {
                desiredTile.set_isOccupied(true);
                playersCurrentTile.set_isOccupied(false);
                _posX = desiredX;
                _posY = desiredY;
                _board.useRoll();
                _board.getGui().updateInfoPanel();
                _board.getGui().updateBoard();

                System.out.print("true");
                return true;
            }
            //hallway -> door
            else if (desiredTileRoom >= 0 && desiredTileRoom < 9 && desiredTile.get_isDoor() && playersCurrentRoom == -1 &&
                    !desiredTile.is_isOccupied()) {
                desiredTile.set_isOccupied(true);
                playersCurrentTile.set_isOccupied(false);
                _posX = desiredX;
                _posY = desiredY;
                _board.useRoll();
                _board.getGui().updateInfoPanel();
                _board.getGui().updateBoard();
                return true;
            }
            //room -> room && room -> door
            else if (desiredTileRoom == playersCurrentRoom && playersCurrentRoom >= 0 && playersCurrentRoom < 9 && !desiredTile.is_isOccupied()) {
                desiredTile.set_isOccupied(true);
                playersCurrentTile.set_isOccupied(false);
                _posX = desiredX;
                _posY = desiredY;
                playersCurrentTile = _board.getTile(_posX, _posY);
                checkPassage(playersCurrentTile, playersCurrentRoom);
                _board.getGui().updateInfoPanel();
                _board.getGui().updateBoard();

                return true;
            }
            //door -> hallway
            else if (playersCurrentRoom >= 0 && playersCurrentRoom < 9 && desiredTileRoom == -1 && playersCurrentTile.get_isDoor() &&
                    !desiredTile.is_isOccupied()) {
                desiredTile.set_isOccupied(true);
                playersCurrentTile.set_isOccupied(false);
                _posX = desiredX;
                _posY = desiredY;
                _board.useRoll();
                _board.getGui().updateInfoPanel();
                _board.getGui().updateBoard();

                return true;
            }

        }

        return false;
    }
    //TODO Since users' turn ends when a user uses a secret passage, set int[] _endTurn to grab coordinates before user decides to use secret passage and int[] _startTurn coordinates after user uses secret passage. This way we can tell if the user can make a suggestion on next turn.
    /**
     * Allows a player to use a secret passage once their are in the room and the new position in the new room is not occupied.
     * When a secret passage is used, the User's previously occupied tile is set to be unoccupied and a new position is set in the new room the User is in.
     * _board.resetRoll() ends the User's turn.
     * @param playersCurrentTile The tile the User is currently at.
     * @param newPosition The User's new position.
     */
    public void useSecretPassage(Tile playersCurrentTile, Tile newPosition){
        playersCurrentTile.set_isOccupied(false);
        _posX = newPosition.get_xCoor();
        _posY = newPosition.get_yCoor();
        newPosition.set_isOccupied(true);
        _board.resetRoll();
    }

    public void checkPassage(Tile playersCurrentTile, int playersCurrentRoom){
        if (playersCurrentTile.is_isPassage()) {
            if (playersCurrentRoom == 6) {
                Tile newPosition = (_board.getRoomByID(0).getRandomTile());
                if (newPosition != null) {
                    useSecretPassage(playersCurrentTile, newPosition);
                }
            } else if (playersCurrentRoom == 0) {
                Tile newPosition = (_board.getRoomByID(6).getRandomTile());
                if (newPosition != null) {
                    useSecretPassage(playersCurrentTile, newPosition);
                }
            } else if (playersCurrentRoom == 4) {
                Tile newPosition = (_board.getRoomByID(2).getRandomTile());
                if (newPosition != null) {
                    useSecretPassage(playersCurrentTile, newPosition);
                }
            } else if (playersCurrentRoom == 2) {
                Tile newPosition = (_board.getRoomByID(4).getRandomTile());
                if (newPosition != null) {
                    useSecretPassage(playersCurrentTile, newPosition);
                }
            }
        }
    }

    /**
     * Sets the starting positions of each player in accordance to their names.
     * @param name a String of the User's character name.
     */
    public void setStartingPosition(String name){
        switch(name){
            case "Miss Scarlett":
                _posX = 0;
                _posY = 6;
                break;

            case "Colonel Mustard":
                _posX = 0;
                _posY = 19;
                break;

            case "Mrs. Peacock":
                _posX = 7;
                _posY = 24;
                break;

            case "Mrs. White":
                _posX = 18;
                _posY = 0;
                break;

            case "Mr. Green":
                _posX = 17;
                _posY = 24;
                break;

            case "Professor Plum":
                _posX = 24;
                _posY = 6;
                break;

            default:
                _posX = 0;
                _posY = 0;
                break;

        }
    }

    public String getCharacterName(){
        return CHARACTER_NAME[_userTurn];
    }




}
