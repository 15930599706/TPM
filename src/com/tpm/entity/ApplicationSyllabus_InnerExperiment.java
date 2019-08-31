package com.tpm.entity;

public class ApplicationSyllabus_InnerExperiment {
	
	private Integer applicationSyllabus_InnerExperimentid;
	private Syllabus_InnerExperiment syllabus_InnerExperiment;
	private Curriculum curriculum;
	private Department department;
	private Professional professional;
	public Integer getApplicationSyllabus_InnerExperimentid() {
		return applicationSyllabus_InnerExperimentid;
	}
	public void setApplicationSyllabus_InnerExperimentid(
			Integer applicationSyllabus_InnerExperimentid) {
		this.applicationSyllabus_InnerExperimentid = applicationSyllabus_InnerExperimentid;
	}
	public Syllabus_InnerExperiment getSyllabus_InnerExperiment() {
		return syllabus_InnerExperiment;
	}
	public void setSyllabus_InnerExperiment(
			Syllabus_InnerExperiment syllabus_InnerExperiment) {
		this.syllabus_InnerExperiment = syllabus_InnerExperiment;
	}
	public Curriculum getCurriculum() {
		return curriculum;
	}
	public void setCurriculum(Curriculum curriculum) {
		this.curriculum = curriculum;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Professional getProfessional() {
		return professional;
	}
	public void setProfessional(Professional professional) {
		this.professional = professional;
	}
	
	
}
