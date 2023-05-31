<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メニュー</title>
</head>
<body>
<% String message = (String)session.getAttribute("loginuser"); 
	if(message != null){ %>
	<p><%= message %>さんこんにちは</p>
	<% 
	} %>
Play<br>
戦績<br>
<a href="UserManagement.jsp">ユーザー管理</a>
<form action="LoginServlet">
	<input type="submit" value="ログアウト"/></p>
</form>
</body>
</html>