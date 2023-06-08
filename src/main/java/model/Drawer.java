package model;

import java.util.ArrayList;

public class Drawer {
	protected Hand hand = new Hand();
	public boolean bust = false;
	
	public Hand getHand() {
		return hand;
	}
	
	public Card drawCard(ArrayList<Card> deck) {
		Card card = deck.remove(0);
		this.hand.getHand().add(card);
		this.hand.calScore();
		return card;
	}	
	
	public boolean judgeBust() {
		this.hand.calScore();
		if(hand.getFinalScore() > 21) {
			this.bust = true;
		}
		return this.bust;
	}
}
