package com.tpm.entity;

import java.util.HashSet;
import java.util.Set;

public class Syllabus_Gra {
	
	private Integer syllabus_Graid;
	private BasePractice basePractice;
	private ContentGra contentGra;
	private Set<ApplicationSyllabus_Gra> setApplicationSyllabus_Gra = new HashSet<ApplicationSyllabus_Gra>();

	public Integer getSyllabus_Graid() {
		return syllabus_Graid;
	}
	public void setSyllabus_Graid(Integer syllabus_Graid) {
		this.syllabus_Graid = syllabus_Graid;
	}
	public BasePractice getBasePractice() {
		return basePractice;
	}
	public void setBasePractice(BasePractice basePractice) {
		this.basePractice = basePractice;
	}
	public ContentGra getContentGra() {
		return contentGra;
	}
	public void setContentGra(ContentGra contentGra) {
		this.contentGra = contentGra;
	}
	public Set<ApplicationSyllabus_Gra> getSetApplicationSyllabus_Gra() {
		return setApplicationSyllabus_Gra;
	}
	public void setSetApplicationSyllabus_Gra(
			Set<ApplicationSyllabus_Gra> setApplicationSyllabus_Gra) {
		this.setApplicationSyllabus_Gra = setApplicationSyllabus_Gra;
	}
	
	
}
