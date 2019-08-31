package com.tpm.entity;

public class TeachCal {
	private Integer teachCalfileid;
	private String teachCalfilename;
	private String teachCalfiletime;
	private String teachCalfilepath;
	private User user;
	public Integer getTeachCalfileid() {
		return teachCalfileid;
	}
	public void setTeachCalfileid(Integer teachCalfileid) {
		this.teachCalfileid = teachCalfileid;
	}
	public String getTeachCalfilename() {
		return teachCalfilename;
	}
	public void setTeachCalfilename(String teachCalfilename) {
		this.teachCalfilename = teachCalfilename;
	}
	public String getTeachCalfiletime() {
		return teachCalfiletime;
	}
	public void setTeachCalfiletime(String teachCalfiletime) {
		this.teachCalfiletime = teachCalfiletime;
	}
	public String getTeachCalfilepath() {
		return teachCalfilepath;
	}
	public void setTeachCalfilepath(String teachCalfilepath) {
		this.teachCalfilepath = teachCalfilepath;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
