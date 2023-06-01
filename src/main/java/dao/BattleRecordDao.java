package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import exception.DataBaseException;

public class BattleRecordDao extends BaseDao{
	
	public BattleRecordDao() throws DataBaseException{
		super();
	}
	
	//勝率トップ５のユーザーを取得するメソッド
	public ArrayList<Integer> getTopFive() throws DataBaseException{
		ArrayList<Integer> topFiveId = new ArrayList<>();
		
		try {
			String sql = "select user_id from user where match_count != 0 order by win_count/match_count desc ";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				topFiveId.add(rs.getInt("user_id"));
				if(topFiveId.size() == 5) {
					break;
				}
			}	
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new DataBaseException("対戦回数を取得できませんでした");
		}
		return topFiveId;
	}
	
	//Idからニックネームと勝率を含むメッセージを返すメソッド
	public String getMessage(int rank, int userId) throws DataBaseException{
		StringBuilder sb = new StringBuilder();
		
		UserDao ud = new UserDao();
		sb.append(rank).append("位：").append(ud.getNickname(userId))
		.append(" 勝率：").append(ud.getWinRate(userId)).append("%");
		return sb.toString();
	}

}
