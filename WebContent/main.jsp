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
			        <p id="book-title">
			        <a href="BookDetailServlet?isbn=${recomend[0].isbn}"><img src="${recomend[0]isbn}"></a></p>
			        <p id="book-title"><a href="BookDetailServlet?isbn=${newBooks[0].isbn}">${recomend[0].bookname}</a></p>
					<p>${recomend[0].authorName}</p>
					<form action="CartServlet" method="get">
			            <input type="hidden" name="isbn" value="${recomend[0].isbn}">
			            <!--<input type="hidden" name="isbn" value=isbn>-->
			            <input type="hidden" name="count" value="1">
			            <input type="submit" class="cart-btn" value="カートに入れる">
					</form>
			        </li>
					<li>
					<p id="book-title">
					<a href="BookDetailServlet?isbn=${recomend[1].isbn}"><img src="${recomend[1]isbn}"></a></p>
					<p id="book-title"><a href="BookDetailServlet?isbn=${recomend[1].isbn}">${recomend[1].bookname}</a></p>
					<p>${recomend[1].authorName}</p>
					<form action="CartServlet" method="get">
			            <input type="hidden" name="isbn" value="${recomend[1].isbn}">
			            <!--<input type="hidden" name="isbn" value=isbn>-->
			            <input type="hidden" name="count" value="1">
			            <input type="submit" class="cart-btn" value="カートに入れる">
					</form>
					</li>
					<li>
					<p id="book-title">
					<a href="BookDetailServlet?isbn=${recomend[2].isbn}"><img src="${recomend[2]isbn}"></a></p>
					<p id="book-title"><a href="BookDetailServlet?isbn=${recomend[2].isbn}">${recomend[2].bookname}</a></p>
					<p>${recomend[2].authorName}</p>
					<form action="CartServlet" method="get">
			            <input type="hidden" name="isbn" value="${recomend[2].isbn}">
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