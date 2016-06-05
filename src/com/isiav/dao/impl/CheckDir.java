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
	 * @param book 书名
	 * @return boolean
	 * @exception IOException
	 * 创建必要的文件
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
	 * @return 所有的书名
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
	 * @return 是否存在书名
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
	 * @return 目录books是否存在
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
