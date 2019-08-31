package com.tpm.entity;

public class ExperimentContent {
	private Integer experimentContentid;
	public Integer getExperimentContentid() {
		return experimentContentid;
	}
	public void setExperimentContentid(Integer experimentContentid) {
		this.experimentContentid = experimentContentid;
	}
	private String num;
	private String aim;
	private String content;
	private String syllabusID;
	
	public String getSyllabusID() {
		return syllabusID;
	}
	public void setSyllabusID(String syllabusID) {
		this.syllabusID = syllabusID;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAim() {
		return aim;
	}
	public void setAim(String aim) {
		this.aim = aim;
	}
}
