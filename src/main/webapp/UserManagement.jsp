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



<title>ユーザー管理</title>
</head>
<body>

<form action="Menu.jsp">
<p><button type="submit" class="menu">Menu</button></p>
</form>

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