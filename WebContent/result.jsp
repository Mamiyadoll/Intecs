<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "beans.ProductBean"%>
<%@ page import = "java.util.List" %>
<!DOCTYPE html>
<html lang="jp">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	検索キーワード:${requestScope.keyword} に一致する商品
	 検索結果：${requestScope.number}件
            <hr>

                <%
                List<ProductBean> book = (List<ProductBean>)request.getAttribute("product");
                for (ProductBean item : book) {
                %>
                <div class="book-box">
                    <div class="book-img">
                            <img src="<%=item.getIsbn() %>" class="hon">
                    </div>
                    <div class="book-text">
                    	<div>
                        <div class="book-title">
                            <p class="subtitle" id="subtitle">著作名：
                            <%=item.getProductName()%><p>
                            著者名：
                            <%=item.getAuthorName()%><br>
                            価格：税込
                            <%=item.getPrice()%>円
                        </div>

                            <form action="CartAddServlet" method="post">
                                <input type="hidden" name="isbn" value="<%=item.getIsbn()%>">
                                <input type="hidden" name="count" value="1">
                                <input type="hidden" name="page" value="result">
                                <input type="submit" class="cart-btn" id="cart-btn" value="カートへ入れる">
                            </form>
                        </div>
                        </div>
                    </div>
                <hr>
                <% } %>


</body>
</html>