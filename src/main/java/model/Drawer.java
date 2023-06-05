package model;

import java.util.ArrayList;

public class Drawer {
	private Hand hand = new Hand();
	public boolean bust = false;
	
	public Hand getHand() {
		return hand;
	}
	
	public Card drawCard(ArrayList<Card> deck) {
		Card card = deck.get(0);
		this.hand.getHand().add(deck.get(0));
		deck.remove(0);
		this.hand.calScore();
		return card;
	}	
	
	public String initialHand(ArrayList<Card> deck) {
		
		Card c1 = drawCard(deck);
		Card c2 = drawCard(deck);
		
		return "あなたが1枚目に引いたカードは"+ c1.getSuit() + "の" + c1.getRankString() +
				", 2枚目に引いたカードは" + c2.getSuit() + "の" + c2.getRankString() + "です";
	}
	
	public boolean judgeBust() {
		this.hand.calScore();
		if(hand.getScore() > 21) {
			this.bust = true;
		}
		return this.bust;
	}
}
