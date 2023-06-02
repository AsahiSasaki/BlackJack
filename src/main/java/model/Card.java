package model;

public class Card {
	private int rank;
	private String suit;
	
	public Card(String suit, int rank) {
		this.rank = rank;
		this.suit = suit;
	}
	
	public int getRank() {
		return rank;
	}
	
	public String getSuit() {
		return suit;
	}

}
