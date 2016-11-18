package edu.buffalo.cse116.code;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * @author Liam Gensel
 */
public class Board {

    private ArrayList<Tile> _tiles;
    private ArrayList<Room> _rooms;
    private int _currentRoll;
    private TurnQueue _playerQueue;
    private String[] CHARACTER_NAME = {"Miss Scarlett", "Professor Plum", "Mr. Green", "Mrs. Peacock", "Mrs. White", "Colonel Mustard"};
    private Gui _gui;
    // Strings to represent weapon items since each weapon needs to be in a room
    static final Object[] WEAPONS = {"WRENCH", "CANDLESTICK", "LEAD PIPE", "ROPE", "REVOLVER", "KNIFE"};
    // static to access 17 door coordinates in other classes
    static final int[][] DOORCOOR = {{4,4},{5,9},{3,11},{1,13},{5,16},{5,21},{8,21},{9,18},{15,18},{16,21},{20,20},{19,14},{20,8},{21,4},{13,4},{12,4},{8,2}};

    public Board(int numberOfPlayers, Gui gui){
        _tiles = generateBoard(25,25);
        _rooms = new ArrayList<Room>();
        this.addDefaultRooms();
        this.addDefaultDoors();
        this.addSecretPassages();
        _playerQueue = new TurnQueue(numberOfPlayers, this);
        _gui = gui;

        /** uncomment
        Deck deck = new Deck();
        ArrayList<Card> envelope = deck.get_envelopeCards();
        ArrayList<Card> deal = deck.get_deck();
        */
    }


    /**
     * Generates a board of tiles with dimensions width*height
     * @return an ArrayList of Tiles with size width *height
     */
    private ArrayList<Tile>generateBoard(int width, int height){
        ArrayList<Tile> generatedBoard = new ArrayList<Tile>();
        for (int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                generatedBoard.add(new Tile(x, y));
            }
        }
        return generatedBoard;
    }


    public ArrayList<Tile> get_tiles() {
        return _tiles;
    }


    /**
     * The method will add the room entered as an argument to the Board's list of rooms
     * @param room the room object to be added to the list of rooms
     */
    public void addRoom(Room room){
        _rooms.add(room);
    }

    /**
     * The method will iterate through each tile in _tiles and return the number of tiles in a room based on
     * the room that was entered as an argument.
     *
     *
     *@param room number corresponding to the room name
     *@return the number of tiles for the room parameter
     *
     */
    public int roomTileCount(int room) {
        int count = 0;
        for (Tile t : _tiles) {
            if (t.get_parentRoom() == room) {
                count++;
            }
        }
        return count;
    }

    /**
     * Adds the default rooms to the board
     */
    public void addDefaultRooms(){
        this.addRoom(new Room(this.get_tiles(), 0, 0, 6, 5, 6)); //adds the Study
        this.addRoom(new Room(this.get_tiles(), 0, 7, 6, 5, 7)); //adds the Library
        this.addRoom(new Room(this.get_tiles(), 0, 13, 6, 5, 8)); //adds the Billard Room
        this.addRoom(new Room(this.get_tiles(), 0, 20, 6, 5, 2)); //adds the Conservatory
        this.addRoom(new Room(this.get_tiles(), 8, 0, 10, 5, 5)); //adds the Hall
        this.addRoom(new Room(this.get_tiles(), 8, 18, 9, 7, 1)); //adds the Ballroom
        this.addRoom(new Room(this.get_tiles(), 20, 0, 5, 5, 4)); //adds the Lounge
        this.addRoom(new Room(this.get_tiles(), 19, 8, 6, 10, 3)); //adds the Dining Room
        this.addRoom(new Room(this.get_tiles(), 19, 20, 6, 5, 0)); //adds the Kitchen
        this.addRoom(new Room(this.get_tiles(), 9, 7, 8, 9, 9)); //adds the Cellar
    }


    /**
     * Adds all of the doors
     * 17 Doors total
     */
    public void addDefaultDoors(){
        for(int i = 0; i < DOORCOOR.length; i++){
            getTile(DOORCOOR[i][0], DOORCOOR[i][1]).set_isDoor(true);
        }
    }

    /**
     * Add the secret passages
     */
    public void addSecretPassages(){
        int[][] secretCoors = {{0,0},{24,0},{0,24},{24,24}};
        for(int i = 0; i < secretCoors.length; i++){
            getTile(secretCoors[i][0], secretCoors[i][1]).set_isPassage(true);
        }
    }

    /**
     * Mimics rolling dice
     * @return int value representing the distance a player can move.
     */
    public int rollDice(){
        _currentRoll = new Random().nextInt(6) + 1;
        return _currentRoll;
    }

    /**
     * Uses the die roll by decrementing the _currentRoll.
     */
    public void useRoll(){
        if(_currentRoll > 0) {
            _currentRoll --;
        }
    }

    /**
     * Sets the _currentRoll to zero and ends the User's turn.
     */
    public void resetRoll(){
        _currentRoll = 0;
    }


    public int get_currentRoll(){
        return _currentRoll;
    }

    public void set_currentRoll(int roll ){_currentRoll = roll;}

    /**
     * Gets a specific tile on the board.
     * @param x The x coordinate of the tile on the board.
     * @param y The y coordinate of the tile on the board.
     * @return
     */
    public Tile getTile(int x, int y){
        for(Tile t : _tiles){
            if(t.get_xCoor() == x && t.get_yCoor() == y){
                return t;
            }
        }
        return null;
    }

    /**
     * Gets the name of a specific room by its index in the _rooms ArrayList.
     * @param iD int of the room's index on the _rooms ArrayList.
     * @return the room's name.
     */
    public Room getRoomByID(int iD){
        for(Room r : _rooms){
            if(r.get_idx() == iD){
                return r;
            }
        }return null;
    }



    public User getCurrentPlayer(){
        return _playerQueue.peek();
    }

    public String getCurrentPlayerName(){
        return _playerQueue.peek().getCharacterName();
    }

    public Gui getGui(){
        return _gui;
    }

    public ArrayList<User> getListOfPlayers() {
        return _playerQueue.get_listOfPlayers();
    }

    public TurnQueue getTurnQueue(){
        return _playerQueue;
    }


}
