<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="beans.ProductBean" import="beans.UserBean"
	import="java.util.*"%>
<!DOCTYPE html>
<html lang="jp">
<head>

<meta charset="UTF-8">
<title>Intecs | オンラインストア</title>
<link rel="stylesheet" href="files/main.css">
<link rel="stylesheet" href="files/submain.css">


</head>


<%@ include file="header.jsp" %>
<!-- BODY -->
<body>

	<div class="search">
		<form action="SearchServlet" method="get">
			<input type="text" name="keyword" style="width: 50% ; height:30px; "
				placeholder="書籍名、キーワード">
			<input type="submit" value="検索">
		</form>
	</div>

	<!-- おすすめ一覧 -->
	<div class="newbooks">

		<ul class="newbooks-box">
			<li>
				<p id="book-title">
					<img src="${pageContext.request.contextPath}/productImg/${product[0].isbn}.jpg">
				</p>
				<p id="book-title">${product[0].productName}</p><br>
				<p>${product[0].authorName}</p>
				<form action="CartAddServlet" method="post">
					<input type="hidden" name="isbn" value="${product[0].isbn}">
					数量
					<select name="number">
						<option value="1" selected>1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
					</select>
					<input type="submit" class="button" value="カートに入れる">
				</form>
			</li>
			<li>
				<p id="book-title">
					<img src="${pageContext.request.contextPath}/productImg/${product[1].isbn}.jpg">
				</p>

				<p id="book-title">${product[1].productName}</p><br>
				<p>${product[1].authorName}</p>

				<form action="CartAddServlet" method="post">
						<input type="hidden" name="isbn" value="${produc[1].isbn}">
					数量
					<select name="number">
						<option value="1" selected>1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
					</select>
					<input type="submit" class="button" value="カートに入れる">

				</form>
			</li>
			<li>
				<p id="book-title">
					<img src="${pageContext.request.contextPath}/productImg/${product[2].isbn}.jpg">
				</p>
				<p id="book-title">${product[2].productName}</p><br>
				<p>${product[2].authorName}</p>
				<form action="CartAddServlet" method="post">
				<input type="hidden" name="isbn" value="${product[2].isbn}">
					数量
					<select name="number">
						<option value="1" selected>1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
					</select>
					<input type="submit" class="button" value="カートに入れる">

				</form>
			</li>
		</ul>
	</div>
	<!-- ユーザレコメンド ここまで -->


</body>
</html>