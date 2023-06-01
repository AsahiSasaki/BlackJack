package dao;

import java.sql.SQLException;

import exception.DataBaseException;
import model.User;

public class UserDao extends BaseDao {
	
	//コンストラクタ
	public UserDao() throws DataBaseException{
		super();
		}
		
	//ログインする
	public User doLogin(int userId, String loginPassword) throws DataBaseException {
			
		//初期状態としてloginUserはいないよってUser型
		User loginUser = null;
			
		try {
				
			//データベースとの照会
			String sql = "select * from user where user_id = ? and login_password = ? ";
			ps = con.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.setString(2, loginPassword);
			rs = ps.executeQuery();
			
			//一致するものがあったら情報をloginUserにセット
			while(rs.next()) {
				
				String PlayerId = rs.getString("user_id");
				String userNickname = rs.getString("user_nickname");
				String userPassword = rs.getString("login_password");
				loginUser = new User(PlayerId, userPassword, userNickname);
			}	
			//loginUserがnull(つまりデータベースに登録されてない人)だったらCampusExceptionを返す
			if(loginUser == null) {
				throw new DataBaseException("ログインできませんでした");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DataBaseException("SQL実行中に例外が発生しました");
			} 
			return loginUser;
		}
	
	//ユーザーを新規登録する際に、入力されたIDが既に使われていないか探す
	public String findUserId(int userId) throws DataBaseException{
		//メモIDの初期化
		String loginId = null;
		try{
			String sql = "select user_id from user where user_id = ? ";
			ps = con.prepareStatement(sql);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			while(rs.next()) {
				loginId = rs.getString("user_id");
			}
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DataBaseException("ユーザーIDの取得に失敗");
		}	
		return loginId;
	}
	
	//ユーザーを新規登録するメソッド
	public void createStudent(int userId, String loginPassword, String userNickname ) throws DataBaseException{
		
		try {
			
			//Userテーブルにユーザー情報を追加
			String sql = "insert into user(user_id, login_password, user_nickname) values(?, ?, ?)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.setString(2, loginPassword);
			ps.setString(3, userNickname);
			ps.executeUpdate();
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new DataBaseException("ユーザー登録に失敗しました");
		}
	}
	
	//ユーザーを削除するメソッド
	public void deleteUser(int userId) throws DataBaseException{
		
		try {
			
			//Userテーブルからユーザーを削除
			String sql = "delete from user where user_id = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new DataBaseException("ユーザー登録に失敗しました");
		}
	}
	
	//ユーザーの対戦回数を取得するメソッド
	public int getMatchCount(int userId) throws DataBaseException{
		int matchCount = 0;
		
		try {
			String sql = "select match_count from user where user_id = ? ";
			ps = con.prepareStatement(sql);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				matchCount = rs.getInt("match_count");
			}	
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new DataBaseException("対戦回数を取得できませんでした");
		}
		return matchCount;
	}
	
	//ユーザーの勝利回数を取得するメソッド
	public int getWinCount(int userId) throws DataBaseException{
		int winCount = 0;
			
		try {
			String sql = "select win_count from user where user_id = ? ";
			ps = con.prepareStatement(sql);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
				
			while(rs.next()) {
				winCount = rs.getInt("win_count");
			}	
				
		}catch(SQLException e) {
			e.printStackTrace();
			throw new DataBaseException("勝利数を取得できませんでした");
		}
		return winCount;
	}
		
	//ユーザーの敗北数を取得するメソッド
	public int getLoseCount(int userId) throws DataBaseException{
		int loseCount = 0;
					
		try {
			String sql = "select lose_count from user where user_id = ? ";
			ps = con.prepareStatement(sql);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				loseCount = rs.getInt("lose_count");
				}	
						
		}catch(SQLException e) {
			e.printStackTrace();
			throw new DataBaseException("敗北数を取得できませんでした");
		}
		return loseCount;
	}
	
	//ユーザーの引き分け数を取得するメソッド
	public int getDrawCount(int userId) throws DataBaseException{
		int drawCount = getMatchCount(userId) - getWinCount(userId) - getLoseCount(userId);
		return drawCount;
	}
		
	//ユーザーの勝率を取得するメソッド
	public double getWinRate(int userId) throws DataBaseException{
		double winRate = 100*(double)getWinCount(userId)/getMatchCount(userId);
		return winRate;
	}
	
	//ユーザーのニックネームを取得するメソッド
	public String getNickname(int userId) throws DataBaseException{
		String nickname = null;
				
		try {
			String sql = "select user_nickname from user where user_id = ? ";
			ps = con.prepareStatement(sql);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
					
			while(rs.next()) {
				nickname = rs.getString("user_nickname");
			}	
					
		}catch(SQLException e) {
			e.printStackTrace();
			throw new DataBaseException("ニックネームを取得できませんでした");
		}
		return nickname;
	}

}
