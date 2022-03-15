<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="jp">
<head>
<meta charset="UTF-8">
<title>登録内容確認 | Intecs オンラインストア</title>
<link rel="stylesheet" href="css/main.css">
</head>
<%@ include file="header.jsp"%>
<body>
	<div class="wrapper">
		<header>
		<div class="header__inner">
			<h1>
				<a href="main.jsp"><img src="img/logo.svg" alt="株式会社インテックス "></a>
			</h1>
		</div>
	</header>

<<<<<<< HEAD
		<%
			String loginId = request.getParameter("loginId");
			String userName = request.getParameter("userName");
			String postCode = request.getParameter("postCode");
			String address = request.getParameter("address");
			String tel = request.getParameter("tel");
			String mail = request.getParameter("mail");
			String pass = request.getParameter("password");
		%>

		<h2 class="headline">登録内容確認</h2>
		<form action="SignUpServlet" method="post">

			<h3>ユーザ名(英数字)</h3>
			<p>
				<%
					out.println(loginId);
				%>
				<input type="hidden" name="loginId" value="<%=loginId%>>">
			</p>
			<h3>password</h3>
			<p>
				<%
					out.println(pass);
				%>
				<input type="hidden" name="password" value="<%=pass%>>">
			</p>
			<h3>氏名</h3>
			]
			<p>
				<%
					out.println(userName);
				%>
				<input type="hidden" name="userName" value="<%=userName%>>">
			</p>
			<h3>郵便番号</h3>
			<p>
				<%
					out.println(postCode);
				%>
				<input type="hidden" name="postCode" value="<%=postCode%>>">
			</p>
			<h3>住所</h3>
			<p>
				<%
					out.println(address);
				%>
				<input type="hidden" name="address" value="<%=address%>>">
			</p>
			<h3>電話番号</h3>
			<p>
				<%
					out.println(tel);
				%>
				<input type="hidden" name="tel" value="<%=tel%>>">
			</p>
			<h3>メールアドレス</h3>
			<p>
				<%
					out.println(mail);
				%>
				<input type="hidden" name="mail" value="<%=mail%>>">
			</p>

			<input type="submit" name="signup" value="登録" class="button">
		</form>
		<button onclick="history.back()" class="button">戻る</button>
	</div>
=======
	<%
		String loginId = request.getParameter("loginId");
		String userName = request.getParameter("userName");
		String postCode = request.getParameter("postCode");
		String address = request.getParameter("address");
		String tel = request.getParameter("tel");
		String mail = request.getParameter("mail");
		String pass = request.getParameter("password");
	%>

	<form action="SignUpServlet" method="post">

		<h3>ユーザ名(英数字)</h3>
		<p>
			<%
				out.println(loginId);
			%>
			<input type="hidden" name="loginId" value="<%=loginId%>>">
		</p>
		<h3>password</h3>
		<p>
			<%
				out.println(pass);
			%>
			<input type="hidden" name="password" value="<%=pass%>>">
		</p>
		<h3>氏名</h3>
		]
		<p>
			<%
				out.println(userName);
			%>
			<input type="hidden" name="userName" value="<%=userName%>>">
		</p>
		<h3>郵便番号</h3>
		<p>
			<%
				out.println(postCode);
			%>
			<input type="hidden" name="postCode" value="<%=postCode%>>">
		</p>
		<h3>住所</h3>
		<p>
			<%
				out.println(address);
			%>
			<input type="hidden" name="address" value="<%=address%>>">
		</p>
		<h3>電話番号</h3>
		<p>
			<%
				out.println(tel);
			%>
			<input type="hidden" name="tel" value="<%=tel%>>">
		</p>
		<h3>メールアドレス</h3>
		<p>
			<%
				out.println(mail);
			%>
			<input type="hidden" name="mail" value="<%=mail%>>">
		</p>

		<input type="submit" name="signup" value="登録" class="button">
	</form>
	<button onclick="history.back()" class="button">戻る</button>
>>>>>>> ff0719c97d53ccd7a9f36978c3090a0a5a43c989
</body>