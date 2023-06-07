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
		
		session.setAttribute("playGame", true);
		
		Deck deck = new Deck();
			
		Player player = new Player();
		Dealer dealer = new Dealer();
		
		session.setAttribute("deck", deck.getDeck());
		session.setAttribute("player", player);
		session.setAttribute("dealer", dealer);
		
		request.setAttribute("playerInit", player.initialHand(deck.getDeck()));
		request.setAttribute("dealerInit", dealer.initialHand(deck.getDeck()));
		
		session.setAttribute("playerTurn", true);
		
		RequestDispatcher rd = request.getRequestDispatcher("BlackJack.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Player player = (Player)session.getAttribute("player");
		Dealer dealer = (Dealer)session.getAttribute("dealer");
		ArrayList<Card> deck = (ArrayList<Card>)session.getAttribute("deck");
		
		//Playerがhitを選択した場合
		if(request.getParameter("hit") != null) {
			Card c1 = player.drawCard(deck);
			player.addStringHand(c1);
			String s = c1.getDisplayName()+"を引きました";
			request.setAttribute("drawMessage", s);
			
			//playerがバーストしていた場合
			if(player.judgeBust()) {
				session.setAttribute("playerTurn", false);
				session.setAttribute("playGame", false);
			}
		}
		
		//Playerがstandを選択した場合
		if(request.getParameter("stand") != null) {
			session.setAttribute("playerTurn", false);
			request.setAttribute("playerScore", player.getHand().getFinalScore());
			dealer.action(deck, dealer.getHand());
			request.setAttribute("dealerAction", dealer.getActionMessage());
			session.setAttribute("playGame", false);
		}
		
		//Playerがバーストする、もしくはDealerのターン終了で
		//ゲーム終了となり、勝敗判定
		//Playerのフィールドresultに勝ち負けを格納
		//
		if(!(boolean)session.getAttribute("playGame")) {
			request.setAttribute("result", dealer.compareScore(player));
			
			//結果をデータベースに書き込む
			int loginUserId = Integer.parseInt((String)session.getAttribute("userId"));
			try{
				ResultRecordDao rrd = new ResultRecordDao(); 
				if(player.getResult().equals("win") | player.getResult().equals("lose")) {
					rrd.recordResult(loginUserId, player.getResult());
				}
				if(player.getResult().equals("draw")) {
					rrd.recordResult(loginUserId);
				}
					
			}catch (DataBaseException e) {
				request.setAttribute("error", "true");
				e.printStackTrace();
			}
		}
		
		
		RequestDispatcher rd = request.getRequestDispatcher("BlackJack.jsp");
		rd.forward(request, response);
		
	}

}
