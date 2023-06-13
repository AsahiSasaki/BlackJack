<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
body{
	background-color: #000000;
}


.menue{
	margin-top: 15%;
	text-align: center;
}

.menue .text{
	text-align: left;
	display: inline-block;
}

.logout{
	position: absolute;
	top: 5%;
	right: 5%;
}

a{	
	color: #FFFFFF;
	text-decoration: none;
	font-family: "Impact";
	font-size: 450% ;
	
}


</style>


<meta charset="UTF-8">
<title>メニュー</title>
</head>
<body>

<div class="menue">
<div class="text">
<p><a href="BlackJackServlet">Play Game</a></p>

<p><a href="BattleRecordServlet" >Battle Record</a></p>

<p><a href="UserManagement.jsp">User Management</a></p>
</div>
</div>

<a href="LoginServlet" class="logout">Logout</a>


</body>
</html>