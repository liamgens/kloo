package edu.buffalo.cse116.code;


import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by liamgens on 10/8/16.
 * 
 * This Deck class is using the Card class to create Cards and is giving those cards of a given type to a specific ArrayList
 * Example: _playerDeck will hold player cards
 * After the three ArrayList<Card> have been created (player, weapon, room) and *shuffled* (ALL shuffles removed for test purposes), one card from each deck will be assigned to the envelope ArrayList<Card>
 * These random cards will be removed from the deck, and will not be used until someone makes an accusation.
 * Once the envelope ArrayList<Card> is filled, the three decks will be combined into one deck, will be shuffled and will be distributed to all players starting from the top of the deck.
 * This way all random cards will be distributed to all players and each player will have an ArrayList<Card> to use for the game. 
 */
public class Deck{
	
		/** _mainDeck is used for dealing cards*/
        private ArrayList<Card> _mainDeck;
        
        /** These are the decks that will be used for the envelope selection */
        private ArrayList<Card> _playerDeck;
        private ArrayList<Card> _weaponDeck;
        private ArrayList<Card> _roomDeck;
        
        /** Stores 3 random cards from the deck */
        private ArrayList<Card> _envelopeCards;
        
        /**
         * CONSTRUCTOR
         * Creates a deck of cards.
         * This will be used for start of the game
         */
        public Deck() {
        	_playerDeck = newPlayerDeck();
        	_weaponDeck = newWeaponDeck();
        	_roomDeck = newRoomDeck();
        	
		/** For Testing purposes, don't shuffle */
//        	shuffle(_playerDeck);
//        	shuffle(_weaponDeck);
//        	shuffle(_roomDeck);
        	
        	_envelopeCards = chosenEnvelopeCards();
        	_mainDeck = createDealingDeck();
        	
        /** For Testing purposes, don't shuffle */
//        	shuffle(_mainDeck);
        }
        
        /**
         * Creates new ordered Player Deck of 6 Player Cards.
         * This is created separately for envelope selection purposes.
         * @return _playerDeck
         */
        public ArrayList<Card> newPlayerDeck() {
        	ArrayList<Card> playerDeck = new ArrayList<Card>();
        	
        	Card suspect1 = new Card(0, 0); // RED
        	Card suspect2 = new Card(0, 1); // WHITE
        	Card suspect3 = new Card(0, 2); // GREEN
        	Card suspect4 = new Card(0, 3); // BLUE
        	Card suspect5 = new Card(0, 4); // PURPLE
        	Card suspect6 = new Card(0, 5); // YELLOW
        	
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
        	Card murderWeapon1 = new Card(1, 0); // WRENCH
        	Card murderWeapon2 = new Card(1, 1); // CANDLESTICK
        	Card murderWeapon3 = new Card(1, 2); // LEAD PIPE
        	Card murderWeapon4 = new Card(1, 3); // ROPE
        	Card murderWeapon5 = new Card(1, 4); // REVOLVER
        	Card murderWeapon6 = new Card(1, 5); // KNIFE
        	
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
        	Card room1 = new Card(2, 0); // KITCHEN
        	Card room2 = new Card(2, 1); // BALLROOM
        	Card room3 = new Card(2, 2); // CONSERVATORY
        	Card room4 = new Card(2, 3); // DINING ROOM
        	Card room5 = new Card(2, 4); // LOUNGE
        	Card room6 = new Card(2, 5); // HALL
        	Card room7 = new Card(2, 6); // STUDY
        	Card room8 = new Card(2, 7); // LIBRARY
        	Card room9 = new Card(2, 8); // BILLARD ROOM
        	
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
        	
        	/** For Testing purposes, don't shuffle */
//        	shuffle(_playerDeck);
//        	shuffle(_weaponDeck);
//        	shuffle(_roomDeck);
        	
        	/** For Testing purposes, envelope predetermined */
        	Card c1 = dealFirstCard(_playerDeck); // RED
        	Card c2 = dealFirstCard(_weaponDeck); // WRENCH
        	Card c3 = dealFirstCard(_roomDeck);   // KITCHEN
        	
        	/** Adds cards to the envelope*/
        	envelopeCards.add(c1); // RED
        	envelopeCards.add(c2); // WRENCH
        	envelopeCards.add(c3); // KITCHEN
        	
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
        	
        	/** For Testing purposes, don't shuffle */
//        	shuffle(mainDeck);
        	
        	return mainDeck;
        }     

        // GETTERS

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
