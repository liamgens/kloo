package edu.buffalo.cse116.code;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
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


    /**
     * Takes in the number of players and creates a game board GUI
     * @param numberOfPlayers
     */
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

    /**
     * Generates the game board with all of the required tiles
     */
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
                    //call the user and make the move here
                    _board.getCurrentPlayer().makeMove(t.get_xCoor(), t.get_yCoor());
                }
            });
            _boardGui.add(space, c);
            _buttons.add(space);


        }
        _window.add(_boardGui, BorderLayout.WEST);
    }

    /**
     * Generates the information panel with the current player, roll and a button to make an accusation
     */
    public void generateInfoPanel() {
        JButton accusationButton = new JButton("Accusation");
        accusationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AccusationPopUp accusationPopUp = new AccusationPopUp(_board);
                updateBoard();
                updateInfoPanel();
            }
        });

        _currentPlayer = new JLabel("Current Player: " + _board.getCurrentPlayerName());
        _currentRoll = new JLabel("Current Roll: " + _board.get_currentRoll());
        _infoPanel = new JPanel();
        _infoPanel.setLayout(new BoxLayout(_infoPanel, BoxLayout.Y_AXIS));
        _infoPanel.add(_currentPlayer);
        _infoPanel.add(_currentRoll);
        _infoPanel.add(accusationButton);
        _window.add(_infoPanel, BorderLayout.EAST);

    }

    /**
     * Generates the initial panel with the current player's cards
     */
    public void generateCardPanel() {
        _currentCards = new JPanel();
        _cardLabel = new JLabel();
        String cards = new String();
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


    /**
     * Returns the correct color for each player if they are on the tile
     * @param t tile to be checked
     * @return
     */
    public Color changePlayerColor(Tile t){
            for(User player : _listOfPlayers){
                if(player.get_posX() == t.get_xCoor() && player.get_posY() == t.get_yCoor()){
                    Color c = selectPlayerColor(player.getCharacterName());
                    return c;
                }
            }

        return Color.GRAY;
    }

    /**
     * Changes the color of the image that is placed on each JButton, according to it's location status
     * @param g2d graphics image
     * @param t tile that determines the color of the image
     * @return image returned with correct color
     */
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

        if (t.is_isOccupied()) {
            g2d.setColor(changePlayerColor(t));
        }

        return g2d;

    }

    /**
     * Repaints the tiles on the board to have the correct colors
     */
    public void updateBoard() {
        for (int i = 0; i < _buttons.size(); i++) {
            Tile t = _board.get_tiles().get(i);
            if (t.is_isOccupied()) {
                BufferedImage img = new BufferedImage(25, 25, BufferedImage.TYPE_INT_RGB);
                Graphics2D g2d = img.createGraphics();

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

    /**
     * Updates the info panel information with the correct player and roll
     */
    public void updateInfoPanel() {
        _currentPlayer.setText("Current Player: " + _board.getCurrentPlayerName());
        _currentRoll.setText("Current Roll: " + _board.get_currentRoll());
        _window.pack();
    }

    /**
     * Updates the card panel to have the current players cards
     */
    public void updateCardPanel(){
        String cards = new String();
        for(int i = 0; i < _board.getCurrentPlayer().get_userCards().size(); i++){
            if(i + 1 == _board.getCurrentPlayer().get_userCards().size()){
                cards += _board.getCurrentPlayer().get_userCards().get(i).get_title();
            }else{
                cards += _board.getCurrentPlayer().get_userCards().get(i).get_title() + ", ";
            }
        }
        _cardLabel.setText(cards);

    }

    /**
     * Switch that returns the correct color based on the player name that is passed in
     * @param name
     * @return
     */
    public Color selectPlayerColor(String name) {
        switch (name) {
            case "Miss Scarlett":
               return Color.red;

            case "Colonel Mustard":
                return Color.yellow;

            case "Mrs. Peacock":
                return Color.cyan;

            case "Mrs. White":
                return Color.white;

            case "Mr. Green":
                return Color.green;

            case "Professor Plum":
                return Color.magenta;

            default:
                return Color.black;

        }



    }


    //Accessor and Mutator Methods
    public Board get_board() {
        return _board;
    }

    public ArrayList<User> get_listOfPlayers(){
        return _listOfPlayers;
    }


}
