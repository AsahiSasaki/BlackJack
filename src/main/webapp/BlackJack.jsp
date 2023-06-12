<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Deck"%> 
<%@page import="model.Card"%> 
<%@page import="model.Player"%>
<%@page import="model.Dealer"%>   
<%@page import="model.GameManagement"%>  
<%@page import="model.GameManagement.Phase"%>

   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ブラックジャック</title>

<style>
img{
    height: 220px;
    width: auto;
}

.dealerhand{
	height: 220px;
    width: auto;
    display: flex;
    justify-content: center;
}

.dealer{
	text-align: center;
}

</style>



</head>
<body>
<h1>ブラック☆ジャック</h1>

<%
GameManagement gm = (GameManagement)session.getAttribute("gameManagement");
Phase phase = gm.getPhase();
ArrayList<Card> deck = (ArrayList<Card>)session.getAttribute("deck");
Player player = (Player)session.getAttribute("player");
ArrayList<Card> playerHand = player.getHand().getHand();
Dealer dealer = (Dealer)session.getAttribute("dealer");
ArrayList<Card> dealerHand = dealer.getHand().getHand();
%>

<div class="dealer"><img src="trump/casino_dealer_woman.png"></div>


<!-- ディーラーの手札 -->
<%
switch(phase){
	case PLAYERTURN :%>	
		
		<div class="dealerhand">
		<% 
		Card card1 =dealerHand.get(0);%>
		<img src="trump/<%=card1.getIllustPath()%>">
		<img src="trump/card_back.png">
		</div><%
		break;
		
	default :%>
		<div class="dealerhand">ディーラー<br><%
		for(Card card :dealerHand){%>
			<img src="trump/<%=card.getIllustPath()%>">
		<%
		} %></div>
<%
}%>

<!-- プレイヤーの手札 -->
<p>あなた<br>
<%
for(Card card :playerHand){%>
	<img src="trump/<%=card.getIllustPath()%>">
<%
}%></p>


<%
switch(phase){
	case PLAYERTURN:%>
		<p>あなたのターンです</p>
		<p><%=player.scoreMessage() %><p>

		<form action="BlackJackServlet" method="post">
		<p><button type="submit" value="hit" name="action">ヒット</button>
		<button type="submit" value="stand" name="action">スタンド</button></p>
		</form>	
		<%
		break;
	
	case DEALERTURN:%>
		<p>ディーラーのターンです<p>
		
		<%
		ArrayList<String> dealerAction = (ArrayList<String>)request.getAttribute("dealerAction");
		for(String s:dealerAction){%>
			<%=s %><br>
		<%
		}%>		
	<% 	
	//DEALERTURN→RESULTの場合はbreakせずに同一の画面で続けて表示
	//PLAYERTURN→RESULTの場合はDEALERTURNを飛ばして最後に引いたカードと結果を表示
	case RESULT:%>
		<p><%=request.getAttribute("result")%></p>
		<a href="BlackJackServlet">next game</a><br>
		<a href="Menue.jsp">メニューに戻る</a>
		<%
		break;
}%>

</body>
</html>