package com.isiav.ctrl;

import java.awt.Color;

import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class TxtPanelCtrl {
	
	public static int fontSize = 14;
	
	public static void insert(String str,AttributeSet attrset,JTextPane textPane,int point){
		Document docs=textPane.getDocument();//利用getDocument()方法取得JTextPane的Document instance.0
		str=str.trim();
		try{
			docs.remove(0, docs.getLength());
		    docs.insertString(0,str,attrset); 
		    //设置光标开始位置
		    textPane.setCaretPosition(point);
		}catch(BadLocationException ble){
		     System.out.println("BadLocationException:"+ble);
		}
 }
	
	public static SimpleAttributeSet getAttrset() {
		SimpleAttributeSet attrset=new SimpleAttributeSet();
		StyleConstants.setForeground(attrset,Color.black);
//		StyleConstants.setItalic(attrset,true);
		if(fontSize < 58 && fontSize>8 ){
			StyleConstants.setFontSize(attrset,fontSize);
		}else if(fontSize >= 58){
			StyleConstants.setFontSize(attrset,fontSize);
			fontSize = 58;
		}else if(fontSize<= 8){
			StyleConstants.setFontSize(attrset,fontSize);
			fontSize = 8;
		}
		return attrset;
	}
	
}
