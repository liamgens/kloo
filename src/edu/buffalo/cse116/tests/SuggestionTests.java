package edu.buffalo.cse116.tests;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

import edu.buffalo.cse116.code.*;

/**
 * All 8 suggestion tests that were requested are shown here. Private instances of Board and Deck are used to start the game.
 * Users are created and will be assigned a "hand" value to keep track of the cards that they currently possess.
 * They will use that hand to test the suggestions that are requested according to different values based on the users suggestion.
 * The envelope is required to separate cards from the dealing deck but they will not be used for testing.
 * @author dromsoft
 * ALL TESTS ARE PREDETERMINED
 */

public class SuggestionTests {
	
	//TODO Write test that provides why suggesstion is false
	@Test
	/**
	 * As a player, I want to know:
	 * which player and the specific card they have that proves my suggestion is false
	 * (why it was wrong).
	 */
	public void provenFalseTest(){
		
	}
	
	
	
	/** Suggestion would be answered by the next player because they have the Player card; */
	@Test
	public void SuggestionTest01() {
		Board b = new Board(6); // Board
		Deck d = new Deck(); // 17 Cards
		
		ArrayList<Card> ec = d.get_envelopeCards(); // winning cards
		ArrayList<Card> deal = d.get_deck(); // creates Array List of deck to deal
		
		// Users
		User u1 = new User(b, 0); 
		User u2 = new User(b, 1); 
		User u3 = new User(b, 2); 
		User u4 = new User(b, 3); 
		User u5 = new User(b, 4); 
		User u6 = new User(b, 5); 
		
		ArrayList<Card> hand1 = new ArrayList<Card>();   // users hand
		ArrayList<Card> hand2 = new ArrayList<Card>();   // users hand
		ArrayList<Card> hand3 = new ArrayList<Card>();   // users hand
		ArrayList<Card> hand4 = new ArrayList<Card>();   // users hand
		ArrayList<Card> hand5 = new ArrayList<Card>();   // users hand
		ArrayList<Card> hand6 = new ArrayList<Card>();   // users hand
		
		// Deal all RANDOM cards
		hand1.add(d.dealFirstCard(deal)); // WHITE        
		hand2.add(d.dealFirstCard(deal)); // GREEN        
		hand3.add(d.dealFirstCard(deal)); // BLUE         
		hand4.add(d.dealFirstCard(deal)); // PURPLE       
		hand5.add(d.dealFirstCard(deal)); // YELLOW       
		hand6.add(d.dealFirstCard(deal)); // CANDLESTICK  
		hand1.add(d.dealFirstCard(deal)); // LEAD PIPE         
		hand2.add(d.dealFirstCard(deal)); // ROPE          
		hand3.add(d.dealFirstCard(deal)); // REVOLVER      
		hand4.add(d.dealFirstCard(deal)); // KNIFE         
		hand5.add(d.dealFirstCard(deal)); // BALLROOM      
		hand6.add(d.dealFirstCard(deal)); // CONSERVATORY  
		hand1.add(d.dealFirstCard(deal)); // DINING ROOM     
		hand2.add(d.dealFirstCard(deal)); // LOUNGE        
		hand3.add(d.dealFirstCard(deal)); // HALL          
		hand4.add(d.dealFirstCard(deal)); // STUDY         
		hand5.add(d.dealFirstCard(deal)); // LIBRARY       
		hand6.add(d.dealFirstCard(deal)); // BILLARD ROOM  
		                                   
		// Set Users hand
		u1.set_userCards(hand1); // WHITE, LEAD PIPE, DINING ROOM
		u2.set_userCards(hand2); // GREEN, ROPE, LOUNGE
		u3.set_userCards(hand3); // BLUE, REVOLVER, HALL
		u4.set_userCards(hand4); // PURPLE, KNIFE, STUDY
		u5.set_userCards(hand5); // YELLOW, BALLROOM, LIBRARY
		u6.set_userCards(hand6); // CANDLESTICK, CONSERVATORY, BILLARD ROOM
		
		/**
		 * With User1's current turn, it is checking the hand of User2 to see if any of the parameters match the card in User2's hand. 
		 * Method checks if user2's hand contains anyone of the following; returns false if otherwise.
		 * USERS HAND PREDETERMINED FOR TESTING PURPOSES
		 * 
		 * USER1 SUGGESTIONS: "GREEN", WRENCH, KITCHEN
		 * USER2 HAND: "GREEN", ROPE, LOUNGE
		 * 
		 * USER3 HAND: BLUE, REVOLVER, HALL
		 * USER4 HAND: PURPLE, KNIFE, STUDY                   
		 * USER5 HAND: YELLOW, BALLROOM, LIBRARY              
		 * USER6 HAND: CANDLESTICK, CONSERVATORY, BILLARD ROOM
		 * 
		 * SUGGESTION TARGET: GREEN (PLAYER) 
		 */
		
		/** Make call for user2 hand to check next players hand for suggested player card */
		assertTrue(u1.makeSuggestion(hand2, "GREEN", "WRENCH", "KITCHEN"));
		assertFalse(u1.makeSuggestion(hand3, "GREEN", "WRENCH", "KITCHEN"));
		assertFalse(u1.makeSuggestion(hand4, "GREEN", "WRENCH", "KITCHEN"));
		assertFalse(u1.makeSuggestion(hand5, "GREEN", "WRENCH", "KITCHEN"));
		assertFalse(u1.makeSuggestion(hand6, "GREEN", "WRENCH", "KITCHEN"));
		}
	
