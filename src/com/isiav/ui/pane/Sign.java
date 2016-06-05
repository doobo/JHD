package com.isiav.ui.pane;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;



import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Sign extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2479138240556573882L;
	private JFrame mainFrame = null;
	private JTextField txt_name;
	private JPasswordField txt_pwd;

	/**
	 * Create the panel.
	 */
	public Sign(JFrame frm) {
		mainFrame = frm;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel label = new JLabel("");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(200, 350, 0, 0);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		add(label, gbc_label);
		
		JLabel lblNewLabel_2 = new JLabel("");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 350);
		gbc_lblNewLabel_2.gridx = 3;
		gbc_lblNewLabel_2.gridy = 0;
		add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JLabel lb_name = new JLabel("\u5E10\u53F7\uFF1A");
		GridBagConstraints gbc_lb_name = new GridBagConstraints();
		gbc_lb_name.anchor = GridBagConstraints.EAST;
		gbc_lb_name.insets = new Insets(0, 0, 5, 5);
		gbc_lb_name.gridx = 1;
		gbc_lb_name.gridy = 1;
		add(lb_name, gbc_lb_name);
		
		txt_name = new JTextField();
		txt_name.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER ){
					txt_pwd.requestFocus();
				}
			}
		});
		GridBagConstraints gbc_txt_name = new GridBagConstraints();
		gbc_txt_name.fill = GridBagConstraints.BOTH;
		gbc_txt_name.insets = new Insets(0, 0, 5, 5);
		gbc_txt_name.gridx = 2;
		gbc_txt_name.gridy = 1;
		add(txt_name, gbc_txt_name);
		txt_name.setColumns(10);
		
		JLabel lb_pwd = new JLabel("\u5BC6\u7801\uFF1A");
		GridBagConstraints gbc_lb_pwd = new GridBagConstraints();
		gbc_lb_pwd.anchor = GridBagConstraints.EAST;
		gbc_lb_pwd.insets = new Insets(0, 0, 5, 5);
		gbc_lb_pwd.gridx = 1;
		gbc_lb_pwd.gridy = 2;
		add(lb_pwd, gbc_lb_pwd);
		
		txt_pwd = new JPasswordField();
		txt_pwd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
						if(e.getKeyCode()==KeyEvent.VK_ENTER ){
							toSign();
						}
			}
		});
		GridBagConstraints gbc_txt_pwd = new GridBagConstraints();
		gbc_txt_pwd.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_pwd.anchor = GridBagConstraints.WEST;
		gbc_txt_pwd.insets = new Insets(0, 0, 5, 5);
		gbc_txt_pwd.gridx = 2;
		gbc_txt_pwd.gridy = 2;
		add(txt_pwd, gbc_txt_pwd);
		
		JButton btn_sign = new JButton("\u6CE8\u518C");
		btn_sign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				toSign();
			}
		});
		GridBagConstraints gbc_btn_sign = new GridBagConstraints();
		gbc_btn_sign.anchor = GridBagConstraints.WEST;
		gbc_btn_sign.gridwidth = 2;
		gbc_btn_sign.insets = new Insets(10, 50, 0, 5);
		gbc_btn_sign.gridx = 1;
		gbc_btn_sign.gridy = 3;
		add(btn_sign, gbc_btn_sign);
		
		
	}

	private void toSign() {
		if(new com.isiav.ctrl.StartCtrl().toSign(txt_name.getText(), new String(txt_pwd.getPassword()))){
			com.isiav.util.PanelUtil.changePanel(mainFrame, new Login(mainFrame));
		}else{
			JDialog dialog = new JDialog(mainFrame,true);
			dialog = com.isiav.util.PanelUtil.setLocation(dialog,"错误提示","用户名或密码长度不够");
			dialog.setVisible(true);
		}
	}

}
