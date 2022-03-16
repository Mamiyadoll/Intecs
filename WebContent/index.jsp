<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<link rel="stylesheet" href="files/index.css">

</head>

<!-- ここからBODY -->

<body>
	<%
		if (request.getAttribute("errorMessage") != "") {
			out.print(request.getAttribute("errorMessage"));
		}
	%>

	<main> <img src="img/img_mens_illustration@2x.png" width="20%"
		height="20%">
	<h1>
		もっとNICEな買い物を。&nbsp;<br>もっとNICEなときめきを。
		<wbr>
		<br>アプリでもオンラインストアでも、 <br> 皆さまのもっとすてきな日常を<br>お手伝いさせていただきます。
	</h1>

	<div class="white">
		<img src="img/img1.png ">

		<div class="parent">
			<div class="inline">
				<h3>Online&nbsp;Store</h3>
				<h4>
					新しく、使いやすくなった<br>Intecs&nbsp;の
					<wbr>
					オンラインストア。
				</h4>

				<a class="button" href="login.jsp ">お買い物する</a>
			</div>

			<div class="inline">
				<h3 class="headline ">Android&nbsp;App</h3>
				<h4>
					Intecs&nbsp;公式アプリ<br>ようこそ、インテックスへ
				</h4>
				<a class="button" href="login.jsp ">お買い物する</a>
			</div>
		</div>
	</div>
	</main>

	<footer>

		<div class="copyright ">Copyright © 2022 Intecs Co., Ltd. All
			righys reserved.</div>

	</footer>
</body>

</html>