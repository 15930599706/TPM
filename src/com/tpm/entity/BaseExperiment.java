package com.tpm.entity;

import java.util.HashSet;
import java.util.Set;

public class BaseExperiment {
	private Integer baseExperimentid;
	private String applicable;
	private String schedule;
	private String assess;
	private Set<Syllabus_InnerExperiment> setSyllabus_InnerExperiment = new HashSet<Syllabus_InnerExperiment>();

	public Set<Syllabus_InnerExperiment> getSetSyllabus_InnerExperiment() {
		return setSyllabus_InnerExperiment;
	}
	public void setSetSyllabus_InnerExperiment(
			Set<Syllabus_InnerExperiment> setSyllabus_InnerExperiment) {
		this.setSyllabus_InnerExperiment = setSyllabus_InnerExperiment;
	}
	public Integer getBaseExperimentid() {
		return baseExperimentid;
	}
	public void setBaseExperimentid(Integer baseExperimentid) {
		this.baseExperimentid = baseExperimentid;
	}
	public String getApplicable() {
		return applicable;
	}
	public void setApplicable(String applicable) {
		this.applicable = applicable;
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
