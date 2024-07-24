package main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import tool.Action;

public class StudentUpdateExecuteAction extends Action {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		HttpSession session = req.getSession();// セッション
		Teacher teacher = (Teacher)session.getAttribute("session_user");

		boolean line = false;

		Student student = null;
		int entYear = 0;
		String no = null;
		String name = null;
		String classNum = null;
		boolean isAttend = false;
		StudentDao sDao = new StudentDao();// 学生Dao

		no = req.getParameter("no");
		name = req.getParameter("name");
		classNum = req.getParameter("classNum");
		if (req.getParameter("isAttend") != null) {
			isAttend = true;
		}

		student = new Student();
		student.setNo(no);
		student.setName(name);
		student.setEntYear(entYear);
		student.setClassNum(classNum);
		student.setAttend(isAttend);
		student.setSchool(teacher.getSchool());

		line=sDao.save(student);


		if(line){
			return "student_update_done.jsp";
		} else {
			return "StudentUpdate.action";
		}

	}
}
