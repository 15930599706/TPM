package com.tpm.entity;

//关联表
public class DistributeHourRelateExperProject {

	private Integer relateid;
	
	private DistributeHour_InnerExperiment innerExperiment;
	
	private ExpermentProject expermentProject;

	public Integer getRelateid() {
		return relateid;
	}

	public void setRelateid(Integer relateid) {
		this.relateid = relateid;
	}

	public DistributeHour_InnerExperiment getInnerExperiment() {
		return innerExperiment;
	}

	public void setInnerExperiment(DistributeHour_InnerExperiment innerExperiment) {
		this.innerExperiment = innerExperiment;
	}

	public ExpermentProject getExpermentProject() {
		return expermentProject;
	}

	public void setExpermentProject(ExpermentProject expermentProject) {
		this.expermentProject = expermentProject;
	}
	
	
}
