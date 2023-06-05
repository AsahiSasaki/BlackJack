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
		
		session.setAttribute("playerTurn", true);
		
		session.setAttribute("deck", deckShuffle);
		session.setAttribute("player", player);
		session.setAttribute("dealer", dealer);
		
		session.setAttribute("playerInit", player.initialHand(deckShuffle));
		session.setAttribute("dealerInit", dealer.initialHand(deckShuffle));
		RequestDispatcher rd = request.getRequestDispatcher("BlackJack.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Player player = (Player)session.getAttribute("player");
		ArrayList<Card> deck = (ArrayList<Card>)session.getAttribute("deck");
		
		if(request.getParameter("hit") != null) {
			Card c1 = player.drawCard(deck);
			String s = c1.getSuit() + "の" + c1.getRankString()+"を引きました";
			request.setAttribute("message", s);
		}
		if(request.getParameter("stand") != null) {
			session.setAttribute("playerTurn", false);
			request.setAttribute("playerScore", player.getHand().getScore());
			player.setStand();
		}
		if(player.judgeBust()) {
			session.setAttribute("playerTurn", false);
		}
		RequestDispatcher rd = request.getRequestDispatcher("BlackJack.jsp");
		rd.forward(request, response);
		
	}

}
