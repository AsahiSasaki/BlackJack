package dao;

import java.sql.SQLException;

import exception.DataBaseException;

public class ResultRecordDao extends BaseDao {
	
	public ResultRecordDao() throws DataBaseException{
		super();
	}
	
	//勝利または敗北したときに試合数と勝利数(敗北数)をそれぞれ+1するメソッド
	public void recordResult(int userId, String result) throws DataBaseException{		
		try {
			StringBuilder sb = new StringBuilder();
			if(result.equals("draw")) {
				sb.append("update user set match_count = match_count + 1 where user_id = ?");
			}else {
				sb.append("update user set match_count = match_count + 1, ").append(result)
				.append("_count = ").append(result).append("_count + 1 where user_id = ?");
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
