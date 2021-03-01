
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
 * 模块说明： 更新需求信息
 * 
 */
public class UpdateView extends JFrame {

	private static final long serialVersionUID = 5292738820127102731L;

	private JPanel jPanelCenter, jPanelSouth;
	private JButton updateButton, exitButton;
	private JTextField urgent, content, developer, method, period, test, outcome, time;

	public UpdateView() {
		init();
	}

	private void init() {
		setTitle(AppConstants.UPDATEVIEW_TITLE);
		// center panel
		jPanelCenter = new JPanel();
		jPanelCenter.setLayout(new GridLayout(9, 2));
		jPanelCenter.add(new JLabel(AppConstants.MANAGE_URGENT));
		urgent = new JTextField();
		jPanelCenter.add(urgent);
		jPanelCenter.add(new JLabel(AppConstants.MANAGE_CONTENT));
		content = new JTextField();
		jPanelCenter.add(content);
		jPanelCenter.add(new JLabel(AppConstants.MANAGE_TIME));
		time = new JTextField();
		jPanelCenter.add(time);
		jPanelCenter.add(new JLabel(AppConstants.MANAGE_DEVELOPER));
		developer = new JTextField();
		jPanelCenter.add(developer);
		jPanelCenter.add(new JLabel(AppConstants.MANAGE_METHOD));
		method = new JTextField();
		jPanelCenter.add(method);
		jPanelCenter.add(new JLabel(AppConstants.MANAGE_PERIOD));
		period = new JTextField();
		jPanelCenter.add(period);
		jPanelCenter.add(new JLabel(AppConstants.MANAGE_TEST));
		test = new JTextField();
		jPanelCenter.add(test);
		jPanelCenter.add(new JLabel(AppConstants.MANAGE_OUTCOME));
		outcome = new JTextField();
		jPanelCenter.add(outcome);
		jPanelCenter.add(new JLabel("wyq----------------------------------------------"));
		jPanelCenter.add(new JLabel("-------------------------------------------------"));

		// south panel
		jPanelSouth = new JPanel();
		jPanelSouth.setLayout(new GridLayout(1, 2));
		updateButton = new JButton(AppConstants.UPDATEVIEW_UPDATEBUTTON);
		updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (check()) {
					MANAGE man = new MANAGE();
					buildMANAGE(man);
					boolean isSuccess = ((MANAGEDAO) BaseDAO.getAbilityDAO(DAO.MANAGEDAO)).update(man);
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
		jPanelSouth.add(updateButton);
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
		setBounds(470, 200, 300, 270);
		setResizable(false);
		setVisible(true);
	}

	private boolean check() {
		boolean result = false;
		if ("".equals(urgent.getText()) || "".equals(content.getText()) || "".equals(developer.getText())
				|| "".equals(test.getText()) || "".equals(method.getText())) {
			return result;
		} else {
			result = true;
		}
		return result;
	}

	private void buildMANAGE(MANAGE man) {
		man.setDeveloper(developer.getText());
		man.setTest(test.getText());
		man.setMethod(method.getText());
		man.setPeriod(period.getText());
		man.setUrgent(urgent.getText());
		man.setContent(content.getText());
		man.setOutcome(outcome.getText());
		man.setTime(time.getText());
	}

	private void setEmpty() {
		urgent.setText("");
		content.setText("");
		developer.setText("");
		time.setText("");
		test.setText("");
		method.setText("");
		outcome.setText("");
		period.setText("");
	}
}
