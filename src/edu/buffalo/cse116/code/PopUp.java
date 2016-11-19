package edu.buffalo.cse116.code;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by dromsoft on 11/18/2016.
 */
public class PopUp {

    private JFrame _window;
    private JPanel _popupGui, _headerPanel, _bodyPanel, _suspectPanel, _weaponPanel, _roomPanel, _submitPanel;
    private JButton _suggestionButton, _accusationButton;
    public JFrame get_window() {
        return _window;
    }

    public PopUp() {
        _window = new JFrame();
        _window.setVisible(true);
        _window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _window.setLayout(new BorderLayout());
        _window.setTitle("Menu");
        mainPopUp();
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
                JComboBox<String> suspectNames = new JComboBox<String>(User.CHARACTER_NAME);
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
                currentRoom.addItem("Current Room");
                currentRoom.setEnabled(false);
                _roomPanel.add(currentRoom);
            _bodyPanel.add(_submitPanel);
                JButton submitButton = new JButton("GO");
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
                JComboBox<String> suspectNames = new JComboBox<String>(User.CHARACTER_NAME);
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
                JComboBox<String> roomNames = new JComboBox<String>(Board.ROOMS);
                _roomPanel.add(roomNames);
            _bodyPanel.add(_submitPanel);
                JButton submitButton = new JButton("GO");
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
                generateSuggestionPopUp();
                System.out.println("You clicked the button");
            }});
        _accusationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                generateAccusationPopUp();
                System.out.println("You clicked the button");
            }});
    }
}
