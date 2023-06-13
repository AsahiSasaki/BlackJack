<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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

.delete{
	margin-top: 15%;
	text-align: center;
	font-size: 150% ;
}
</style>



<title>ユーザー管理</title>
</head>
<body>
<a href="Menu.jsp">Menu</a>
<div class="delete">
<p>現在ログインしているユーザーを削除します。<br>
パスワードを入力して削除ボタンを押してください</p>
<% String message = (String)request.getAttribute("message");  
	if(message != null){ %>
	<span style="color:red"><%= message %></span>
	<% 
	} %>

<form action="UserManagementServlet" method="post">
<p><input type="text" placeholder="Password" name="loginPassword" required="true"/></p>
<p><input type="submit" value="削除"/> </p>
</form>
</div>
</body>
</html>