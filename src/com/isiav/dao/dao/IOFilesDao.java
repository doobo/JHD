package com.isiav.dao.dao;


import com.isiav.vo.JHDTree;

public interface IOFilesDao {
	
	//�õ��ļ�����
	public JHDTree readFiles(String filename);
	
	//������д���ļ�
	public boolean writeFiles(String filenaem,JHDTree tree);
}
