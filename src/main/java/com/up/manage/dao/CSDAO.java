
package com.up.manage.dao;

import com.up.manage.base.BaseDAO;

import java.sql.SQLException;

/**
 * 模块说明： 测试增删改查
 * 
 */
public class CSDAO extends BaseDAO {

	private static CSDAO ad = null;

	public static synchronized CSDAO getInstance() {
		if (ad == null) {
			ad = new CSDAO();
		}
		return ad;
	}

	public boolean queryForLogin(String username, String password) {
		boolean result = false;
		if (username.length() == 0 || password.length() == 0) {
			return result;
		}
		String sql = "select * from cs where username=? and password=?";
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
