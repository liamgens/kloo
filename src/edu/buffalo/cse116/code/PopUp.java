package edu.buffalo.cse116.code;

import com.sun.deploy.util.ArrayUtil;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by dromsoft on 11/18/2016.
 */
public class PopUp{

    private JFrame _window;
    private JPanel _popupGui, _headerPanel, _bodyPanel, _suspectPanel, _weaponPanel, _roomPanel, _submitPanel;
    private JButton _suggestionButton, _accusationButton;
    private User _chosenSuspect, _currentPlayer;
    private String _suspectChosen, _weaponChosen, _roomChosen;
    private ArrayList<User> _currentAList;
    private Tile _currentTile;
    private Board _board;
    private int _sus_posX, _sus_posY;

    public String get_suspectChosen(){ return _suspectChosen; }
    public String get_weaponChosen(){ return _weaponChosen; }
    public String get_roomChosen(){ return _roomChosen; }

    public PopUp(Tile currentTile, Board board) {
        _board = board;
        _currentPlayer = board.getCurrentPlayer();
        _currentAList = board.getListOfPlayers();
        _currentTile = currentTile;
        _window = new JFrame();
        _window.setVisible(true);
        _window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _window.setLayout(new BorderLayout());
        _window.setTitle("Menu");
        mainPopUp(); // Triggers Suggestion Menu or Accusation Menu
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

    public void generateAccusationPopUp() {
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
        JLabel header = new JLabel("Make An Accusation!", JLabel.CENTER);
        _headerPanel.add(header);
            _bodyPanel.setLayout(new BoxLayout(_bodyPanel, BoxLayout.X_AXIS));
                Border blackline = BorderFactory.createLineBorder(Color.black);
            _bodyPanel.add(_suspectPanel);
                TitledBorder suspectTitle;
                suspectTitle = BorderFactory.createTitledBorder(blackline, "Suspect");
                suspectTitle.setTitleJustification(TitledBorder.CENTER);
                _suspectPanel.setBorder(suspectTitle);
                JComboBox<String> suspectNames = new JComboBox<String>(currentList(_currentAList));
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
                JComboBox<String> roomNames = new JComboBox<String>(Room.ROOMS);
                    _roomChosen = String.valueOf(roomNames.getSelectedItem());
                _roomPanel.add(roomNames);
            _bodyPanel.add(_submitPanel);
                JButton submitButton = new JButton("GO");
                submitButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        accusation();
                        _window.dispose();
                    }
                });
                _submitPanel.add(submitButton);
        _window.add(_popupGui);
    }

    public void mainPopUp() {
        _popupGui = new JPanel();
            _headerPanel = new JPanel();
            _bodyPanel = new JPanel();
        _popupGui.setLayout(new BoxLayout(_popupGui,BoxLayout.Y_AXIS));
        _popupGui.add(_headerPanel);
        _popupGui.add(_bodyPanel);
                _suggestionButton = new JButton("SUGGESTION");
                _accusationButton = new JButton("ACCUSATION");
            _headerPanel.add(_suggestionButton);
            _bodyPanel.add(_accusationButton);
        _window.add(_popupGui);
        _suggestionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                _popupGui.setVisible(false);
                generateSuggestionPopUp();
                _window.revalidate();
                _window.repaint();
                _window.pack();
            }});
        _accusationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                _popupGui.setVisible(false);
                generateAccusationPopUp();
                _window.revalidate();
                _window.repaint();
                _window.pack();
            }});
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
        }
    }

    /**
     * Returns User chosen name from drop-down list
     * @param chosenUserName
     * @return User
     */
    public User returnUser(String chosenUserName) {
        int index = 0;
        for (int i = 0; i < User.CHARACTER_NAME.length; i++) {
            if (User.CHARACTER_NAME[i] == chosenUserName) {
                index = i;
                System.out.print(true);
                break;
            }
        }
        User chosen = _currentAList.get(index);
        return chosen;
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

    ///////// ACCUSATION ///////////

    /**
     * Gets value from drop-down list and checks if it matches with the envelope cards
     */
    public void accusation() {
        int correctCounter = 0;
        ArrayList<Card> envelope = _board.get_envelope();
        for (int i = 0; i < envelope.size(); i++) {
            Card c = envelope.get(i);
            String correct = c.get_title().toLowerCase();
            if (correct == _suspectChosen.toLowerCase() || correct == _weaponChosen.toLowerCase() || correct == _roomChosen.toLowerCase()) {
                correctCounter = correctCounter + 1;
            }
        }
        if (correctCounter == 3) {
            System.out.println("YOU WIN! GAME OVER");
        } else {
            System.out.println("YOU LOSE!");
            _board.getTurnQueue().dequeue();
        }
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

    /**
     * Displays a array of names of current players in the game
     * @param currentAList
     * @return array of names of current players
     */
    public String[] currentList(ArrayList<User> currentAList) {
        String[] retVal = new String[currentAList.size()];
        for (int j = 0; j < currentAList.size(); j++) {
            retVal[j] = User.CHARACTER_NAME[j];
        }
        return retVal;
    }



}