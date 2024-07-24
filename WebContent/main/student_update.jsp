<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/base.jsp">

	<c:param name="body">

		<section class="me-4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">学生情報変更</h2>

			<form method="post" action="StudentUpdateExecute.action">
				<div class="mb-3">
					<label class="form-label" for="student-ent-year">入学年度</label>
					<input type="text" class="form-control" id="student-ent-year"
						name="entYear" value="${student.entYear}" readonly>
				</div>

				<div class="mb-3">
					<label class="form-label" for="student-no">学生番号</label>
					<input type="text" class="form-control" id="student-no"
						name="no" value="${student.no}" readonly>
				</div>

				<div class="mb-3">
					<label class="form-label" for="student-name">氏名</label>
					<input type="text" class="form-control" id="student-name" value="${student.name}"
						maxlength="10" name="name" placeholder="氏名を入力してください" required>
				</div>

				<div class="mb-3">
					<label class="form-label" for="student-class-num">クラス</label>
					<select class="form-select" id="student-class-num" name="classNum">
						<c:forEach var="num" items="${class_num_set}">
							<option value="${num}" <c:if test="${num == student.classNum}">selected</c:if>>${num}</option>
						</c:forEach>
					</select>
				</div>

				<div class="mb-3 form-check">
					<label class="form-check-label" for="student-Attend">在学中
						<input type="checkbox" class="form-check-input" id="student-Attend" name="isAttend"
							<c:if test="${student.isAttend()}">checked="checked"</c:if>>
					</label>
				</div>

				<div class="mb-3">
					<input type="submit" value="変更" class="btn btn-secondary">
				</div>
			</form>

			<a href="StudentList.action">戻る</a>

		</section>

	</c:param>

</c:import>