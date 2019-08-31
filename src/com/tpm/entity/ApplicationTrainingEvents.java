package com.tpm.entity;

public class ApplicationTrainingEvents {
	private Integer applicationTrainingEventsid;
	private TrainingEvents trainingEvents;
	private Department department;
	private Professional professional;
	public Integer getApplicationTrainingEventsid() {
		return applicationTrainingEventsid;
	}
	public void setApplicationTrainingEventsid(Integer applicationTrainingEventsid) {
		this.applicationTrainingEventsid = applicationTrainingEventsid;
	}
	public TrainingEvents getTrainingEvents() {
		return trainingEvents;
	}
	public void setTrainingEvents(TrainingEvents trainingEvents) {
		this.trainingEvents = trainingEvents;
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
