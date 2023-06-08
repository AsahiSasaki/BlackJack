<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>トップページ</title>
</head>
<body>
<% String message = (String)request.getAttribute("message");  
	if(message != null){ %>
	<span style="color:red"><%= message %></span>
	<% 
	} %>
<form action="LoginServlet" method="post">
<p><input type="text" name="loginID" placeholder="UserID" required="true"/></p>
<p><input type="text" name="loginPassword" placeholder="Password" required="true"/></p>
<p><button type="submit">ログイン</button></p>
</form>

<a href="Register.jsp">新規登録</a>

</body>
</html>