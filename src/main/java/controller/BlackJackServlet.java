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
		
		if(session.getAttribute("userId") == null) {
			request.setAttribute("message", "ログインしてください");
			RequestDispatcher rd = request.getRequestDispatcher("TopPage.jsp");
			rd.forward(request, response);
		}

		GameManagement gm = new GameManagement();
		
		Deck deck = new Deck();
			
		Player player = new Player();
		Dealer dealer = new Dealer();
		
		session.setAttribute("gameManagement", gm);
		session.setAttribute("deck", deck.getDeck());
		session.setAttribute("player", player);
		session.setAttribute("dealer", dealer);
		
		request.setAttribute("playerInit", player.initialHand(deck.getDeck()));
		request.setAttribute("dealerInit", dealer.initialHand(deck.getDeck()));
		
		
		RequestDispatcher rd = request.getRequestDispatcher("BlackJack.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean close = false;
		HttpSession session = request.getSession();
		GameManagement gm = (GameManagement)session.getAttribute("gameManagement");
		Player player = (Player)session.getAttribute("player");
		Dealer dealer = (Dealer)session.getAttribute("dealer");
		ArrayList<Card> deck = (ArrayList<Card>)session.getAttribute("deck");
		
		//Playerが選択したactionによってスイッチ
		switch(request.getParameter("action")) {
			case "hit":
				Card c1 = player.drawCard(deck);
				player.addStringHand(c1);
				request.setAttribute("drawMessage", c1.getDisplayName()+"を引きました");
				
				//Playerがバーストしていた場合
				if(player.judgeBust()) {
					gm.setPhase("PHASE3");
					close = true;
				}
				break;
			case "stand":
				dealer.action(deck);
				request.setAttribute("dealerAction", dealer.getActionMessage());
				gm.setPhase("PHASE2");
				close = true;
				break;
		}
		
		//ゲームが終了した時
		if(close) {
			request.setAttribute("result", dealer.compareScore(player));
			//結果をデータベースに書き込む
			int loginUserId = Integer.parseInt((String)session.getAttribute("userId"));
			try{
				ResultRecordDao rrd = new ResultRecordDao(); 
				rrd.recordResult(loginUserId, player.getResult());
			}catch (DataBaseException e) {
				e.printStackTrace();
			}
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("BlackJack.jsp");
		rd.forward(request, response);
		
	}

}
