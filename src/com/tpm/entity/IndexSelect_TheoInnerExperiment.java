package com.tpm.entity;

public class IndexSelect_TheoInnerExperiment {
	private Integer indexSelect_TheoInnerExperimentid;	//指标点选择
	private Ability ability;	//毕业要求
	private Analysis analisis;	//指标分解
	private String syllabusid;
	public Integer getIndexSelect_TheoInnerExperimentid() {
		return indexSelect_TheoInnerExperimentid;
	}
	public void setIndexSelect_TheoInnerExperimentid(
			Integer indexSelect_TheoInnerExperimentid) {
		this.indexSelect_TheoInnerExperimentid = indexSelect_TheoInnerExperimentid;
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
	public String getSyllabusid() {
		return syllabusid;
	}
	public void setSyllabusid(String syllabusid) {
		this.syllabusid = syllabusid;
	}
	

}