	/** Suggestion would be answered by the next player because they have the Room card; */
	@Test
	public void SuggestionTest02() {
		Board b = new Board(6); // Board
		Deck d = new Deck(); // 17 Cards
		
		
		ArrayList<Card> ec = d.get_envelopeCards(); // winning cards
		
		ArrayList<Card> deal = d.get_deck(); // creates Array List of deck to deal
		
		User u1 = new User(b, 0);      // users
		User u2 = new User(b, 1);      // users
		User u3 = new User(b, 2);      // users
		User u4 = new User(b, 3);      // users
		User u5 = new User(b, 4);      // users
		User u6 = new User(b, 5);      // users
		
		ArrayList<Card> hand1 = new ArrayList<Card>();   // users hand
		ArrayList<Card> hand2 = new ArrayList<Card>();   // users hand
		ArrayList<Card> hand3 = new ArrayList<Card>();   // users hand
		ArrayList<Card> hand4 = new ArrayList<Card>();   // users hand
		ArrayList<Card> hand5 = new ArrayList<Card>();   // users hand
		ArrayList<Card> hand6 = new ArrayList<Card>();   // users hand
		
		// Deal all RANDOM cards
		
		/** For testing purposes cards are dealt one deck at a time 
		 * CARDS REMOVED: RED, WRENCH, KITCHEN 
		 */
		hand1.add(d.dealFirstCard(deal)); // WHITE        
		hand2.add(d.dealFirstCard(deal)); // GREEN        
		hand3.add(d.dealFirstCard(deal)); // BLUE         
		hand4.add(d.dealFirstCard(deal)); // PURPLE       
		hand5.add(d.dealFirstCard(deal)); // YELLOW       
		hand6.add(d.dealFirstCard(deal)); // CANDLESTICK  
		hand1.add(d.dealFirstCard(deal)); // LEAD PIPE         
		hand2.add(d.dealFirstCard(deal)); // ROPE          
		hand3.add(d.dealFirstCard(deal)); // REVOLVER      
		hand4.add(d.dealFirstCard(deal)); // KNIFE         
		hand5.add(d.dealFirstCard(deal)); // BALLROOM      
		hand6.add(d.dealFirstCard(deal)); // CONSERVATORY  
		hand1.add(d.dealFirstCard(deal)); // DINING ROOM     
		hand2.add(d.dealFirstCard(deal)); // LOUNGE        
		hand3.add(d.dealFirstCard(deal)); // HALL          
		hand4.add(d.dealFirstCard(deal)); // STUDY         
		hand5.add(d.dealFirstCard(deal)); // LIBRARY       
		hand6.add(d.dealFirstCard(deal)); // BILLARD ROOM  
		                                   
		// Set Users hand
		u1.set_userCards(hand1); // WHITE, LEAD PIPE, DINING ROOM
		u2.set_userCards(hand2); // GREEN, ROPE, LOUNGE
		u3.set_userCards(hand3); // BLUE, REVOLVER, HALL
		u4.set_userCards(hand4); // PURPLE, KNIFE, STUDY
		u5.set_userCards(hand5); // YELLOW, BALLROOM, LIBRARY
		u6.set_userCards(hand6); // CANDLESTICK, CONSERVATORY, BILLARD ROOM
		
		/**
		 * With User1's current turn, it is checking the hand of User2 to see if any of the parameters match the card in User2's hand. 
		 * Method checks if user2's hand contains anyone of the following; returns false if otherwise.
		 * USERS HAND PREDETERMINED FOR TESTING PURPOSES
		 * 
		 * USER1 SUGGESTIONS: RED, WRENCH, "LOUNGE"
		 * USER2 HAND: GREEN, ROPE, "LOUNGE"
		 * 
		 * USER3 HAND: BLUE, REVOLVER, HALL
		 * USER4 HAND: PURPLE, KNIFE, STUDY                   
		 * USER5 HAND: YELLOW, BALLROOM, LIBRARY              
		 * USER6 HAND: CANDLESTICK, CONSERVATORY, BILLARD ROOM
		 * 
		 * SUGGESTION TARGET: "LOUNGE" (ROOM) 
		 */
		
		/** Make call for user2 hand to check next players hand for suggested room card */
		assertTrue(u1.makeSuggestion(hand2, "RED", "WRENCH", "LOUNGE"));
		assertFalse(u1.makeSuggestion(hand3, "RED", "WRENCH", "LOUNGE"));
		assertFalse(u1.makeSuggestion(hand4, "RED", "WRENCH", "LOUNGE"));
		assertFalse(u1.makeSuggestion(hand5, "RED", "WRENCH", "LOUNGE"));
		assertFalse(u1.makeSuggestion(hand6, "RED", "WRENCH", "LOUNGE"));
		
	}
	
