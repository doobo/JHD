package com.isiav.ctrl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.TreeMap;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import com.isiav.vo.JHDBean;
import com.isiav.vo.JHDTree;
import com.isiav.vo.TxtBean;

public class EditCtrl {

	private static EditCtrl editctrl = null;
	private static Vector<String> listVe = new Vector<String>(10, 10);
	private static TreeMap<Integer,TxtBean> map = new TreeMap<Integer,TxtBean>();
	private static int i=0;

	// 普通搜索引擎
	public boolean toSearch(String sub, String bookName, String keyword,
			JList list) {
		if (sub.equals("搜索全文")
				&& com.isiav.dao.impl.CheckDir.getInstance().hasBook(bookName)) {
			Vector<String> ve = new Vector<String>(10, 10);
			for (int i = 0; i < listVe.size(); i++) {
				if (listVe.get(i).contains(keyword)) {
					ve.add(listVe.get(i));
				}
			}
			list.setListData(ve);
		} else if (sub.equals("搜索所有")) {
			listVe.removeAllElements();
			String[] books = com.isiav.dao.impl.CheckDir.getInstance().getDir();
			for (int i = 0; i < books.length; i++) {
				String[] listData = com.isiav.dao.factory.Factory.getInstance()
						.readFiles(books[i]).getMap().get(1).getContent()
						.split("\\*");
				for (int j = 0; j < listData.length; j++) {
					if (listData[j].contains(keyword)) {
						listVe.add(listData[j] + "~" + books[i]);
					}
				}
			}
			list.setListData(listVe);
		} else {
			try {
				String rurl = com.isiav.util.RandString.getInstance().getUrl(
						URLEncoder.encode(keyword, "UTF-8"));
				if (java.awt.Desktop.isDesktopSupported()) {
					try {
						// 创建一个URI实例
						java.net.URI uri = java.net.URI.create(rurl);
						// 获取当前系统桌面扩展
						java.awt.Desktop dp = java.awt.Desktop.getDesktop();
						// 判断系统桌面是否支持要执行的功能
						if (dp.isSupported(java.awt.Desktop.Action.BROWSE)) {
							// 获取系统默认浏览器打开链接
							dp.browse(uri);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} catch (UnsupportedEncodingException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * @author QieLan
	 * @param bookName
	 * @param title
	 * @param tag
	 * @param content
	 * @return 是否成功保存 1、判读输入是否合法 2、判读书是否存在，不存在就建立 3、判断是否重复或者是否在修改，重复就更改
	 *         4、写入文件，更新界面
	 */
	public boolean toNew(final String bookName, final String title,
			final String tag, final String content) {
		String book = bookName.trim() + ".properties";
		if (book.matches("[^/\\\\<>*?|\"]+\\.[^/\\\\<>*?|\"]+")
				&& title.trim().length() > 0 && tag.trim().length() > 0) {
			if (!com.isiav.dao.impl.CheckDir.getInstance().hasBook(bookName)) {
				com.isiav.dao.impl.CheckDir.getInstance().createBook(bookName);
				JHDTree tree = new JHDTree(1, new JHDBean(0, bookName, "Index",
						"1-" + title.trim() + "  " + tag.trim() + "*"));
				tree.getMap()
						.put(2,
								new JHDBean(1, title.trim(), tag.trim(),
										content.trim()));
				com.isiav.dao.factory.Factory.getInstance().writeFiles(
						bookName.trim(), tree);
				return true;
			} else {
				JHDTree tree = com.isiav.dao.factory.Factory.getInstance()
						.readFiles(bookName.trim());
				for (int i = 2; i <= tree.getMap().size(); i++) {
					if (tree.getMap().get(i) != null) {
						if (tree.getMap().get(i).getTitle()
								.equals(title.trim())) {
							return false;
						}
					}
				}
				tree.addMap(
						tree.getMap().lastKey() + 1,
						new JHDBean(tree.getMap().lastKey(), title.trim(), tag
								.trim(), content.trim()));
				tree.addMap(1, new JHDBean(1, bookName.trim(), bookName
						+ "Index", tree.getMap().get(1).getContent()
						+ (tree.getMap().lastKey() - 1) + "-" + title.trim()
						+ "  " + tag.trim() + "*"));
				com.isiav.dao.factory.Factory.getInstance().writeFiles(
						bookName, tree);
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * 1、判断书名是否存在，存在就进行匹配 如果匹配到，就进行修改 没有匹配到，就进行增加操作 2、不存在，就进行新建操作
	 * 
	 */
	public boolean toSave(final String bookName, final String title,
			final String tag, final String content) {
		if (com.isiav.dao.impl.CheckDir.getInstance().hasBook(bookName)
				&& title.trim().length() > 0) {
			if (com.isiav.dao.factory.Factory.getInstance().readFiles(bookName)
					.getMap().get(1) != null) {
				JHDTree tree = com.isiav.dao.factory.Factory.getInstance()
						.readFiles(bookName);
				boolean tt = true;
				String index = "";
				for (int i = 2; i <= tree.getMap().lastKey(); i++) {
					if (tree.getMap().get(i) != null) {
						if (tree.getMap().get(i).getTitle()
								.equals(title.trim())) {
							tree.addMap(i,
									new JHDBean(i - 1, title.trim(),
											tag.trim(), content.trim()));
							tt = false;
						}
						index = index + tree.getMap().get(i).getId() + "-"
								+ tree.getMap().get(i).getTitle().trim() + "  "
								+ tree.getMap().get(i).getLable().trim() + "*";
					}
				}
				if (tt) {
					tree.addMap(tree.getMap().lastKey() + 1, new JHDBean(tree
							.getMap().lastKey(), title, tag, content));
					index = index
							+ tree.getMap().get(tree.getMap().lastKey())
									.getId()
							+ "-"
							+ tree.getMap().get(tree.getMap().lastKey())
									.getTitle().trim()
							+ "  "
							+ tree.getMap().get(tree.getMap().lastKey())
									.getLable().trim() + "*";
				}
				tree.addMap(1, new JHDBean(1, bookName, "Index", index));
				com.isiav.dao.factory.Factory.getInstance().writeFiles(
						bookName, tree);
			}
			return true;
		} else {
			toNew(bookName, title, tag, content);
		}
		return false;
	}

	/**
	 * @author QieLan
	 * @return
	 */
	public boolean toDel(String bookName, String title) {
		if (com.isiav.dao.impl.CheckDir.getInstance().hasBook(bookName)
				&& title.trim().length() > 0) {
			if (com.isiav.dao.factory.Factory.getInstance().readFiles(bookName)
					.getMap().get(1) != null) {
				boolean tt = false;
				JHDTree tree = com.isiav.dao.factory.Factory.getInstance()
						.readFiles(bookName);
				String index = "";
				for (int i = 2; i <= tree.getMap().lastKey(); i++) {
					if (tree.getMap().get(i) != null) {
						if (tree.getMap().get(i).getTitle()
								.equals(title.trim())) {
							tree.removeMap(i);
							tt = true;
						} else {
							index = index + tree.getMap().get(i).getId() + "-"
									+ tree.getMap().get(i).getTitle().trim()
									+ "  "
									+ tree.getMap().get(i).getLable().trim()
									+ "*";
						}
					}
				}
				if (tt) {
					tree.addMap(1, new JHDBean(1, bookName, "Index", index));
					com.isiav.dao.factory.Factory.getInstance().writeFiles(bookName, tree);
					return true;
				}
			}
		}

		return false;
	}

	public boolean setJListData(JList list, String bookName) {
		if (com.isiav.dao.impl.CheckDir.getInstance().hasBook(bookName)) {
			if (com.isiav.dao.factory.Factory.getInstance().readFiles(bookName)
					.getMap().get(1) != null) {
				String[] listData = com.isiav.dao.factory.Factory.getInstance()
						.readFiles(bookName).getMap().get(1).getContent()
						.split("\\*");
				list.setListData(listData);
				listVe.removeAllElements();
				for (int i = 0; i < listData.length; i++) {
					listVe.add(listData[i]);
				}
				return true;
			}
		}
		return false;
	}

	public boolean initTxtPane(JTextField txt_title, JTextField txt_tag,
		JTextPane txt_edit, JTextField txt_search, String itemData,
		String bookName, JComboBox cmb) {
			if (itemData.contains("~")) {
				bookName = itemData.substring(itemData.indexOf("~") + 1,
						itemData.length());
				cmb.setSelectedItem(bookName);
			}
			if(itemData.contains("-")){
					String ints = itemData.substring(0, itemData.indexOf("-"));
					JHDTree tree = com.isiav.dao.factory.Factory.getInstance().readFiles(
							bookName);
					txt_title.setText(tree.getMap().get(Integer.parseInt(ints) + 1)
							.getTitle());
					txt_tag.setText(tree.getMap().get(Integer.parseInt(ints) + 1)
							.getLable());
					txt_search.setText(tree.getMap().get(Integer.parseInt(ints) + 1)
							.getTitle()
							+ " "
							+ tree.getMap().get(Integer.parseInt(ints) + 1).getLable());
					txt_edit.setText(tree.getMap().get(Integer.parseInt(ints) + 1)
							.getContent());
					TxtPanelCtrl.insert(txt_edit.getText(), TxtPanelCtrl.getAttrset(),
							txt_edit, 0);
					SaveTxtBean(new TxtBean(tree.getMap().get(Integer.parseInt(ints) + 1).getTitle(),
							tree.getMap().get(Integer.parseInt(ints) + 1).getLable(),
							tree.getMap().get(Integer.parseInt(ints) + 1).getContent(),
							tree.getMap().get(Integer.parseInt(ints) + 1).getTitle()+"  "+tree.getMap().get(Integer.parseInt(ints) + 1).getLable(),
							bookName
							));
					return true;
		}
		return false;
	}
	
	private void SaveTxtBean(TxtBean txtBean){
		if(i<20){
				if(map.get(0)!=null){
					TreeMap<Integer, TxtBean> temp = new TreeMap<Integer, TxtBean>();
					temp.putAll(map.headMap(i));
					map.clear();
					map.putAll(temp);
					map.put(i++, txtBean);
				}else{
					map.put(i++, txtBean);
				}
		}else{
			TreeMap<Integer, TxtBean> temp = new TreeMap<Integer, TxtBean>();
			temp.putAll(map.tailMap(10));
			map.clear();
			int m = 0;
			for(int j=temp.firstKey();j<temp.lastKey();j++){
				map.put(m++, temp.get(j));
			}
			i=map.lastKey()+1;
			map.put(i++, txtBean);
		}
	}

	public boolean toForward(JTextField txt_title, JTextField txt_tag,JTextPane txt_edit, JTextField txt_search){
		if(map.get(i)==null){
			return false;
		}else{
			txt_title.setText(map.get(i).getTitle());
			txt_tag.setText(map.get(i).getTag());
			txt_edit.setText(map.get(i).getContent());
			txt_search.setText(map.get(i).getSearch());
			TxtPanelCtrl.insert(txt_edit.getText(), TxtPanelCtrl.getAttrset(),	txt_edit, 0);
			i++;
			return true;
		}
	}
	
	public boolean toBack(JTextField txt_title, JTextField txt_tag,JTextPane txt_edit, JTextField txt_search){
		if(map.get(i-2)==null){
			return false;
		}else{
			txt_title.setText(map.get(i-2).getTitle());
			txt_tag.setText(map.get(i-2).getTag());
			txt_edit.setText(map.get(i-2).getContent());
			txt_search.setText(map.get(i-2).getSearch());
			TxtPanelCtrl.insert(txt_edit.getText(), TxtPanelCtrl.getAttrset(),	txt_edit, 0);
			i--;
			return true;
		}
	}
	
	public boolean toZoom(JTextPane txt_edit, int point) {
		TxtPanelCtrl.fontSize++;
		TxtPanelCtrl.insert(txt_edit.getText(), TxtPanelCtrl.getAttrset(),
				txt_edit, point);
		return false;
	}

	public boolean toLessen(JTextPane txt_edit, int point) {
		TxtPanelCtrl.fontSize--;
		TxtPanelCtrl.insert(txt_edit.getText(), TxtPanelCtrl.getAttrset(),
				txt_edit, point);
		return false;
	}

	public static EditCtrl getInstance() {
		if (editctrl == null) {
			return new EditCtrl();
		}
		return editctrl;
	}

}
