<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="dao.BattleRecordDao" %>
<%@ page import="dao.UserDao" %>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<style>
body{
	background-color: #000000;
	color: #FFFFFF;
	font-family: "Yu Mincho";
}

a{	
	color: #FFFFFF;
	text-decoration: none;
	font-family: "Impact";
	font-size: 450% ;
	
}

.return{
	position: absolute;
	top: 0%;
	left: 0%;
}

.record{
	margin-top: 5%;
	text-align: center;
	font-size: 200% ;
}

.subject{
	color: orange;
	font-size: 200% ;
}

.record .text{
	text-align: left;
	display: inline-block;
}

button.menu{
  cursor: pointer;
  border: none;
  background: none;
  color: #FFFFFF;
  font-family: "Impact";
  font-size: 450% ;
}

button:hover {
	color: #000000;
    background-color: #FFFFFF;
 }


</style>

<meta charset="UTF-8">
<title>戦績表時</title>
</head>
<body>

<form action="Menu.jsp">
<p><button type="submit" class="menu">Menu</button></p>
</form>

<%int loginId = Integer.parseInt((String)session.getAttribute("userId"));
UserDao ud = new UserDao();%>
<div class="record">
<div class="subject"><%=session.getAttribute("loginuser") %>さんの成績</div>
<p><%=session.getAttribute("battleRecord")%><br>
チップの保有枚数：<%=ud.getChip(loginId) %>枚</p>
<div class="subject">チップ所持枚数トップ5</div>

<%	BattleRecordDao brd = new BattleRecordDao();
	ArrayList<Integer> topFive = brd.getTopFive();%>
	<table width=30% align="center"><% 
	for(int i=0; i < topFive.size();i++) {
		int userId = topFive.get(i);%>
		<tr>
			<td><%=i+1%>位</td>
			<td><%=ud.getNickname(userId) %></td>
			<td><%=ud.getChip(userId) %>枚</td>
		</tr>
	<% 
	}%>
	</table>

</div>
</body>
</html>