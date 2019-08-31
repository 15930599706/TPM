package com.tpm.entity;

import java.util.HashSet;
import java.util.Set;

public class BasePractice {
	private Integer basePracticeid;

	private String topic;
	private String schedule;
	private String assess;

	
	private Set<Syllabus_Gra> setSyllabus_Gra = new HashSet<Syllabus_Gra>();
	
	public Set<Syllabus_Gra> getSetSyllabus_Gra() {
		return setSyllabus_Gra;
	}
	public void setSetSyllabus_Gra(Set<Syllabus_Gra> setSyllabus_Gra) {
		this.setSyllabus_Gra = setSyllabus_Gra;
	}
	public Integer getBasePracticeid() {
		return basePracticeid;
	}
	public void setBasePracticeid(Integer basePracticeid) {
		this.basePracticeid = basePracticeid;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getSchedule() {
		return schedule;
	}
	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	public String getAssess() {
		return assess;
	}
	public void setAssess(String assess) {
		this.assess = assess;
	}

}
