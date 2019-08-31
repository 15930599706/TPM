package com.tpm.entity;

public class Notice {
	private Integer noticeid;
	private User user;
	private String noticetime;
	private String noticetitle;
	private String noticecontent;
	public Integer getNoticeid() {
		return noticeid;
	}
	public void setNoticeid(Integer noticeid) {
		this.noticeid = noticeid;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getNoticetime() {
		return noticetime;
	}
	public void setNoticetime(String noticetime) {
		this.noticetime = noticetime;
	}
	public String getNoticetitle() {
		return noticetitle;
	}
	public void setNoticetitle(String noticetitle) {
		this.noticetitle = noticetitle;
	}
	public String getNoticecontent() {
		return noticecontent;
	}
	public void setNoticecontent(String noticecontent) {
		this.noticecontent = noticecontent;
	}
	
}
