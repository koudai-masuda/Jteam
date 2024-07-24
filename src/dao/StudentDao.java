package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;

public class StudentDao extends Dao {
	private String bassSql = "select * from student where school_cd = ? ";

	public Student get(String no) throws Exception {
		Student student = null;
		Connection con = getConnection();
		PreparedStatement st = null;

		st = con.prepareStatement("select * from student where no = ?");
		st.setString(1, no);
		ResultSet rs = st.executeQuery();

		SchoolDao schoolDao = new SchoolDao();

		if (rs.next()) {
			student = new Student();
			student.setNo(rs.getString("no"));
			student.setName(rs.getString("name"));
			student.setEntYear(rs.getInt("ent_year"));
			student.setClassNum(rs.getString("class_num"));
			student.setAttend(rs.getBoolean("is_attend"));
			student.setSchool(schoolDao.get(rs.getString("school_cd")));
		}

		st.close();
		con.close();

		return student;
	}

	public boolean save(Student student) throws Exception {
		StudentDao dao = new StudentDao();
		Connection con = getConnection();
		PreparedStatement st =null;

		int count = 0;

		if(dao.get(student.getNo()) != null){
			st = con.prepareStatement(
				"update student set name = ?,class_num = ?,is_attend = ? where no= ?");
			st.setString(1, student.getName());
			st.setString(2,student.getClassNum());
			st.setBoolean(3, student.isAttend());
			st.setString(4,student.getNo());
			st.executeUpdate();
			count = 1;


		}else{
			st = con.prepareStatement(
				"insert into student values(?, ?, ?, ?, ?, ?)");

			st.setString(1, student.getNo());
			st.setString(2, student.getName());
			st.setInt(3, student.getEntYear());
			st.setString(4, student.getClassNum());
			st.setBoolean(5, student.isAttend());
			st.setString(6, student.getSchool().getCd());
			st.executeUpdate();
			count = 1;

		}

		st.close();
		con.close();

		if (count > 0){
			return true;
		}else {
			return false;
		}
	}

	public List<Student> postFilter(ResultSet rSet,School school) throws Exception{
		List<Student> list = new ArrayList<>();
		while(rSet.next()){
			Student s = new Student();

			s.setNo(rSet.getString("no"));
			s.setName(rSet.getString("name"));
			s.setEntYear(rSet.getInt("ent_year"));
			s.setClassNum(rSet.getString("class_num"));
			s.setAttend(rSet.getBoolean("is_attend"));
			s.setSchool(school);
			list.add(s);
		}

		return list;
	}

//	学生リスト条件別
//	111?
	public List<Student> filter(School school,int entYear,String classNum,boolean isAttend) throws Exception{
		List<Student> list = new ArrayList<>();
		Connection con = getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;

		String condition = "and ent_year = ? and class_num = ?";
		String order = " order by no";
		String conditionIsAttend = "";
		if (isAttend) {
			conditionIsAttend = " and is_attend = true";
		}

		st = con.prepareStatement(bassSql + condition + conditionIsAttend + order);
		st.setString(1, school.getCd());
		st.setInt(2,entYear);
		st.setString(3, classNum);
		rs = st.executeQuery();

		list = postFilter(rs, school);

		st.close();
		con.close();

		return list;
	}
//	110?
	public List<Student> filter(School school,int entYear,boolean isAttend) throws Exception{
		List<Student> list = new ArrayList<>();
		Connection con = getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;

		String condition = "and ent_year = ?";
		String order = " order by no";
		String conditionIsAttend = "";
		if (isAttend) {
			conditionIsAttend = " and is_attend = true";
		}

		st = con.prepareStatement(bassSql + condition + conditionIsAttend + order);
		st.setString(1, school.getCd());
		st.setInt(2,entYear);
		rs = st.executeQuery();

		list = postFilter(rs, school);

		st.close();
		con.close();

		return list;
	}
//	100?
	public List<Student> filter(School school,boolean isAttend) throws Exception{
		List<Student> list = new ArrayList<>();
		Connection con = getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;

		String order = " order by no";
		String conditionIsAttend = "";
		if (isAttend) {
			conditionIsAttend = " and is_attend = true";
		}

		st = con.prepareStatement(bassSql + conditionIsAttend + order);
		st.setString(1, school.getCd());
		rs = st.executeQuery();

		list = postFilter(rs, school);

		st.close();
		con.close();

		return list;
	}
}
