package edu.buffalo.cse116.code;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by liamgens on 10/5/16.
 * @author Liam Gensel
 */
public class Room {

    private int _topLx, _topLy, _width, _height, _idx;
    private String _roomName;
    // static to access array data in other classes
    static final String[] ROOMS = {"Kitchen", "Ballroom", "Conservatory", "Dining Room",
            "Lounge", "Hall", "Study", "Library", "Billard Room", "Cellar"};
    private ArrayList<Tile> _tilesOfBoard;
    private ArrayList<Tile> _tilesOfRoom;    

    /**
     * Creates a Room that takes in an ArrayList of Tiles.
     * @param tiles The tiles inside the room.
     * @param topLx The X-coordinate of the top-left tile in the room.
     * @param topLy The Y-coordinate of the top-left tile in the room.
     * @param width The desired width of the room.
     * @param height The desired height of the room.
     * @param idx The room that this new room represents.
     */
    public Room(ArrayList<Tile> tiles, int topLx, int topLy, int width, int height, int idx){
        _tilesOfBoard = tiles;
        _width = width;
        _height = height;
        _topLx = topLx;
        _topLy = topLy;
        _idx = idx;
        _roomName = ROOMS[idx];
        _tilesOfRoom = tellTilesWhereTheyAre();

    }

    /**
     *Sets tiles parent room and adds them to the _tilesOfRoom ArrayList<Tile>
     * @return the ArrayList<Tile> that contains the room's tiles
     */
    public ArrayList<Tile> tellTilesWhereTheyAre(){
        ArrayList<Tile> retVal = new ArrayList<Tile>();
        for(Tile t : _tilesOfBoard){
            if(tileIsInBounds(t)){
                t.set_parentRoom(_idx);
                retVal.add(t);
            }
        }
        return retVal;
    }

    /**
     * Checks if a tile is in the bounds of a room based on the dimensions and topLeft tile of a room.
     * @param t tile to be checked
     * @return if the tile is in the room or not
     */
    public boolean tileIsInBounds(Tile t){
        return t.get_xCoor() >= _topLx && t.get_xCoor() < _topLx + _width && t.get_xCoor() < 25 &&
                t.get_yCoor() >= _topLy && t.get_yCoor() < _topLy + _height && t.get_yCoor() < 25;
    }


    /**
     * Takes in an x&y coordinate of the door to be set and it becomes a door if it is in the room,
     * and the x&y coordinate are equal and it is not already a door.
     *
     * @param x the xCoor of the door to be added
     * @param y the yCoor of the door to be added
     * @return true if the door is added and false if the door is not added
     */
    public boolean addDoors(int x, int y){
        //Will take in inputs for which xy coordinates are doors and set the doors boolean for the tile
        for(Tile t : _tilesOfRoom){
            if(t.get_xCoor() == x && t.get_yCoor() == y && !t.get_isDoor() && tileIsInBounds(t) && isValidDoorLocation(t)) {
                t.set_isDoor(true);
                return true;
            }
        }
        return false;
    }


    /**
     * Checks if the tile is on the border of the room -- a valid location for a door
     *
     * @param t tile to be examined
     * @return true if the tile is on the border, false if it is not.
     */
    public boolean isValidDoorLocation(Tile t){
        int x = t.get_xCoor();
        int y = t.get_yCoor();

        if(tileIsInBounds(t)) {
            if (x == _topLx || y == _topLy || x == _topLx + _width -1 || y == _topLy + _height - 1) {
                if (x > 0 && x < 24 && y > 0 && y < 24) {
                    return true;
                }
            }
        }
        return false;
    }

    public int get_idx() {
        return _idx;
    }

    /**
     * Gets a random tile from a corner room.
     * @return
     */
    public Tile getRandomTile(){
        ArrayList<Tile> validRoomTilesToTeleportTo = new ArrayList<Tile>();
        for(Tile t : _tilesOfRoom){
            if(!isValidDoorLocation(t) && !t.is_isPassage()){
                validRoomTilesToTeleportTo.add(t);
            }
        }

        for(Tile t: validRoomTilesToTeleportTo){
            if(!t.is_isOccupied()){
                return t;
            }
        }
        return null;
    }

    public boolean isRoomBorder(Tile t){
        int x = t.get_xCoor();
        int y = t.get_yCoor();

        if(tileIsInBounds(t)) {
            if (x == _topLx || y == _topLy || x == _topLx + _width -1 || y == _topLy + _height - 1) {
                return true;
            }
        }
        return false;
    }
}