	/** Suggestion would be answered by the next player because they have the Weapon card; */
	@Test
	public void SuggestionTest03() {
		Board b = new Board(6); // Board
		Deck d = new Deck(); // 17 Cards
		
		
		ArrayList<Card> ec = d.get_envelopeCards(); // winning cards
		
		
		ArrayList<Card> deal = d.get_deck(); // creates Array List of deck to deal
		
		User u1 = new User(b, 0);      // users
		User u2 = new User(b, 1);      // users
		User u3 = new User(b, 2);      // users
		User u4 = new User(b, 3);      // users
		User u5 = new User(b, 4);      // users
		User u6 = new User(b, 5);      // users
		
		ArrayList<Card> hand1 = new ArrayList<Card>();   // users hand
		ArrayList<Card> hand2 = new ArrayList<Card>();   // users hand
		ArrayList<Card> hand3 = new ArrayList<Card>();   // users hand
		ArrayList<Card> hand4 = new ArrayList<Card>();   // users hand
		ArrayList<Card> hand5 = new ArrayList<Card>();   // users hand
		ArrayList<Card> hand6 = new ArrayList<Card>();   // users hand
		
		// Deal all RANDOM cards
		
		/** For testing purposes cards are dealt one deck at a time 
		 * CARDS REMOVED: RED, WRENCH, KITCHEN 
		 */
		hand1.add(d.dealFirstCard(deal)); // WHITE        
		hand2.add(d.dealFirstCard(deal)); // GREEN        
		hand3.add(d.dealFirstCard(deal)); // BLUE         
		hand4.add(d.dealFirstCard(deal)); // PURPLE       
		hand5.add(d.dealFirstCard(deal)); // YELLOW       
		hand6.add(d.dealFirstCard(deal)); // CANDLESTICK  
		hand1.add(d.dealFirstCard(deal)); // LEAD PIPE         
		hand2.add(d.dealFirstCard(deal)); // ROPE          
		hand3.add(d.dealFirstCard(deal)); // REVOLVER      
		hand4.add(d.dealFirstCard(deal)); // KNIFE         
		hand5.add(d.dealFirstCard(deal)); // BALLROOM      
		hand6.add(d.dealFirstCard(deal)); // CONSERVATORY  
		hand1.add(d.dealFirstCard(deal)); // DINING ROOM     
		hand2.add(d.dealFirstCard(deal)); // LOUNGE        
		hand3.add(d.dealFirstCard(deal)); // HALL          
		hand4.add(d.dealFirstCard(deal)); // STUDY         
		hand5.add(d.dealFirstCard(deal)); // LIBRARY       
		hand6.add(d.dealFirstCard(deal)); // BILLARD ROOM  
		                                   
		// Set Users hand
		u1.set_userCards(hand1); // WHITE, LEAD PIPE, DINING ROOM
		u2.set_userCards(hand2); // GREEN, ROPE, LOUNGE
		u3.set_userCards(hand3); // BLUE, REVOLVER, HALL
		u4.set_userCards(hand4); // PURPLE, KNIFE, STUDY
		u5.set_userCards(hand5); // YELLOW, BALLROOM, LIBRARY
		u6.set_userCards(hand6); // CANDLESTICK, CONSERVATORY, BILLARD ROOM
		
		/**
		 * With User1's current turn, it is checking the hand of User2 to see if any of the parameters match the card in User2's hand. 
		 * Method checks if user2's hand contains anyone of the following; returns false if otherwise.
		 * USERS HAND PREDETERMINED FOR TESTING PURPOSES
		 * 
		 * USER1 SUGGESTIONS: RED, "ROPE", KITCHEN
		 * USER2 HAND: GREEN, "ROPE", LOUNGE
		 * 
		 * USER3 HAND: BLUE, REVOLVER, HALL
		 * USER4 HAND: PURPLE, KNIFE, STUDY                   
		 * USER5 HAND: YELLOW, BALLROOM, LIBRARY              
		 * USER6 HAND: CANDLESTICK, CONSERVATORY, BILLARD ROOM
		 * 
		 * SUGGESTION TARGET: ROPE 
		 */
		
		/** Make call for user2 hand to check next players hand for suggested WEAPON card */
		assertTrue(u1.makeSuggestion(hand2, "RED", "ROPE", "KITCHEN"));  
		assertFalse(u1.makeSuggestion(hand3, "RED", "ROPE", "KITCHEN")); 
		assertFalse(u1.makeSuggestion(hand4, "RED", "ROPE", "KITCHEN")); 
		assertFalse(u1.makeSuggestion(hand5, "RED", "ROPE", "KITCHEN")); 
		assertFalse(u1.makeSuggestion(hand6, "RED", "ROPE", "KITCHEN"));
	}
	
