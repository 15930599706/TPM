package com.tpm.entity;

public class ApplicationSyllabus_CourseDesign {
	
	private Integer applicationSyllabus_CourseDesignid;
	private Syllabus_CourseDesign syllabus_CourseDesign;
	private Curriculum curriculum;
	private Department department;
	private Professional professional;
	public Integer getApplicationSyllabus_CourseDesignid() {
		return applicationSyllabus_CourseDesignid;
	}
	public void setApplicationSyllabus_CourseDesignid(
			Integer applicationSyllabus_CourseDesignid) {
		this.applicationSyllabus_CourseDesignid = applicationSyllabus_CourseDesignid;
	}
	public Syllabus_CourseDesign getSyllabus_CourseDesign() {
		return syllabus_CourseDesign;
	}
	public void setSyllabus_CourseDesign(Syllabus_CourseDesign syllabus_CourseDesign) {
		this.syllabus_CourseDesign = syllabus_CourseDesign;
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
