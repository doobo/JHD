package com.isiav.dao.factory;

import com.isiav.dao.dao.IOFilesDao;
import com.isiav.dao.impl.IOImpl;

public class Factory {
	private static IOFilesDao io= null;
	
	public static IOFilesDao getInstance(){
		if(io==null){
			return io = new IOImpl();
		}
		return io;
	}
}
