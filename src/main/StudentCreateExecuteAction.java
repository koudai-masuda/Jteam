package main;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import tool.Action;

public class StudentCreateExecuteAction extends Action {

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
		StudentDao sDao = new StudentDao();// 学生Dao
		Map<String,String> errors = new HashMap<>();// エラーメッセージ

		entYear = Integer.parseInt(req.getParameter("entYear"));
		no = req.getParameter("no");
		name = req.getParameter("name");
		classNum = req.getParameter("classNum");

		if (entYear == 0) {
			req.setAttribute("no", no);
			req.setAttribute("name", name);
			req.setAttribute("classNum", classNum);

			errors.put("entYear", "入学年度を選択してください");
			req.setAttribute("errors", errors);

			return "StudentCreate.action";

		}else if (sDao.get(no) != null) {
			errors.put("no", "学生番号が重複しています");
			req.setAttribute("errors", errors);
			return "StudentCreate.action";

		}else {
			student = new Student();
			student.setNo(no);
			student.setName(name);
			student.setEntYear(entYear);
			student.setClassNum(classNum);
			student.setAttend(true);
			student.setSchool(teacher.getSchool());

			line=sDao.save(student);
		}

		if(line){
			return "student_create_done.jsp";
		} else {
			return "StudentCreate.action";
		}

	}
}
