package model;

import java.util.ArrayList;

public class Dealer extends Drawer{
	private ArrayList<String> message = new ArrayList<>();
	
	public Dealer() {
		super();
	}
	
	public void  action(ArrayList<Card> deck, Hand hand) {
		message.add(scoreMessage(hand));
		while(hand.getScore() < 17) {
			Card card = drawCard(deck);
			message.add(cardMessage(card));
			message.add(scoreMessage(hand));
		}
	}
	
	public ArrayList<String> getMessage(){
		return message;
	}
	
	public String initialHand(ArrayList<Card> deck) {
		
		Card c1 = drawCard(deck);
		drawCard(deck);
		
		return "ディーラーが1枚目に引いたカードは"+ c1.getSuit() + "の" + c1.getRankString() +
				", 2枚目に引いたカードは分かりません";
	}
	
	public String cardMessage(Card card) {
		return card.getSuit() + "の" + card.getRankString() + "を引きました";
	}
	
	public String scoreMessage(Hand hand) {
		return "ディーラーの現在の点数は"+hand.getScore()+"です";
	}
	
	public String compareScore(Player player) {
		int playerScore = player.getHand().getScore();
		int dealerScore = this.getHand().getScore();
		String result = null;
		if(player.judgeBust()) {
			result = "バーストしました。あなたの負けです。対戦ありがとうございました";
			player.setResult("lose");
		}
		if(judgeBust()) {
			result =  "ディーラーがバーストしました。あなたの勝ちです。";
			player.setResult("win");
		}
		if(!judgeBust() && !player.judgeBust()) {
			if(playerScore > dealerScore) {
				result =  playerScore + "対" + dealerScore + "であなたの勝ちです";
				player.setResult("win");
			}
			if(playerScore < dealerScore) {
				result =  playerScore + "対" + dealerScore + "であなたの負けです";
				player.setResult("lose");
			}
			if(playerScore == dealerScore) {
				result =  playerScore + "対" + dealerScore + "で引き分けです";
				player.setResult("draw");
			}
		}
		return result;

	}
	

}
