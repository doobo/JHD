package com.isiav.dao.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.isiav.dao.dao.IOFilesDao;
import com.isiav.vo.JHDTree;

public class IOImpl implements IOFilesDao {

	@Override
	public JHDTree readFiles(String filename) {
		String str = "books";
		try {
			ObjectInputStream in = new ObjectInputStream(
					new BufferedInputStream(
					new FileInputStream(str+File.separator+filename+".properties")));
			JHDTree tree = (JHDTree) in.readObject();
			in.close();
			return tree;
		} catch (FileNotFoundException e) {
			System.out.println("文件不存在，IOImpl.java");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean writeFiles(String filename, JHDTree tree) {
		String str = "books";
		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new BufferedOutputStream(
					new FileOutputStream(str+File.separator+filename+".properties")));
			out.writeObject(tree);
			out.close();
			return true;
		} catch (FileNotFoundException e) {
			System.out.println("文件不存在，IOImpl.java");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
