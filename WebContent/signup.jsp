<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="jp">
<head>
<meta charset="UTF-8">
<title>新規会員登録 | Intecsオンラインストア</title>
</head>
<body>
<!-- header -->
<%@ include file="header.jsp"%>

			<p style="color:red;">
		<%
			if(request.getAttribute("errorMessage")!=null){
				out.print(request.getAttribute("errorMessage"));
			}
		%>
		</p>

<br>
	<form action="SignupServlet" method="post">
	ユーザー名（英数字）<br>
		<input type="text" style="text-align: center;" name="id" placeholder="ユーザー名" class="box" pattern="^[0-9A-Za-z]+$" required><br>
	Password(8文字以上、半角英数字小文字大文字、半角数字を必ず含むこと)<br>
		<input type="password" style="text-align: center;"  class="box" name="password" placeholder="パスワード" pattern="(?=.*?[a-z])(?=.*?[A-Z])(?=.*?\d)[a-zA-Z\d]{8,}"" pattern=".*\S+.*" required><br>
	氏名<br>
		<input type="text" style="text-align: center;" placeholder="氏名" class="box" name="name" required><br>
	郵便番号(ハイフンなしで入力)<br>
		<input type="text" style="text-align: center;" class="box" placeholder="郵便番号" name="postcode" pattern="^\d{7}$" required><br>
	住所<br>
		<input type="text" style="text-align: center;" placeholder="住所" class="box" name="address" required><br>
	電話番号(ハイフンなしで入力) <br>
		<input type="text" style="text-align: center;" class="box" name="tel" placeholder="電話番号" pattern="^0\d{9,10}$" required><br>
	メールアドレス<br>
		<input type="email" style="text-align: center;" class="box" placeholder="メールアドレス" name="mail" required><br>
		<input type="hidden" name="userFlg" value="0">
		<input type="submit"  class="btn" value="登録する">
	</form>

		<!-- footer -->
		<%@ include file="footer.jsp"%>

</body>
</html>