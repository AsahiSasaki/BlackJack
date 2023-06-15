package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exception.DataBaseException;

public abstract class BaseDao {
	
	protected Connection con = null;
	protected PreparedStatement ps = null;
	protected ResultSet rs = null;
	
	public BaseDao() throws DataBaseException{
		//子クラスがこのコンストラクタを呼ぶとデータベースに接続
		getConnection();
	}
	
	private void getConnection() throws DataBaseException{
		try{
			//JDBCドライバの登録
			if(con == null) {
		
				Class.forName("org.mariadb.jdbc.Driver");
			
			//データベースへの接続
				String url = "jdbc:mariadb://localhost/blackjack";
				String user = "root";
				String password = "sasaki0617";
			
				con = DriverManager.getConnection(url, user, password);
			}	
		}catch (ClassNotFoundException e){
			e.printStackTrace();
			throw new DataBaseException("JDBCドライバが見つかりません");
		}catch (SQLException e){
			e.printStackTrace();
			throw new DataBaseException("SQL実行中に例外が発生しました");
		}
	
	}
	
	//接続を切る
	public void close() throws SQLException{
		if (con != null) {
			con.close();
		}
		if (ps != null) {
			ps.close();
		}
		if (rs != null) {
			rs.close();
		}
	}
	
}