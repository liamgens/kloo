package edu.buffalo.cse116.code;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

/**
 * Created by dromsoft on 11/20/2016.
 */
public class SuggestionPopUp {

    private JFrame _window, _prompt, _showCard;
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
        _window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _window.setSize(200, 100);
        _window.setLocationRelativeTo(null);
        _window.setUndecorated(true);
        _window.setVisible(true);
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
        _suspectPanel.add(suspectNames);
        _bodyPanel.add(_weaponPanel);
        TitledBorder weaponTitle;
        weaponTitle = BorderFactory.createTitledBorder(blackline, "Weapon");
        weaponTitle.setTitleJustification(TitledBorder.CENTER);
        _weaponPanel.setBorder(weaponTitle);
        JComboBox<String> weaponNames = new JComboBox<String>(Board.WEAPONS);
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

                _suspectChosen = String.valueOf(suspectNames.getSelectedItem());
                _weaponChosen = String.valueOf(weaponNames.getSelectedItem());

                System.out.println(_suspectChosen);
                System.out.println(_weaponChosen);

                suggestion();
                _window.dispose();
            }
        });
        _submitPanel.add(submitButton);
        _window.add(_popupGui);
    }

    public void displayCards(String[] cards, String s) {
        _prompt = new JFrame();
        JPanel show = new JPanel();
        JPanel top = new JPanel();
        JPanel title = new JPanel();
        JPanel body = new JPanel();
        JPanel action = new JPanel();
        JList choose = new JList(cards);
        JLabel intro = new JLabel("Please select which card to show:");
        JLabel gotem = new JLabel("Current User: " + s);
        JLabel instructions = new JLabel("Select Cards:");
        JButton submit = new JButton("Submit");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = "";
                if (choose.getSelectedValue() != null) {
                    selected = choose.getSelectedValue().toString();
                    showCard(selected);
                } else {
                    choose.setSelectedIndex(0);
                    selected = choose.getSelectedValue().toString();
                    showCard(selected);
                }
                    show.remove(instructions);
                    show.remove(choose);
                    _prompt.dispose();
            }
        });
        _prompt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _prompt.setBounds(250, 250, 300, 300);
        _prompt.setUndecorated(true);
        _prompt.setVisible(true);
        _prompt.setLayout(new BorderLayout());
        _prompt.setTitle("Select Card to show:");
        _prompt.add(show);

        show.add(top);
        show.add(title);
        show.add(body);
        show.add(action);
        show.setLayout(new BoxLayout(show,BoxLayout.Y_AXIS));

        top.add(gotem);
        title.add(intro);
        body.add(instructions);
        body.add(choose);
        action.add(submit);
        _prompt.pack();
    }

    public void showCard(String s) {
        _showCard = new JFrame();
        JPanel display = new JPanel();
        JPanel top = new JPanel();
        JPanel body = new JPanel();
        JPanel storeButton = new JPanel();
        JLabel showing = new JLabel("Showing:");
        JLabel result = new JLabel(s + " Card");
        JButton close = new JButton("Close");
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _showCard.dispose();
            }
        });

        _showCard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _showCard.setBounds(250, 250, 300, 300);
        _showCard.setSize(200, 100);
        _showCard.setUndecorated(true);
        _showCard.setVisible(true);

        _showCard.add(display);
        display.add(top);
        display.add(body);
        display.add(storeButton);

        top.add(showing);
        body.add(result);
        storeButton.add(close);

        showing.setForeground(Color.BLUE);
        result.setForeground(Color.red);
    }


    /////////// SUGGESTION //////////

    public void suggestion() {
        _chosenSuspect = returnUser(_suspectChosen);
        moveUserHere(_chosenSuspect, _currentPlayer);
        checkAllCards(_currentAList, _board, _currentPlayer);
        _board.get_playerQueue().endTurn();
        _board.rollDice();
        _board.getGui().updateBoard();
        _board.getGui().updateCardPanel();
        _board.getGui().updateInfoPanel();
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
        ArrayList<User> test = _board.getListOfPlayers();
        for (User u : test) {
            if (u.getCharacterName() == chosenUserName) {
                //System.out.print(u.getCharacterName());
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
        //_board.get_playerQueue().endTurn();
        _board.resetRoll();
    }

    public void checkAllCards(ArrayList<User> currentAList, Board board, User current) {
        String user = "";
        ArrayList<Card> show = new ArrayList<Card>();


        for (User u : currentAList) {
            if (current.checkCards(u.get_userCards(), _suspectChosen, _weaponChosen, Room.ROOMS[_currentTile.get_parentRoom()])) {
                user = u.getCharacterName();

                for (Card c : u.get_userCards()) {
                    if (c.get_title() == _suspectChosen) {
                        show.add(c);
                    }
                    if (c.get_title() == _weaponChosen) {
                        show.add(c);
                    }
                    if (c.get_title() == Room.ROOMS[_currentTile.get_parentRoom()]) {
                        show.add(c);
                    }
                }

                System.out.println("Breaking");
                break;
            } else {
                System.out.println("false");
            }

        }

        String[] array = new String[show.size()];
        for (int i = 0; i < show.size(); i++) {
            array[i] = show.get(i).get_title();
        }


        displayCards(array, user);
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

