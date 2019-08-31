package com.tpm.entity;

public class IndexSelect {
	private Integer indexSelectid;	//指标点选择
	private Ability ability;	//毕业要求
	private Analysis analisis;	//指标分解
	private String syllabusID;
	
	public String getSyllabusID() {
		return syllabusID;
	}
	public void setSyllabusID(String syllabusID) {
		this.syllabusID = syllabusID;
	}

	public Integer getIndexSelectid() {
		return indexSelectid;
	}
	public void setIndexSelectid(Integer indexSelectid) {
		this.indexSelectid = indexSelectid;
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
	

}
