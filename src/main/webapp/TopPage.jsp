<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
h1{
	margin-top: 20%;
	text-align: center;
	font-size: 500% ;

}

.login{
	margin-top: -3%;
	text-align: center;
}

button{
    width: 8%;
}

input{
	width: 8%;
}

.signup{
	margin-top: 5%;
	text-align: center;
}

a{	
	text-decoration: none;
	color: #000000;
}

footer{
	position: fixed;
	bottom: 0;
	right: 1%;
}


</style>
<meta charset="UTF-8">
<title>トップ</title>
</head>
<body>
<h1>BLACK JACK</h1>

<div class="login">
<% String message = (String)request.getAttribute("message");  
	if(message != null){ %>
	<span style="color:red"><%= message %></span>
	<% 
	} %>
<form action="LoginServlet" method="post">
<p><input type="text" name="loginID" placeholder="UserID" required="true"  maxlength="9"/></p>
<p><input type="text" name="loginPassword" placeholder="Password" required="true"/></p>
<p><button type="submit">ログイン</button></p>
</form>
</div>

<div class="signup"><a href="Register.jsp">新規登録はこちら</a></div>

<footer>
    <p><small>&copy; 2023 Asahi Sasaki.</small></p>
 </footer>

</body>
</html>