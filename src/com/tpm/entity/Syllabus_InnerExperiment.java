package com.tpm.entity;

import java.util.HashSet;
import java.util.Set;

public class Syllabus_InnerExperiment {
	
	private Integer syllabus_InnerExperimentid;
	private BaseExperiment baseExperiment;
	private Set<ApplicationSyllabus_InnerExperiment> setApplicationSyllabus_InnerExperiment = new HashSet<ApplicationSyllabus_InnerExperiment>();
	public Integer getSyllabus_InnerExperimentid() {
		return syllabus_InnerExperimentid;
	}
	public void setSyllabus_InnerExperimentid(Integer syllabus_InnerExperimentid) {
		this.syllabus_InnerExperimentid = syllabus_InnerExperimentid;
	}
	public BaseExperiment getBaseExperiment() {
		return baseExperiment;
	}
	public void setBaseExperiment(BaseExperiment baseExperiment) {
		this.baseExperiment = baseExperiment;
	}
	public Set<ApplicationSyllabus_InnerExperiment> getSetApplicationSyllabus_InnerExperiment() {
		return setApplicationSyllabus_InnerExperiment;
	}
	public void setSetApplicationSyllabus_InnerExperiment(
			Set<ApplicationSyllabus_InnerExperiment> setApplicationSyllabus_InnerExperiment) {
		this.setApplicationSyllabus_InnerExperiment = setApplicationSyllabus_InnerExperiment;
	}
	
}
