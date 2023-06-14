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

button.link-style-btn, .logout{
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


label{
  color: #FFFFFF;
}


button.link-style-btn.logout{
	position: absolute;
	top: 5%;
	right: 5%;
}


</style>


<meta charset="UTF-8">
<title>メニュー</title>
</head>
<body>

<div class="menue">
<div class="text">

<form action="BlackJackServlet">
<p><button type="submit" class="link-style-btn">Play Game</button></p>
</form>

<form action="BattleRecordServlet">
<p><button type="submit" class="link-style-btn">Battle Record</button></p>
</form>

<form action="UserManagement.jsp">
<p><button type="submit" class="link-style-btn">User Management</button></p>
</form>

</div>
</div>

<form action="LoginServlet">
<p><button type="submit" class="logout">Logout</button></p>
</form>



</body>
</html>