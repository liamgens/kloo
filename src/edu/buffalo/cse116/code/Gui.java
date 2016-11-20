package edu.buffalo.cse116.code;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * These GUI requirements specify that you display:
 * the board, 
 * "die roll", 
 * player pieces, 
 * and cards.
 * Created by liamgens on 10/24/16.
 */
public class Gui {

    private JFrame _window;
    private JPanel _boardGui, _currentCards, _infoPanel;
    private Board _board;
    private JLabel _cardLabel;

    private Color _hallway = new Color(24, 189, 156);
    private Color _door = new Color(186, 184, 184);
    private Color _room = new Color(1, 99, 122);

    private JLabel _currentPlayer, _currentRoll;
    private ArrayList<JButton> _buttons;
    private ArrayList<User> _listOfPlayers;
    private ImageIcon _hallwayIcon;


    public Gui(int numberOfPlayers) {
        _window = new JFrame();
        _window.setTitle("Kloo");
        _window.setVisible(true);
        _window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _board = new Board(numberOfPlayers, this);
        _window.setLayout(new BorderLayout());
        _buttons = new ArrayList<JButton>();
        _listOfPlayers = _board.getListOfPlayers();
        _board.rollDice();
        JLabel listOfRooms = new JLabel();
        listOfRooms.setText("1: Kitchen | 2: Ballroom | 3: Conservatory | 4. Dining Room | 5: Lounge | 6: Hall | 7: Study | 8: Billard Room");
        JPanel rooms = new JPanel();
        rooms.add(listOfRooms);
        _window.add(rooms, BorderLayout.NORTH);
        generateGameBoard();
        generateInfoPanel();
        generateCardPanel();
        _window.pack();
    }

    public Board get_board() {
        return _board;
    }

