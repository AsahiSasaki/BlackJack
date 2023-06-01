package dao;

import exception.DataBaseException;

public class Asa {

	public static void main(String[] args) throws DataBaseException{
		UserDao ud = new UserDao();
		String s = null;
		for(int i = 1; i < 7; i++) {
			int matchCount = ud.getMatchCount(i);
			int winCount = ud.getWinCount(i);
			int loseCount = ud.getLoseCount(i);
			int drawCount = ud.getDrawCount(i);
			double winRate = ud.getWinRate(i);
			s = String.format("対戦回数：%d 勝利数：%d 引き分け数：%d 負け数： %d 勝率： %.1f％",
					matchCount, winCount, drawCount, loseCount, winRate);
			System.out.println(ud.getNickname(i));
			System.out.println(s);
			
//			String sql = "select user_id from user where match_count != 0 order by win_count/match_count desc;";
//			System.out.println(sql);
		}
		
		BattleRecordDao brd = new BattleRecordDao();
		for(int i : brd.getTopFive()) {
			System.out.println(brd.getMessage(i+1, i));
		
		}
	}

}
