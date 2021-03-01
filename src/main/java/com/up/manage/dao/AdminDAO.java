
package com.up.manage.dao;

import java.sql.SQLException;

import com.up.manage.base.BaseDAO;
import com.up.manage.model.Admin;
/**
 * 模块说明： 管理员增删改查
 * 
 */
public class AdminDAO extends BaseDAO {

	private static AdminDAO ad = null;

	public static synchronized AdminDAO getInstance() {
		if (ad == null) {
			ad = new AdminDAO();
		}
		return ad;
	}

	public boolean queryForLogin(String username, String password) {
		boolean result = false;
		if (username.length() == 0 || password.length() == 0) {
			return result;
		}
		String sql = "select * from admin where username=? and password=?";
		String[] param = { username, password };
		rs = db.executeQuery(sql, param);
		try {
			if (rs.next()) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			destroy();
		}
		return result;
	}

}
