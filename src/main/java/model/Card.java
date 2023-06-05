package model;

public class Card {
	private int rank;
	private String suit;
	private String rankString;
	
	public Card(String suit, int rank) {
		this.rank = rank;
		this.suit = suit;
		this.rankString = changePicture(rank);
	}
	
	public int getRank() {
		return rank;
	}
	
	public String getSuit() {
		return suit;
	}
	
	public String getRankString() {
		return rankString;
	}
	
	private String changePicture(int rank) {
		switch (rank) {
		case 1:
			return "A";
		case 11:
			return "J";
		case 12:
			return "Q";
		case 13:
			return "K";
		}
		return String.valueOf(rank);
	}

}
