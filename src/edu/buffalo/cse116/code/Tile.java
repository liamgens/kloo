package edu.buffalo.cse116.code;

/**
 * Created by liamgens on 10/5/16.
 */
public class Tile {

    private boolean _isDoor, _isPassage, _isOccupied;
    private int _xCoor, _yCoor, _parentRoom;

    public Tile(int xCoor, int yCoor){
        _xCoor = xCoor;
        _yCoor = yCoor;

        //Defaults that are set
        _isDoor = false;
        _isPassage = false;
        _isOccupied = false;

        //-1 for _parentRoom implies that it has NO parent Room
        _parentRoom = -1;
    }

    //All of the getters and setters

    public boolean get_isDoor() {
        return _isDoor;
    }

    public void set_isDoor(boolean _isDoor) {
        this._isDoor = _isDoor;
    }

    public boolean is_isPassage() {
        return _isPassage;
    }

    public void set_isPassage(boolean _isPassage) {
        this._isPassage = _isPassage;
    }

    public int get_xCoor() {
        return _xCoor;
    }

    public void set_xCoor(int _xCoor) {
        this._xCoor = _xCoor;
    }

    public int get_yCoor() {
        return _yCoor;
    }

    public void set_yCoor(int _yCoor) {
        this._yCoor = _yCoor;
    }

    public int get_parentRoom() {
        return _parentRoom;
    }

    public void set_parentRoom(int _parentRoom) {
        this._parentRoom = _parentRoom;
    }

    public boolean is_isDoor() {
        return _isDoor;
    }

    public boolean is_isOccupied() {
        return _isOccupied;
    }

    public void set_isOccupied(boolean _isOccupied) {
        this._isOccupied = _isOccupied;
    }

    //gets all of the border tiles

    public Tile getNorth(Board board){
        return board.getTile(_xCoor, _yCoor - 1);
    }

    public Tile getSouth(Board board){
        return board.getTile(_xCoor, _yCoor + 1);
    }

    public Tile getEast(Board board){
        return board.getTile(_xCoor + 1, _yCoor);
    }

    public Tile getWest(Board board){
        return board.getTile(_xCoor - 1, _yCoor);
    }



}
