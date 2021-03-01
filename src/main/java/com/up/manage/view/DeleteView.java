
package com.up.manage.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.up.manage.AppConstants;
import com.up.manage.DAO;
import com.up.manage.base.BaseDAO;
import com.up.manage.dao.MANAGEDAO;
import com.up.manage.model.MANAGE;

/**
 * 模块说明： 删除需求
 * 
 */
public class DeleteView extends JFrame {

	private static final long serialVersionUID = -7668153283910203959L;

	private JPanel jPanelCenter, jPanelSouth;
	private JButton deleteButton, exitButton;
	private JTextField urgent, content; // 根据姓名+学号删除学生

	public DeleteView() {
		init();
	}

	private void init() {
		setTitle(AppConstants.DELETEVIEW_TITLE);
		// center panel
		jPanelCenter = new JPanel();
		jPanelCenter.setLayout(new GridLayout(3, 2));
		jPanelCenter.add(new JLabel(AppConstants.MANAGE_CONTENT));
		content = new JTextField();
		jPanelCenter.add(content);
		jPanelCenter.add(new JLabel(AppConstants.MANAGE_URGENT));
		urgent = new JTextField();
		jPanelCenter.add(urgent);
		jPanelCenter.add(new JLabel("-------------------------------------------------"));
		jPanelCenter.add(new JLabel("-------------------------------------------------"));

		// south panel
		jPanelSouth = new JPanel();
		jPanelSouth.setLayout(new GridLayout(1, 2));
		deleteButton = new JButton(AppConstants.DELETEVIEW_DELETEBUTTON);
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (check()) {
					MANAGE man = new MANAGE();
					buildMANAGE(man);
					boolean isSuccess = ((MANAGEDAO) BaseDAO.getAbilityDAO(DAO.MANAGEDAO)).delete(man);
					if (isSuccess) {
						setEmpty();
						if (MainView.currPageNum < 0 || MainView.currPageNum > 99) {
							MainView.currPageNum = 1;
						}
						String[][] result = ((MANAGEDAO) BaseDAO.getAbilityDAO(DAO.MANAGEDAO))
								.list(MainView.currPageNum);
						MainView.initJTable(MainView.jTable, result);
					}
				}
			}
		});
		jPanelSouth.add(deleteButton);
		exitButton = new JButton(AppConstants.EXITBUTTON);
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		jPanelSouth.add(exitButton);

		this.add(jPanelCenter, BorderLayout.CENTER);
		this.add(jPanelSouth, BorderLayout.SOUTH);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(470, 250, 400, 130);
		setResizable(false);
		setVisible(true);
	}

	private boolean check() {
		boolean result = false;
		if ("".equals(urgent.getText()) || "".equals(content.getText())) {
			return result;
		} else {
			result = true;
		}
		return result;
	}

	private void buildMANAGE(MANAGE man) {
		man.setUrgent(urgent.getText());
		man.setContent(content.getText());
	}

	private void setEmpty() {
		urgent.setText("");
		content.setText("");
	}

}
