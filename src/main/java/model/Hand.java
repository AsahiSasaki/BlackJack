package model;

import java.util.ArrayList;

public class Hand {
	private ArrayList<Card> hand = new ArrayList<>();
	private int score ;
		
	
	public ArrayList<Card> getHand(){
		return hand;
	}
	
	public int calScore(){
		int num = 0;
		for(Card h:hand) {
			if(h.getRank() < 11) {
				num += h.getRank();
			}
			if(h.getRank() > 10) {
				num += 10;
			}
		}
		this.score = num;
		return this.score;
	}
	
	
	public int getScore() {
		return this.score;
	}
	
	public String showCard(Card card) {
		String s ="ディーラーが2枚目に引いたカードは"
			+ card.getSuit() + "の" + card.getRankString()+"です";
		return s;
	}
}