	/** Suggestion would be answered by the next player because they have 2 matching cards; */
	@Test
	public void SuggestionTest04() {
		Board b = new Board(6); // Board
		Deck d = new Deck(); // 17 Cards
		
		
		ArrayList<Card> ec = d.get_envelopeCards(); // winning cards
		
		
		ArrayList<Card> deal = d.get_deck(); // creates Array List of deck to deal
		
		User u1 = new User(b, 0);      // users
		User u2 = new User(b, 1);      // users
		User u3 = new User(b, 2);      // users
		User u4 = new User(b, 3);      // users
		User u5 = new User(b, 4);      // users
		User u6 = new User(b, 5);      // users
		
		ArrayList<Card> hand1 = new ArrayList<Card>();   // users hand
		ArrayList<Card> hand2 = new ArrayList<Card>();   // users hand
		ArrayList<Card> hand3 = new ArrayList<Card>();   // users hand
		ArrayList<Card> hand4 = new ArrayList<Card>();   // users hand
		ArrayList<Card> hand5 = new ArrayList<Card>();   // users hand
		ArrayList<Card> hand6 = new ArrayList<Card>();   // users hand
		
		// Deal all RANDOM cards
		
		/** For testing purposes cards are dealt one deck at a time 
		 * CARDS REMOVED: RED, WRENCH, KITCHEN 
		 */
		hand1.add(d.dealFirstCard(deal)); // WHITE        
		hand2.add(d.dealFirstCard(deal)); // GREEN        
		hand3.add(d.dealFirstCard(deal)); // BLUE         
		hand4.add(d.dealFirstCard(deal)); // PURPLE       
		hand5.add(d.dealFirstCard(deal)); // YELLOW       
		hand6.add(d.dealFirstCard(deal)); // CANDLESTICK  
		hand1.add(d.dealFirstCard(deal)); // LEAD PIPE         
		hand2.add(d.dealFirstCard(deal)); // ROPE          
		hand3.add(d.dealFirstCard(deal)); // REVOLVER      
		hand4.add(d.dealFirstCard(deal)); // KNIFE         
		hand5.add(d.dealFirstCard(deal)); // BALLROOM      
		hand6.add(d.dealFirstCard(deal)); // CONSERVATORY  
		hand1.add(d.dealFirstCard(deal)); // DINING ROOM     
		hand2.add(d.dealFirstCard(deal)); // LOUNGE        
		hand3.add(d.dealFirstCard(deal)); // HALL          
		hand4.add(d.dealFirstCard(deal)); // STUDY         
		hand5.add(d.dealFirstCard(deal)); // LIBRARY       
		hand6.add(d.dealFirstCard(deal)); // BILLARD ROOM  
		                                   
		// Set Users hand
		u1.set_userCards(hand1); // WHITE, LEAD PIPE, DINING ROOM
		u2.set_userCards(hand2); // GREEN, ROPE, LOUNGE
		u3.set_userCards(hand3); // BLUE, REVOLVER, HALL
		u4.set_userCards(hand4); // PURPLE, KNIFE, STUDY
		u5.set_userCards(hand5); // YELLOW, BALLROOM, LIBRARY
		u6.set_userCards(hand6); // CANDLESTICK, CONSERVATORY, BILLARD ROOM
		
		/**
		 * With User1's current turn, it is checking the hand of next use to see if any of the parameters match the card in users hand. 
		 * Method checks if users hand contains anyone of the following; returns false if otherwise.
		 * USERS HAND PREDETERMINED FOR TESTING PURPOSES
		 * 
		 * USER1 SUGGESTION: "GREEN", "ROPE", KITCHEN
		 * 
		 * USER2 HAND: "GREEN", "ROPE", LOUNGE
		 * USER3 HAND: BLUE, REVOLVER, HALL
		 * USER4 HAND: PURPLE, KNIFE, STUDY                   
		 * USER5 HAND: YELLOW, BALLROOM, LIBRARY              
		 * USER6 HAND: CANDLESTICK, CONSERVATORY, BILLARD ROOM
		 * 
		 * SUGGESTION TARGET: "GREEN", "ROPE"
		 */
		
		/** Make call to check players if there is  */
		assertTrue(u1.makeSuggestion(hand2, "GREEN", "ROPE", "KITCHEN"));
		assertFalse(u1.makeSuggestion(hand3, "GREEN", "ROPE", "KITCHEN"));
		assertFalse(u1.makeSuggestion(hand4, "GREEN", "ROPE", "KITCHEN"));
		assertFalse(u1.makeSuggestion(hand5, "GREEN", "ROPE", "KITCHEN"));
		assertFalse(u1.makeSuggestion(hand6, "GREEN", "ROPE", "KITCHEN"));
		
	}
	
