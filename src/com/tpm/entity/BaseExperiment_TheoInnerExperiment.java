package com.tpm.entity;

import java.util.HashSet;
import java.util.Set;

public class BaseExperiment_TheoInnerExperiment {
	private Integer baseExperiment_TheoInnerExperimentid;
	private String schedule;
	private String assess;
	
	
	private Set<Syllabus_TheoInnerExperiment> setSyllabus_TheoInnerExperiment = new HashSet<Syllabus_TheoInnerExperiment>();

	public Set<Syllabus_TheoInnerExperiment> getSetSyllabus_TheoInnerExperiment() {
		return setSyllabus_TheoInnerExperiment;
	}
	public void setSetSyllabus_TheoInnerExperiment(
			Set<Syllabus_TheoInnerExperiment> setSyllabus_TheoInnerExperiment) {
		this.setSyllabus_TheoInnerExperiment = setSyllabus_TheoInnerExperiment;
	}
	public Integer getBaseExperiment_TheoInnerExperimentid() {
		return baseExperiment_TheoInnerExperimentid;
	}
	public void setBaseExperiment_TheoInnerExperimentid(
			Integer baseExperiment_TheoInnerExperimentid) {
		this.baseExperiment_TheoInnerExperimentid = baseExperiment_TheoInnerExperimentid;
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
