
package com.up.manage.base;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.up.manage.DAO;
import com.up.manage.dao.AdminDAO;
import com.up.manage.dao.CSDAO;
import com.up.manage.dao.CXYDAO;
import com.up.manage.dao.MANAGEDAO;
import com.up.manage.util.DBUtil;

/**
 * 模块说明： DAO基类
 * 
 */
public abstract class BaseDAO {
	protected final DBUtil db = DBUtil.getDBUtil();
	protected ResultSet rs;
	private static BaseDAO baseDAO;

	public BaseDAO() {
		init();
	}

	private void init() {
		// buildAbilityDAO();
	}

	// protected abstract void buildAbilityDAO();

	public static synchronized BaseDAO getAbilityDAO(DAO dao) {
		switch (dao) {
		case AdminDAO:
			if (baseDAO == null || baseDAO.getClass() != AdminDAO.class) {
				baseDAO = AdminDAO.getInstance();
			}
			break;
		case MANAGEDAO:
			if (baseDAO == null || baseDAO.getClass() != MANAGEDAO.class) {
				baseDAO = MANAGEDAO.getInstance();
			}
			break;
		case CXYDAO:
			if (baseDAO == null || baseDAO.getClass() != CXYDAO.class) {
				baseDAO = CXYDAO.getInstance();
			}
			break;
		case CSDAO:
			if (baseDAO == null || baseDAO.getClass() != CSDAO.class) {
				baseDAO = CSDAO.getInstance();
			}
				break;
		default:
			break;
		}
		return baseDAO;
	}

	protected void destroy() {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			db.close();
		}
	}
}
