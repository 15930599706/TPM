package com.tpm.entity;

import java.util.HashSet;
import java.util.Set;

public class ContentGra {
	private Integer contentGraid;
	private String content;
	private String request;
	private Set<Syllabus_Gra> setSyllabus_Gra = new HashSet<Syllabus_Gra>();
	
	public Set<Syllabus_Gra> getSetSyllabus_Gra() {
		return setSyllabus_Gra;
	}
	public void setSetSyllabus_Gra(Set<Syllabus_Gra> setSyllabus_Gra) {
		this.setSyllabus_Gra = setSyllabus_Gra;
	}
	public Integer getContentGraid() {
		return contentGraid;
	}
	public void setContentGraid(Integer contentGraid) {
		this.contentGraid = contentGraid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	
}
