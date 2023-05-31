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
<p>ID:<input type="text" name="loginID"/></p>
<p>パスワード:<input type="text" name="loginPassword"/></p>
<p><input type="submit" value="ログイン"/> </p>
</form>

<a href="Register.jsp">新規登録</a>

</body>
</html>