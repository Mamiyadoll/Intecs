<%@ page language="java" contentType = "text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="jp">
<head>
<meta charset="UTF-8">
<title>登録内容確認</title>
</head>
<body>

	<%
	String loginId = request.getParameter("loginId");
	String userName = request.getParameter("userName");
	String postCode = request.getParameter("postCode");
	String address = request.getParameter("address");
	String tel = request.getParameter("tel");
	String mail = request.getParameter("mail");
	String pass = request.getParameter("password");
	%>
<h3>ユーザ名(英数字)</h3>
<p><%out.println(loginId);%></p>
<h3>password</h3>
<p><%out.println(pass);%></p>
<h3>氏名</h3>]
<p><%out.println(userName);%></p>
<h3>郵便番号</h3>
<p><%out.println(postCode);%></p>
<h3>住所</h3>
<p><%out.println(address);%></p>
<h3>電話番号</h3>
<p><%out.println(postCode);%></p>
<h3>メールアドレス</h3>
<p><%out.println(mail);%></p>
<form action="SignUpServlet" method="post">
<input type="submit" name="signup" value="登録" class="button">
</form>
<button onclick="history.back()" class="button">戻る</button>
</body>
