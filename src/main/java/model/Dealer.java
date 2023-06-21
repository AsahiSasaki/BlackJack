package model;

import java.util.ArrayList;

import model.GameManagement.Result;

public class Dealer extends Drawer{
	
	public Dealer() {
		super();
	}
	
	public void  action(ArrayList<Card> deck) {
		while(hand.getFinalScore() < 17) {
			drawCard(deck);
		}
	}
	
	public void compareScore(GameManagement gm, Player player) {
		int playerScore = player.getHand().getFinalScore();
		int dealerScore = this.getHand().getFinalScore();
	
		if(player.judgeBust()) {
			gm.setResult("LOSE_BUST");
		}
		if(judgeBust() && !player.judgeBust()) {
			gm.setResult("WIN_BUST");
		}
		if(!judgeBust() && !player.judgeBust()) {
			if(playerScore > dealerScore) {
				gm.setResult("WIN");
			}
			if(playerScore < dealerScore) {
				gm.setResult("LOSE");
			}
			if(playerScore == dealerScore) {
				gm.setResult("DRAW");
			}
		}

	}
	
	public void compareBJ(GameManagement gm, Player player) {
		int playerScore = player.getHand().getFinalScore();
		int dealerScore = this.getHand().getFinalScore();
	
		if(playerScore > dealerScore) {
			gm.setResult("WIN_BJ");
		}
		if(playerScore < dealerScore) {
			gm.setResult("LOSE_BJ");
		}
		if(playerScore == dealerScore) {
			gm.setResult("DRAW");
		}
	}
	
	public int calChip(int betChip, Result result) {
		int ans = 0;
		switch(result) {
			case WIN:
				//breakしない
			case WIN_BUST:
				ans = betChip*2;
				break;
			case WIN_BJ:
				ans = (int)(betChip*2.5);
				break;
			case LOSE:
				//breakしない
			case LOSE_BUST:
				//breakしない
			case LOSE_BJ:
				break;
			case DRAW:
				ans = betChip;
		}
		return ans;
	}
	
	public void compareSplitScore(GameManagement gm, Player player) {
		int playerScore = player.getSplitHand().getFinalScore();
		int dealerScore = this.getHand().getFinalScore();
		
		if(player.judgeBust(player.getSplitHand())) {
			gm.setSplitResult("LOSE_BUST");
		}
		if(judgeBust() && !player.judgeBust(player.getSplitHand())) {
			gm.setSplitResult("WIN_BUST");
		}
		if(!judgeBust() && !player.judgeBust(player.getSplitHand())) {
			if(playerScore > dealerScore) {
				gm.setSplitResult("WIN");
			}
			if(playerScore < dealerScore) {
				gm.setSplitResult("LOSE");
			}
			if(playerScore == dealerScore) {
				gm.setSplitResult("DRAW");
			}
		}
	}
}
