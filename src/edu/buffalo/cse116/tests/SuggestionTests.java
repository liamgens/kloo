package edu.buffalo.cse116.tests;

import edu.buffalo.cse116.code.*;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

/**
 * Created by liamgens on 10/20/16.
 */
public class SuggestionTests {
    /** Suggestion would be answered by the next player because they have the Player card; */
    @Test
    public void SuggestionTest01() {
        Board b = new Board(); // Board
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
        hand1.add(d.dealFirstCard(deal));
        hand2.add(d.dealFirstCard(deal));
        hand3.add(d.dealFirstCard(deal));
        hand4.add(d.dealFirstCard(deal));
        hand5.add(d.dealFirstCard(deal));
        hand6.add(d.dealFirstCard(deal));
        hand1.add(d.dealFirstCard(deal));
        hand2.add(d.dealFirstCard(deal));
        hand3.add(d.dealFirstCard(deal));
        hand4.add(d.dealFirstCard(deal));
        hand5.add(d.dealFirstCard(deal));
        hand6.add(d.dealFirstCard(deal));
        hand1.add(d.dealFirstCard(deal));
        hand2.add(d.dealFirstCard(deal));
        hand3.add(d.dealFirstCard(deal));
        hand4.add(d.dealFirstCard(deal));
        hand5.add(d.dealFirstCard(deal));
        hand6.add(d.dealFirstCard(deal));

        // Set Users hand
        u1.set_userCards(hand1);
        u2.set_userCards(hand2);
        u3.set_userCards(hand3);
        u4.set_userCards(hand4);
        u5.set_userCards(hand5);
        u6.set_userCards(hand6);


//		for (Card c : hand2) {
//			System.out.println(c.get_title());
//		}


        //FIXME We can check for the three cards from the envelope first and then when we know what they are -- we test for them -- true and against them -- false

        /** Method checks if user2's hand contains anyone of the following; returns false if otherwise */
        assertTrue(u1.makeSuggestion(hand2, "WHITE", "WRENCH", "KITCHEN"));

    }

    /** Suggestion would be answered by the next player because they have the Room card; */
    @Test

    public void SuggestionTest02() {
        Board b = new Board(); // Board
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

        // Deal all cards
        hand1.add(d.dealFirstCard(deal));
        hand2.add(d.dealFirstCard(deal));
        hand3.add(d.dealFirstCard(deal));
        hand4.add(d.dealFirstCard(deal));
        hand5.add(d.dealFirstCard(deal));
        hand6.add(d.dealFirstCard(deal));
        hand1.add(d.dealFirstCard(deal));
        hand2.add(d.dealFirstCard(deal));
        hand3.add(d.dealFirstCard(deal));
        hand4.add(d.dealFirstCard(deal));
        hand5.add(d.dealFirstCard(deal));
        hand6.add(d.dealFirstCard(deal));
        hand1.add(d.dealFirstCard(deal));
        hand2.add(d.dealFirstCard(deal));
        hand3.add(d.dealFirstCard(deal));
        hand4.add(d.dealFirstCard(deal));
        hand5.add(d.dealFirstCard(deal));
        hand6.add(d.dealFirstCard(deal));

        // Set Users hand
        u1.set_userCards(hand1);
        u2.set_userCards(hand2);
        u3.set_userCards(hand3);
        u4.set_userCards(hand4);
        u5.set_userCards(hand5);
        u6.set_userCards(hand6);


//		for (Card c : hand2) {
//			System.out.println(c.get_title());
//		}

        assertTrue(u1.makeSuggestion(hand2, "WHITE", "WRENCH", "KITCHEN"));

    }

    /** Suggestion would be answered by the next player because they have the Weapon card; */
    @Test
    public void SuggestionTest03() {
        Board b = new Board(); // Board
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

        // Deal all cards
        hand1.add(d.dealFirstCard(deal));
        hand2.add(d.dealFirstCard(deal));
        hand3.add(d.dealFirstCard(deal));
        hand4.add(d.dealFirstCard(deal));
        hand5.add(d.dealFirstCard(deal));
        hand6.add(d.dealFirstCard(deal));
        hand1.add(d.dealFirstCard(deal));
        hand2.add(d.dealFirstCard(deal));
        hand3.add(d.dealFirstCard(deal));
        hand4.add(d.dealFirstCard(deal));
        hand5.add(d.dealFirstCard(deal));
        hand6.add(d.dealFirstCard(deal));
        hand1.add(d.dealFirstCard(deal));
        hand2.add(d.dealFirstCard(deal));
        hand3.add(d.dealFirstCard(deal));
        hand4.add(d.dealFirstCard(deal));
        hand5.add(d.dealFirstCard(deal));
        hand6.add(d.dealFirstCard(deal));

        // Set Users hand
        u1.set_userCards(hand1);
        u2.set_userCards(hand2);
        u3.set_userCards(hand3);
        u4.set_userCards(hand4);
        u5.set_userCards(hand5);
        u6.set_userCards(hand6);


        for (Card c : hand2) {
            System.out.println(c.get_title());
        }

        assertTrue(u1.makeSuggestion(hand2, "WHITE", "WRENCH", "KITCHEN"));
    }

    /** Suggestion would be answered by the next player because they have 2 matching cards; */
    @Test
    public void SuggestionTest04() {

    }

    /** Suggestion would be answered by the player after the next player because they have 1 or more matching cards; */
    @Test
    public void SuggestionTest05() {

    }

    /** Suggestion would be answered by the player immediately before player making suggestion because they have 1 or more matching cards; */
    @Test
    public void SuggestionTest06() {

    }

    /** Suggestion cannot be answered by any player but the player making the suggestion has 1 or more matching cards; */
    @Test
    public void SuggestionTest07() {

    }

    /** Suggestion cannot be answered by any player and the player making the suggestion does not have any matching cards. */
    @Test
    public void SuggestionTest08() {

    }

}
