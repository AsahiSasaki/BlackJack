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

import dao.ResultRecordDao;
import dao.UserDao;
import exception.DataBaseException;
import model.Card;
import model.Dealer;
import model.Deck;
import model.GameManagement;
import model.Player;


@WebServlet("/BlackJackServlet")
public class BlackJackServlet extends HttpServlet {
	
	//MenueページでPlayを選択、ブラックジャックページでnext gameを選択した
	//場合にdoGetでお互いに最初の２枚を引くところまで行う
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//ログインユーザーがいない場合プレイできない
		if(session.getAttribute("userId") == null) {
			request.setAttribute("message", "ログインしてください");
			RequestDispatcher rd = request.getRequestDispatcher("TopPage.jsp");
			rd.forward(request, response);
		}
		
		//最初だけgameの初期設定を行う。
		if(session.getAttribute("gameManagement") == null) {
			GameManagement gm = new GameManagement();
			session.setAttribute("gameManagement", gm);
			
			Player player = new Player();
			Dealer dealer = new Dealer();
			
			session.setAttribute("player", player);
			session.setAttribute("dealer", dealer);	
		}
		
		
		//BET額の変更を押されたときはsession betChipを破棄する
		if(request.getParameter("changeBet") != null) {
			session.removeAttribute("betChip");
		}
		
		//チップが設定されていない場合、なにもせずにそのまま画面に戻す
		if(request.getParameter("betChip")==null & session.getAttribute("betChip")==null) {
			
		}else {	
			int userId = Integer.parseInt((String)session.getAttribute("userId"));
			if(request.getParameter("betChip") != null) {
				int betChip = Integer.parseInt((String)request.getParameter("betChip"));
				session.setAttribute("betChip", betChip);
			}
			int betChip = (Integer)session.getAttribute("betChip");
			
			try{
				
				UserDao ud = new UserDao();
				ud.updateChip(userId, -betChip);
			}catch (DataBaseException e) {
				e.printStackTrace();
			}
			
			GameManagement gm = (GameManagement)session.getAttribute("gameManagement");
			Player player = (Player)session.getAttribute("player");
			Dealer dealer = (Dealer)session.getAttribute("dealer");
			Deck deck = new Deck();	
			session.setAttribute("deck", deck.getDeck());
			
			
			gm.setPhase("PLAYERTURN");

			player.initialHand(deck.getDeck());
			dealer.initialHand(deck.getDeck());
			
			//もしこの時点で21点だった場合はナチュラルブラックジャックでRESULTへ移動
			if(player.getHand().getFinalScore() == 21 || dealer.getHand().getFinalScore() == 21){
				String result = dealer.compareBJ(player);
				request.setAttribute("result", result);
				gm.setPhase("RESULT");
				//データベースへの書き込み
			
				try{
					ResultRecordDao rrd = new ResultRecordDao(); 
					rrd.recordResult(userId, player.getResult());
					UserDao ud = new UserDao();
					ud.updateChip(userId, dealer.calChip(betChip, player.getResult(), player.isBlackJack()));
					
				}catch (DataBaseException e) {
					e.printStackTrace();
				}
				
			}
		}
		
		
		RequestDispatcher rd = request.getRequestDispatcher("BlackJack.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();

		GameManagement gm = (GameManagement)session.getAttribute("gameManagement");
		Player player = (Player)session.getAttribute("player");
		Dealer dealer = (Dealer)session.getAttribute("dealer");
		ArrayList<Card> deck = (ArrayList<Card>)session.getAttribute("deck");

		
		//Playerが選択したactionによってスイッチ
		switch(request.getParameter("action")) {
			case "hit":
				player.drawCard(deck);
				
				//Playerがバーストしていた場合
				if(player.judgeBust()) {
					gm.setPhase("RESULT");
					gm.setClose();
				}
				break;
			case "stand":
				dealer.action(deck);
				request.setAttribute("dealerAction", dealer.getActionMessage());
				gm.setPhase("RESULT");
				gm.setClose();
				break;
		}
		
		//ゲームが終了した時
		if(gm.getClose()) {
			//ディーラーに勝敗判定をしてもらう
			request.setAttribute("result", dealer.compareScore(player));
			//結果をデータベースに書き込む
			try{
				int betChip=(Integer)session.getAttribute("betChip");
				int userId = Integer.parseInt((String)session.getAttribute("userId"));
				ResultRecordDao rrd = new ResultRecordDao(); 
				rrd.recordResult(userId, player.getResult());
				UserDao ud = new UserDao();
				ud.updateChip(userId, dealer.calChip(betChip, player.getResult(), player.isBlackJack()));
			}catch (DataBaseException e) {
				e.printStackTrace();
			}
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("BlackJack.jsp");
		rd.forward(request, response);
		
	}

}
