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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		Deck deck = new Deck();
		ArrayList<Card> deckReset = deck.resetDeck();
		ArrayList<Card> deckShuffle = deck.shuffleDeck(deckReset);
			
		Player player = new Player();
		Dealer dealer = new Dealer();
		
		session.setAttribute("playGame", true);
		session.setAttribute("playerTurn", true);
		
		session.setAttribute("deck", deckShuffle);
		session.setAttribute("player", player);
		session.setAttribute("dealer", dealer);
		
		request.setAttribute("playerInit", player.initialHand(deckShuffle));
		request.setAttribute("dealerInit", dealer.initialHand(deckShuffle));
		RequestDispatcher rd = request.getRequestDispatcher("BlackJack.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Player player = (Player)session.getAttribute("player");
		Dealer dealer = (Dealer)session.getAttribute("dealer");
		ArrayList<Card> deck = (ArrayList<Card>)session.getAttribute("deck");
		
		if(request.getParameter("hit") != null) {
			Card c1 = player.drawCard(deck);
			String s = c1.getSuit() + "の" + c1.getRankString()+"を引きました";
			request.setAttribute("drawMessage", s);
		}
		
		if(request.getParameter("stand") != null) {
			session.setAttribute("playerTurn", false);
			request.setAttribute("playerScore", player.getHand().getScore());
			player.setStand();
			
		}
		
		if(player.judgeBust()) {
			session.setAttribute("playerTurn", false);
			session.setAttribute("playGame", false);
		}
		
		if(request.getParameter("stand") != null) {
			Card d2 = dealer.getHand().getHand().get(1);
			dealer.getMessage()
			.add("ディーラーが２枚目に引いたカードは"+ d2.getSuit() + "の" + d2.getRankString() + "でした");
			dealer.action(deck, dealer.getHand());
			request.setAttribute("dealerAction", dealer.getMessage());
			session.setAttribute("playGame", false);
		}
		
		if(!(boolean)session.getAttribute("playGame")) {
			request.setAttribute("result", dealer.compareScore(player));
		}
		
		if(player.getResult() != null) {
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
