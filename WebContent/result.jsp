<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="jp">
<head>
<meta charset="UTF-8">
<title>検索結果 | Intecs オンラインストア</title>
<link rel="stylesheet" href="css/main.css">
</head>
<body>
	<div class="wrapper">

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