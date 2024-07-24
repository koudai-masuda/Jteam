<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/base.jsp">

	<c:param name="body">

		<section class="me-4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">学生登録情報</h2>

			<form method="post" action="StudentCreateExecute.action">
				<div class="mb-3">
					<label class="form-label" for="student-ent-year">入学年度</label>
					<select class="form-select" id="student-ent-year" name="entYear">
						<option value="0">---------</option>
						<c:forEach var="year" items="${ent_year_set}">
							<option value="${year}">${year}</option>
						</c:forEach>
					</select>
					<div class="text-warning">${errors.get("entYear")}</div>
				</div>

				<div class="mb-3">
					<label class="form-label" for="student-no">学生番号</label>
					<input type="text" class="form-control" id="student-no" value="${no}"
						maxlength="10" name="no" placeholder="学生番号を入力してください" required>
					<div class="text-warning">${errors.get("no")}</div>
				</div>

				<div class="mb-3">
					<label class="form-label" for="student-name">氏名</label>
					<input type="text" class="form-control" id="student-name" value="${name}"
						maxlength="10" name="name" placeholder="氏名を入力してください" required>
				</div>

				<div class="mb-3">
					<label class="form-label" for="student-class-num">クラス</label>
					<select class="form-select" id="student-class-num" name="classNum">
						<c:forEach var="num" items="${class_num_set}">
							<option value="${num}" <c:if test="${num == classNum}">selected</c:if>>${num}</option>
						</c:forEach>
					</select>
				</div>

				<div class="mb-3">
					<input type="submit" value="登録して終了" class="btn btn-secondary">
				</div>
			</form>

			<a href="StudentList.action">戻る</a>

		</section>

	</c:param>

</c:import>