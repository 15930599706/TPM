package com.tpm.entity;

public class ExpermentProject {
	private Integer expermentProjectid;
	private String name;
	private String time;
	private String num;
	private String equipment;
	private String theory;
	private String request;
	private String syllabus_InnerExperimentid;
	

	public String getSyllabus_InnerExperimentid() {
		return syllabus_InnerExperimentid;
	}
	public void setSyllabus_InnerExperimentid(String syllabus_InnerExperimentid) {
		this.syllabus_InnerExperimentid = syllabus_InnerExperimentid;
	}
	public Integer getExpermentProjectid() {
		return expermentProjectid;
	}
	public void setExpermentProjectid(Integer expermentProjectid) {
		this.expermentProjectid = expermentProjectid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getEquipment() {
		return equipment;
	}
	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}
	public String getTheory() {
		return theory;
	}
	public void setTheory(String theory) {
		this.theory = theory;
	}
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	

}
