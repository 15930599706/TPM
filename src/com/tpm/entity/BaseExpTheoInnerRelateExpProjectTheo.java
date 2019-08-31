package com.tpm.entity;

public class BaseExpTheoInnerRelateExpProjectTheo {

	private Integer relateid;
	
	private DistributeHour_TheoInnerExperiment theoInnerExperiment;
	
	private ExpermentProject_Theo theo;
	public Integer getRelateid() {
		return relateid;
	}

	public void setRelateid(Integer relateid) {
		this.relateid = relateid;
	}

	public DistributeHour_TheoInnerExperiment getTheoInnerExperiment() {
		return theoInnerExperiment;
	}

	public void setTheoInnerExperiment(
			DistributeHour_TheoInnerExperiment theoInnerExperiment) {
		this.theoInnerExperiment = theoInnerExperiment;
	}

	public ExpermentProject_Theo getTheo() {
		return theo;
	}

	public void setTheo(ExpermentProject_Theo theo) {
		this.theo = theo;
	}
	
	
	
	
}
