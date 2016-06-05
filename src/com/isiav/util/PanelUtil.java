package com.isiav.util;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.isiav.ui.JHDMain;

public class PanelUtil {

	private PanelUtil(){
	}
	
	public static void changePanel(JFrame jFrame,JPanel newPanel){
		jFrame.getContentPane().removeAll();
		jFrame.setContentPane(newPanel);
		jFrame.repaint();
		jFrame.validate();
		
	}
	
	public static JDialog getJDialog(){
		 JDialog dialog = new JDialog();       
		 dialog.setSize(300, 200);
		 dialog.setLocation(         JHDMain.point.x +JHDMain.width/2 - dialog.getWidth()/2,        JHDMain.point.y + JHDMain.height/2 - dialog.getHeight()/2);
		 dialog.setVisible(true);
		 return dialog;
	}
	


	public static JDialog setLocation(JDialog dialog,String title,String lb) {
		dialog.setTitle(title);
		dialog.setLocation(         JHDMain.point.x +JHDMain.width/2 - dialog.getWidth()/2,        JHDMain.point.y + JHDMain.height/2 - dialog.getHeight()/2);
		dialog.setSize(200,100);
		dialog.add(new JLabel(lb));
		return dialog;
	}
}
