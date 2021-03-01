package com.up.manage;

/**
 * 模块说明： 常量
 *
 */
public class AppConstants {
	// jdbc
	public static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/manage?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone = GMT";
	public static final String JDBC_USERNAME = "root";
	public static final String JDBC_PASSWORD = "root";
	public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

	// manage field
	public static final String MANAGE_URGENT = "紧迫性";
	public static final String MANAGE_CONTENT = "具体内容";
	public static final String MANAGE_TIME = "最终上线时间";
	public static final String MANAGE_DEVELOPER = "开发人员";
	public static final String MANAGE_METHOD = "解决方法";
	public static final String MANAGE_PERIOD = "开发周期";
	public static final String MANAGE_TEST = "测试人员";
	public static final String MANAGE_OUTCOME = "测试结果";


	// login view
	public static final String LOGIN_TITLE = "登录界面";
	public static final String LOGIN_USERNAME = "用户名";
	public static final String LOGIN_PASSWORD = "密码";
	public static final String LOGIN = "登录";
	public static final String RESET = "重置";

	// main view
	public static final String MAINVIEW_TITLE = "产品需求管理系统";
	public static final String MAINVIEW_PAGENUM_JLABEL_DI = "第 ";
	public static final String MAINVIEW_PAGENUM_JLABEL_YE = "/99 页";
	public static final String MAINVIEW_FIND_JLABEL = "查询结果";
	public static final String MAINVIEW_FIRST = "首页";
	public static final String MAINVIEW_LAST = "末页";
	public static final String MAINVIEW_PRE = "上一页";
	public static final String MAINVIEW_NEXT = "下一页";
	public static final String PARAM_FIND_CONDITION = "";
	public static final String PARAM_FIND = "查找";
	public static final String PARAM_ADD = "添加";
	public static final String PARAM_DELETE = "删除";
	public static final String PARAM_UPDATE = "更新";

	// add view
	public static final String ADDVIEW_TITLE = "添加需求信息";
	public static final String ADDVIEW_ADDBUTTON = "添加";
	public static final String EXITBUTTON = "退出";

	// delete view
	public static final String DELETEVIEW_TITLE = "删除需求信息";
	public static final String DELETEVIEW_DELETEBUTTON = "删除";

	// update view
	public static final String UPDATEVIEW_TITLE = "更新需求信息";
	public static final String UPDATEVIEW_UPDATEBUTTON = "更新";

}
