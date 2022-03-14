<%@ page language="java" contentType = "text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="jp">
<head>
<meta charset="UTF-8">
<title>カート</title>
<link rel="stylesheet" href="files/main.css">
<script src="${pageContext.request.contextPath}/js/library.js"></script>
</head>
<body>
<h2 class="logo">Cart</h2>
<hr>
<c:forEach items="${cart}" var="u" varStatus="s">
<div class="purchaseImg">
<img src="${pageContext.request.contextPath}/productImg/${u.isbn}.jpg">
</div>
<div class="purchaseName">
<p>${u.productName}</p>
</div>
<div class="purchaseAuthor">
<p>${u.authorName}</p>
</div>
<div class="quantity">
<p>数量：${u.quantity}</p>
</div>
<div class="productPrice">
<p>価格：${u.price}</p>
</div>
</c:forEach>
<div class="totalPrice">
<p>合計：${totalPrice}円</p>
</div>
<hr>
<div class="button-position">
<form action="PurchaseConfirmServlet" method="post">
<input type="submit" name="purchase" value="購入手続き" class="button">
</form>

<form action="CartDeleteServlet" method="post">
<input type="submit" name="delete" value="全削除" class="button">
</form>
</div>
</body>
</html>