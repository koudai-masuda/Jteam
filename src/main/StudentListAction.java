package main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

public class StudentListAction extends Action {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		HttpSession session = req.getSession();// セッション
		Teacher teacher = (Teacher)session.getAttribute("session_user");

		String entYearStr = "";// 入力された入学年度
		String classNum = "";// 入力されたクラス番号
		String isAttenStr = "";// 入力された在学フラグ
		int entYear = 0;// 入学年度
		boolean isAttend = false;//在学フラグ
		List<Student> students = null;// 学生リスト
		LocalDate todaysDate = LocalDate.now();// LocalDateインスタンスを取得
		int year = todaysDate.getYear();// 現在の年を取得
		StudentDao sDao = new StudentDao();// 学生Dao
		ClassNumDao cNumDao = new ClassNumDao();// クラス番号Dao
		Map<String,String> errors = new HashMap<>();// エラーメッセージ

		entYearStr = req.getParameter("f1");
		classNum = req.getParameter("f2");
		isAttenStr = req.getParameter("f3");

		if (entYearStr != "0" && entYearStr != null) {
			entYear = Integer.parseInt(entYearStr);
		}
		if (isAttenStr != null) {
			isAttend = true;
		}

		List<String> list = cNumDao.filter(teacher.getSchool());

		if (entYear != 0 && !classNum.equals("0")) {
			students = sDao.filter(teacher.getSchool(), entYear, classNum, isAttend);
		}else if (entYear != 0 && classNum.equals("0")) {
			students = sDao.filter(teacher.getSchool(), entYear, isAttend);
		}else if (entYear == 0 && classNum == null || entYear == 0 && classNum.equals("0")) {
			students = sDao.filter(teacher.getSchool(), isAttend);
		}else {
			errors.put("f1", "クラスを指定する場合は入学年度も指定してください");
			req.setAttribute("errors", errors);
			students = sDao.filter(teacher.getSchool(), isAttend);
		}


		int goalYear = year + 11;
		List<Integer> entYearSet = new ArrayList<>();
		for (int i = year - 10; i< goalYear; i++){
			entYearSet.add(i);
		}


		req.setAttribute("f1", entYear);
		req.setAttribute("f2", classNum);
		req.setAttribute("f3", isAttenStr);
		req.setAttribute("students", students);
		req.setAttribute("class_num_set", list);
		req.setAttribute("ent_year_set", entYearSet);

		return "student_list.jsp";
	}

}
