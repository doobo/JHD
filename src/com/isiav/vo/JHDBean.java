package com.isiav.vo;

import java.io.Serializable;

public class JHDBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7748650966249338522L;
	private int id;
	private String title,lable,content;
	
	public JHDBean(){
		
	}
	
	public JHDBean(int id,String title,String lable,String content){
		setId(id);
		setTitle(title);
		setLable(lable);
		setContent(content);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLable() {
		return lable;
	}
	public void setLable(String lable) {
		this.lable = lable;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JHDBean other = (JHDBean) obj;
		if (id != other.id)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "JHDBean [id=" + id + ", title=" + title + ", lable=" + lable
				+ ", content=" + content + "]";
	}
	
	
}
