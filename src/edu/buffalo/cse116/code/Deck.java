package edu.buffalo.cse116.code;


import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by liamgens on 10/8/16.
 */
public class Deck{

    /** _mainDeck will either be used by the Users or will be used for dealing cards*/
    private ArrayList<Card> _mainDeck;

    /** These are the decks that will be used for the envelope selection */
    private ArrayList<Card> _playerDeck;
    private ArrayList<Card> _weaponDeck;
    private ArrayList<Card> _roomDeck;

    private ArrayList<Card> _envelopeCards;

    /**
     * Creates a deck of cards.
     * This will be used for start of the game
     */

    public Deck() {
        _playerDeck = newPlayerDeck();
        _weaponDeck = newWeaponDeck();
        _roomDeck = newRoomDeck();
        shuffle(_playerDeck);
        shuffle(_weaponDeck);
        shuffle(_roomDeck);
        _envelopeCards = chosenEnvelopeCards();
        _mainDeck = createDealingDeck();
        shuffle(_mainDeck);
    }


    /**
     * Creates new ordered Player Deck of 6 Player Cards.
     * This is created separately for envelope selection purposes.
     * @return _playerDeck
     */
    public ArrayList<Card> newPlayerDeck() {
        ArrayList<Card> playerDeck = new ArrayList<Card>();

        Card suspect1 = new Card(0, 0);
        Card suspect2 = new Card(0, 1);
        Card suspect3 = new Card(0, 2);
        Card suspect4 = new Card(0, 3);
        Card suspect5 = new Card(0, 4);
        Card suspect6 = new Card(0, 5);

        playerDeck.add(suspect1);
        playerDeck.add(suspect2);
        playerDeck.add(suspect3);
        playerDeck.add(suspect4);
        playerDeck.add(suspect5);
        playerDeck.add(suspect6);

        return playerDeck;
    }

    /**
     * Creates new ordered Weapon Deck of 6 Weapon Cards
     * This is created separately for envelope selection purposes.
     * @return _weaponDeck
     */
    public ArrayList<Card> newWeaponDeck() {
        ArrayList<Card> weaponDeck = new ArrayList<Card>();
        Card murderWeapon1 = new Card(1, 0);
        Card murderWeapon2 = new Card(1, 1);
        Card murderWeapon3 = new Card(1, 2);
        Card murderWeapon4 = new Card(1, 3);
        Card murderWeapon5 = new Card(1, 4);
        Card murderWeapon6 = new Card(1, 5);

        weaponDeck.add(murderWeapon1);
        weaponDeck.add(murderWeapon2);
        weaponDeck.add(murderWeapon3);
        weaponDeck.add(murderWeapon4);
        weaponDeck.add(murderWeapon5);
        weaponDeck.add(murderWeapon6);

        return weaponDeck;
    }

    /**
     * Creates new ordered Room Deck of 8 Room Cards
     * This is created separately for envelope selection purposes.
     * @return _roomDeck
     */
    public ArrayList<Card> newRoomDeck() {
        ArrayList<Card> roomDeck = new ArrayList<Card>();
        Card room1 = new Card(2, 0);
        Card room2 = new Card(2, 1);
        Card room3 = new Card(2, 2);
        Card room4 = new Card(2, 3);
        Card room5 = new Card(2, 4);
        Card room6 = new Card(2, 5);
        Card room7 = new Card(2, 6);
        Card room8 = new Card(2, 7);
        Card room9 = new Card(2, 8);

        roomDeck.add(room1);
        roomDeck.add(room2);
        roomDeck.add(room3);
        roomDeck.add(room4);
        roomDeck.add(room5);
        roomDeck.add(room6);
        roomDeck.add(room7);
        roomDeck.add(room8);
        roomDeck.add(room9);

        return roomDeck;
    }

    /** Shuffles one ArrayList of cards
     * @return */
    public static void shuffle(ArrayList<Card> deck){
        Collections.shuffle(deck);
    }

    /**
     * Removes the first card from the deck
     * @return the first card from the deck
     */
    public Card dealFirstCard(ArrayList<Card> deck){
        Card firstCard = deck.get(0);
        deck.remove(0);
        return firstCard;
    }

    /**
     * Separates one random card from each deck and returns selected cards.
     */
    public ArrayList<Card> chosenEnvelopeCards() {
        ArrayList<Card> envelopeCards = new ArrayList<Card>();

        shuffle(_playerDeck);
        shuffle(_weaponDeck);
        shuffle(_roomDeck);
        Card c1 = dealFirstCard(_playerDeck);
        Card c2 = dealFirstCard(_weaponDeck);
        Card c3 = dealFirstCard(_roomDeck);
        envelopeCards.add(c1);
        envelopeCards.add(c2);
        envelopeCards.add(c3);
        return envelopeCards;
    }

    /**
     * After the envelope has selected one random card from each category then it's time to combine the ArrayList's.
     * This combines all three ArrayLists into one Deck, the dealing deck.
     * The dealing deck will later be used to distribute the cards to the players.
     * At start of game, _mainDeck should be empty
     * @return mainDeck
     */
    public ArrayList<Card> createDealingDeck(){
        ArrayList<Card> mainDeck = new ArrayList<Card>();
        mainDeck.addAll(_playerDeck); // 5 Cards
        mainDeck.addAll(_weaponDeck); // 5 Cards
        mainDeck.addAll(_roomDeck); // 8 Cards
        shuffle(mainDeck);
        return mainDeck;
    }

    //GETTERS

    public ArrayList<Card> get_deck() {
        return _mainDeck;
    }

    public int size() {
        return _mainDeck.size();
    }

    public ArrayList<Card> get_envelopeCards() {
        return _envelopeCards;
    }
}
