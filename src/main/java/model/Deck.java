package model;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	private ArrayList<Card> deck;
	
	private enum Suit{
		クローバー,
		ダイヤ,
		ハート,
		スペード;	
	}
	
	public ArrayList<Card> resetDeck(){
		ArrayList<Card> deck = new ArrayList<>();
		for(Suit suit : Suit.values()) { 
			for(int i = 1; i < 14; i++) {
				Card card = new Card(suit.toString(), i);
				deck.add(card);
			}
		}
		this.deck = deck;
		return this.deck;
	}
	
	public ArrayList<Card> shuffleDeck(ArrayList<Card> deck){
		Collections.shuffle(deck);
		return deck;
		
	}
}
