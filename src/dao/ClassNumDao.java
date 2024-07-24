package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;

public class ClassNumDao extends Dao {
	public List<String> filter(School school) throws Exception{
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement(
				"select * from class_num where school_cd = ? order by class_num");
		st.setString(1, school.getCd());

		ResultSet rs = st.executeQuery();

		List<String> list = new ArrayList<>();
		while(rs.next()){
			list.add(rs.getString("class_num"));
		}

		st.close();
		con.close();
		return list;
	}
}
