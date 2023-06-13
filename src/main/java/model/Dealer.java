package model;

import java.util.ArrayList;

public class Dealer extends Drawer{
	private ArrayList<String> actionMessage = new ArrayList<>();
	
	public Dealer() {
		super();
	}
	
	public void  action(ArrayList<Card> deck) {
		actionMessage.add(scoreMessage());
		while(hand.getFinalScore() < 17) {
			Card card = drawCard(deck);
			actionMessage.add(cardMessage(card));
			if(!judgeBust()) {
				actionMessage.add(scoreMessage());
			}
		}
	}
	
	public ArrayList<String> getActionMessage(){
		return actionMessage;
	}
	
	public String initialHand(ArrayList<Card> deck) {
		
		Card c1 = drawCard(deck);
		Card c2 = drawCard(deck);
		
		actionMessage.add("２枚目に引いたカードは"+ c2.getDisplayName()+ "でした");
		
		return "ディーラーが1枚目に引いたカードは"+ c1.getDisplayName() +
				", 2枚目に引いたカードは分かりません";
	}
	
	private String cardMessage(Card card) {
		return card.getDisplayName()+ "を引きました";
	}
	
	public String scoreMessage() {
		if(hand.getExistA() && hand.getMaxScore() < 17) {
			return hand.getMinScore()+"/"+hand.getMaxScore();
		}else {
			return String.valueOf(hand.getFinalScore());
			
		}
	}
	
	public String compareScore(Player player) {
		int playerScore = player.getHand().getFinalScore();
		int dealerScore = this.getHand().getFinalScore();
		String resultMessage = null;
		if(player.judgeBust()) {
			resultMessage = "バーストしました。あなたの負けです。対戦ありがとうございました";
			player.setResult("lose");
		}
		if(judgeBust()) {
			resultMessage =  "ディーラーがバーストしました。あなたの勝ちです。";
			player.setResult("win");
		}
		if(!judgeBust() && !player.judgeBust()) {
			if(playerScore > dealerScore) {
				resultMessage =  playerScore + "対" + dealerScore + "であなたの勝ちです";
				player.setResult("win");
			}
			if(playerScore < dealerScore) {
				resultMessage =  playerScore + "対" + dealerScore + "であなたの負けです";
				player.setResult("lose");
			}
			if(playerScore == dealerScore) {
				resultMessage =  playerScore + "対" + dealerScore + "で引き分けです";
				player.setResult("draw");
			}
		}
		return resultMessage;

	}
	
	public String compareBJ(Player player) {
		int playerScore = player.getHand().getFinalScore();
		int dealerScore = this.getHand().getFinalScore();
		String resultMessage = null;
		if(playerScore > dealerScore) {
			resultMessage =  "ブラックジャック！！あなたの勝ちです";
			player.setResult("win");
		}
		if(playerScore < dealerScore) {
			resultMessage =  "ディーラーのブラックジャックあなたの負けです";
			player.setResult("lose");
		}
		if(playerScore == dealerScore) {
			resultMessage =  "引き分けです";
			player.setResult("draw");
		}
		return resultMessage;
	}

}
