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
    private ArrayList<User> _current;

    public String get_suspectChosen(){ return _suspectChosen; }
    public String get_weaponChosen(){ return _weaponChosen; }
    public String get_roomChosen(){ return _roomChosen; }

    public JFrame get_window() {
        return _window;
    }

    public PopUp(ArrayList<User> currentAList, User currentPlayer) {
        _currentPlayer = currentPlayer;
        _current = currentAList;
        _window = new JFrame();
        _window.setVisible(true);
        _window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _window.setLayout(new BorderLayout());
        _window.setTitle("Menu");
        mainPopUp(); // Triggers Suggestion Menu or Accusation Menu
        _window.pack();
    }

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
                JComboBox<String> suspectNames = new JComboBox<String>(currentList(_current));
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
                currentRoom.addItem("Current Room");
                currentRoom.setEnabled(false);
                _roomPanel.add(currentRoom);
            _bodyPanel.add(_submitPanel);
                JButton submitButton = new JButton("GO");
                submitButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        suggestion();
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
                JComboBox<String> suspectNames = new JComboBox<String>(currentList(_current));
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
                JComboBox<String> roomNames = new JComboBox<String>(Board.ROOMS);
                    _roomChosen = String.valueOf(roomNames.getSelectedItem());
                _roomPanel.add(roomNames);
            _bodyPanel.add(_submitPanel);
                JButton submitButton = new JButton("GO");
                submitButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        accusation();
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

    public void suggestion() {
        _chosenSuspect = returnUser(_suspectChosen);
        moveUserHere(_chosenSuspect);


    }

    /**
     * Returns Chosen User from Array list of User based off their name
     * @param chosenUserName
     * @return User
     */
    public User returnUser(String chosenUserName) {
        int index = 0;
        for (int i = 0; i < User.CHARACTER_NAME.length; i++) {
            if (User.CHARACTER_NAME[i] == chosenUserName) {
                index = i;
                break;
            }
        }
        User chosen = _current.get(index);
        return chosen;
    }

    /**
     *
     * @param user
     */
    public void moveUserHere(User user) {

    }

    /**
     * Gets
     */
    public void accusation() {

    }

    /**
     * Displays a array of names of current players in the game
     * @param currentPlayers
     * @return array of names of current players
     */
    public String[] currentList(ArrayList<User> currentPlayers) {
        String[] retVal = new String[currentPlayers.size()];
        for (int j = 0; j < currentPlayers.size(); j++) {
            retVal[j] = User.CHARACTER_NAME[j];
        }
        return retVal;
    }




}
