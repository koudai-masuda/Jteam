<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>得点管理システム</title>
		<link rel="stylesheet" href="../base.css"/>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
	</head>

	<body class="body">
		<header class="header">
			<h1 class="title">得点管理システム</h1>

			<c:if test="${session_user != null}">
				<div class="after-login-menu">
					<span>${session_user.name}様</span>
					<a href="Logout.action">ログアウト</a>
				</div>
			</c:if>
		</header>


		<div class="container">
			<div class="side-bar">

			<c:if test="${session_user != null}">
				<c:import url="/menu.jsp"/>
			</c:if>
			</div>
			<div class="main-contents">
				${ param.body }
			</div>
		</div>


		<footer class="footer">
			<p class="copyright">&copy; 2023 TIC<br>大原学園</p>
		</footer>
	</body>
</html>