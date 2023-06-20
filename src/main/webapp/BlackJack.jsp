<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Deck"%> 
<%@page import="model.Card"%> 
<%@page import="model.Player"%>
<%@page import="model.Dealer"%>   
<%@page import="model.GameManagement"%>  
<%@page import="model.GameManagement.Phase"%>
<%@ page import="dao.BattleRecordDao" %>
<%@ page import="dao.UserDao" %>
   
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
   	cursor: pointer;
   	box-shadow: 0px 5px 0 #000;
    transition: .3
}

button:hover {
  transform: translate3d(0, 5px, 0);
  box-shadow: none;
}

.hit{
	color: #FFF;	
	background-color: #000000;
}

.stand{
	color: #fff;
	background-color: #FF8C00;
}

.split{
	color: #FF0000;	
	background-color: #FFFF4D;
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

.changeBet{
	position: fixed;
	bottom: 13%;
	right: 5%;
	width: 20%;
    height: 70px;
    margin: 10px;
    font-size: 220% ;
   	border: 0;
   	background-color: #FF0000;
   	color: #000000;
}

.nowBet{
	position: fixed;
	bottom: 23.6%;
	right: 5%;
	width: 20%;
    height: 70px;
    margin: 10px;
    font-size: 300% ;
   	color: #000000;
   	font-family: "Impact";
}


.result{
	font-weight: bold;
	margin-top: 130px;
	text-align: center;	
	font-size: 170% ;
}

.next, .deal{
    width: 20%;
    height: 70px;
    margin: 10px;
    font-size: 220% ;
   	border: 0;
   	background-color: #005FFF;
   	color: #FFFF22;
}

.deal{
	position: absolute;
	bottom: 2.4%;
	left: 40%;
}


.chip{
	margin-top: 45px;
	margin-bottom: 0;
    display: flex;
    justify-content: center;
}
.chip label{
    display: block;
    height: 150px;
    width: 150px;
    background: #FFFFFF;
    color: #000000;
    padding: 10px;
    margin: 10px;
    box-sizing: border-box;
    text-align: center;
    text-decoration: none;
    border-radius: 50%;
    cursor: pointer;
    border: 25px solid #FF0000;
    padding: 21px;
    font-size: 45px;
    font-family: "Impact";
}

.chip input:hover+label{
	color: #000000;
    background: #FFFFFF;
    border: 25px solid #FF3333;
 }
 
.chip input:checked+label{
    background: #FFFFFF;
    color: #000000;
    border: 25px solid #0000FF;
}
.chip input{
    display: none;
}



.center{
	text-align: center;
}

.betText{
	font-weight: bold;
	text-align: center;	
	font-size: 170% ;
}

table{
	font-size: 300%;
	font-family: "Impact";
}

.numchip{
	position: fixed;
	bottom: 0px;
	left: 0px;
}


</style>



</head>
<body>

<%
GameManagement gm = (GameManagement)session.getAttribute("gameManagement");
Phase phase = gm.getPhase();
Player player = (Player)session.getAttribute("player");
ArrayList<Card> playerHand = player.getHand().getHand();
Dealer dealer = (Dealer)session.getAttribute("dealer");
ArrayList<Card> dealerHand = dealer.getHand().getHand();
UserDao ud = new UserDao();
int userId= Integer.parseInt((String)session.getAttribute("userId"));
%>

<div class="dealer"><img src="trump/casino_dealer_woman.png"></div>

<div class="numchip">
   	<table>
		<tr>
			<td><img src="trump/casino_chip.png"></td>
			<td><%=ud.getChip(userId) %></td>
		</tr>
	</table>
 </div>
 <%ud.close(); %>

<%
switch(phase){
    case INIT:%>
    	
    	<form action="BlackJackServlet">
    	<div class="center">
    	<div class="betText">いくら賭けますか？</div>
      	<div class="chip">
    	<input type="radio" name="betChip" value="1" id="one" checked> <label for="one" >1</label>
    	<input type="radio" name="betChip" value="2" id="two"> <label for="two" >2</label>
    	<input type="radio" name="betChip" value="3" id="three"> <label for="three" >3</label>
       	<input type="radio" name="betChip" value="4" id="four"> <label for="four" >4</label>
    	<input type="radio" name="betChip" value="5" id="five"> <label for="five" >5</label>
    	</div>
    	<div class="chip">
    	<input type="radio" name="betChip" value="6" id="six"> <label for="six" >6</label>
    	<input type="radio" name="betChip" value="7" id="seven"> <label for="seven" >7</label>
    	<input type="radio" name="betChip" value="8" id="eight"> <label for="eight" >8</label>
    	<input type="radio" name="betChip" value="9" id="nine"> <label for="nine" >9</label>
    	<input type="radio" name="betChip" value="10" id="ten" > <label for="ten" >10</label> 
		</div>
		<p><button type="submit" class="deal">DEAL</button><p>
		</div></form>
		
		<form action="Menu.jsp">
		<button type="submit" class="menue">メニューに戻る</button>
		</form>
		
		

		<%
		break;
	case PLAYERTURN :%>	
	
		<!-- ディーラーの手札 -->
		<div class="dealerhand">
		<img src="trump/<%=dealerHand.get(0).getIllustPath()%>">
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
		
		<form action="BlackJackServlet" method="post">
		<div class="action">
		<button type="submit" value="hit" name="action" class="hit">HIT</button>
		<button type="submit" value="stand" name="action" class="stand">STAND</button></div>
		</form>	
		
		<div class="nowBet">BET：<%=session.getAttribute("betChip") %></div>
		
		<%break;
		
	case RESULT :%>
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
    	
    	<div class="result"><%=request.getAttribute("result")%>
		<form action="BlackJackServlet">
		<button type="submit" class="next">NEXT GAME</button></div>
		<button type="submit" class="changeBet" name="changeBet">BET額を変更</button>
		</form>
		
		<div class="nowBet">BET：<%=session.getAttribute("betChip") %></div>
		<%session.removeAttribute("gameManagement");
		%>
		<form action="Menu.jsp">
		<button type="submit" class="menue">メニューに戻る</button>
		</form>
    <% 	break;
	case POSSIBLESPLIT:%>
		
		<!-- ディーラーの手札 -->
		<div class="dealerhand">
		<img src="trump/<%=dealerHand.get(0).getIllustPath()%>">
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
		
		<form action="BlackJackServlet" method="post">
		<div class="action">
		<button type="submit" value="split" name="split" class="split">SPLIT</button>
		<button type="submit" value="hit" name="action" class="hit">HIT</button>
		<button type="submit" value="stand" name="action" class="stand">STAND</button></div>
		</form>	
		
		<div class="nowBet">BET：<%=session.getAttribute("betChip") %></div>
		
		<%break;

}%>




</body>
</html>