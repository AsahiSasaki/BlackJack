package model;

public class GameManagement {
	private Phase phase;
	private Result result;
	private Result splitResult;
	private boolean close;
	
	public GameManagement() {
		setPhase("INIT");
	}
	
	public enum Phase{
//		BET額を決める段階をINIT
//		プレイヤーのターンをPLAYERTURN
//		ディーラーのターンと結果の判定を行う段階をRESULT
		INIT,
		PLAYERTURN,
		RESULT,
		
//		２枚の初期手札が一緒でスプリットの権利があるときをPOSSIBLESPLIT
//		SPLITしたときの最初の手札のターンをSPLIT_A
//		SPLITしたときの後の手札のターンをSPLIT_B
//		SPLITしたときの結果の表示をSPLIT_RESULT
		POSSIBLESPLIT,
		SPLIT_A,
		SPLIT_B,
		SPLIT_RESULT;
	}
	
	public enum Result{
		WIN,
		WIN_BJ,
		WIN_BUST,
		LOSE,
		LOSE_BJ,
		LOSE_BUST,
		DRAW;
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
	
	public void setResult(String string) {
		this.result = Result.valueOf(string);
	}
	
	public Result getResult() {
		return this.result;
	}
	
	public void setSplitResult(String string) {
		this.splitResult = Result.valueOf(string);
	}
	
	public Result getSplitResult() {
		return this.splitResult;
	}
	
	public String getResultMessage(Result result) {
		String resultMessage = null;
		switch(result) {
			case WIN:
				resultMessage = "YOU WIN!!";
				break;
			case WIN_BJ:
				resultMessage = "BLACKJACK!! YOU WIN!!";
				break;
			case WIN_BUST:
				resultMessage = "DEALER BUST!! YOU WIN!!";
				break;
			case LOSE:
				resultMessage = "YOU LOSE";
				break;
			case LOSE_BJ:
				resultMessage = "DEALER'S BLACKJACK!! YOU LOSE NICE GAME!!!!";
				break;
			case LOSE_BUST:
				resultMessage = "BUST!! YOU LOSE NICE GAME!!!!";
				break;
			case DRAW:
				resultMessage = "DRAW";
				break;
		}
		return resultMessage;
	}
}
