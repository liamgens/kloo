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

    private JFrame _window, _winPrompt, _losPrompt;
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
        JComboBox<String> roomNames = new JComboBox<String>(Room.ROOMS);
        JButton submitButton = new JButton("GO");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _suspectChosen = String.valueOf(suspectNames.getSelectedItem());
                _weaponChosen = String.valueOf(weaponNames.getSelectedItem());
                _roomChosen = String.valueOf(roomNames.getSelectedItem());
                accusation(_suspectChosen, _weaponChosen, _roomChosen);
                _window.dispose();
            }
        });
        _roomPanel.add(roomNames);
        _bodyPanel.add(_submitPanel);
        _submitPanel.add(submitButton);
        _window.add(_popupGui);
    }
    public void generateWinner(String s) {
        _winPrompt = new JFrame();
        JPanel show = new JPanel();
        JPanel top = new JPanel();
        JPanel player = new JPanel();
        JPanel message = new JPanel();
        JPanel bottom = new JPanel();
        JLabel winner = new JLabel("Congratulations:");
        JLabel playerWin = new JLabel(s);
        JLabel youWin = new JLabel("YOU WIN!");
        JButton endGame = new JButton("END GAME");
        endGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        _winPrompt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _winPrompt.setBounds(250, 250, 300, 300);
        _winPrompt.setUndecorated(true);
        _winPrompt.setVisible(true);
        _winPrompt.setLayout(new BorderLayout());
        _winPrompt.add(show);
        show.setLayout(new BoxLayout(show,BoxLayout.Y_AXIS));
        show.add(top);
        show.add(player);
        show.add(message);
        show.add(bottom);

        top.add(winner);
        player.add(playerWin);
        message.add(youWin);
        bottom.add(endGame);

        winner.setForeground(Color.BLUE);
        player.setForeground(Color.BLACK);
        youWin.setForeground(Color.MAGENTA);

        _winPrompt.pack();
    }

    public void generateLoser(String s) {
        String[] winningCards = new String[3];
        for (int i = 0; i < _board.get_envelope().size(); i++) {
            winningCards[i] = _board.get_envelope().get(i).get_title().toString();
        }
        _losPrompt = new JFrame();
        JPanel show = new JPanel();
        JPanel top = new JPanel();
        JPanel chin = new JPanel();
        JPanel title = new JPanel();
        JPanel body = new JPanel();
        JPanel bottom = new JPanel();
        JList list = new JList(winningCards);

        _losPrompt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _losPrompt.setBounds(250, 250, 300, 300);
        _losPrompt.setUndecorated(true);
        _losPrompt.setVisible(true);
        _losPrompt.setLayout(new BorderLayout());

        JLabel loser = new JLabel(s + " has chosen incorrectly", JLabel.CENTER);
        JLabel sentence = new JLabel("Correct Cards:");
        JLabel caption = new JLabel("YOU LOSE");
        JButton close = new JButton("Close");
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _losPrompt.dispose();
            }
        });
        _losPrompt.add(show);
        show.setLayout(new BoxLayout(show,BoxLayout.Y_AXIS));
        show.add(top);
        show.add(chin);
        show.add(title);
        show.add(body);
        show.add(bottom);

        top.add(loser);
        loser.setForeground(Color.BLUE);
        chin.add(caption);
        caption.setForeground(Color.red);
        title.add(sentence);
        body.add(list);
        bottom.add(close);

        _losPrompt.pack();
    }

    ///////// ACCUSATION ///////////

    /**
     * Gets value from drop-down list and checks if it matches with the envelope cards
     */
    public void accusation(String sus, String wep, String room) {
        int correctCounter = 0;
        ArrayList<Card> envelope = _board.get_envelope();
        for (Card c : envelope) {
            if (c.get_title().toString() == sus || c.get_title().toString() == wep || c.get_title().toString() == room) {
                correctCounter = correctCounter + 1;
            }
        }

        if (correctCounter == 3) {
            generateWinner(_currentPlayer.getCharacterName().toString());
        } else {
            generateLoser(_currentPlayer.getCharacterName().toString());
            _board.getTurnQueue().dequeue();
        }
    }

    public void checkBlocking(Tile currentTile, int currentRoom) {
        if (_currentTile.get_isDoor()) {
            if (_currentTile.get_parentRoom() == 0) {
                Tile newPosition = (_board.getRoomByID(0).getRandomTile());
                if (newPosition != null) {
                    setNewLocation(currentTile, newPosition);
                }
            }
        }
    }

    public void setNewLocation(Tile currentTile, Tile newPosition) {
        currentTile.set_isOccupied(false);
        newPosition.set_isOccupied(true);
        _board.resetRoll();
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