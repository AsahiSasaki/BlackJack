<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メニュー</title>
</head>
<body>
<% String message = (String)session.getAttribute("loginuser");  %>
<p><%= message %>さんこんにちは</p>
	

<a href="BlackJackServlet">Play</a>

<p><a href="BattleRecordServlet" >戦績</a><p>

<a href="UserManagement.jsp">ユーザー管理</a>

<form action="LoginServlet">
	<input type="submit" value="ログアウト"/></p>
</form>

</body>
</html>