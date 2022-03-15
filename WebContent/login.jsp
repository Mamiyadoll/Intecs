<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="jp">
<head>
<meta charset="UTF-8">
<title>ログイン | Intecs オンラインストア</title>
<link rel="stylesheet" href="files/main.css">
</head>

<%@ include file="header.jsp"%>
<body>
	<h2>会員ログイン</h2>

	<%
		if (request.getAttribute("errorMessage") != null) {
			out.print(request.getAttribute("errorMessage"));
		}
	%>

	<form action="LoginServlet" method="post">
		<input type="text" name="loginId" placeholder="ログインID"><br>
		<input type="password" name="password" placeholder="パスワード"><br>
		<input type="submit" value="ログイン">
	</form>
	<button onclick="location.href='signup.jsp'" class="button">新規登録</button>
</body>
<%@ include file="footer.jsp"%>

</html>