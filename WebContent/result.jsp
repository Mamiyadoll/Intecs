<<<<<<< HEAD
<%@ page language="java" contentType="text/html;charset=UTF-8"
=======
<%@ page language="java" contentType="text/html; charset=UTF-8"
>>>>>>> ff0719c97d53ccd7a9f36978c3090a0a5a43c989
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="beans.ProductBean" import="beans.UserBean"
	import="java.util.*"%>
<html lang="jp">
<head>

<link rel="stylesheet" href="files/main.css">
<link rel="stylesheet" href="files/result.css">

<meta charset="UTF-8">
<<<<<<< HEAD
<title>検索結果 | Intecs オンラインストア</title>
<link rel="stylesheet" href="css/main.css">
=======
<title>検索結果 | Intecsオンラインストア</title>
>>>>>>> ff0719c97d53ccd7a9f36978c3090a0a5a43c989
</head>

<%@include file="header.jsp"%>
<body>
	<div class="wrapper">

<<<<<<< HEAD
		検索結果：${requestScope.count}件
		<hr>

		<%
			List<BookBean> book = (List<BookBean>) request.getAttribute("list");
			for (BookBean item : book) {
		%>
		<div class="book-box">
			<div class="book-img">
				<a href="BookDetailServlet?isbn=<%=item.getIsbn()%>"> <img
					src="<%=item.getImg()%>" class="hon">
				</a>
			</div>
			<div class="book-text">
				<div>
					<div class="book-title">
						<p class="subtitle" id="subtitle">
							著作名：
							<%=item.getBookname()%>
						<p>
							著者名：
							<%=item.getAuthorName()%><br> 価格：税込
							<%=item.getPrice()%>円
					</div>

					<form action="" method="">
						<input type="hidden" name="isbn" value="<%=item.getIsbn()%>">
						<input type="hidden" name="count" value="1"> <input
							type="submit" class="cart-btn" id="cart-btn" value="カートへ入れる">
=======
	検索結果：${requestScope.number}件
	<hr>

	<%
		List<ProductBean> product = (List<ProductBean>) request.getAttribute("product");
		for (ProductBean item : product) {
	%>
	<div class="book-box">
		<div class="book-img">
			<img src="${pageContext.request.contextPath}/productImg/${product.isbn}.jpg">
		</div>
		<div class="book-text">
			<div class="book-title">
				<p class="subtitle" id="subtitle">
					著作名：
					<%=item.getProductName()%>
				<p>
					著者名：
					<%=item.getAuthorName()%><br> 価格：税込
					<%=item.getPrice()%>円
			</div>

			<form action="CartAddServlet" method="post">
				<input type="hidden" name="isbn" value="<%=item.getIsbn()%>">
				数量 <select name="number">
					<option value="1" selected>1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
				</select>
				<input type="submit" class="button" value="カートに入れる">
			</form>
		</div>
	</div>
	<hr>
	<%
		}
	%>
>>>>>>> ff0719c97d53ccd7a9f36978c3090a0a5a43c989

					</form>
				</div>
			</div>
		</div>
		<hr>
		<%
			}
		%>

	</div>
</body>
</html>