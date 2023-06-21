package dao;

import java.sql.SQLException;

import exception.DataBaseException;
import model.GameManagement.Result;

public class ResultRecordDao extends BaseDao {
	
	public ResultRecordDao() throws DataBaseException{
		super();
	}
	
	//勝利または敗北したときに試合数と勝利数(敗北数)をそれぞれ+1するメソッド
	public void recordResult(int userId, Result result) throws DataBaseException{		
		try {
			StringBuilder sb = new StringBuilder();
			switch(result) {
				case WIN:
					//breakしない
				case WIN_BJ:
					//breakしない
				case WIN_BUST:
					sb.append("update user set match_count = match_count + 1, ")
					.append("win_count = ").append("win_count + 1 where user_id = ?");
					break;
				case LOSE:
					//breakしない
				case LOSE_BJ:
					//breakしない
				case LOSE_BUST:
					sb.append("update user set match_count = match_count + 1, ")
					.append("lose_count = ").append("lose_count + 1 where user_id = ?");
					break;
				case DRAW:
					sb.append("update user set match_count = match_count + 1 where user_id = ?");
					break;
			}
			ps = con.prepareStatement(sb.toString());
			ps.setInt(1, userId);
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			throw new DataBaseException("試合結果の記録に失敗しました");
		}
		
	}
	
	//ユーザーIDとチップの数を引数とし、現在のチップ数を更新
		public int updateChip(int userId, int chip) throws DataBaseException{
						
			try {
				String sql = "update user set chip = chip + ? where user_id = ? ";
				ps = con.prepareStatement(sql);
				ps.setInt(1, chip);
				ps.setInt(2, userId);
				ps.executeUpdate();
						
			}catch(SQLException e) {
				e.printStackTrace();
				throw new DataBaseException("チップを記入できませんでした");
			}
			return chip;
		}	
}	
