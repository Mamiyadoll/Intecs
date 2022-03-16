<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="jp">
<head>
<meta charset="UTF-8">
<title>購入完了 | Intecs オンラインストア</title>
<link rel="stylesheet" href="css/main.css">
</head>

<body>
	<div class="wrapper">

	<%@ include file="header.jsp"%>

		<p id="err_chk" style=" color: red;">
		<%
			if (request.getAttribute("errorMessage") != "") {
				out.print(request.getAttribute("errorMessage"));
			}
		%>
		</p>

		<h3>ご注文ありがとうございました。</h3>
		<a href="index.jsp ">
			<button type="button" class="button">TOPへ</button>
		</a>
	</div>
	<div class="copyright ">Copyright © 2022 Intecs Co., Ltd. All
			righys reserved.</div>
</body>
</html>