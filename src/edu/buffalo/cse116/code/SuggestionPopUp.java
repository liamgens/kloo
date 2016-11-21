package edu.buffalo.cse116.code;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by dromsoft on 11/20/2016.
 */
public class SuggestionPopUp {

    private JFrame _window;
    private JPanel _popupGui, _headerPanel, _bodyPanel, _suspectPanel, _weaponPanel, _roomPanel, _submitPanel;

    private User _chosenSuspect, _currentPlayer;
    private String _suspectChosen, _weaponChosen;
    private ArrayList<User> _currentAList;
    private Tile _currentTile;
    private Board _board;
    private int _sus_posX, _sus_posY;

    //////// GETTERS /////////
    public String get_suspectChosen(){ return _suspectChosen; }
    public String get_weaponChosen(){ return _weaponChosen; }
    public int get_newposX() { return _sus_posX; }
    public int get_newposY() { return _sus_posY; }
    public User get_chosenSuspect() { return _chosenSuspect;}

    public SuggestionPopUp(Tile currentTile, Board board) {
        _board = board;
        _currentPlayer = board.getCurrentPlayer();
        _currentAList = board.getListOfPlayers();
        _currentTile = currentTile;
        _window = new JFrame();
        _window.setVisible(true);
        _window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _window.setLayout(new BorderLayout());
        _window.setTitle("Menu");
        generateSuggestionPopUp(); // Triggers Suggestion Menu or Accusation Menu
        _window.pack();
    }

    ////////// Gui ////////////

