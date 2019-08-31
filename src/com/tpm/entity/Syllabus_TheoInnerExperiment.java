package com.tpm.entity;

import java.util.HashSet;
import java.util.Set;

public class Syllabus_TheoInnerExperiment {
	
	private Integer syllabus_TheoInnerExperimentid;

	private Set<ApplicationSyllabus_TheoInnerExperiment> setApplicationSyllabus_TheoInnerExperiment = new HashSet<ApplicationSyllabus_TheoInnerExperiment>();
	private BaseExperiment_TheoInnerExperiment baseExperiment_TheoInnerExperiment;
	
	public BaseExperiment_TheoInnerExperiment getBaseExperiment_TheoInnerExperiment() {
		return baseExperiment_TheoInnerExperiment;
	}

	public void setBaseExperiment_TheoInnerExperiment(
			BaseExperiment_TheoInnerExperiment baseExperiment_TheoInnerExperiment) {
		this.baseExperiment_TheoInnerExperiment = baseExperiment_TheoInnerExperiment;
	}

	public Integer getSyllabus_TheoInnerExperimentid() {
		return syllabus_TheoInnerExperimentid;
	}

	public void setSyllabus_TheoInnerExperimentid(
			Integer syllabus_TheoInnerExperimentid) {
		this.syllabus_TheoInnerExperimentid = syllabus_TheoInnerExperimentid;
	}

	public Set<ApplicationSyllabus_TheoInnerExperiment> getSetApplicationSyllabus_TheoInnerExperiment() {
		return setApplicationSyllabus_TheoInnerExperiment;
	}

	public void setSetApplicationSyllabus_TheoInnerExperiment(
			Set<ApplicationSyllabus_TheoInnerExperiment> setApplicationSyllabus_TheoInnerExperiment) {
		this.setApplicationSyllabus_TheoInnerExperiment = setApplicationSyllabus_TheoInnerExperiment;
	}
	
	
}