    public void generateGameBoard() {

        _boardGui = new JPanel();
        _boardGui.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        for (Tile t : _board.get_tiles()) {
            BufferedImage img = new BufferedImage(25, 25, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = img.createGraphics();
            g2d.setColor(_hallway);

            changeColor(g2d, t);

            g2d.fillRect(0, 0, 25, 25);
            g2d.dispose();

            _hallwayIcon = new ImageIcon(img);


            JButton space = new JButton(new ImageIcon(img));
            if(t.get_parentRoom() != -1 && t.get_parentRoom() != 9 && !t.is_isDoor() && !t.is_isPassage()){
                int roomID = t.get_parentRoom();
                String room = "" + (roomID + 1);
                space = new JButton(room , new ImageIcon(img));

            }else{
                space = new JButton(new ImageIcon(img));
            }

            space.setHorizontalTextPosition(JButton.CENTER);
            space.setVerticalTextPosition(JButton.CENTER);
            c.insets = new Insets(0, 0, 0, 0);
            space.setMargin(new Insets(0, 0, 0, 0));
            space.setContentAreaFilled(false);
            space.setFocusPainted(false);
            Border thinBorder = LineBorder.createBlackLineBorder();
            space.setBorder(thinBorder);

            c.gridx = t.get_xCoor();
            c.gridy = t.get_yCoor();


            //Adds an event listener to the buttons that prints the XY coor of the buttons
            space.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("x: " + t.get_xCoor() + " y: " + t.get_yCoor());
                    //call the user and make the move here
                    _board.getCurrentPlayer().makeMove(t.get_xCoor(), t.get_yCoor());
                }
            });
            _boardGui.add(space, c);
            _buttons.add(space);


        }
        _window.add(_boardGui, BorderLayout.WEST);
    }

    public void generateInfoPanel() {
        _currentPlayer = new JLabel("Current Player: " + _board.getCurrentPlayerName());
        _currentRoll = new JLabel("Current Roll: " + _board.get_currentRoll());
        _infoPanel = new JPanel();
        _infoPanel.setLayout(new BoxLayout(_infoPanel, BoxLayout.Y_AXIS));
        _infoPanel.add(_currentPlayer);
        _infoPanel.add(_currentRoll);
        _window.add(_infoPanel, BorderLayout.EAST);

    }

    public void generateCardPanel() {
        _currentCards = new JPanel();
        _cardLabel = new JLabel();
        String cards = new String();
//        for(Card c: _board.getCurrentPlayer().get_userCards()){
//            cards += c.get_title().toLowerCase() + ", ";
//        }

        for(int i = 0; i < _board.getCurrentPlayer().get_userCards().size(); i++){
            if(i + 1 == _board.getCurrentPlayer().get_userCards().size()){
                cards += _board.getCurrentPlayer().get_userCards().get(i).get_title();
            }else{
                cards += _board.getCurrentPlayer().get_userCards().get(i).get_title() + ", ";
            }
        }

         _cardLabel.setText(cards);
        _currentCards.add(_cardLabel);
        _window.add(_currentCards, BorderLayout.SOUTH);
        _window.pack();

    }

    public void updateBoard() {

        for (int i = 0; i < _buttons.size(); i++) {
            Tile t = _board.get_tiles().get(i);
            if (t.is_isOccupied()) {

                BufferedImage img = new BufferedImage(25, 25, BufferedImage.TYPE_INT_RGB);
                Graphics2D g2d = img.createGraphics();
                g2d.setColor(_hallway);

                changeColor(g2d, t);

                g2d.fillRect(0, 0, 25, 25);
                g2d.dispose();

                _buttons.get(i).setIcon(new ImageIcon(img));

            } else {
                BufferedImage img = new BufferedImage(25, 25, BufferedImage.TYPE_INT_RGB);
                Graphics2D g2d = img.createGraphics();

                g2d = changeColor(g2d, t);
                g2d.fillRect(0, 0, 25, 25);
                g2d.dispose();


                _buttons.get(i).setIcon(new ImageIcon(img));
            }
        }

    }

    public Color changePlayerColor(Tile t, Graphics2D g2d){
            for(User player : _listOfPlayers){
                if(player.get_posX() == t.get_xCoor() && player.get_posY() == t.get_yCoor()){
                    Color c = selectPlayerColor(player.getCharacterName());
                    return c;
                }
            }
            //selectPlayerColor(_board.getCurrentPlayerName(), g2d);


        return Color.GRAY;
    }

    public Graphics2D changeColor(Graphics2D g2d, Tile t) {
        if (t.get_parentRoom() == -1) {
            g2d.setColor(_hallway);
        } else if (t.get_isDoor()) {
            g2d.setColor(_door);
        } else if (t.get_parentRoom() == 9) {
            g2d.setColor(Color.DARK_GRAY);
        } else if (_board.getRoomByID(t.get_parentRoom()).isRoomBorder(t) && !t.get_isDoor()) {
            g2d.setColor(_room);

        } else {
            g2d.setColor(_room);
        }

        if (t.is_isPassage()) {
            g2d.setColor(Color.ORANGE);

        }

        //System.out.println(_board.getCurrentPlayerName());

        if (t.is_isOccupied()) {
            g2d.setColor(changePlayerColor(t, g2d));
        }

        return g2d;

    }

    public void updateInfoPanel() {
        _currentPlayer.setText("Current Player: " + _board.getCurrentPlayerName());
        _currentRoll.setText("Current Roll: " + _board.get_currentRoll());
        _window.pack();
    }

    public void updateCardPanel(){
        String cards = new String();
//        for(Card c: _board.getCurrentPlayer().get_userCards()){
//            cards += c.get_title().toLowerCase() + ", ";
//        }

        for(int i = 0; i < _board.getCurrentPlayer().get_userCards().size(); i++){
            if(i + 1 == _board.getCurrentPlayer().get_userCards().size()){
                cards += _board.getCurrentPlayer().get_userCards().get(i).get_title();
            }else{
                cards += _board.getCurrentPlayer().get_userCards().get(i).get_title() + ", ";
            }
        }
        _cardLabel.setText(cards);

    }

    public Color selectPlayerColor(String name) {
        switch (name) {
            case "Miss Scarlett":
               return Color.red;
            //break;

            case "Colonel Mustard":
                return Color.yellow;

            //break;

            case "Mrs. Peacock":
                return Color.cyan;

            //break;

            case "Mrs. White":
                return Color.white;

            //break;

            case "Mr. Green":
                return Color.green;

            //break;

            case "Professor Plum":
                return Color.magenta;

            //break;

            default:
                return Color.black;

            //break;

        }

    }

    public ArrayList<User> get_listOfPlayers(){
        return _listOfPlayers;
    }


}
