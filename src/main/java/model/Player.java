package model;

import java.util.ArrayList;
import java.util.Scanner;

public class Player extends Drawer {
	private boolean stand = false;
	
	public Player() {
		super();
	}
	
	public boolean getStand() {
		return stand;
	}
	
	public void setStand() {
		this.stand = true;
	}
	//Playerはスタンドするかバーストするまで引く
	public void action(ArrayList<Card> deck, Hand hand) {

		while(!stand && !judgeBust()) {
			hand.calScore();
			System.out.println("現在の点数は"+hand.getScore()+"です");
			selectAction();
			if(stand) {
				break;
			}
			Card c1 = drawCard(deck);
			System.out.println(c1.getSuit() + "の" + c1.getRankString()+"を引きました");
		}
	}
	
	//スタンドかヒットか入力して決めてもらう
	public void selectAction() {
		Scanner scan = new Scanner(System.in);
		System.out.println("h or s ?");
		String action = scan.nextLine();
		if(action.equals("s")){
			this.stand = true;
		}
	}
}