	/** Suggestion would be answered by the player after the next player because they have 1 or more matching cards; */
	@Test
	public void SuggestionTest05() {
		Board b = new Board(6); // Board
		Deck d = new Deck(); // 17 Cards
		
		
		ArrayList<Card> ec = d.get_envelopeCards(); // winning cards
		
		
		ArrayList<Card> deal = d.get_deck(); // creates Array List of deck to deal
		
		User u1 = new User(b, 0);      // users
		User u2 = new User(b, 1);      // users
		User u3 = new User(b, 2);      // users
		User u4 = new User(b, 3);      // users
		User u5 = new User(b, 4);      // users
		User u6 = new User(b, 5);      // users
		
		ArrayList<Card> hand1 = new ArrayList<Card>();   // users hand
		ArrayList<Card> hand2 = new ArrayList<Card>();   // users hand
		ArrayList<Card> hand3 = new ArrayList<Card>();   // users hand
		ArrayList<Card> hand4 = new ArrayList<Card>();   // users hand
		ArrayList<Card> hand5 = new ArrayList<Card>();   // users hand
		ArrayList<Card> hand6 = new ArrayList<Card>();   // users hand
		
		// Deal all RANDOM cards
		
		/** For testing purposes cards are dealt one deck at a time 
		 * CARDS REMOVED: RED, WRENCH, KITCHEN 
		 */
		hand1.add(d.dealFirstCard(deal)); // WHITE        
		hand2.add(d.dealFirstCard(deal)); // GREEN        
		hand3.add(d.dealFirstCard(deal)); // BLUE         
		hand4.add(d.dealFirstCard(deal)); // PURPLE       
		hand5.add(d.dealFirstCard(deal)); // YELLOW       
		hand6.add(d.dealFirstCard(deal)); // CANDLESTICK  
		hand1.add(d.dealFirstCard(deal)); // LEAD PIPE         
		hand2.add(d.dealFirstCard(deal)); // ROPE          
		hand3.add(d.dealFirstCard(deal)); // REVOLVER      
		hand4.add(d.dealFirstCard(deal)); // KNIFE         
		hand5.add(d.dealFirstCard(deal)); // BALLROOM      
		hand6.add(d.dealFirstCard(deal)); // CONSERVATORY  
		hand1.add(d.dealFirstCard(deal)); // DINING ROOM     
		hand2.add(d.dealFirstCard(deal)); // LOUNGE        
		hand3.add(d.dealFirstCard(deal)); // HALL          
		hand4.add(d.dealFirstCard(deal)); // STUDY         
		hand5.add(d.dealFirstCard(deal)); // LIBRARY       
		hand6.add(d.dealFirstCard(deal)); // BILLARD ROOM  
		                                   
		// Set Users hand
		u1.set_userCards(hand1); // WHITE, LEAD PIPE, DINING ROOM
		u2.set_userCards(hand2); // GREEN, ROPE, LOUNGE
		u3.set_userCards(hand3); // BLUE, REVOLVER, HALL
		u4.set_userCards(hand4); // PURPLE, KNIFE, STUDY
		u5.set_userCards(hand5); // YELLOW, BALLROOM, LIBRARY
		u6.set_userCards(hand6); // CANDLESTICK, CONSERVATORY, BILLARD ROOM

		/**
		 * With User1's current turn, it is checking the hand of User2 to see if any of the parameters match the card in User2's hand. 
		 * Method checks if user2's hand contains anyone of the following; returns false if otherwise.
		 * USERS HAND PREDETERMINED FOR TESTING PURPOSES
		 * 
		 * USER1 SUGGESTION: "BLUE", WRENCH KITCHEN
		 * 
		 * USER2 HAND: GREEN, ROPE, LOUNGE
		 * 
		 * USER3 HAND: "BLUE", REVOLVER, HALL
		 * 
		 * USER4 HAND: PURPLE, KNIFE, STUDY                   
		 * USER5 HAND: YELLOW, BALLROOM, LIBRARY              
		 * USER6 HAND: CANDLESTICK, CONSERVATORY, BILLARD ROOM
		 * 
		 * SUGGESTION TARGET: "BLUE"
		 */
		
		/** Make call to check players hand after user2 for one card */
		assertFalse(u1.makeSuggestion(hand2, "BLUE", "WRENCH", "KITCHEN"));
		assertTrue(u1.makeSuggestion(hand3, "BLUE", "WRENCH", "KITCHEN"));
		assertFalse(u1.makeSuggestion(hand4, "BLUE", "WRENCH", "KITCHEN"));
		assertFalse(u1.makeSuggestion(hand5, "BLUE", "WRENCH", "KITCHEN"));
		assertFalse(u1.makeSuggestion(hand6, "BLUE", "WRENCH", "KITCHEN"));
		
		/** Make call to check players hand after user2 for more than one card 
		 * SUGGESTION TARGET: "PURPLE", "KNIFE"
		 */
		assertFalse(u1.makeSuggestion(hand2, "PURPLE", "KNIFE", "KITCHEN"));
		assertFalse(u1.makeSuggestion(hand3, "PURPLE", "KNIFE", "KITCHEN"));
		assertTrue(u1.makeSuggestion(hand4, "PURPLE", "KNIFE", "KITCHEN"));
		assertFalse(u1.makeSuggestion(hand5, "PURPLE", "KNIFE", "KITCHEN"));
		assertFalse(u1.makeSuggestion(hand6, "PURPLE", "KNIFE", "KITCHEN"));
	}
	
