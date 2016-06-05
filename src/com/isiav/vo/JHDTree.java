package com.isiav.vo;

import java.io.Serializable;
import java.util.TreeMap;

public class JHDTree implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4815652734692247277L;
	private TreeMap<Integer,JHDBean> map = new TreeMap<Integer,JHDBean>()  ;

	public TreeMap<Integer, JHDBean> getMap() {
		return map;
	}

	public void setMap(TreeMap<Integer, JHDBean> map) {
		this.map = map;
	}
	
	public boolean addMap(int i,JHDBean bean){
			getMap().put(i, bean);
		return true;
	}
	
	public boolean removeMap(int i){
		if(map.remove(i) != null){
			return true;
		}
		return false;
	}
	
	
	
	public JHDTree(){
		
	}
	
	public JHDTree(int i ,JHDBean bean){
		addMap(i,bean);
	}
	
	public JHDTree(int i ,JHDBean bean,String bookName){
		map  = com.isiav.dao.factory.Factory.getInstance().readFiles(bookName).getMap();
		addMap(i,bean);
	}
	
	public JHDTree(int i ,String bookName){
		map  = com.isiav.dao.factory.Factory.getInstance().readFiles(bookName).getMap();
		removeMap(i);
	}
	
}
