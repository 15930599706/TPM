package com.tpm.entity;

import java.util.HashSet;
import java.util.Set;

public class BaseTheo {
	private Integer baseTheoid;
	private String firstcurriculum;
	private String plan;
	private Set<Syllabus> setSyllabus = new HashSet<Syllabus>();
	
	
	public Set<Syllabus> getSetSyllabus() {
		return setSyllabus;
	}
	public void setSetSyllabus(Set<Syllabus> setSyllabus) {
		this.setSyllabus = setSyllabus;
	}
	public Integer getBaseTheoid() {
		return baseTheoid;
	}
	public void setBaseTheoid(Integer baseTheoid) {
		this.baseTheoid = baseTheoid;
	}
	public String getFirstcurriculum() {
		return firstcurriculum;
	}
	public void setFirstcurriculum(String firstcurriculum) {
		this.firstcurriculum = firstcurriculum;
	}
	public String getPlan() {
		return plan;
	}
	public void setPlan(String plan) {
		this.plan = plan;
	}
	
}
