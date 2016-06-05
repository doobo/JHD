package com.isiav.ctrl;

import com.isiav.vo.JHDBean;
import com.isiav.vo.JHDTree;

public class StartCtrl {
	
	
	public boolean toSign(final String username,final String pwd){
	
		if(!(pwd.length()<6) && !(username.length()<5)){
			 new Thread(new Runnable(){  
				    public void run(){  
				    	JHDTree tree =  new JHDTree(2, new JHDBean(1, "Ù¤À¶¹Å·ç", "Doobo@foxmail.com", "×÷Õß£ºÙ¤À¶¹Å·ç\nÓÊÏä£ºdoobo@foxmail.com"));
				    	tree.addMap(0, new JHDBean(0,com.isiav.util.EncryptDecrypt.SHA1(username).trim(),com.isiav.util.EncryptDecrypt.encryptAES(pwd.trim(), username.trim()+"abc"),""));
						if(tree.addMap(1, new JHDBean(1, "×÷Õß", "Ù¤À¶¹Å·ç", "1-"+"Ù¤À¶¹Å·ç"+"  "+"doobo@foxmail.com"+"*" ))){
							com.isiav.dao.factory.Factory.getInstance().writeFiles("ÁãÁãÒ»ÎÄµµ",tree);
						}
				    }  
				}).start();
			return true;
		}
		return false;
	}
	
	public boolean toLogin(String username,String pwd){
		JHDBean bean = com.isiav.dao.factory.Factory.getInstance().readFiles("ÁãÁãÒ»ÎÄµµ").getMap().get(0);
		if(com.isiav.util.EncryptDecrypt.SHA1(username.trim()).equals(bean.getTitle()) 
				&& com.isiav.util.EncryptDecrypt.encryptAES(pwd.trim(),username.trim()+"abc").equals(bean.getLable())){
					return true;
		}
		return false;
	}
}
