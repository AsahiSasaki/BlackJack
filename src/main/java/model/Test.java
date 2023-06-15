package model;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.UserDao;
import exception.DataBaseException;

public class Test {

	public static void main(String[] args) throws DataBaseException, SQLException {
		UserDao ud = new UserDao();
		ud.close();
		Deck deck = new Deck();
		ArrayList<Card> initDeck = deck.getDeck();
		for(Card c : initDeck) {
			System.out.println(c.getDisplayName());
		}
		
		Player player = new Player();
		Dealer dealer = new Dealer();
		
		System.out.println(player.initialHand(initDeck));
		System.out.println(dealer.initialHand(initDeck));

		
		System.out.println("あなたのターンです");
		player.action(initDeck, player.getHand());

		if(player.judgeBust()) {
			System.out.println("バーストしました");
			System.out.println("あなたの負けです");
		}else {
			System.out.println("ディーラーのターンです");
			dealer.action(initDeck);
			for(String s:dealer.getActionMessage()) {
				System.out.println(s);
			}
			if(dealer.judgeBust()) {
				System.out.println("ディーラーがバーストしました");
				System.out.println("あなたの勝ちです");
			}
		}
		
		if(!player.judgeBust() && !dealer.judgeBust()) {
			int pScore = player.getHand().getFinalScore();
			int dScore = dealer.getHand().getFinalScore();
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
