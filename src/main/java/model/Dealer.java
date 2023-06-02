package model;

import java.util.ArrayList;

public class Dealer extends Drawer{
	
	public Dealer() {
		super();
	}
	
	public void  action(ArrayList<Card> deck, Hand hand) {
		hand.calScore();
		while(hand.getScore() < 17) {
			Card card = drawCard(deck);
			System.out.println(card.getSuit() + "の" + card.getRank() + "を引きました");
			hand.calScore();
			System.out.println(hand.getScore());
			
		}
		
	}
	

}