	/** Suggestion would be answered by the player immediately before player making suggestion because they have 1 or more matching cards; 
	 * 
	 * I believe this is asking that this will check the the current player (user1) has the suggestion before making a suggestion.
	 * This is done by calling makeSuggestion to themselves 
	 */
	@Test
	public void SuggestionTest06() {
		Board b = new Board(6); // Board
		Deck d = new Deck(); // 17 Cards
		
		
		ArrayList<Card> ec = d.get_envelopeCards(); // winning cards
		
		
		ArrayList<Card> deal = d.get_deck(); // creates Array List of deck to deal
		
		User u1 = new User(b, 0);      // users
		User u2 = new User(b, 1);      // users
		User u3 = new User(b, 2);      // users
		User u4 = new User(b, 3);      // users
		User u5 = new User(b, 4);      // users
		User u6 = new User(b, 5);      // users
		
		ArrayList<Card> hand1 = new ArrayList<Card>();   // users hand
		ArrayList<Card> hand2 = new ArrayList<Card>();   // users hand
		ArrayList<Card> hand3 = new ArrayList<Card>();   // users hand
		ArrayList<Card> hand4 = new ArrayList<Card>();   // users hand
		ArrayList<Card> hand5 = new ArrayList<Card>();   // users hand
		ArrayList<Card> hand6 = new ArrayList<Card>();   // users hand
		
		// Deal all RANDOM cards
		
		/** For testing purposes cards are dealt one deck at a time 
		 * CARDS REMOVED: RED, WRENCH, KITCHEN 
		 */
		hand1.add(d.dealFirstCard(deal)); // WHITE        
		hand2.add(d.dealFirstCard(deal)); // GREEN        
		hand3.add(d.dealFirstCard(deal)); // BLUE         
		hand4.add(d.dealFirstCard(deal)); // PURPLE       
		hand5.add(d.dealFirstCard(deal)); // YELLOW       
		hand6.add(d.dealFirstCard(deal)); // CANDLESTICK  
		hand1.add(d.dealFirstCard(deal)); // LEAD PIPE         
		hand2.add(d.dealFirstCard(deal)); // ROPE          
		hand3.add(d.dealFirstCard(deal)); // REVOLVER      
		hand4.add(d.dealFirstCard(deal)); // KNIFE         
		hand5.add(d.dealFirstCard(deal)); // BALLROOM      
		hand6.add(d.dealFirstCard(deal)); // CONSERVATORY  
		hand1.add(d.dealFirstCard(deal)); // DINING ROOM     
		hand2.add(d.dealFirstCard(deal)); // LOUNGE        
		hand3.add(d.dealFirstCard(deal)); // HALL          
		hand4.add(d.dealFirstCard(deal)); // STUDY         
		hand5.add(d.dealFirstCard(deal)); // LIBRARY       
		hand6.add(d.dealFirstCard(deal)); // BILLARD ROOM  
		                                   
		// Set Users hand
		u1.set_userCards(hand1); // WHITE, LEAD PIPE, DINING ROOM
		u2.set_userCards(hand2); // GREEN, ROPE, LOUNGE
		u3.set_userCards(hand3); // BLUE, REVOLVER, HALL
		u4.set_userCards(hand4); // PURPLE, KNIFE, STUDY
		u5.set_userCards(hand5); // YELLOW, BALLROOM, LIBRARY
		u6.set_userCards(hand6); // CANDLESTICK, CONSERVATORY, BILLARD ROOM
		
		/**
		 * With User1's current turn, it is checking the hand of User2 to see if any of the parameters match the card in User2's hand. 
		 * Method checks if user2's hand contains anyone of the following; returns false if otherwise.
		 * USERS HAND PREDETERMINED FOR TESTING PURPOSES
		 * 
		 * USER1 HAND: GREEN, ROPE, LOUNGE
		 * 
		 * SUGGESTION CHECKS: if User1 has the card that it's suggesting... "WHITE", "LEAD PIPE", "DINING ROOM"
		 */
		
		/** Make call for user1 hand to check players */
		assertTrue(u1.makeSuggestion(hand1, "WHITE", "LEAD PIPE", "DINING ROOM"));
	}
	
