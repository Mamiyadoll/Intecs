<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="beans.ProductBean" import="beans.UserBean"
	import="java.util.*"%>
<html lang="jp">
<head>

<link rel="stylesheet" href="files/main.css">
<link rel="stylesheet" href="files/result.css">

<meta charset="UTF-8">

<title>検索結果 | Intecs オンラインストア</title>
<link rel="stylesheet" href="css/main.css">

</head>

<%@include file="header.jsp"%>
<body>

	<%
			if (request.getAttribute("errorMessage") != "") {
				out.print(request.getAttribute("errorMessage"));
			}
		%>
	<div class="wrapper">
		検索キーワード:${requestScope.keyword} に一致する商品
		検索結果：${requestScope.number}件
		<hr>

		<%
		List<ProductBean> product = (List<ProductBean>) request.getAttribute("product");
		for (ProductBean item : product) {
	%>
		<div class="book-box">
			<div class="book-img">
				<img
					src="${pageContext.request.contextPath}/productImg/${product.isbn}.jpg">
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
					</select> <input type="submit" class="button" value="カートに入れる">
				</form>
			</div>
		</div>
		<hr>


	</div>
</body>
</html>