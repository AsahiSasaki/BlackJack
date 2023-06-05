package model;

import java.util.ArrayList;

public class Dealer extends Drawer{
	
	public Dealer() {
		super();
	}
	
	public void  action(ArrayList<Card> deck, Hand hand) {
		hand.calScore();
		System.out.println("ディーラーの現在の点数は"+hand.getScore()+"です");
		while(hand.getScore() < 17) {
			Card card = drawCard(deck);
			System.out.println(card.getSuit() + "の" + card.getRankString() + "を引きました");
			
			
		}
		
	}
	
	public String initialHand(ArrayList<Card> deck) {
		
		Card c1 = drawCard(deck);
		drawCard(deck);
		
		return "ディーラーが1枚目に引いたカードは"+ c1.getSuit() + "の" + c1.getRankString() +
				", 2枚目に引いたカードは分かりません";
	}
	

}
