package main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.TeacherDao;
import tool.Action;

public class LoginTestAction extends Action {
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		HttpSession session = req.getSession();

		String id = "admin1";
		String password = "password";
		TeacherDao dao = new TeacherDao();
		Teacher teacher = dao.search(id, password);

		if (teacher != null) {
			session.setAttribute("session_user", teacher);
			return "MenuTest.jsp";
		}else {
			return "MenuTest.jsp";
		}
	}
}
