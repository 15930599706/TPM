package com.tpm.entity;

public class DiscussContent {
	private Integer discussContentid;
	private String num;
	private String aim;
	private String content;
	private String implementation;
	private String request;
	private String syllabusID;
	
	public String getSyllabusID() {
		return syllabusID;
	}
	public void setSyllabusID(String syllabusID) {
		this.syllabusID = syllabusID;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getDiscussContentid() {
		return discussContentid;
	}
	public void setDiscussContentid(Integer discussContentid) {
		this.discussContentid = discussContentid;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getAim() {
		return aim;
	}
	public void setAim(String aim) {
		this.aim = aim;
	}
	public String getImplementation() {
		return implementation;
	}
	public void setImplementation(String implementation) {
		this.implementation = implementation;
	}
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}

}
