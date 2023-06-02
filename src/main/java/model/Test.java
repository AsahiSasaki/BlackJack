package model;

import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		Deck deck = new Deck();
		ArrayList<Card> deckReset = deck.resetDeck();
		
//		for(Card i: deckReset) {
//			System.out.println(i.getSuit() + "の" + i.getRank());
//		}
		
		ArrayList<Card> deckShuffle = deck.shuffleDeck(deckReset);
//		for(Card i: deckShuffle) {
//			System.out.println(i.getSuit() + "の" + i.getRank());
//		}
		
		
		Dealer dealer = new Dealer();
		System.out.println(dealer.initialHand(deckShuffle));
		dealer.action(deckShuffle, dealer.getHand());
		System.out.println("バースト判定：" + dealer.judgeBust());
		
	}

}
