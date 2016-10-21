package edu.buffalo.cse116.code;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by liamgens on 10/9/16.
 */
public class User {

    private int _posX, _posY;
    private Board _board;

    public User(Board board, String characterName){
        _board = board;
        setStartingPosition(characterName);
        _board.getTile(get_posX(),get_posY()).set_isOccupied(true);
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
                return true;
            }
            //room -> room && room -> door
            else if (desiredTileRoom == playersCurrentRoom && playersCurrentRoom >= 0 && playersCurrentRoom < 9 && !desiredTile.is_isOccupied()) {
                desiredTile.set_isOccupied(true);
                playersCurrentTile.set_isOccupied(false);
                _posX = desiredX;
                _posY = desiredY;
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
                return true;
            }

            playersCurrentTile = _board.getTile(_posX, _posY);

            //secret passage to random roomTile
            if (playersCurrentTile.is_isPassage())
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
        return false;
    }

    public void useSecretPassage(Tile playersCurrentTile, Tile newPosition){
        playersCurrentTile.set_isOccupied(false);
        _posX = newPosition.get_xCoor();
        _posY = newPosition.get_yCoor();
        newPosition.set_isOccupied(true);
        _board.resetRoll();
    }

    public void setStartingPosition(String name){
        switch(name){
            case "Mrs. White":
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

            case "Miss Scarlett":
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

    public String[] makeSuggestion(String room, String suspect, String weapon){
        String[] suggestion = {room, suspect, weapon};
        return suggestion;
    }




}
