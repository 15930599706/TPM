package com.tpm.entity;

public class TrainingAnother {
	private Integer trainingAnotherid;
	private String extracurricular;//课外安排与要求
	private String businessPractice;//企业集中实践安排与要求
	private String businessTeacher;//企业教师授课安排与要求
	private Department department;
	public Integer getTrainingAnotherid() {
		return trainingAnotherid;
	}
	public void setTrainingAnotherid(Integer trainingAnotherid) {
		this.trainingAnotherid = trainingAnotherid;
	}
	public String getExtracurricular() {
		return extracurricular;
	}
	public void setExtracurricular(String extracurricular) {
		this.extracurricular = extracurricular;
	}
	public String getBusinessPractice() {
		return businessPractice;
	}
	public void setBusinessPractice(String businessPractice) {
		this.businessPractice = businessPractice;
	}
	public String getBusinessTeacher() {
		return businessTeacher;
	}
	public void setBusinessTeacher(String businessTeacher) {
		this.businessTeacher = businessTeacher;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}

}
