package com.isiav.dao.dao;


import com.isiav.vo.JHDTree;

public interface IOFilesDao {
	
	//得到文件内容
	public JHDTree readFiles(String filename);
	
	//把内容写入文件
	public boolean writeFiles(String filenaem,JHDTree tree);
}
