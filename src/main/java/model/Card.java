package model;

public class Card {
	private int rank;
	private String suit;
	private String rankString;
	private String illustPath;
	
	
	public Card(String suit, int rank) {
		this.rank = rank;
		this.suit = suit;
		this.rankString = changePicture(rank);
		this.illustPath= changeIllustPath(rank, suit);
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
	
	public String getDisplayName() {
		return suit + "の" + rankString;
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
	
	private String changeIllustPath(int rank, String suit) {
		String s = null;
		switch (suit){
			case "スペード":
				s =  "card_spade_" + rank + ".png";
				break;
			case "ハート":
				s =  "card_heart_" + rank + ".png";
				break;
			case "ダイヤ":
				s =  "card_diamond_" + rank + ".png";
				break;
			case "クラブ":
				s = "card_club_" + rank + ".png";
				break;
		}
		return s;
		
	}
	
	public String getIllustPath() {
		return illustPath;
	}

}
