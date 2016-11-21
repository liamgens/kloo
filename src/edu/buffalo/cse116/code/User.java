package edu.buffalo.cse116.code;

import java.util.ArrayList;

/**
 * @author liamgens
 */
public class User {

    private ArrayList<Card> _userCards; // Used to return cards
    public static String[] CHARACTER_NAME = {"Miss Scarlett", "Professor Plum", "Mr. Green", "Mrs. Peacock", "Mrs. White", "Colonel Mustard"};
    private int _userTurn;
    private int _posX, _posY;
    private Board _board;

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
            _board.getGui().updateCardPanel();

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
                _board.getGui().updateCardPanel();

                System.out.print("true");
                //return true;
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
                _board.getGui().updateCardPanel();

                SuggestionPopUp popUp = new SuggestionPopUp(_board.getTile(get_posX(), get_posY()), _board);
                //popUp.moveChoseUser(popUp.get_chosenSuspect(), popUp.get_newposX(), popUp.get_newposY(), _board);
                _board.getGui().updateBoard();


                //return true;
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
                _board.getGui().updateCardPanel();


                //return true;
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
                _board.getGui().updateCardPanel();


                //return true;
            }



        }

        if(_board.get_currentRoll() <= 0){
            _board.getTurnQueue().endTurn();
            _board.rollDice();
            _board.getGui().updateInfoPanel();
            _board.getGui().updateCardPanel();

            return false;
        }

        return false;
    }

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

    public ArrayList<Card> get_userCards(){
        return _userCards;
    }




}