package com.tpm.entity;

public class NoticeFile {
	private Integer noticefileid;
	private String noticefilename;
	private String noticefiletime;
	private String noticefilepath;
	private User user;
	public Integer getNoticefileid() {
		return noticefileid;
	}
	public void setNoticefileid(Integer noticefileid) {
		this.noticefileid = noticefileid;
	}
	public String getNoticefilename() {
		return noticefilename;
	}
	public void setNoticefilename(String noticefilename) {
		this.noticefilename = noticefilename;
	}
	public String getNoticefiletime() {
		return noticefiletime;
	}
	public void setNoticefiletime(String noticefiletime) {
		this.noticefiletime = noticefiletime;
	}
	public String getNoticefilepath() {
		return noticefilepath;
	}
	public void setNoticefilepath(String noticefilepath) {
		this.noticefilepath = noticefilepath;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
