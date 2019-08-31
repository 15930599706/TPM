package com.tpm.entity;

import java.util.HashSet;
import java.util.Set;

public class FieldWork {
	private Integer fieldWorkid;
	private String applicable;
	private String maptheo;
	private String distribPer;
	private String assess;
	private Set<Syllabus_FieldWork> setSyllabus_FieldWork = new HashSet<Syllabus_FieldWork>();

	public Set<Syllabus_FieldWork> getSetSyllabus_FieldWork() {
		return setSyllabus_FieldWork;
	}
	public void setSetSyllabus_FieldWork(
			Set<Syllabus_FieldWork> setSyllabus_FieldWork) {
		this.setSyllabus_FieldWork = setSyllabus_FieldWork;
	}
	public Integer getFieldWorkid() {
		return fieldWorkid;
	}
	public void setFieldWorkid(Integer fieldWorkid) {
		this.fieldWorkid = fieldWorkid;
	}
	public String getApplicable() {
		return applicable;
	}
	public void setApplicable(String applicable) {
		this.applicable = applicable;
	}
	public String getMaptheo() {
		return maptheo;
	}
	public void setMaptheo(String maptheo) {
		this.maptheo = maptheo;
	}
	public String getDistribPer() {
		return distribPer;
	}
	public void setDistribPer(String distribPer) {
		this.distribPer = distribPer;
	}
	public String getAssess() {
		return assess;
	}
	public void setAssess(String assess) {
		this.assess = assess;
	}

}
