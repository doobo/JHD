package com.isiav.dao.impl;

import java.io.File;
import java.io.IOException;


public class CheckDir {
	
	private static CheckDir checkdir = null;
	
	File dir = new File("books");
	static{
		makeDir();
	}
	
	/**
	 * @author QieLan
	 * @param book ����
	 * @return boolean
	 * @exception IOException
	 * ������Ҫ���ļ�
	 */
	public boolean createBook(String book){
		if(!hasBook(book)){
		String str2 = dir.getPath() + File.separator+book+".properties";
		File file = new File(str2);
		try {
			file.createNewFile();
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return false;
	}
	
	/**
	 * @author QieLan
	 * @return ���е�����
	 */
	public String[] getDir(){
		 String []	dir_all = dir.list();
		 String str[] = new String[dir_all.length];
		 if(dir_all.length>0){
		 for(int i=0;i<dir_all.length;i++){
			 str[i]= dir_all[i].substring(0, dir_all[i].indexOf("."));
		 }
		 }
		 return str;
		}
	/**
	 * @author QieLan
	 * @param bookName 
	 * @return �Ƿ��������
	 */
	public boolean hasBook(String bookName){
		String dir[] = getDir();
		for(int i=0;i<dir.length;i++){
			if(dir[i].equals(bookName))
				return true;
		}
		return false;
	}
	
	/**
	 * @author QieLan
	 * @return Ŀ¼books�Ƿ����
	 */
	public static  boolean makeDir(){
		  if(!new File("books").exists()){
			  new File("books").mkdir();
		     return true;
		  }
		  return false;
	  }
	
	public static CheckDir getInstance(){
		if(checkdir==null){
			return new CheckDir();
		}
		return checkdir;
	}
	

}
