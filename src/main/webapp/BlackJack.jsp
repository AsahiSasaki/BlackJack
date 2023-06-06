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


<%if(request.getAttribute("playerInit") != null){ %>
<p><%=request.getAttribute("playerInit") %><p>
<%} %>
<%if(request.getAttribute("dealerInit") != null){ %>
<p><%=request.getAttribute("dealerInit") %><p>
<%} %>

<%
ArrayList<Card> deck = (ArrayList<Card>)session.getAttribute("deck");
Player player = (Player)session.getAttribute("player");
Dealer dealer = (Dealer)session.getAttribute("dealer");

%>
<%if(request.getAttribute("drawMessage") != null){%>
		<p><%=request.getAttribute("drawMessage")%><p>
	<%
	}%>
<%

if((boolean)session.getAttribute("playerTurn") && !player.judgeBust()){%>
	<p>現在の得点は<%=player.getHand().getScore() %>です。<p>
	<form action="BlackJackServlet" method="post">
	<input type="submit" value="ヒット" name="hit">
	<input type="submit" value="スタンド" name = "stand">
	</form>
		
<%	
}%>

<%int playerScore = player.getHand().getScore(); %>

<%
if(request.getAttribute("playerScore") != null){%>
	<p>あなたの得点は<%=playerScore%>です<p>
<%}%>

<%
ArrayList<String> s = (ArrayList<String>)request.getAttribute("dealerAction");
if(s != null){
for(String string:s){%>
<p><%=string %><p>
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