package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jp.co.aforce.bean.LoginBean;

public class LoginDAO extends DAOBase{

	public boolean login(LoginBean bean) throws Exception {

		boolean isLogin = false;

		Connection con = getConnection();
		PreparedStatement stmt = con.prepareStatement("select id from login where id = ? AND hash_password = ?");
		stmt.setString(1, bean.getId());
		stmt.setString(2, bean.getHashPassword());

		ResultSet rs = stmt.executeQuery();

		while(rs.next()) {
			if(rs.getString("id").equals(bean.getId())) {
				System.out.println(rs.getString("id"));
				isLogin = true;
			}
		}

		stmt.close();
		con.close();

		return isLogin;
	}

	public int addUser(LoginBean bean) throws Exception {

		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement("INSERT INTO login values(?, ?)");
		st.setString(1, bean.getId());
		st.setString(2, bean.getHashPassword());
		int line = st.executeUpdate();

		st.close();
		con.close();

		return line;
	}

}
