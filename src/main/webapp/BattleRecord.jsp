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

.content{
	color: orange;
	font-size: 200% ;
}


</style>

<meta charset="UTF-8">
<title>戦績表時</title>
</head>
<body>
<a href="Menu.jsp" class="return">Menu</a>

<div class="record">
<p class="content"><%=session.getAttribute("loginuser") %>さんの成績</p>
<%=session.getAttribute("battleRecord") %>
<p class="content">勝率トップ５ユーザー</p>
<%	BattleRecordDao brd = new BattleRecordDao();
	UserDao ud = new UserDao();
	ArrayList<Integer> topFive = brd.getTopFive();
	for(int i=0; i < topFive.size();i++) {
		int userId = topFive.get(i);%>
		<p><%=brd.getMessage(i+1, userId)%></p>
	<% 
	}%>
</div>
</body>
</html>