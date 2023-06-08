<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録</title>
</head>
<body>
<p><a href="TopPage.jsp">トップページに戻る</a></p>
<% String message = (String)request.getAttribute("message"); 
	if(message != null){ %>
	<span style="color:red"><%= message %></span>
	<% 
	} %>
<form action="RegisterServlet" method="post">
<p>ID:<input type="text" name="loginID" required="true"/></p>
<p>パスワード:<input type="text" name="loginPassword" required="true"/></p>
<p>パスワード確認:<input type="text" name="confirmationLoginPassword" required="true"/></p>
<p>ニックネーム:<input type="text" name="nickname" required="true"/></p>
<p><input type="submit" value="登録"/> </p>
</form>

</body>
</html>