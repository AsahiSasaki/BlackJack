package model;

import java.util.ArrayList;

public class Player extends Drawer {
	private Hand splitHand = new Hand();
	
	public Player() {
		super();
	}
	
	public String scoreMessage() {
		if(hand.getExistA() && hand.getMaxScore() < 21) {
			return hand.getMinScore()+"/"+hand.getMaxScore();
		}else{
			return String.valueOf(hand.getFinalScore());
		}
	}
	
	public boolean possibleSplit() {
		boolean possibleSplit = false;
		if(hand.getHand().get(0).getScore() == hand.getHand().get(1).getScore()) {
			possibleSplit = true;
		}
		return possibleSplit;
	}
	
	public Card drawCard(ArrayList<Card> deck, Hand hand) {
		Card card = deck.remove(0);
		hand.getHand().add(card);
		hand.calScore();
		return card;
	}	
	
	public void split() {
		Card card = this.hand.getHand().remove(0);
		splitHand.getHand().add(card);
	}
	
	public Hand getSplitHand() {
		return this.splitHand;
	}
	
	public String splitScore() {
		if(splitHand.getExistA() && splitHand.getMaxScore() < 21) {
			return splitHand.getMinScore()+"/"+splitHand.getMaxScore();
		}else{
			return String.valueOf(splitHand.getFinalScore());
		}
	}
	
	public boolean judgeBust(Hand splitHand) {
		boolean splitbust = false;
		if(splitHand.getFinalScore() > 21) {
			splitbust = true;
		}
	return splitbust;
}

}
