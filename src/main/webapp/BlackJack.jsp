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
</head>
<body>
<h1>ブラック☆ジャック</h1>

<%
GameManagement gm = (GameManagement)session.getAttribute("gameManagement");
Phase phase = gm.getPhase();
ArrayList<Card> deck = (ArrayList<Card>)session.getAttribute("deck");
Player player = (Player)session.getAttribute("player");
Dealer dealer = (Dealer)session.getAttribute("dealer");
%>

<%
switch(phase){

	case PHASE0: %>
		<p><%=request.getAttribute("playerInit") %><p>
		<p><%=request.getAttribute("dealerInit") %><p>
		<%gm.setPhase("PHASE1"); 
		//2枚の手札の表示は最初だけ行い、そのままPHASE1に移行
	case PHASE1:%>
		<p>あなたのターンです</p>
		<%
		if(request.getAttribute("drawMessage") != null){%>
			<p><%=request.getAttribute("drawMessage")%><p>
		<%
		}%>
		
		<p>現在の手札<br>
		<%=player.getStringHand() %></p>
		<p><%=player.scoreMessage() %><p>

		<form action="BlackJackServlet" method="post">
		<p><button type="submit" value="hit" name="action">ヒット</button>
		<button type="submit" value="stand" name="action">スタンド</button></p>
		</form>	
		
		<%
		break;
	
	case PHASE2:%>
		<p>ディーラーのターンです<p>
		<%
		ArrayList<String> dealerAction = (ArrayList<String>)request.getAttribute("dealerAction");
		for(String s:dealerAction){%>
			<p><%=s %><p>
		<%
		gm.setPhase("PHASE3"); 
		}%>		
	<% 	
	//PHASE2→PHASE3の場合は同一の画面で続けて表示
	//PHASE1→PHASE3の場合はPHASE2を飛ばして最後に引いたカードと結果を表示
	case PHASE3:%>
		<%
		if(request.getAttribute("drawMessage") != null){%>
			<p><%=request.getAttribute("drawMessage")%><p>
		<%
		}%>
		<p><%=request.getAttribute("result")%></p>
		<a href="BlackJackServlet">next game</a><br>
		<a href="Menue.jsp">メニューに戻る</a>
		<%
		break;
}%>

</body>
</html>