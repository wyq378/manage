
package com.up.manage.dao;

import com.up.manage.base.BaseDAO;

import java.sql.SQLException;

/**
 * 模块说明： 程序员增删改查
 * 
 */
public class CXYDAO extends BaseDAO {

	private static CXYDAO ad = null;

	public static synchronized CXYDAO getInstance() {
		if (ad == null) {
			ad = new CXYDAO();
		}
		return ad;
	}

	public boolean queryForLogin(String username, String password) {
		boolean result = false;
		if (username.length() == 0 || password.length() == 0) {
			return result;
		}
		String sql = "select * from cxy where username=? and password=?";
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
