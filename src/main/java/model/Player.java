package model;

import java.util.ArrayList;
import java.util.Scanner;

public class Player extends Drawer {
	private ArrayList<String> stringHand = new ArrayList<>();
	private boolean stand = false;
	private String result = null;
	
	public Player() {
		super();
	}
	
	public boolean getStand() {
		return stand;
	}
	
	public void setStand() {
		this.stand = true;
	}
	
	public void setResult(String result) {
		this.result = result;
	}
	
	public String getResult() {
		return result;
	}
	
	//Playerはスタンドするかバーストするまで引く
	public void action(ArrayList<Card> deck, Hand hand) {

		while(!stand && !judgeBust()) {
		
			if(hand.getExistA() && hand.getMaxScore() < 21) {
				System.out.println("現在の点数は"+hand.getMinScore()+"/"+hand.getMaxScore()+"です");
			}else {
				System.out.println("現在の点数は"+hand.getFinalScore()+"です");
			}
			
			selectAction();
			if(stand) {
				break;
			}
			Card c1 = drawCard(deck);
			System.out.println(c1.getDisplayName() + "を引きました");
		}
	}
	
	public String initialHand(ArrayList<Card> deck) {
		
		Card c1 = drawCard(deck);
		Card c2 = drawCard(deck);
		addStringHand(c1);
		addStringHand(c2);
			
		return "あなたが1枚目に引いたカードは"+ c1.getSuit() + "の" + c1.getRankString() +
				", 2枚目に引いたカードは" + c2.getSuit() + "の" + c2.getRankString() + "です";
	}
	
	public void addStringHand(Card card) {
		stringHand.add(card.getDisplayName());
	}
	
	public ArrayList<String> getStringHand(){
		return stringHand;
	}
	
	public String scoreMessage() {
		if(hand.getExistA() && hand.getMaxScore() < 21) {
			return "現在の点数は"+hand.getMinScore()+"/"+hand.getMaxScore()+"です";
		}else{
			return "現在の点数は"+hand.getFinalScore()+"です";
		}
	}
	
	public String drawMessage(Card card) {
		return card.getDisplayName()+"を引きました";
	}
	
	//スタンドかヒットか入力して決めてもらう
	public void selectAction() {
		Scanner scan = new Scanner(System.in);
		System.out.println("h or s ?");
		String action = scan.nextLine();
		if(action.equals("s")){
			this.stand = true;
		}else if(action.equals("h")) {
			//何もしない
		}else {
			System.out.println("入力が間違っています");
			selectAction();
		}
	}
}
