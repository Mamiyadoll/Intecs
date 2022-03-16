<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="jp">
<head>
<meta charset="UTF-8">
<title>新規登録完了 | Intecs オンラインストア</title>
<link rel="stylesheet" href="css/main.css">
</head>
<%@ include file="header.jsp"%>
<body>

	<div class="wrapper">

		<%
			if (request.getAttribute("errorMessage") != "") {
				out.print(request.getAttribute("errorMessage"));
			}
		%>

		<h2 class="headline">
			新規登録が完了しました。 <br>引き続きお買い物をお楽しみください
		</h2>
		<a href="main.jsp ">
			<button type="button" class="button">TOPへ</button>
		</a>
	</div>
</body>
</html>