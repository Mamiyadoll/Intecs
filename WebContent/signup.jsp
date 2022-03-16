<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="jp">
<head>
<meta charset="UTF-8">
<title>新規会員登録 | Intecs オンラインストア</title>
<script src="${pageContext.request.contextPath}/js/library.js"></script>
<link rel="stylesheet" href="files/main.css">
</head>
<body>
	<div class="wrapper">
	<header>
		<div class="header__inner">
			<h1>
				<a href="main.jsp"><img src="img/logo.svg" alt="株式会社インテックス "></a>
			</h1>
		</div>
	</header>
		<h2 class="headline">新規会員登録</h2>
		<hr>
		<div class="txt">
			<form action="signupConfirm.jsp" method="post" oninput="enableSubmit('signUpForm','salmon')" id="signUpForm">
				<h3>ユーザ名(英数字)</h3>
				<input type="text" name="loginId" id="loginId" maxlength="50"
					placeholder="半角英数8文字以上">
				<h3>Password</h3>
				<input type="password" name="password1" id="password1"
					maxlength="50" placeholder="半角英数8文字以上">
				<p id="pass_chk" style="display: none; color: red;">パスワードが半角英数8文字以上になっていません</p>
				<h3>氏名</h3>
				<input type="text" name="userName" id="userName" maxlength="100"
					placeholder="山田太郎">
				<h3>郵便番号</h3>
				<input type="text" name="postCode" id="postCode" maxlength="7"
					placeholder="ハイフンなし">
				<p id="yuubin_chk" style="display: none; color: red;">郵便番号が数字7桁になっていません</p>
				<h3>住所</h3>
				<input type="text" name="address" id="address" maxlength="200">
				<h3>電話番号</h3>
				<input type="text" name="tel" id="tel" maxlength="20"
					placeholder="ハイフンなし">
				<p id="phone_chk" style="display: none; color: red;">電話番号が形式に沿っていません</p>
				<h3>メールアドレス</h3>
				<input type="email" name="mail" id="mail" maxlength="254"
					placeholder="aaaa@gmail.com">
				<p id="mail_chk" style="display: none; color: red;">メールアドレスが形式に沿っていません</p>
				<br><input type="submit" value="会員登録" disabled onclick=" return check()"
					class="button">
			</form>
		</div>
	</div>
	<script>
		function check() {
			const loginId = document.getElementById("loginId").value;
			const userName = document.getElementById("userName").value;
			const postCode = document.getElementById("postCode").value;
			const address = document.getElementById("address").value;
			const tel = document.getElementById("tel").value;
			const mail = document.getElementById("mail").value;
			const password1 = document.getElementById("password1").value;

			// エラーフラグ
			var flag_chk = 0;

			// IDチェック
			var regexp = /^([a-zA-Z0-9]{8,})$/;
			if (regexp.test(loginId) != true) {
				document.getElementById('id_chk').style.display = "block";
				flag_chk = 1;
			} else {
				document.getElementById('id_chk').style.display = "none";
			}

			// 郵便番号チェック
			var regexp = /^\d{7}$/;
			if (regexp.test(postCode) != true) {
				document.getElementById('yuubin_chk').style.display = "block";
				flag_chk = 1;
			} else {
				document.getElementById('yuubin_chk').style.display = "none";
			}

			// 電話番号チェック
			var regexp = /^(0{1}\d{9,10})$/;
			if (regexp.test(tel) != true) {
				document.getElementById('phone_chk').style.display = "block";
				flag_chk = 1;
			} else {
				document.getElementById('phone_chk').style.display = "none";
			}

			// メールアドレスチェック
			var regexp = /^[A-Za-z0-9]{1}[A-Za-z0-9_.-]*@{1}[A-Za-z0-9_.-]{1,}\.[A-Za-z0-9]{1,}$/;
			if (regexp.test(mail) != true) {
				document.getElementById('mail_chk').style.display = "block";
				flag_chk = 1;
			} else {
				document.getElementById('mail_chk').style.display = "none";
			}

			// パスワードチェック
			var regexp = /^([a-zA-Z0-9]{8,})$/;
			if (regexp.test(password1) != true) {
				document.getElementById('pass_chk').style.display = "block";
				flag_chk = 1;
			} else {
				document.getElementById('pass_chk').style.display = "none";
			}

			//エラーフラグ判定
			if (flag_chk == 1) {
				return false;
			} else {
				return true;
			}
		}
	</script>

</body>
</html>