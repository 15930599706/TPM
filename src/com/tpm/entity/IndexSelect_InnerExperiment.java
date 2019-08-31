package com.tpm.entity;

public class IndexSelect_InnerExperiment {
	private Integer indexSelect_InnerExperimentid;	//指标点选择
	private Ability ability;	//毕业要求
	private Analysis analisis;	//指标分解
	private String syllabus_InnerExperimentid;
	public Integer getIndexSelect_InnerExperimentid() {
		return indexSelect_InnerExperimentid;
	}
	public void setIndexSelect_InnerExperimentid(
			Integer indexSelect_InnerExperimentid) {
		this.indexSelect_InnerExperimentid = indexSelect_InnerExperimentid;
	}
	public Ability getAbility() {
		return ability;
	}
	public void setAbility(Ability ability) {
		this.ability = ability;
	}
	public Analysis getAnalisis() {
		return analisis;
	}
	public void setAnalisis(Analysis analisis) {
		this.analisis = analisis;
	}
	public String getSyllabus_InnerExperimentid() {
		return syllabus_InnerExperimentid;
	}
	public void setSyllabus_InnerExperimentid(String syllabus_InnerExperimentid) {
		this.syllabus_InnerExperimentid = syllabus_InnerExperimentid;
	}
	
	

}
