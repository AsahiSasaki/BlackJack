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

<%
ArrayList<Card> deck = (ArrayList<Card>)session.getAttribute("deck");
Player player = (Player)session.getAttribute("player");
Dealer dealer = (Dealer)session.getAttribute("dealer");
%>


<%if(request.getAttribute("playerInit") != null){ %>
<p><%=request.getAttribute("playerInit") %><p>
<%} %>
<%if(request.getAttribute("dealerInit") != null){ %>
<p><%=request.getAttribute("dealerInit") %><p>
<%} %>



<%
if(request.getAttribute("drawMessage") != null){%>
	<p><%=request.getAttribute("drawMessage")%><p>
<%
}%>

<%
if((boolean)session.getAttribute("playerTurn")){%>
	現在の手札<br>
	<%=player.getStringHand() %>
	<% 
	if(player.getHand().getExistA() && player.getHand().getMaxScore() < 21) {%>
		<p>現在の得点は<%=player.getHand().getMinScore() %>/<%= player.getHand().getMaxScore()%>です。<p>
	<%
	}else {%>
		<p>現在の得点は<%=player.getHand().getFinalScore() %>です。<p>
	<%
	}%>

	<form action="BlackJackServlet" method="post">
	<input type="submit" value="ヒット" name="hit">
	<input type="submit" value="スタンド" name = "stand">
	</form>	
<%	
}%>

<%
if(request.getAttribute("playerScore") != null){%>
	<p>あなたの得点は<%=player.getHand().getFinalScore()%>です<p>
<%}%>

<%
ArrayList<String> dealerAction = (ArrayList<String>)request.getAttribute("dealerAction");
if(dealerAction != null){
for(String s:dealerAction){%>
<p><%=s %><p>
<%}
} %>

<%if(request.getAttribute("result") != null){%>
<p><%=request.getAttribute("result")%><p></p>
<%}%>

<% if(!(boolean)session.getAttribute("playGame")){%>
	<a href="BlackJackServlet">next game</a><br>
	<a href="Menue.jsp">メニューに戻る</a>
<% }%>


		

</body>
</html>