package com.tpm.entity;

import java.util.HashSet;
import java.util.Set;

public class ConCourseDesign {
	private Integer conCourseDesignid;
	private String content;
	private String request;
	private Set<Syllabus_CourseDesign> setSyllabus_CourseDesign = new HashSet<Syllabus_CourseDesign>();
	
	public Set<Syllabus_CourseDesign> getSetSyllabus_CourseDesign() {
		return setSyllabus_CourseDesign;
	}
	public void setSetSyllabus_CourseDesign(
			Set<Syllabus_CourseDesign> setSyllabus_CourseDesign) {
		this.setSyllabus_CourseDesign = setSyllabus_CourseDesign;
	}
	public Integer getConCourseDesignid() {
		return conCourseDesignid;
	}
	public void setConCourseDesignid(Integer conCourseDesignid) {
		this.conCourseDesignid = conCourseDesignid;
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
