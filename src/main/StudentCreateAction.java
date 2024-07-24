package main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.ClassNumDao;
import tool.Action;

public class StudentCreateAction extends Action {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		HttpSession session = req.getSession();// セッション
		Teacher teacher = (Teacher)session.getAttribute("session_user");

		ClassNumDao cNumDao = new ClassNumDao();// クラス番号Dao

		LocalDate todaysDate = LocalDate.now();// LocalDateインスタンスを取得
		int year = todaysDate.getYear();// 現在の年を取得

		List<String> list = cNumDao.filter(teacher.getSchool());

		int goalYear = year + 11;
		List<Integer> entYearSet = new ArrayList<>();
		for (int i = year - 10; i< goalYear; i++){
			entYearSet.add(i);
		}

		req.setAttribute("class_num_set", list);
		req.setAttribute("ent_year_set", entYearSet);


		return "student_create.jsp";
	}
}