	/** Suggestion cannot be answered by any player but the player making the suggestion has 1 or more matching cards; */
	@Test
	public void SuggestionTest07() {
		Board b = new Board(6); // Board
		Deck d = new Deck(); // 17 Cards
		
		ArrayList<Card> ec = d.get_envelopeCards(); // winning cards
		
		ArrayList<Card> deal = d.get_deck(); // creates Array List of deck to deal
		
		User u1 = new User(b, 0);      // users
		User u2 = new User(b, 1);      // users
		User u3 = new User(b, 2);      // users
		User u4 = new User(b, 3);      // users
		User u5 = new User(b, 4);      // users
		User u6 = new User(b, 5);      // users
		
		ArrayList<Card> hand1 = new ArrayList<Card>();   // users hand
		ArrayList<Card> hand2 = new ArrayList<Card>();   // users hand
		ArrayList<Card> hand3 = new ArrayList<Card>();   // users hand
		ArrayList<Card> hand4 = new ArrayList<Card>();   // users hand
		ArrayList<Card> hand5 = new ArrayList<Card>();   // users hand
		ArrayList<Card> hand6 = new ArrayList<Card>();   // users hand
		
		// Deal all RANDOM cards
		
		/** For testing purposes cards are dealt one deck at a time 
		 * CARDS REMOVED: RED, WRENCH, KITCHEN 
		 */
		hand1.add(d.dealFirstCard(deal)); // WHITE        
		hand2.add(d.dealFirstCard(deal)); // GREEN        
		hand3.add(d.dealFirstCard(deal)); // BLUE         
		hand4.add(d.dealFirstCard(deal)); // PURPLE       
		hand5.add(d.dealFirstCard(deal)); // YELLOW       
		hand6.add(d.dealFirstCard(deal)); // CANDLESTICK  
		hand1.add(d.dealFirstCard(deal)); // LEAD PIPE         
		hand2.add(d.dealFirstCard(deal)); // ROPE          
		hand3.add(d.dealFirstCard(deal)); // REVOLVER      
		hand4.add(d.dealFirstCard(deal)); // KNIFE         
		hand5.add(d.dealFirstCard(deal)); // BALLROOM      
		hand6.add(d.dealFirstCard(deal)); // CONSERVATORY  
		hand1.add(d.dealFirstCard(deal)); // DINING ROOM     
		hand2.add(d.dealFirstCard(deal)); // LOUNGE        
		hand3.add(d.dealFirstCard(deal)); // HALL          
		hand4.add(d.dealFirstCard(deal)); // STUDY         
		hand5.add(d.dealFirstCard(deal)); // LIBRARY       
		hand6.add(d.dealFirstCard(deal)); // BILLARD ROOM  
		                                   
		// Set Users hand
		u1.set_userCards(hand1); // WHITE, LEAD PIPE, DINING ROOM
		u2.set_userCards(hand2); // GREEN, ROPE, LOUNGE
		u3.set_userCards(hand3); // BLUE, REVOLVER, HALL
		u4.set_userCards(hand4); // PURPLE, KNIFE, STUDY
		u5.set_userCards(hand5); // YELLOW, BALLROOM, LIBRARY
		u6.set_userCards(hand6); // CANDLESTICK, CONSERVATORY, BILLARD ROOM
				
		/**
		 * With User1's current turn, it is checking the hand of User2 to see if any of the parameters match the card in User2's hand. 
		 * Method checks if user2's hand contains anyone of the following; returns false if otherwise.
		 * USERS HAND PREDETERMINED FOR TESTING PURPOSES
		 * 
		 * SUGGESTION CHECKS if each player has one of these cards: "WHITE", "LEAD PIPE", "DINING ROOM"
		 * WHILE USER1 has one or more of the suggested cards
		 * 
		 * USER1 HAND: "WHITE", "LEAD PIPE", "DINING ROOM"
		 * USER2 HAND: GREEN, ROPE, LOUNGE
		 * USER3 HAND: BLUE, REVOLVER, HALL
		 * USER4 HAND: PURPLE, KNIFE, STUDY                   
		 * USER5 HAND: YELLOW, BALLROOM, LIBRARY              
		 * USER6 HAND: CANDLESTICK, CONSERVATORY, BILLARD ROOM
		 */
		
		/** Make call for user2 hand to check players */
		assertTrue(u1.makeSuggestion(hand1, "WHITE", "LEAD PIPE", "DINING ROOM"));
		assertFalse(u1.makeSuggestion(hand2, "WHITE", "LEAD PIPE", "DINING ROOM"));
		assertFalse(u1.makeSuggestion(hand3, "WHITE", "LEAD PIPE", "DINING ROOM"));
		assertFalse(u1.makeSuggestion(hand4, "WHITE", "LEAD PIPE", "DINING ROOM"));
		assertFalse(u1.makeSuggestion(hand5, "WHITE", "LEAD PIPE", "DINING ROOM"));
		assertFalse(u1.makeSuggestion(hand6, "WHITE", "LEAD PIPE", "DINING ROOM"));
	}
	
