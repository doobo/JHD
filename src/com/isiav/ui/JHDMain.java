package com.isiav.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import com.isiav.ui.pane.Login;
import com.isiav.ui.pane.Sign;

import java.awt.Point;
import java.awt.Toolkit;

public class JHDMain {

	private JFrame frmIt;
	Toolkit tk = Toolkit.getDefaultToolkit();
	public static int width,height;//父窗体的宽度和高度  
	public static Point point;//记录父窗体在屏幕的坐标
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JHDMain window = new JHDMain();
					window.frmIt.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JHDMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmIt = new JFrame();
		frmIt.setTitle("\u7F16\u7A0B\u5DE5\u5177\u5305V1.5");
		frmIt.setIconImage(Toolkit.getDefaultToolkit().getImage(JHDMain.class.getResource("/com/isiav/util/white24.png")));
		width = 1000;height = 680;
		frmIt.setSize(width, height);
		frmIt.setLocation((tk.getScreenSize().width - width)/2,     (tk.getScreenSize().height - height)/2-20);
		point = frmIt.getLocation();
		frmIt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		if(!com.isiav.dao.impl.CheckDir.getInstance().hasBook("零零一文档")){
			com.isiav.util.PanelUtil.changePanel(frmIt, new Sign(frmIt));
		}else{
				com.isiav.util.PanelUtil.changePanel(frmIt, new Login(frmIt));
		}
	}
}

