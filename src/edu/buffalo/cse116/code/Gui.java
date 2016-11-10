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
    private Color _hallway;
    private JLabel _currentPlayer, _currentRoll;
    private JButton _roll, _suggestion, _accusation;
    private ArrayList<JButton> _buttons;

    public JFrame get_window() {
        return _window;
    }

    public JPanel get_boardGui() {
        return _boardGui;
    }

    public JPanel get_currentCards() {
        return _currentCards;
    }

    public JPanel get_infoPanel() {
        return _infoPanel;
    }

    public Gui(){
        _window = new JFrame();
        _window.setVisible(true);
        _window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _board = new Board();
        _hallway = new Color(255,255,218);
        _window.setLayout(new BorderLayout());
        _buttons = new ArrayList<JButton>();
        generateGameBoard();
        updateBoard();
        generateInfoPanel();
        _window.pack();
    }

    public Board get_board() {
        return _board;
    }

    public void generateGameBoard(){
        User p1 = new User(_board,0);
        User p2 = new User(_board,1);
        User p3 = new User(_board,2);
        User p4 = new User(_board,3);
        User p5 = new User(_board,4);
        User p6 = new User(_board,5);
        _boardGui = new JPanel();
        _boardGui.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        for(Tile t : _board.get_tiles()) {
            BufferedImage img = new BufferedImage(25, 25, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = img.createGraphics();
            g2d.setColor(_hallway);

            if (t.get_parentRoom() == - 1){
                g2d.setColor(_hallway);
            }else if(t.get_isDoor()){
                g2d.setColor(Color.GREEN);
            }else if(t.get_parentRoom() == 9) {
                g2d.setColor(Color.DARK_GRAY);
            }else if(_board.getRoomByID(t.get_parentRoom()).isRoomBorder(t) && !t.get_isDoor()){
                g2d.setColor(Color.RED);
            }
            if(t.is_isOccupied()){
                g2d.setColor(Color.CYAN);
            }
            g2d.fillRect(0, 0, 25, 25);
            g2d.dispose();

            JButton space = new JButton(new ImageIcon(img));
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
                public void actionPerformed(ActionEvent e){
                    System.out.println("x: " + t.get_xCoor() + " y: " + t.get_yCoor());
                    //call the user and make the move here
                    p1.makeMove(t.get_xCoor(), t.get_yCoor());
                    updateBoard();

                }
            });
            _boardGui.add(space,c);
            _buttons.add(space);


        }
        _window.add(_boardGui, BorderLayout.WEST);
    }

    public void generateInfoPanel(){
        _currentPlayer = new JLabel("Current Player: Mrs. White"/*_board.getCurrentPlayer()*/ );
        _currentRoll = new JLabel("Current Roll: " + _board.get_currentRoll());
        _infoPanel = new JPanel();
        _infoPanel.setLayout(new BoxLayout(_infoPanel, BoxLayout.Y_AXIS));
        _infoPanel.add(_currentPlayer);
        _infoPanel.add(_currentRoll);
        _window.add(_infoPanel, BorderLayout.EAST);

    }

    public void updateBoard(){
        for(int i = 0; i < _buttons.size(); i++){
            Tile t = _board.get_tiles().get(i);
            if(t.is_isOccupied()){
                _buttons.get(i).setText("HEY");
            }else{
                _buttons.get(i).setText("");
            }
        }

    }

}