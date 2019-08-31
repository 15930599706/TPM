package com.tpm.entity;

public class ApplicationSyllabus_FieldWork {
	
	private Integer applicationSyllabus_FieldWorkid;
	private Syllabus_FieldWork syllabus_FieldWork;
	private Curriculum curriculum;
	private Department department;
	private Professional professional;
	public Integer getApplicationSyllabus_FieldWorkid() {
		return applicationSyllabus_FieldWorkid;
	}
	public void setApplicationSyllabus_FieldWorkid(
			Integer applicationSyllabus_FieldWorkid) {
		this.applicationSyllabus_FieldWorkid = applicationSyllabus_FieldWorkid;
	}

	public Syllabus_FieldWork getSyllabus_FieldWork() {
		return syllabus_FieldWork;
	}
	public void setSyllabus_FieldWork(Syllabus_FieldWork syllabus_FieldWork) {
		this.syllabus_FieldWork = syllabus_FieldWork;
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
