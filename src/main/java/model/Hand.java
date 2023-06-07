package model;

import java.util.ArrayList;

public class Hand {
	private ArrayList<Card> hand = new ArrayList<>();
	private int finalScore;
	private int minScore;
	private int maxScore;
	private boolean existA;
	
	public ArrayList<Card> getHand(){
		return hand;
	}
	
	public void calScore(){
		existA = false;
		int min = 0;
		int max = 0;
		for(Card h:hand) {
			if(h.getRank() ==1) {
				min += 1;
				//Aが一枚目の時だけAを11としてカウント
				if(!existA) {
					max += 11;
					existA = true;
				}else {
					max += 1;
				}
			}
			if(1 < h.getRank() && h.getRank() < 11) {
				min += h.getRank();
				max += h.getRank();
			}
			if(h.getRank() > 10) {
				min += 10;
				max += 10;
			}
		}
		this.minScore = min;
		this.maxScore = max;
		int score = 0;
		if(maxScore < 22) {
			score = maxScore;
		}else {
			score = minScore;
		}
		this.finalScore = score;
	}
	
	public int getMinScore() {
		return this.minScore;
	}
	
	public int getMaxScore() {
		return this.maxScore;
	}
	
	public int getFinalScore() {
		return this.finalScore;
	}
	
	public boolean getExistA() {
		return existA;
	}
}
