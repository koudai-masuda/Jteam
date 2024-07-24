<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/base.jsp">

	<c:param name="body">
		<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">学生登録情報</h2>

		<p class="mb-3 bg-success bg-opacity-75 text-center py-2 px-4">登録が完了しました</p>

		<a href="StudentCreate.action">戻る</a>
		<a href="StudentList.action" class="text-right">学生一覧</a>
	</c:param>

</c:import>