<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="jp">
<head>
<meta charset="UTF-8">
<title>Free | オンラインストア</title>
</head>
<body>
<br><br><br><br>
	<%
		if (request.getAttribute("errorMessage") != "") {
			out.print(request.getAttribute("errorMessage"));
		}
	%>

	<h1>会員ログイン</h1>
	<form action="LoginServlet" method="post">
		<input type="text" name="loginId" placeholder="ログインID"><br>
		<input type="password" name="password" placeholder="パスワード"><br>
		<input type="submit" value="ログイン">
	</form>
	<button onclick="location.href='signup.jsp'">新規登録</button>
<br><br><br><br>
<%@ include file="footer.jsp"%>
</body>
</html>