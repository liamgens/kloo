package edu.buffalo.cse116.code;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by liamgens on 10/24/16.
 */
public class Gui {

    private JFrame _window;
    private JPanel _boardGui;
    private Board _board;
    private Color _hallway;


    public Gui(){
        _window = new JFrame();
        _window.setVisible(true);
        _window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _board = new Board();
        _hallway = new Color(255,255,218);
        _window.add(generateGameBoard());
        _window.pack();
    }

    public Board get_board() {
        return _board;
    }

    public JPanel generateGameBoard(){
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
            _boardGui.add(space,c);
        }
        return _boardGui;
    }

}
