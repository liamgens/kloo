package edu.buffalo.cse116.code;

/**
 * Created by liamgens on 10/8/16.
 * Card Types = PLAYER, WEAPON, ROOM
 * Card Values = PURPLE, WRENCH, LOUNGE, etc... [depends on index]
 * 
 * Card Class will give each card a value based on the inputs in the 2D cardArray. There are three types of cards: PLAYER, WEAPON, ROOM.
 * Each card will later be assigned a value based on the card type. These cards will later be used to create the decks.
 * @author dromsoft
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
            {"Miss Scarlett", "Professor Plum", "Mr. Green", "Mrs. Peacock", "Mrs. White", "Colonel Mustard"}, // 6 PLAYERS CARDS
            {"Wrench", "Candlestick", "Lead Pipe", "Rope", "Revolver", "Knife"}, // 6 WEAPONS CARDS
            {"Kitchen", "Ballroom", "Conservatory", "Dining Room", "Lounge", "Hall", "Study", "Library", "Billard Room"} // 9 ROOMS CARDS
    };

    /** Represent Card Types in String form */
    private String[] _cardTypes2String = {"Player", "Weapon", "Room"};

    /**
     * CONSTRUCTOR
     * Uses cardArray to assign individual & valid values and types to one card.
     * @param cardType The type of the card (Player, Weapon, Location)
     * @param cardVal The value of the card.
     */
    public Card(int cardType, int cardVal){
        if (cardType != PLAYER && cardType != WEAPON && cardType != ROOM) {
            throw new IllegalArgumentException("Illegal playing card type");
        } else {
            _typeOfCard = cardType;
            _title = cardArray[_typeOfCard][cardVal];
        }
    }

    // GETTERS
    
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