    public void generateSuggestionPopUp() {
        _popupGui = new JPanel();
        _headerPanel = new JPanel();
        _bodyPanel = new JPanel();
        _suspectPanel = new JPanel();
        _weaponPanel = new JPanel();
        _roomPanel = new JPanel();
        _submitPanel = new JPanel();

        _popupGui.setLayout(new BoxLayout(_popupGui,BoxLayout.Y_AXIS));
        _popupGui.add(_headerPanel);
        _popupGui.add(_bodyPanel);
        _popupGui.setBorder(BorderFactory.createLineBorder(Color.black));
        JLabel header = new JLabel("Make A Suggestion!", JLabel.CENTER);
        _headerPanel.add(header);

        _bodyPanel.setLayout(new BoxLayout(_bodyPanel, BoxLayout.X_AXIS));
        Border blackline = BorderFactory.createLineBorder(Color.black);
        _bodyPanel.add(_suspectPanel);
        TitledBorder suspectTitle;
        suspectTitle = BorderFactory.createTitledBorder(blackline, "Suspect");
        suspectTitle.setTitleJustification(TitledBorder.CENTER);
        _suspectPanel.setBorder(suspectTitle);
        JComboBox<String> suspectNames = new JComboBox<String>(currentListMinusOne(_currentAList));
        _suspectChosen = String.valueOf(suspectNames.getSelectedItem());
        _suspectPanel.add(suspectNames);
        _bodyPanel.add(_weaponPanel);
        TitledBorder weaponTitle;
        weaponTitle = BorderFactory.createTitledBorder(blackline, "Weapon");
        weaponTitle.setTitleJustification(TitledBorder.CENTER);
        _weaponPanel.setBorder(weaponTitle);
        JComboBox<String> weaponNames = new JComboBox<String>(Board.WEAPONS);
        _weaponChosen = String.valueOf(weaponNames.getSelectedItem());
        _weaponPanel.add(weaponNames);
        _bodyPanel.add(_roomPanel);
        TitledBorder roomTitle;
        roomTitle = BorderFactory.createTitledBorder(blackline, "Room");
        roomTitle.setTitleJustification(TitledBorder.CENTER);
        _roomPanel.setBorder(roomTitle);
        JComboBox<String> currentRoom = new JComboBox<String>();
        currentRoom.addItem(Room.ROOMS[_currentTile.get_parentRoom()]);
        currentRoom.setEnabled(false);
        _roomPanel.add(currentRoom);
        _bodyPanel.add(_submitPanel);
        JButton submitButton = new JButton("GO");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                suggestion();
                _window.dispose();
            }
        });
        _submitPanel.add(submitButton);
        _window.add(_popupGui);
    }

    /////////// SUGGESTION //////////

    public void suggestion() {
        _chosenSuspect = returnUser(_suspectChosen);
        _sus_posX = _chosenSuspect.get_posX();
        _sus_posY = _chosenSuspect.get_posY();
        moveUserHere(_chosenSuspect, _currentPlayer);
    }

    /**
     * Method that moves suspect to the current players room.
     * @param suspect
     * @param currentPlayer
     */
    public void moveUserHere(User suspect, User currentPlayer) {
        Tile currentTile = _board.getTile(currentPlayer.get_posX(), currentPlayer.get_posY());
        Tile suspectTile = _board.getTile(suspect.get_posX(), suspect.get_posY());

        int playersCurrentRoom = currentTile.get_parentRoom();
        int suspectCurrentRoom = suspectTile.get_parentRoom();

        if (playersCurrentRoom != suspectCurrentRoom) {
            checkPassage(currentTile, suspectTile, playersCurrentRoom);
            _chosenSuspect.set_posX(_sus_posX);
            _chosenSuspect.set_posY(_sus_posY);
            _board.getGui().updateInfoPanel();
            _board.getGui().updateBoard();
            _board.getGui().updateCardPanel();
        }
    }

    /**
     * Returns User chosen name from drop-down list
     * @param chosenUserName
     * @return User
     */
    public User returnUser(String chosenUserName) {
        for (User u : _board.getListOfPlayers()) {
            if (u.getCharacterName() == chosenUserName) {
                return u;
            }
        }
        return null;
    }

    /**
     * Edited copy of "checkPassage()" method from UserClass
     *
     * Checks if current user is at a door leading to a specific room,
     * then selects a random tile from the room and sets the suspects current tile to the new tile
     * @param playersCurrentTile
     * @param suspectCurrentTile
     * @param playersCurrentRoom
     */
    public void checkPassage(Tile playersCurrentTile, Tile suspectCurrentTile, int playersCurrentRoom){
        if (playersCurrentTile.get_isDoor()) {
            if (playersCurrentRoom == 0) {
                Tile newPosition = (_board.getRoomByID(0).getRandomTile());
                if (newPosition != null) {
                    setNewLocation(suspectCurrentTile, newPosition);
                }
            } else if (playersCurrentRoom == 1) {
                Tile newPosition = (_board.getRoomByID(1).getRandomTile());
                if (newPosition != null) {
                    setNewLocation(suspectCurrentTile, newPosition);
                }
            } else if (playersCurrentRoom == 2) {
                Tile newPosition = (_board.getRoomByID(2).getRandomTile());
                if (newPosition != null) {
                    setNewLocation(suspectCurrentTile, newPosition);
                }
            } else if (playersCurrentRoom == 3) {
                Tile newPosition = (_board.getRoomByID(3).getRandomTile());
                if (newPosition != null) {
                    setNewLocation(suspectCurrentTile, newPosition);
                }
            } else if (playersCurrentRoom == 4) {
                Tile newPosition = (_board.getRoomByID(4).getRandomTile());
                if (newPosition != null) {
                    setNewLocation(suspectCurrentTile, newPosition);
                }
            } else if (playersCurrentRoom == 5) {
                Tile newPosition = (_board.getRoomByID(5).getRandomTile());
                if (newPosition != null) {
                    setNewLocation(suspectCurrentTile, newPosition);
                }
            } else if (playersCurrentRoom == 6) {
                Tile newPosition = (_board.getRoomByID(6).getRandomTile());
                if (newPosition != null) {
                    setNewLocation(suspectCurrentTile, newPosition);
                }
            } else if (playersCurrentRoom == 7) {
                Tile newPosition = (_board.getRoomByID(7).getRandomTile());
                if (newPosition != null) {
                    setNewLocation(suspectCurrentTile, newPosition);
                }
            } else if (playersCurrentRoom == 8) {
                Tile newPosition = (_board.getRoomByID(8).getRandomTile());
                if (newPosition != null) {
                    setNewLocation(suspectCurrentTile, newPosition);
                }
            }
        }
    }

    /**
     * Edited copy of "useSecretPassage()" method from User Class
     *
     * Sets new location of the desired CurrentTile and the new Tile position
     * @param desiredCurrentTile
     * @param newPosition
     */
    public void setNewLocation(Tile desiredCurrentTile, Tile newPosition){
        desiredCurrentTile.set_isOccupied(false);
        _sus_posX = newPosition.get_xCoor();
        _sus_posY = newPosition.get_yCoor();
        newPosition.set_isOccupied(true);
        _board.resetRoll();
    }

    /////////// DROP DOWN LIST METHODS ///////////

    /**
     * Displays a array of names of current players in the game minus current player
     * @param currentAList
     * @return array of names of current players
     */
    public String[] currentListMinusOne(ArrayList<User> currentAList) {
        String cur = _currentPlayer.getCharacterName();
        ArrayList<String> temp = new ArrayList<String>();
        for (User u : currentAList) {
            if (u.getCharacterName() != cur) {
                String name = u.getCharacterName();
                temp.add(name);
            }
        }
        String[] stored = temp.toArray(new String[temp.size()]);
        return stored;
    }


}

