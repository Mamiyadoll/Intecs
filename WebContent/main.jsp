<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="beans.ProductBean" import="beans.UserBean" import="java.util.*" %>
<!DOCTYPE html>
<html lang="jp">
<head>

<meta charset="UTF-8">
<title>Intecs | オンラインストア</title>

</head>

<!-- BODY -->
<body>

		<div class="search">
			<form action="SearchServlet" method="get">
				<input type="text" name="keyword" style="width: 50%;"
					placeholder="書籍名、キーワード"> <input type="submit" value="&#xf002">
			</form>
		</div>

			<!-- おすすめ一覧 -->
			<div class="newbooks">
				<h2 class="subtitle">Recomended Books</h2>

				<ul class="newbooks-box">
			        <li>
				        <p id="book-title"><img src="${product[0].isbn}"></p>
				        <p id="book-title">${product[0].productName}</p>
						<p>${product[0].authorName}</p>
							<form action="CartAddServlet" method="post">
					            <input type="hidden" name="isbn" value="${product[0].isbn}">
					            <!--<input type="hidden" name="isbn" value=isbn>-->
					            <input type="hidden" name="count" value="1">
					            <input type="submit" class="cart-btn" value="カートに入れる">
							</form>
			        </li>
					<li>
						<p id="book-title"><img src="${product[1].isbn}"></p>
						<p id="book-title">${product[1].productName}</p>
						<p>${product[1].authorName}</p>
							<form action="CartAddServlet" method="post">
					            <input type="hidden" name="isbn" value="${product[1].isbn}">
					            <!--<input type="hidden" name="isbn" value=isbn>-->
					            <input type="hidden" name="count" value="1">
					            <input type="submit" class="cart-btn" value="カートに入れる">
							</form>
					</li>
					<li>
						<p id="book-title"><img src="${product[2].isbn}"></p>
						<p id="book-title">${product[2].productName}</p>
						<p>${product[2].authorName}</p>
							<form action="CartAddServlet" method="post">
					            <input type="hidden" name="isbn" value="${product[2].isbn}">
					            <!--<input type="hidden" name="isbn" value=isbn>-->
					            <input type="hidden" name="count" value="1">
					            <input type="submit" class="cart-btn" value="カートに入れる">
							</form>
					</li>
				</ul>
			</div>
			<!-- ユーザレコメンド ここまで -->


</body>
</html>