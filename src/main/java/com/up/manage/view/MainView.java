
package com.up.manage.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.up.manage.AppConstants;
import com.up.manage.DAO;
import com.up.manage.base.BaseDAO;
import com.up.manage.dao.MANAGEDAO;

/**
 * 模块说明： 首页
 * 
 */
public class MainView extends JFrame {

	private static final long serialVersionUID = 5870864087464173884L;

	private final int maxPageNum = 99;


	private JPanel jPanelNorth, jPanelSouth, jPanelCenter;
	private JButton jButtonFirst, jButtonLast, jButtonNext, jButtonPre, jButtonAdd, jButtonDelete, jButtonUpdate,
			jButtonFind;
	private JLabel currPageNumJLabel;
	private JTextField condition;
	public static JTable jTable;
	private JScrollPane jScrollPane;
	private DefaultTableModel myTableModel;

	public static String[] column = { "id", AppConstants.MANAGE_URGENT, AppConstants.MANAGE_CONTENT,
			AppConstants.MANAGE_TIME, AppConstants.MANAGE_DEVELOPER, AppConstants.MANAGE_METHOD,
			AppConstants.MANAGE_PERIOD, AppConstants.MANAGE_TEST, AppConstants.MANAGE_OUTCOME };
	public static int currPageNum = 1;

