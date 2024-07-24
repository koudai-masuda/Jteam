<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav>
	<ul class="side-menu">
		<li><a href="Menu.action">メニュー</a></li>
		<li><a href="StudentList.action">学生管理</a></li>
		<li>成績管理
			<ul>
			<li><a href="TestRegist.action">成績登録</a></li>
			<li><a href="TestList.action">成績参照</a></li>
			</ul>
		</li>
		<li><a href="SubjectList.action">科目管理</a></li>
	</ul>
</nav>