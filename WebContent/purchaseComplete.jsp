<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="jp">
<head>
<meta charset="UTF-8">
<title>購入完了 | Intecs オンラインストア</title>
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
		<h2 class="headline">ご注文ありがとうございました。</h2>
		<a href="index.jsp ">
			<button type="button" class="button">TOPへ</button>
		</a>
	</div>
</body>
</html>