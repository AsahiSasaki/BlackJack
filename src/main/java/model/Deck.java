package model;

import java.util.ArrayList;
import java.util.Collections;


public class Deck {
	private ArrayList<Card> deck;
	
	public Deck() {
		resetDeck();
		shuffleDeck();
	}
	
	private enum Suit{
		クラブ,
		ダイヤ,
		ハート,
		スペード;	
	}
	
	private void resetDeck(){
		ArrayList<Card> deck = new ArrayList<>();
		for(Suit suit : Suit.values()) { 
			for(int i = 1; i < 14; i++) {
				Card card = new Card(suit.toString(), i);
				deck.add(card);
			}
		}
		this.deck = deck;
	}
	
	private void shuffleDeck(){
		Collections.shuffle(this.deck);	
	}
	
	public ArrayList<Card> getDeck(){
		return this.deck;
	}
}