	/** Suggestion cannot be answered by any player and the player making the suggestion does not have any matching cards. */
	@Test
	public void SuggestionTest08() {
		Board b = new Board(6); // Board
		Deck d = new Deck(); // 17 Cards
		
		ArrayList<Card> ec = d.get_envelopeCards(); // winning cards: RED, WRENCH, KTICHEN
		
		// creates Array List of deck to deal
		ArrayList<Card> deal = d.get_deck();
		
		User u1 = new User(b, 0); // users
		User u2 = new User(b, 1); // users
		User u3 = new User(b, 2); // users
		User u4 = new User(b, 3); // users
		User u5 = new User(b, 4); // users
		User u6 = new User(b, 5); // users
		
		ArrayList<Card> hand1 = new ArrayList<Card>();   // users hand
		ArrayList<Card> hand2 = new ArrayList<Card>();   // users hand
		ArrayList<Card> hand3 = new ArrayList<Card>();   // users hand
		ArrayList<Card> hand4 = new ArrayList<Card>();   // users hand
		ArrayList<Card> hand5 = new ArrayList<Card>();   // users hand
		ArrayList<Card> hand6 = new ArrayList<Card>();   // users hand
		
		// Deal all RANDOM cards
		
		/** For testing purposes cards are dealt one deck at a time 
		 * CARDS REMOVED: RED, WRENCH, KITCHEN 
		 */
		hand1.add(d.dealFirstCard(deal)); // WHITE        
		hand2.add(d.dealFirstCard(deal)); // GREEN        
		hand3.add(d.dealFirstCard(deal)); // BLUE         
		hand4.add(d.dealFirstCard(deal)); // PURPLE       
		hand5.add(d.dealFirstCard(deal)); // YELLOW       
		hand6.add(d.dealFirstCard(deal)); // CANDLESTICK  
		hand1.add(d.dealFirstCard(deal)); // LEAD PIPE         
		hand2.add(d.dealFirstCard(deal)); // ROPE          
		hand3.add(d.dealFirstCard(deal)); // REVOLVER      
		hand4.add(d.dealFirstCard(deal)); // KNIFE         
		hand5.add(d.dealFirstCard(deal)); // BALLROOM      
		hand6.add(d.dealFirstCard(deal)); // CONSERVATORY  
		hand1.add(d.dealFirstCard(deal)); // DINING ROOM     
		hand2.add(d.dealFirstCard(deal)); // LOUNGE        
		hand3.add(d.dealFirstCard(deal)); // HALL          
		hand4.add(d.dealFirstCard(deal)); // STUDY         
		hand5.add(d.dealFirstCard(deal)); // LIBRARY       
		hand6.add(d.dealFirstCard(deal)); // BILLARD ROOM  
		                                   
		// Set Users hand
		u1.set_userCards(hand1); // WHITE, LEAD PIPE, DINING ROOM
		u2.set_userCards(hand2); // GREEN, ROPE, LOUNGE
		u3.set_userCards(hand3); // BLUE, REVOLVER, HALL
		u4.set_userCards(hand4); // PURPLE, KNIFE, STUDY
		u5.set_userCards(hand5); // YELLOW, BALLROOM, LIBRARY
		u6.set_userCards(hand6); // CANDLESTICK, CONSERVATORY, BILLARD ROOM
		
		/**
		 * With User1's current turn, it is checking the hand of User2 to see if any of the parameters match the card in User2's hand. 
		 * Method checks if user2's hand contains anyone of the following; returns false if otherwise.
		 * USERS HAND PREDETERMINED FOR TESTING PURPOSES
		 * 
		 * SUGGESTION CHECKS if each player has one of these cards: "RED", "WRENTCH", "KITCHEN"
		 * 
		 * USER1 HAND: WHITE, LEAD PIPE, DINING ROOM
		 * USER2 HAND: GREEN, ROPE, LOUNGE
		 * USER3 HAND: BLUE, REVOLVER, HALL
		 * USER4 HAND: PURPLE, KNIFE, STUDY                   
		 * USER5 HAND: YELLOW, BALLROOM, LIBRARY              
		 * USER6 HAND: CANDLESTICK, CONSERVATORY, BILLARD ROOM
		 * 
		 * SUGGESTION TARGET: "RED", "WRENTCH", "KITCHEN"
		 */
		
		/** Make calls to all users if they do not have these cards */
		assertFalse(u1.makeSuggestion(hand1, "RED", "WRENTCH", "KITCHEN"));
		assertFalse(u1.makeSuggestion(hand2, "RED", "WRENTCH", "KITCHEN"));
		assertFalse(u1.makeSuggestion(hand3, "RED", "WRENTCH", "KITCHEN"));
		assertFalse(u1.makeSuggestion(hand4, "RED", "WRENTCH", "KITCHEN"));
		assertFalse(u1.makeSuggestion(hand5, "RED", "WRENTCH", "KITCHEN"));
		assertFalse(u1.makeSuggestion(hand6, "RED", "WRENTCH", "KITCHEN"));
	}
}
