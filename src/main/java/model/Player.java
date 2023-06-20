package model;

public class Player extends Drawer {
	private String result = null;
	private boolean blackJack = false;
	
	public Player() {
		super();
	}
	
	
	public void setResult(String result) {
		this.result = result;
	}
	
	public String getResult() {
		return result;
	}
	
	
	public boolean isBlackJack(){
		return blackJack;
	}
	
	public void setBlackJack() {
		this.blackJack = true;
	}
	
	
	public String scoreMessage() {
		if(hand.getExistA() && hand.getMaxScore() < 21) {
			return hand.getMinScore()+"/"+hand.getMaxScore();
		}else{
			return String.valueOf(hand.getFinalScore());
		}
	}
	
	public boolean possibleSplit() {
		boolean possibleSplit = false;
		if(hand.getHand().get(0).getScore() == hand.getHand().get(1).getScore()) {
			possibleSplit = true;
		}
		return possibleSplit;
	}
	
}
