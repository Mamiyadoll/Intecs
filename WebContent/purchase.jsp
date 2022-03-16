<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="jp">
<head>
<meta charset="UTF-8">
<title>購入手続き | Intecs オンラインストア</title>
<link rel="stylesheet" href="css/main.css">
<script src="${pageContext.request.contextPath}/js/library.js"></script>
</head>

<body>
	<div class="wrapper">
		<%@ include file="header.jsp"%>
		<h2 class="headline">購入手続き</h2>

		<p id="err_chk" style="color: red;">
			<%
				if (request.getAttribute("errorMessage") != "") {
					out.print(request.getAttribute("errorMessage"));
				}
			%>
		</p>

		<hr>
		<c:forEach items="${cart}" var="u" varStatus="s">
			<div class="purchaseImg">
				<img
					src="${pageContext.request.contextPath}/productImg/${u.isbn}.jpg">
			</div>
			<div class="purchaseName">
				<p>${u.productName}</p>
			</div>
			<hr>
			<div class="quantity">
				<p>数量：${u.quantity}</p>
			</div>
			<div class="productPrice">
				<p>価格：${u.price}</p>
			</div>
		</c:forEach>
		<hr>
		<p>合計：${totalprice}</p>
		<div class="postCode">
			<p>送付先：〒${user.postCode}</p>
		</div>
		<div class="address">
			<p>${user.address}</p>
		</div>
		<div class="button-position">
			<div class="button-center">
		<form action="PurchaseServlet" method="post">
			<input type="submit" name="purchase" value="購入を確定" class="button">
		</form>
		<form action="CartServlet" method="post">
			<input type="submit" name="cartBack" value="戻る" class="button">
		</form>
		</div>
		</div>
	</div>
	<div class="copyright ">Copyright © 2022 Intecs Co., Ltd. All
		righys reserved.</div>
</body>
</html>