package model;

import java.util.ArrayList;

public class Dealer extends Drawer{
	
	public Dealer() {
		super();
	}
	
	public void  action(ArrayList<Card> deck) {
		while(hand.getFinalScore() < 17) {
			drawCard(deck);
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
			player.setBlackJack();
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
	
	public int calChip(int betChip, String result, boolean isBlackJack) {
		int ans = 0;
		switch(result) {
			case "win":
				if(isBlackJack) {
					ans = (int)(betChip*2.5);
				}else {
					ans = betChip*2; 
				}
			case "lose":
				break;
			case "draw":
				ans = betChip;
		}
		return ans;
	}

}
