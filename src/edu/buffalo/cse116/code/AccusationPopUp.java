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
public class AccusationPopUp {

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

    /**
     * Creates a window that lets the player know whether they can make an accusation or not.
     * @param board
     */
    public AccusationPopUp(Board board) {
        _board = board;
        _currentPlayer = board.getCurrentPlayer();
        _currentAList = board.getListOfPlayers();
        _window = new JFrame();
        _window.setVisible(true);
        _window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _window.setLayout(new BorderLayout());
        _window.setTitle("Menu");
        generateAccusationPopUp(); // Triggers Suggestion Menu or Accusation Menu
        _window.pack();
    }

    ////////// Gui ////////////
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
