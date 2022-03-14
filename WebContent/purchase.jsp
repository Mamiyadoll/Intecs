<%@ page language="java" contentType = "text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="jp">
<head>
<meta charset="UTF-8">
<title>購入手続き</title>
<link rel="stylesheet" href="css/main.css">
<script src="${pageContext.request.contextPath}/js/library.js"></script>
</head>
<body>
<%@ include file="header.jsp"%>
<h1>購入手続き</h1>
<hr>
<c:forEach items="${product}" var="u" varStatus="s">
<div class="purchaseImg">
<img src="${pageContext.request.contextPath}/productImg/${u.isbn}.jpg">
</div>
<div class="purchaseName">
<p>${u.productName}</p>
</div>
<hr>
<div class="quantity">
<p>数量：${cart.quantity}</p>
</div>
<div class="productPrice">
<p>価格：${u.productPrice}</p>
</div>
</c:forEach>
<hr>
<p>合計：${u.totalprice}</p>
<div class="postCode">
<p>送付先：〒${user.postCode}</p>
</div>
<div class="address">
<p>${user.address}</p>
</div>
<form action="PurchaseServlet" method="post">
<input type="submit" name="purchase" value="購入を確定する" class="button">
</form>
<form action="CartServlet" method="post">
<input type="submit" name="cartBack" value="戻る" class="button">
</form>
</body>
</html>