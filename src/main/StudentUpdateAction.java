package main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

public class StudentUpdateAction extends Action {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		HttpSession session = req.getSession();// セッション
		Teacher teacher = (Teacher)session.getAttribute("session_user");

		String no = req.getParameter("no");

		StudentDao sdao = new StudentDao();
		Student student = sdao.get(no);

		ClassNumDao cNumDao = new ClassNumDao();// クラス番号Dao

		List<String> list = cNumDao.filter(teacher.getSchool());

		req.setAttribute("student", student);
		req.setAttribute("class_num_set", list);

		return "student_update.jsp";
	}
}
