package com.tpm.entity;

public class ApplicationMainTainOfPT {
	private Integer applicationMainTainOfPTid;
	private MainTainOfPT mainTainOfPT;
	private Department department;
	private Professional professional;
	public Integer getApplicationMainTainOfPTid() {
		return applicationMainTainOfPTid;
	}
	public void setApplicationMainTainOfPTid(Integer applicationMainTainOfPTid) {
		this.applicationMainTainOfPTid = applicationMainTainOfPTid;
	}
	public MainTainOfPT getMainTainOfPT() {
		return mainTainOfPT;
	}
	public void setMainTainOfPT(MainTainOfPT mainTainOfPT) {
		this.mainTainOfPT = mainTainOfPT;
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
