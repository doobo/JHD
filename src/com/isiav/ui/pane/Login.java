package com.isiav.ui.pane;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPasswordField;







import com.isiav.ctrl.StartCtrl;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6190051032480667239L;
	private JTextField txt_name;
	private JPasswordField txt_pwd;
	private JFrame mainFrame = null;
	/**
	 * Create the panel.
	 */
	public Login(JFrame frm) {
			
		this.mainFrame = frm;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lb_name = new JLabel("\u5E10\u53F7\uFF1A");
		GridBagConstraints gbc_lb_name = new GridBagConstraints();
		gbc_lb_name.ipady = 10;
		gbc_lb_name.anchor = GridBagConstraints.EAST;
		gbc_lb_name.insets = new Insets(200, 300, 10, 5);
		gbc_lb_name.gridx = 0;
		gbc_lb_name.gridy = 0;
		add(lb_name, gbc_lb_name);
		
		txt_name = new JTextField();
		txt_name.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER )
					txt_pwd.requestFocus();
			}
		});
		GridBagConstraints gbc_txt_name = new GridBagConstraints();
		gbc_txt_name.ipady = 10;
		gbc_txt_name.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_name.insets = new Insets(200, 0, 10, 0);
		gbc_txt_name.gridx = 1;
		gbc_txt_name.gridy = 0;
		add(txt_name, gbc_txt_name);
		txt_name.setColumns(10);
		
		JLabel kong_1 = new JLabel("");
		GridBagConstraints gbc_kong_1 = new GridBagConstraints();
		gbc_kong_1.ipady = 10;
		gbc_kong_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_kong_1.insets = new Insets(0, 0, 5, 350);
		gbc_kong_1.gridx = 2;
		gbc_kong_1.gridy = 0;
		add(kong_1, gbc_kong_1);
		
		JLabel lb_pwd = new JLabel("\u5BC6\u7801\uFF1A");
		GridBagConstraints gbc_lb_pwd = new GridBagConstraints();
		gbc_lb_pwd.ipady = 10;
		gbc_lb_pwd.anchor = GridBagConstraints.EAST;
		gbc_lb_pwd.insets = new Insets(0, 0, 5, 5);
		gbc_lb_pwd.gridx = 0;
		gbc_lb_pwd.gridy = 1;
		add(lb_pwd, gbc_lb_pwd);
		
		txt_pwd = new JPasswordField();
		txt_pwd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER )
					toLogin();
			}
		});
		GridBagConstraints gbc_txt_pwd = new GridBagConstraints();
		gbc_txt_pwd.ipady = 10;
		gbc_txt_pwd.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_pwd.insets = new Insets(0, 0, 5, 0);
		gbc_txt_pwd.gridx = 1;
		gbc_txt_pwd.gridy = 1;
		add(txt_pwd, gbc_txt_pwd);
		
		JButton btn_login = new JButton("\u767B\u5F55");
		btn_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				toLogin();
			}
		});
		GridBagConstraints gbc_btn_login = new GridBagConstraints();
		gbc_btn_login.ipady = 10;
		gbc_btn_login.gridwidth = 3;
		gbc_btn_login.insets = new Insets(10, 0, 0, 0);
		gbc_btn_login.gridx = 0;
		gbc_btn_login.gridy = 2;
		add(btn_login, gbc_btn_login);

	}
	private void toLogin() {
//		com.isiav.util.PanelUtil.changePanel(mainFrame, new EditPane(mainFrame));
		if(new StartCtrl().toLogin(txt_name.getText(), new String(txt_pwd.getPassword()))){
			com.isiav.util.PanelUtil.changePanel(mainFrame,new EditPane(mainFrame,0));
		}else{
			JDialog dialog = new JDialog(mainFrame,true);
			dialog = com.isiav.util.PanelUtil.setLocation(dialog,"错误提示","用户名或密码不匹配！");
			dialog.setVisible(true);
			txt_name.setText("");
			txt_pwd.setText("");
			txt_name.requestFocus();
		}
	}

}
