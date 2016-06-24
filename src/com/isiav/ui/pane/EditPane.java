package com.isiav.ui.pane;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.Font;

import javax.swing.ImageIcon;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import javax.swing.ListSelectionModel;

import com.isiav.ctrl.TxtPanelCtrl;

public class EditPane extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3239918693395140849L;
	private static  JTextField txt_title;
	private static JTextField txt_tag;
	private JTextField txt_serach;
	private JComboBox cmb_books,cmb_sub;
	private static JTextPane txt_edit = null;
	private JList list = null;
	private int point = 0;
	private boolean newTag = false;
	
	private JFrame mainFrame = null;
	private JScrollPane scrollPane_1;
	private GridBagConstraints gbc_scrollPane_1;
	
	


	/**
	 * Create the panel.
	 */
	public EditPane(JFrame frm,int index) {
		
		mainFrame = frm;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblNewLabel_2 = new JLabel("\u4E66\u76EE\u5F55");
		lblNewLabel_2.setPreferredSize(new Dimension(100, 20));
		lblNewLabel_2.setIcon(new ImageIcon(EditPane.class.getResource("/com/isiav/util/10.png")));
		lblNewLabel_2.setFont(new Font("宋体", Font.BOLD, 15));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.insets = new Insets(0, 5, 5, 0);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 0;
		add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JButton btn_new = new JButton("\u65B0 \u5EFA");
		btn_new.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				toNew();
			}
		});
		GridBagConstraints gbc_btn_new = new GridBagConstraints();
		gbc_btn_new.insets = new Insets(0, 0, 5, 5);
		gbc_btn_new.anchor = GridBagConstraints.EAST;
		gbc_btn_new.ipady = 10;
		gbc_btn_new.gridx = 1;
		gbc_btn_new.gridy = 0;
		add(btn_new, gbc_btn_new);
		
		JButton btn_save = new JButton("\u4FDD \u5B58");
		btn_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				toBtn_save();
			}
		});
		GridBagConstraints gbc_btn_save = new GridBagConstraints();
		gbc_btn_save.insets = new Insets(0, 0, 5, 5);
		gbc_btn_save.ipady = 10;
		gbc_btn_save.gridx = 2;
		gbc_btn_save.gridy = 0;
		add(btn_save, gbc_btn_save);
		
		JButton btn_del = new JButton("\u5220 \u9664");
		btn_del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				com.isiav.ctrl.EditCtrl.getInstance().toDel((String)cmb_books.getSelectedItem(),txt_title.getText());
				toKong();
				com.isiav.ctrl.EditCtrl.getInstance().setJListData(list, (String)cmb_books.getSelectedItem());
			}
		});
		GridBagConstraints gbc_btn_del = new GridBagConstraints();
		gbc_btn_del.fill = GridBagConstraints.HORIZONTAL;
		gbc_btn_del.insets = new Insets(0, 0, 5, 5);
		gbc_btn_del.ipady = 10;
		gbc_btn_del.gridx = 3;
		gbc_btn_del.gridy = 0;
		add(btn_del, gbc_btn_del);
		
		JButton btn_back = new JButton("\u540E \u9000");
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!com.isiav.ctrl.EditCtrl.getInstance().toBack(txt_title, txt_tag, txt_edit, txt_serach)){
					getDialog("提示", "没纪录了哦！");
				}
			}
		});
		GridBagConstraints gbc_btn_back = new GridBagConstraints();
		gbc_btn_back.anchor = GridBagConstraints.WEST;
		gbc_btn_back.insets = new Insets(0, 0, 5, 5);
		gbc_btn_back.ipady = 10;
		gbc_btn_back.gridx = 4;
		gbc_btn_back.gridy = 0;
		add(btn_back, gbc_btn_back);
		
		JButton btn_forward = new JButton("\u5411 \u524D");
		btn_forward.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!com.isiav.ctrl.EditCtrl.getInstance().toForward(txt_title, txt_tag, txt_edit, txt_serach)){
					getDialog("提示", "没有记录了哦！");
				}
			}
		});
		GridBagConstraints gbc_btn_forward = new GridBagConstraints();
		gbc_btn_forward.insets = new Insets(0, 0, 5, 5);
		gbc_btn_forward.ipady = 10;
		gbc_btn_forward.gridx = 5;
		gbc_btn_forward.gridy = 0;
		add(btn_forward, gbc_btn_forward);
		
		JButton btn_util = new JButton("\u5DE5 \u5177");
		GridBagConstraints gbc_btn_util = new GridBagConstraints();
		gbc_btn_util.insets = new Insets(0, 0, 5, 5);
		gbc_btn_util.anchor = GridBagConstraints.WEST;
		gbc_btn_util.ipady = 10;
		gbc_btn_util.gridx = 6;
		gbc_btn_util.gridy = 0;
		add(btn_util, gbc_btn_util);
		
		cmb_sub = new JComboBox();
		cmb_sub.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				 if(e.getStateChange() == ItemEvent.SELECTED)
	             {
					 if(((String)cmb_sub.getSelectedItem()).equals("搜索全文")){
						 com.isiav.ctrl.EditCtrl.getInstance().setJListData(list, (String)cmb_books.getSelectedItem());
					 }
	             }
				
			}
		});
		cmb_sub.setPreferredSize(new Dimension(100, 20));
		cmb_sub.setModel(new DefaultComboBoxModel(new String[] {"\u641C\u7D22\u5168\u6587", "\u641C\u7D22\u6240\u6709", "\u7F51\u9875\u641C\u7D22"}));
		GridBagConstraints gbc_cmb_sub = new GridBagConstraints();
		gbc_cmb_sub.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmb_sub.insets = new Insets(0, 10, 5, 5);
		gbc_cmb_sub.gridx = 0;
		gbc_cmb_sub.gridy = 1;
		add(cmb_sub, gbc_cmb_sub);
		
		JLabel lb_title = new JLabel("\u6807\u9898\uFF1A");
		GridBagConstraints gbc_lb_title = new GridBagConstraints();
		gbc_lb_title.anchor = GridBagConstraints.EAST;
		gbc_lb_title.insets = new Insets(10, 0, 10, 5);
		gbc_lb_title.gridx = 5;
		gbc_lb_title.gridy = 1;
		add(lb_title, gbc_lb_title);
		
		txt_title = new JTextField();
		txt_title.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER ){
					txt_tag.requestFocus();
				}
			}
		});
		txt_title.setFont(new Font("宋体", Font.BOLD, 13));
		GridBagConstraints gbc_txt_title = new GridBagConstraints();
		gbc_txt_title.insets = new Insets(10, 0, 10, 5);
		gbc_txt_title.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_title.gridx = 6;
		gbc_txt_title.gridy = 1;
		add(txt_title, gbc_txt_title);
		txt_title.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 65);
		gbc_lblNewLabel_1.gridx = 8;
		gbc_lblNewLabel_1.gridy = 1;
		add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		cmb_books = new JComboBox();
		cmb_books.setPreferredSize(new Dimension(100,20));
		cmb_books.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				 if(e.getStateChange() == ItemEvent.SELECTED)
	             {
					 
	                 com.isiav.ctrl.EditCtrl.getInstance().setJListData(list, (String)cmb_books.getSelectedItem());
	             }
			}
		});
		cmb_books.setEditable(true);
		GridBagConstraints gbc_cmb_books = new GridBagConstraints();
		gbc_cmb_books.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmb_books.insets = new Insets(0, 10, 5, 5);
		gbc_cmb_books.gridx = 0;
		gbc_cmb_books.gridy = 2;
		add(cmb_books, gbc_cmb_books);
		
		JLabel lb_tag = new JLabel("\u6807\u7B7E\uFF1A");
		GridBagConstraints gbc_lb_tag = new GridBagConstraints();
		gbc_lb_tag.insets = new Insets(0, 0, 5, 5);
		gbc_lb_tag.anchor = GridBagConstraints.EAST;
		gbc_lb_tag.gridx = 5;
		gbc_lb_tag.gridy = 2;
		add(lb_tag, gbc_lb_tag);
		
		txt_tag = new JTextField();
		txt_tag.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER ){
					txt_edit.requestFocus();
				}
			}
		});
		txt_tag.setFont(new Font("宋体", Font.BOLD, 13));
		GridBagConstraints gbc_txt_tag = new GridBagConstraints();
		gbc_txt_tag.insets = new Insets(0, 0, 5, 5);
		gbc_txt_tag.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_tag.gridx = 6;
		gbc_txt_tag.gridy = 2;
		add(txt_tag, gbc_txt_tag);
		txt_tag.setColumns(10);
		
		txt_serach = new JTextField();
		txt_serach.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txt_serach.setText("");
			}
		});
		txt_serach.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER ){
					com.isiav.ctrl.EditCtrl.getInstance().toSearch((String)cmb_sub.getSelectedItem(), (String)cmb_books.getSelectedItem(), txt_serach.getText(),list);
				}
			}
		});
		txt_serach.setFont(new Font("宋体", Font.BOLD, 15));
		GridBagConstraints gbc_txt_serach = new GridBagConstraints();
		gbc_txt_serach.ipady = 10;
		gbc_txt_serach.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_serach.insets = new Insets(0, 10, 5, 5);
		gbc_txt_serach.gridx = 0;
		gbc_txt_serach.gridy = 3;
		add(txt_serach, gbc_txt_serach);
		txt_serach.setColumns(10);
		
		JButton btn_search = new JButton("");
		btn_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				com.isiav.ctrl.EditCtrl.getInstance().toSearch((String)cmb_sub.getSelectedItem(), (String)cmb_books.getSelectedItem(), txt_serach.getText(),list);
			}
		});
		btn_search.setIcon(new ImageIcon(EditPane.class.getResource("/com/isiav/util/so.png")));
		GridBagConstraints gbc_btn_search = new GridBagConstraints();
		gbc_btn_search.anchor = GridBagConstraints.EAST;
		gbc_btn_search.insets = new Insets(0, 0, 5, 0);
		gbc_btn_search.gridx = 1;
		gbc_btn_search.gridy = 3;
		add(btn_search, gbc_btn_search);
		
		JButton btn_zoom = new JButton("");
		btn_zoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txt_edit.requestFocus();
				com.isiav.ctrl.EditCtrl.getInstance().toZoom(txt_edit,point);
			}
		});
		btn_zoom.setIcon(new ImageIcon(EditPane.class.getResource("/com/isiav/util/da.png")));
		GridBagConstraints gbc_btn_zoom = new GridBagConstraints();
		gbc_btn_zoom.insets = new Insets(0, 0, 5, 0);
		gbc_btn_zoom.gridx = 2;
		gbc_btn_zoom.gridy = 3;
		add(btn_zoom, gbc_btn_zoom);
		
		JButton btn_lessen = new JButton("");
		btn_lessen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txt_edit.requestFocus();
				com.isiav.ctrl.EditCtrl.getInstance().toLessen(txt_edit,point);
			}
		});
		btn_lessen.setIcon(new ImageIcon(EditPane.class.getResource("/com/isiav/util/xiao.png")));
		GridBagConstraints gbc_btn_lessen = new GridBagConstraints();
		gbc_btn_lessen.insets = new Insets(0, 0, 5, 0);
		gbc_btn_lessen.gridx = 3;
		gbc_btn_lessen.gridy = 3;
		add(btn_lessen, gbc_btn_lessen);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				  toBigWindow();
			}
		});
		button.setIcon(new ImageIcon(EditPane.class.getResource("/com/isiav/util/big.png")));
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 5, 0);
		gbc_button.gridx = 4;
		gbc_button.gridy = 3;
		add(button, gbc_button);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(100, 800));
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 10, 20, 5);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 4;
		add(scrollPane, gbc_scrollPane);
		
		list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				com.isiav.ctrl.EditCtrl.getInstance().initTxtPane(txt_title, txt_tag, txt_edit,txt_serach, (String)list.getSelectedValue()	, (String)cmb_books.getSelectedItem(),cmb_books);
			}
		});
		list.setLayoutOrientation(JList.VERTICAL);
		scrollPane.setViewportView(list);
		
		scrollPane_1 = new JScrollPane();
		gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.insets = new Insets(0, 0, 20, 10);
		gbc_scrollPane_1.gridwidth = 8;
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 1;
		gbc_scrollPane_1.gridy = 4;
		add(scrollPane_1, gbc_scrollPane_1);
		
		txt_edit = new JTextPane();
		txt_edit.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				 if(e.isControlDown()&&e.getKeyCode()==KeyEvent.VK_S){
					  	toBtn_save();
				 }else if(e.isControlDown()&&e.getKeyCode()==KeyEvent.VK_N){
		                toNew();
				 }else if(e.isControlDown()&&e.getKeyCode()==KeyEvent.VK_ENTER){
		                toBigWindow();
				 }else if(e.getKeyCode()==KeyEvent.VK_ESCAPE ){
					 	String edit_txt = txt_edit.getText();
					 	String title_txt = txt_title.getText();
					 	String tag_txt = txt_tag.getText();
					 	int index =  cmb_books.getSelectedIndex();
					 	com.isiav.util.PanelUtil.changePanel(mainFrame,new EditPane(mainFrame,index));
					 	txt_title.setText(title_txt);
					 	txt_tag.setText(tag_txt);
					 	TxtPanelCtrl.insert(edit_txt, TxtPanelCtrl.getAttrset(),	txt_edit, point);
		                txt_edit.requestFocus();
				 }
			}
		});
		txt_edit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				point = txt_edit.getCaretPosition();
			}
		});
		txt_edit.setFont(new Font("宋体", Font.PLAIN, 15));
		scrollPane_1.setViewportView(txt_edit);
		
		initRun(index);

	}
	
	private void initRun(int index){
		
				cmb_books.removeAllItems();
				cmb_books.setModel(new DefaultComboBoxModel(com.isiav.dao.impl.CheckDir.getInstance().getDir()));
				if(com.isiav.ctrl.EditCtrl.getInstance().setJListData(list, (String)cmb_books.getSelectedItem())){
					list.setSelectedIndex(0);
					com.isiav.ctrl.EditCtrl.getInstance().initTxtPane(txt_title, txt_tag, txt_edit,txt_serach, (String)list.getSelectedValue()	, (String)cmb_books.getSelectedItem(),cmb_books);
					cmb_books.setSelectedIndex(index);
				}
				if(index>0){
					txt_serach.setText("");
				}
	}
	
	private void toBtn_save() {
		if(newTag){
			if(com.isiav.ctrl.EditCtrl.getInstance().toNew((String)cmb_books.getSelectedItem(), txt_title.getText(), txt_tag.getText(), txt_edit.getText())){
			newTag = false;
			String selectBook = (String)cmb_books.getSelectedItem();
			cmb_books.removeAllItems();
			cmb_books.setModel(new DefaultComboBoxModel(com.isiav.dao.impl.CheckDir.getInstance().getDir()));
			cmb_books.setSelectedItem(selectBook);
			com.isiav.ctrl.EditCtrl.getInstance().setJListData(list, (String)cmb_books.getSelectedItem());
			}else{
				getDialog("错误提示", "含重复标题或命名不规范！");
			}
			
		}else{
			if(com.isiav.ctrl.EditCtrl.getInstance().toSave((String)cmb_books.getSelectedItem(), txt_title.getText(), txt_tag.getText(), txt_edit.getText())){
			String selectBook = (String)cmb_books.getSelectedItem();
			cmb_books.removeAllItems();
			cmb_books.setModel(new DefaultComboBoxModel(com.isiav.dao.impl.CheckDir.getInstance().getDir()));
			cmb_books.setSelectedItem(selectBook);
			com.isiav.ctrl.EditCtrl.getInstance().setJListData(list, (String)cmb_books.getSelectedItem());
			}else{
				getDialog("错误提示", "含重复标题或命名不规范！");
			}
		}
	}

	private void getDialog(String err,String msg) {
		JDialog dialog = new JDialog(mainFrame,true); 
		dialog = com.isiav.util.PanelUtil.setLocation(dialog,err,msg );
		dialog.setVisible(true);
	}
	
	private void toNew(){
		toKong();
		newTag = true;
	}
	
	private void toKong(){
		txt_title.setText("");
		txt_tag.setText("");
		txt_edit.setText("");
		txt_title.requestFocus();
		
	}
	
	private void toBigWindow() {
		mainFrame.getContentPane().removeAll();
		gbc_scrollPane_1.insets = new Insets(0,0, 0, 0);
		gbc_scrollPane_1.gridx = 0;
		mainFrame.getContentPane().add(scrollPane_1,gbc_scrollPane_1);
		mainFrame.repaint();
		mainFrame.validate();
		mainFrame.invalidate();
		mainFrame.validate();
		txt_edit.requestFocus();
	}

}
