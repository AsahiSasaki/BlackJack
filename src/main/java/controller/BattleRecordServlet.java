package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BattleRecordDao;
import dao.UserDao;
import exception.DataBaseException;



@WebServlet("/BattleRecordServlet")
public class BattleRecordServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		HttpSession session = request.getSession();
		int loginUserId = Integer.parseInt((String)session.getAttribute("userId"));
		String battleRecord = "対戦記録がありません";	
		try {
			UserDao ud = new UserDao();
			int matchCount = ud.getMatchCount(loginUserId);
			if(matchCount != 0) {
				int winCount = ud.getWinCount(loginUserId);
				int loseCount = ud.getLoseCount(loginUserId);
				int drawCount = ud.getDrawCount(loginUserId);
				double winRate = ud.getWinRate(loginUserId);
				battleRecord = String.format("対戦回数：%d 勝利数：%d 引き分け数：%d 敗北数： %d 勝率： %.1f％",
					matchCount, winCount, drawCount, loseCount, winRate);
			}
			
			BattleRecordDao brd = new BattleRecordDao();
			ArrayList<Integer> topFiveId = brd.getTopFive();
			session.setAttribute("battleRecord", battleRecord);
			session.setAttribute("topFiveId", topFiveId);
		
				
		}catch (DataBaseException e) {
			String message = e.getMessage();
			request.setAttribute("message", message);
			request.setAttribute("error", "true");
		}
			
			
		RequestDispatcher rd = request.getRequestDispatcher("BattleRecord.jsp");
		rd.forward(request, response);
		
		
	}

	
	

}
