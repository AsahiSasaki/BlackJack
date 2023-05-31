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
		User loginUser =null;
			
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
				loginUser = new User(PlayerId, userNickname, userPassword);
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
	public String findMemoId(int userId) throws DataBaseException{
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
	

}
