package model;

import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		Deck deck = new Deck();
		ArrayList<Card> deckReset = deck.resetDeck();
		ArrayList<Card> deckShuffle = deck.shuffleDeck(deckReset);

		
		Player player = new Player();
		Dealer dealer = new Dealer();
		
		System.out.println(player.initialHand(deckShuffle));
		System.out.println(dealer.initialHand(deckShuffle));

		
		System.out.println("あなたのターンです");
		player.action(deckShuffle, player.getHand());

		if(player.judgeBust()) {
			System.out.println("バーストしました");
			System.out.println("あなたの負けです");
		}else {
			System.out.println("ディーラーのターンです");
			System.out.println(dealer.getHand().showCard(dealer.getHand().getHand().get(1)));	
			dealer.action(deckShuffle, dealer.getHand());
			if(dealer.judgeBust()) {
				System.out.println("ディーラーがバーストしました");
				System.out.println("あなたの勝ちです");
			}
		}
		
		if(!player.judgeBust() && !dealer.judgeBust()) {
			int pScore = player.getHand().getScore();
			int dScore = dealer.getHand().getScore();
			if(pScore < dScore) {
				System.out.println(pScore +"対" + dScore + "であなたの負けです");
			}
			if(pScore > dScore) {
				System.out.println(pScore +"対" + dScore + "であなたの勝ちです");
			}
			if(pScore == dScore) {
				System.out.println(pScore +"対" + dScore + "引き分け");
			}
		}	
	}

}
