package model;

public class GameManagement {
	private Phase phase;
	private boolean close;
	
	public GameManagement() {
		setPhase("INIT");
	}
	
	public enum Phase{
//		デッキを新たに作り、最初の手札を2枚ずつ引く段階をINIT
//		プレイヤーのターンをPLAYERTURN
//		ディーラーのターンをDEALERTURN
//		プレイヤーがバースト、ディーラーがバースト、
//		もしくは両者の手札が確定した場合結果の判定を行う段階をRESULT
		INIT,
		PLAYERTURN,
		DEALERTURN,
		RESULT;
	}
	
	public void setPhase(String string) {
		this.phase = Phase.valueOf(string);
	}
	
	public Phase getPhase() {
		return this.phase;
	}
	
	public void setClose() {
		this.close = true;
	}
	
	public boolean getClose() {
		return close;
	}

}
