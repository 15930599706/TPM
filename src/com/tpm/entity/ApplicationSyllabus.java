package com.tpm.entity;

import com.tpm.entity.Syllabus;

public class ApplicationSyllabus {
	private Integer applicationSyllabusid;
	private Syllabus syllabus;
	private Curriculum curriculum;
	private Department department;
	private Professional professional;
	public Integer getApplicationSyllabusid() {
		return applicationSyllabusid;
	}
	public void setApplicationSyllabusid(Integer applicationSyllabusid) {
		this.applicationSyllabusid = applicationSyllabusid;
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
	public Syllabus getSyllabus() {
		return syllabus;
	}
	public void setSyllabus(Syllabus syllabus) {
		this.syllabus = syllabus;
	}

	
}
