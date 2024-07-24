package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.Teacher;;

public class TeacherDao extends Dao {
	public Teacher search(String id,String password) throws Exception {
		Teacher teacher = null;

		Connection con = getConnection();

		PreparedStatement st;
		st = con.prepareStatement("select * from teacher where id = ? and password = ?");
		st.setString(1, id);
		st.setString(2, password);
		ResultSet rs = st.executeQuery();

		SchoolDao schoolDao = new SchoolDao();

		if (rs.next()) {
			teacher = new Teacher();
			teacher.setId(rs.getString("id"));
			teacher.setName(rs.getString("name"));
			teacher.setPassword(rs.getString("password"));
			teacher.setSchool(schoolDao.get(rs.getString("school_cd")));
		}

		st.close();
		con.close();

		return teacher;
	}
}
