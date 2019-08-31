package com.tpm.entity;

public class ApplicationSyllabus_Gra {
	
	private Integer ApplicationSyllabus_Graid;
	private Syllabus_Gra syllabus_Gra;
	private Curriculum curriculum;
	private Department department;
	private Professional professional;
	public Integer getApplicationSyllabus_Graid() {
		return ApplicationSyllabus_Graid;
	}
	public void setApplicationSyllabus_Graid(Integer applicationSyllabus_Graid) {
		ApplicationSyllabus_Graid = applicationSyllabus_Graid;
	}
	public Syllabus_Gra getSyllabus_Gra() {
		return syllabus_Gra;
	}
	public void setSyllabus_Gra(Syllabus_Gra syllabus_Gra) {
		this.syllabus_Gra = syllabus_Gra;
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
