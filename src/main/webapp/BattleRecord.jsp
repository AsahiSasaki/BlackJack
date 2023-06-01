<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="dao.BattleRecordDao" %>
<%@ page import="dao.UserDao" %>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>戦績表時</title>
</head>
<body>
<p><a href="Menue.jsp">メニューに戻る</a></p>
<h1>戦績</h1>

<h2><p><%=session.getAttribute("loginuser") %>さんの成績</p></h2>
<p><%=session.getAttribute("battleRecord") %></p>
<h2><p>勝率トップ５ユーザー</p></h2>
<%	BattleRecordDao brd = new BattleRecordDao();
	UserDao ud = new UserDao();
	ArrayList<Integer> topFive = brd.getTopFive();
	for(int i=0; i < topFive.size();i++) {
		int userId = topFive.get(i);%>
		<p><%=brd.getMessage(i+1, userId)%></p>
	<% 
	}%>
</body>
</html>