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
body{
	background-color: #78FF94;
	background-image: linear-gradient(0deg, transparent calc(100% - 2px), #FFF calc(100% - 2px)),
                    linear-gradient(90deg, transparent calc(100% - 2px), #FFF calc(100% - 2px));
  	background-size: 47px 47px;
  	background-repeat: repeat;
  	background-position: center center;
}

img{
    height: 220px;
    width: auto;
}

.dealerhand{
	height: 220px;
    width: auto;
    display: flex;
    justify-content: center;
    margin-top: -90px;
}

.dealer{
	margin-top: -10px;
	text-align: center;
}

.playerhand{
	height: 220px;
    width: auto;
    display: flex;
    justify-content: center;
    margin-top: 110px;
}

.action{
	margin-top: 130px;
	text-align: center;
}

button{
    width: 14%;
    height: 90px;
    margin: 10px;
    font-size: 300% ;
   	border: 0;
   	background-color: #005FFF;
   	color: #FFFF22;
}

.hit{
	color: #FFF;	
	background-color: #000000;
}

.stand{
	color: #fff;
	background-color: #FF8C00;
}

.balloon-right {
  position: absolute;
  display: inline-block;
  margin: 1.5em 15px 1.5em 0;
  padding: 0 5px;
  width: 110px;
  height: 110px;
  line-height: 55px;
  text-align: center;
  color: #FFF;
  background: #ff8e9d;
  font-size: 30px;
  font-weight: bold;
  border-radius: 50%;
  box-sizing: border-box;

}

 
.balloon-right:before {
  content: "";
  position: absolute;
  bottom: 88px;
  right: -8px;
  margin-top: -15px;
  border: 15px solid transparent;
  border-left: 15px solid #ff8e9d;
  z-index: 0;
  -webkit-transform: rotate(-45deg);
  transform: rotate(-45deg);
}




.point{
	position: absolute;
    top: 66%;
    left:36.5%;
    
}

.dealerpoint{
	position: absolute;
    top:32%;
    left:36.5%;
}

.menue{
	position: fixed;
	bottom: 2.4%;
	right: 5%;
	width: 20%;
    height: 70px;
    margin: 10px;
    font-size: 220% ;
   	border: 0;
   	background-color: #990000;
   	color: #FFFF22;
}

.result{
	font-weight: bold;
	margin-top: 130px;
	text-align: center;	
	font-size: 170% ;
}

.next{
    width: 20%;
    height: 70px;
    margin: 10px;
    font-size: 220% ;
   	border: 0;
   	background-color: #005FFF;
   	color: #FFFF22;
}

</style>



</head>
<body>

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
		
		<div class="dealerhand"><% 
		Card card1 =dealerHand.get(0);%>
		<img src="trump/<%=card1.getIllustPath()%>">
		<img src="trump/card_back.png">
		</div>
		
		<div class="dealerpoint">
		<div class="balloon-right">
    	<p>？<p>
		</div></div>
		
		<!-- プレイヤーの手札 -->


		<div class="playerhand"><%
		for(Card card :playerHand){%>
		<img src="trump/<%=card.getIllustPath()%>">
		<%
		}%></div>
		<div class="point">
		<div class="balloon-right">
    	<p><%=player.scoreMessage() %><p></div></div>
		
		
		<%break;
		
	default :%>
		<div class="dealerhand"><%
		for(Card card :dealerHand){%>
			<img src="trump/<%=card.getIllustPath()%>">
		<%
		} %></div>
		<div class="dealerpoint">
		<div class="balloon-right">
    	<p><%=dealer.getHand().getFinalScore() %><p>
		</div></div>
		
		
		<div class="playerhand"><%
		for(Card card :playerHand){%>
			<img src="trump/<%=card.getIllustPath()%>">
			<%
		}%></div>
		<div class="point">
		<div class="balloon-right">
    	<p><%=player.getHand().getFinalScore() %><p></div></div>
		
<%
}%>


<%
switch(phase){
	case PLAYERTURN:%>
		
		<form action="BlackJackServlet" method="post">
		<div class="action">
		<button type="submit" value="hit" name="action" class="hit">HIT</button>
		<button type="submit" value="stand" name="action" class="stand">STAND</button></div>
		</form>	
		<%
		break;
	
	case DEALERTURN:%>
		
			
	<% 	
	//DEALERTURN→RESULTの場合はbreakせずに同一の画面で続けて表示
	//PLAYERTURN→RESULTの場合はDEALERTURNを飛ばして最後に引いたカードと結果を表示
	case RESULT:%>
		<div class="result"><%=request.getAttribute("result")%>
		<form action="BlackJackServlet">
		<button type="submit" class="next">NEXT GAME</button>
		</form>
		</div>
		<form action="Menu.jsp">
		<button type="submit" class="menue">メニューに戻る</button>
		</form>
		</div>
		
		<%
		break;
}%>

</body>
</html>