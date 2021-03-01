package com.up.manage.dao;

import com.up.manage.base.BaseDAO;
import com.up.manage.model.MANAGE;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * 模块说明： 需求增删改查
 * 
 */
public class MANAGEDAO extends BaseDAO {
	private final int fieldNum = 9;
	private final int showNum = 15;
	private static MANAGEDAO sd = null;

	public static synchronized MANAGEDAO getInstance() {
		if (sd == null) {
			sd = new MANAGEDAO();
		}
		return sd;
	}

	// update
	public boolean update(MANAGE man) {
		boolean result = false;
		if (man == null) {
			return result;
		}
		try {
			// check
			if (queryByContent(man.getContent()) == 0) {
				return result;
			}
			// update
			String sql = "update manage set time=?,developer=?,test=?,outcome=?,method=?,period=? where urgent=? and content=?";
			String[] param = { man.getTime(), man.getDeveloper(), man.getTest(), man.getOutcome(), man.getMethod(),
					man.getPeriod(), man.getUrgent(), man.getContent() };
			int rowCount = db.executeUpdate(sql, param);
			if (rowCount == 1) {
				result = true;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			destroy();
		}
		return result;
	}

	// delete
	public boolean delete(MANAGE man) {
		boolean result = false;
		if (man == null) {
			return result;
		}
		String sql = "delete from manage where content=? and urgent=?";
		String[] param = { man.getUrgent(), man.getContent() };
		int rowCount = db.executeUpdate(sql, param);
		if (rowCount == 1) {
			result = true;
		}
		destroy();
		return result;
	}

	// add
	public boolean add(MANAGE man) {
		boolean result = false;
		if (man == null) {
			return result;
		}
		try {
			// check
			if (queryByContent(man.getContent()) == 1) {
				return result;
			}
			// insert
			String sql = "insert into manage(urgent,content,time,developer,method,period,test,outcome) values(?,?,?,?,?,?,?,?)";
			String[] param = { man.getUrgent(), man.getContent(), man.getTime(), man.getDeveloper(), man.getMethod(),
					man.getPeriod(), man.getTest(), man.getOutcome() };
			if (db.executeUpdate(sql, param) == 1) {
				result = true;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			destroy();
		}
		return result;
	}

	// query by urgent
	public String[][] queryByUrgent(String urgent) {
		String[][] result = null;
		if (urgent.length() < 0) {
			return result;
		}
		List<MANAGE> mans = new ArrayList<MANAGE>();
		int i = 0;
		String sql = "select * from manage where urgent like ?";
		String[] param = { "%" + urgent + "%" };
		rs = db.executeQuery(sql, param);
		try {
			while (rs.next()) {
				buildList(rs, mans, i);
				i++;
			}
			if (mans.size() > 0) {
				result = new String[mans.size()][fieldNum];
				for (int j = 0; j < mans.size(); j++) {
					buildResult(result, mans, j);
				}
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			destroy();
		}

		return result;
	}

	// query
	public String[][] list(int pageNum) {
		String[][] result = null;
		if (pageNum < 1) {
			return result;
		}
		List<MANAGE> mans = new ArrayList<MANAGE>();
		int i = 0;
		int beginNum = (pageNum - 1) * showNum;
		String sql = "select * from manage limit ?,? ";
		Object [] param = {beginNum, showNum};
		rs = db.executeQuery(sql, param);
		try {
			while (rs.next()) {
				buildList(rs, mans, i);
				i++;
			}
			if (mans.size() > 0) {
				result = new String[mans.size()][fieldNum];
				for (int j = 0; j < mans.size(); j++) {
					buildResult(result, mans, j);
				}
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			destroy();
		}

		return result;
	}

	// 将rs记录添加到list中
	private void buildList(ResultSet rs, List<MANAGE> list, int i) throws SQLException {
		MANAGE man = new MANAGE();
		man.setId(i + 1);
		man.setUrgent(rs.getString("urgent"));
		man.setDeveloper(rs.getString("developer"));
		man.setTest(rs.getString("test"));
		man.setMethod(rs.getString("method"));
		man.setPeriod(rs.getString("period"));
		man.setTime(rs.getString("time"));
		man.setContent(rs.getString("content"));
		man.setOutcome(rs.getString("outcome"));
		list.add(man);
	}

	// 将list中记录添加到二维数组中
	private void buildResult(String[][] result, List<MANAGE> mans, int j) {
		MANAGE man = mans.get(j);
		result[j][0] = String.valueOf(man.getId());
		result[j][1] = man.getUrgent();
		result[j][2] = man.getContent();
		result[j][3] = man.getTime();
		result[j][4] = man.getDeveloper();
		result[j][5] = man.getMethod();
		result[j][6] = man.getPeriod();
		result[j][7] = man.getTest();
		result[j][8] = man.getOutcome();
	}

	// query by content
	private int queryByContent(String content) throws SQLException {
		int result = 0;
		if ("".equals(content) || content == null) {
			return result;
		}
		String checkSql = "select * from manage where content=?";
		String[] checkParam = { content };
		rs = db.executeQuery(checkSql, checkParam);
		if (rs.next()) {    //表的第一行不为空
			result = 1;
		}
		return result;
	}

}
