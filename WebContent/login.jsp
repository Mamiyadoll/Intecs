<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="jp">
<head>
<meta charset="UTF-8">
<title>ログイン | Intecs オンラインストア</title>
<link rel="stylesheet" href="files/main.css">
</head>

<body>
	<div class="wrapper">
		<header>
		<div class="header__inner">
			<h1>
				<a href="main.jsp"><img src="img/logo.svg" alt="株式会社インテックス "></a>
			</h1>
		</div>
	</header>
		<h2 class="headline">会員ログイン</h2>

		<%
			if (request.getAttribute("errorMessage") != "") {
				out.print(request.getAttribute("errorMessage"));
			}
		%>

		<form action="LoginServlet" method="post">
			<input type="text" name="loginId" placeholder="ログインID"><br>
			<input type="password" name="password" placeholder="パスワード"><br>
			<input type="submit" value="ログイン" class="button">
		</form>
		<button onclick="location.href='signup.jsp'" class="button">新規登録</button>

		<%@ include file="footer.jsp"%>
	</div>
</body>


</html>