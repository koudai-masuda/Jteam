package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.School;

public class SchoolDao extends Dao {
	public School get(String cd) throws Exception {
		School school = new School();
		Connection con = getConnection();
		PreparedStatement st = null;

		st = con.prepareStatement("select * from school where cd = ?");
		st.setString(1, cd);
		ResultSet rs = st.executeQuery();

		if (rs.next()) {
			school.setCd(rs.getString("cd"));
			school.setName(rs.getString("name"));
		}

		st.close();
		con.close();

		return school;
	}
}
