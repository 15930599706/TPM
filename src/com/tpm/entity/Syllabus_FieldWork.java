package com.tpm.entity;

import java.util.HashSet;
import java.util.Set;

public class Syllabus_FieldWork {
	
	private Integer syllabus_FieldWorkID;
	private FieldWork fieldWork;
	private FieldContent fieldContent;
	
	private Set<ApplicationSyllabus_FieldWork> setApplicationSyllabus_FieldWork = new HashSet<ApplicationSyllabus_FieldWork>();

	public Set<ApplicationSyllabus_FieldWork> getSetApplicationSyllabus_FieldWork() {
		return setApplicationSyllabus_FieldWork;
	}
	public void setSetApplicationSyllabus_FieldWork(
			Set<ApplicationSyllabus_FieldWork> setApplicationSyllabus_FieldWork) {
		this.setApplicationSyllabus_FieldWork = setApplicationSyllabus_FieldWork;
	}
	public Integer getSyllabus_FieldWorkID() {
		return syllabus_FieldWorkID;
	}
	public void setSyllabus_FieldWorkID(Integer syllabus_FieldWorkID) {
		this.syllabus_FieldWorkID = syllabus_FieldWorkID;
	}
	public FieldWork getFieldWork() {
		return fieldWork;
	}
	public void setFieldWork(FieldWork fieldWork) {
		this.fieldWork = fieldWork;
	}
	public FieldContent getFieldContent() {
		return fieldContent;
	}
	public void setFieldContent(FieldContent fieldContent) {
		this.fieldContent = fieldContent;
	}
	
	
}
