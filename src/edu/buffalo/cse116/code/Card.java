package edu.buffalo.cse116.code;

//import java.util.ArrayList;

/**
 * Created by liamgens on 10/8/16.
 * Card Types = PLAYER, WEAPON, ROOM
 * Card Values = PURPLE, WRENCH, LOUNGE, etc... [depends on index]
 */
public class Card {

    private String _title; // Card Values
    private int _typeOfCard; // Card Types

    /** Card Types; they do not change */
    private static final int PLAYER = 0;
    private static final int WEAPON = 1;
    private static final int ROOM = 2;

    /** 2D Array of Card Values */
    private String[][] cardArray = {
            {"RED", "WHITE", "GREEN", "BLUE", "PURPLE", "YELLOW"}, // 6 PLAYERS CARDS
            {"WRENCH", "CANDLESTICK", "LEAD PIPE", "ROPE", "REVOLVER", "KNIFE"}, // 6 WEAPONS CARDS
            {"KITCHEN", "BALLROOM", "CONSERVATORY", "DINING ROOM", "LOUNGE", "HALL", "STUDY", "LIBRARY", "BILLARD ROOM"} // 9 ROOMS CARDS
    };

    /** Represent Card Types in String form */
    private String[] _cardTypes2String = {"Player", "Weapon", "Room"};

    public Card(int cardType, int cardVal){
        if (cardType != PLAYER && cardType != WEAPON && cardType != ROOM) {
            throw new IllegalArgumentException("Illegal playing card type");
        } else {
            _typeOfCard = cardType;
            _title = cardArray[_typeOfCard][cardVal];
        }
    }

    public String get_title() {
        return _title;
    }

    public int get_typeOfCard() {
        return _typeOfCard;
    }

    public String getCardType(){
        return _cardTypes2String[_typeOfCard];
    }

}
