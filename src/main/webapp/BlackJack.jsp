<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Deck"%> 
<%@page import="model.Card"%> 
<%@page import="model.Player"%>
<%@page import="model.Dealer"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ブラックジャック</title>
</head>
<body>
<h1>ブラック☆ジャック</h1>
<p><%=session.getAttribute("playerInit") %><p>
<p><%=session.getAttribute("dealerInit") %><p>

<%
ArrayList<Card> deck = (ArrayList<Card>)session.getAttribute("deck");
Player player = (Player)session.getAttribute("player");
Dealer dealer = (Dealer)session.getAttribute("dealer");
%>
<%if((String)request.getAttribute("message") != null){%>
		<%=request.getAttribute("message")%>
	<%
	}%>
<%

if((boolean)session.getAttribute("playerTurn") && !player.judgeBust()){
	player.getHand().calScore();%>
	<p>現在の得点は<%=player.getHand().getScore() %>です。<p>
	<form action="BlackJackServlet" method="post">
	<input type="submit" value="ヒット" name="hit">
	<input type="submit" value="スタンド" name = "stand">
	</form>
		
<%	
}%>

<%
if(player.judgeBust()){%>
	バーストしました<br>
	あなたのまけです<br>

<%}
if(request.getAttribute("playerScore") != null){%>
	<p>あなたの得点は<%=player.getHand().getScore()%>です<p>
<%}%>


<% if(!(boolean)session.getAttribute("playerTurn")){%>
	<a href="BlackJackServlet">next game</a>
<% }%>


		

</body>
</html>