	public MainView() {
		init();
	}
	private class FindListener extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				find();
			}
		}
	}

	private void find() {
		currPageNum = 0;
		String param = condition.getText();
		if ("".equals(param) || param == null) {
			initJTable(MainView.jTable, null);
			currPageNumJLabel.setText(AppConstants.MAINVIEW_FIND_JLABEL);
			return;
		}
		String[][] result = ((MANAGEDAO) BaseDAO.getAbilityDAO(DAO.MANAGEDAO)).queryByUrgent(param);
		condition.setText("");
		initJTable(MainView.jTable, result);
		currPageNumJLabel.setText(AppConstants.MAINVIEW_FIND_JLABEL);
	}


	private void init() {
		setTitle(AppConstants.MAINVIEW_TITLE);

		// north panel
		jPanelNorth = new JPanel();
		jPanelNorth.setLayout(new GridLayout(1, 5));//1行5列

		condition = new JTextField(AppConstants.PARAM_FIND_CONDITION);
		condition.addKeyListener(new FindListener());
		jPanelNorth.add(condition);

		// query by name
		jButtonFind = new JButton(AppConstants.PARAM_FIND);
		jButtonFind.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				find();
			}
		});
		jButtonFind.addKeyListener(new FindListener());

		// add
		jPanelNorth.add(jButtonFind);
		jButtonAdd = new JButton(AppConstants.PARAM_ADD);
		jButtonAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddView();
			}
		});
		jPanelNorth.add(jButtonAdd);

		// delete
		jButtonDelete = new JButton(AppConstants.PARAM_DELETE);
		jButtonDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new DeleteView();
			}
		});
		jPanelNorth.add(jButtonDelete);

		// update
		jButtonUpdate = new JButton(AppConstants.PARAM_UPDATE);
		jButtonUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new UpdateView();
			}
		});
		jPanelNorth.add(jButtonUpdate);

		// center panel
		jPanelCenter = new JPanel();
		jPanelCenter.setLayout(new GridLayout(1, 1));

		// init jTable
		String[][] result = ((MANAGEDAO) BaseDAO.getAbilityDAO(DAO.MANAGEDAO)).list(currPageNum);
		myTableModel = new DefaultTableModel(result, column);
		jTable = new JTable(myTableModel);
		DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
		cr.setHorizontalAlignment(JLabel.CENTER);
		jTable.setDefaultRenderer(Object.class, cr);
		initJTable(jTable, result);

		jScrollPane = new JScrollPane(jTable);
		jPanelCenter.add(jScrollPane);

		// south panel
		jPanelSouth = new JPanel();
		jPanelSouth.setLayout(new GridLayout(1, 5));

		jButtonFirst = new JButton(AppConstants.MAINVIEW_FIRST);
		jButtonFirst.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currPageNum = 1;
				String[][] result = ((MANAGEDAO) BaseDAO.getAbilityDAO(DAO.MANAGEDAO)).list(currPageNum);
				initJTable(jTable, result);
				currPageNumJLabel.setText(AppConstants.MAINVIEW_PAGENUM_JLABEL_DI + currPageNum
						+ AppConstants.MAINVIEW_PAGENUM_JLABEL_YE);
			}
		});
		jButtonPre = new JButton(AppConstants.MAINVIEW_PRE);
		jButtonPre.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				currPageNum--;
				if (currPageNum <= 0) {
					currPageNum = 1;
				}
				String[][] result = ((MANAGEDAO) BaseDAO.getAbilityDAO(DAO.MANAGEDAO)).list(currPageNum);
				initJTable(jTable, result);
				currPageNumJLabel.setText(AppConstants.MAINVIEW_PAGENUM_JLABEL_DI + currPageNum
						+ AppConstants.MAINVIEW_PAGENUM_JLABEL_YE);
			}
		});
		jButtonNext = new JButton(AppConstants.MAINVIEW_NEXT);
		jButtonNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currPageNum++;
				if (currPageNum > maxPageNum) {
					currPageNum = maxPageNum;
				}
				String[][] result = ((MANAGEDAO) BaseDAO.getAbilityDAO(DAO.MANAGEDAO)).list(currPageNum);
				initJTable(jTable, result);
				currPageNumJLabel.setText(AppConstants.MAINVIEW_PAGENUM_JLABEL_DI + currPageNum
						+ AppConstants.MAINVIEW_PAGENUM_JLABEL_YE);
			}
		});
		jButtonLast = new JButton(AppConstants.MAINVIEW_LAST);
		jButtonLast.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currPageNum = maxPageNum;
				String[][] result = ((MANAGEDAO) BaseDAO.getAbilityDAO(DAO.MANAGEDAO)).list(currPageNum);
				initJTable(jTable, result);
				currPageNumJLabel.setText(AppConstants.MAINVIEW_PAGENUM_JLABEL_DI + currPageNum
						+ AppConstants.MAINVIEW_PAGENUM_JLABEL_YE);
			}
		});

		currPageNumJLabel = new JLabel(
				AppConstants.MAINVIEW_PAGENUM_JLABEL_DI + currPageNum + AppConstants.MAINVIEW_PAGENUM_JLABEL_YE);
		currPageNumJLabel.setHorizontalAlignment(JLabel.CENTER);

		jPanelSouth.add(jButtonFirst);
		jPanelSouth.add(jButtonPre);
		jPanelSouth.add(currPageNumJLabel);
		jPanelSouth.add(jButtonNext);
		jPanelSouth.add(jButtonLast);

		this.add(jPanelNorth, BorderLayout.NORTH);
		this.add(jPanelCenter, BorderLayout.CENTER);
		this.add(jPanelSouth, BorderLayout.SOUTH);

		setBounds(400, 200, 1000, 500);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	public static void initJTable(JTable jTable, String[][] result) {
		((DefaultTableModel) jTable.getModel()).setDataVector(result, column);
		jTable.setRowHeight(30);
		TableColumn firstColumn = jTable.getColumnModel().getColumn(0);
		firstColumn.setPreferredWidth(30);
		firstColumn.setMaxWidth(30);
		firstColumn.setMinWidth(30);
		TableColumn secondColumn = jTable.getColumnModel().getColumn(1);
		secondColumn.setPreferredWidth(60);
		secondColumn.setMaxWidth(60);
		secondColumn.setMinWidth(60);
		TableColumn thirdColumn = jTable.getColumnModel().getColumn(2);
		thirdColumn.setPreferredWidth(150);
		thirdColumn.setMaxWidth(150);
		thirdColumn.setMinWidth(150);
		TableColumn fourthColumn = jTable.getColumnModel().getColumn(3);
		fourthColumn.setPreferredWidth(90);
		fourthColumn.setMaxWidth(90);
		fourthColumn.setMinWidth(90);
		TableColumn seventhColumn = jTable.getColumnModel().getColumn(6);
		seventhColumn.setPreferredWidth(90);
		seventhColumn.setMaxWidth(90);
		seventhColumn.setMinWidth(90);
		TableColumn ninthColumn = jTable.getColumnModel().getColumn(8);
		ninthColumn.setPreferredWidth(150);
		ninthColumn.setMaxWidth(150);
		ninthColumn.setMinWidth(150);
	}


}