package com.tpm.entity;

import java.util.HashSet;
import java.util.Set;

import com.tpm.entity.Syllabus;

public class TeaMethodTheo {
	private Integer teaMethodTheoid;
	private String teaMethod;
	private String teaok;
	private String scoreok;
	
	private Set<Syllabus> setSyllabus = new HashSet<Syllabus>();
	
	public Set<Syllabus> getSetSyllabus() {
		return setSyllabus;
	}
	public void setSetSyllabus(Set<Syllabus> setSyllabus) {
		this.setSyllabus = setSyllabus;
	}
	public Integer getTeaMethodTheoid() {
		return teaMethodTheoid;
	}
	public void setTeaMethodTheoid(Integer teaMethodTheoid) {
		this.teaMethodTheoid = teaMethodTheoid;
	}
	public String getTeaMethod() {
		return teaMethod;
	}
	public void setTeaMethod(String teaMethod) {
		this.teaMethod = teaMethod;
	}
	public String getTeaok() {
		return teaok;
	}
	public void setTeaok(String teaok) {
		this.teaok = teaok;
	}
	public String getScoreok() {
		return scoreok;
	}
	public void setScoreok(String scoreok) {
		this.scoreok = scoreok;
	}
}
