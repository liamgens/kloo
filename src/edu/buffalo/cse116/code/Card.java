package edu.buffalo.cse116.code;

/**
 * Created by liamgens on 10/20/16.
 */
public class Card {
    private int _cardType;
    private String[] weapons = {"Candlestick", "Dagger", "Lead Pipe", "Revolver", "Rope", "Wrench"};
    private String[] players = {"Mrs. White","Colonel Mustard","Mrs. Peacock","Miss Scarlett","Mr. Green","Professor Plum"};
    private String[] rooms = {"Kitchen", "Ballroom", "Conservatory", "Dining Room",
            "Lounge", "Hall", "Study", "Library", "Billard Room"};

    public Card (int cardType){
        _cardType = cardType;

    }
}
