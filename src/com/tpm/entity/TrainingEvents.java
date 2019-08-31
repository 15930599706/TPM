package com.tpm.entity;

import java.util.HashSet;
import java.util.Set;

public class TrainingEvents {
	private Integer trainingEventsid;
	private String trainingEventsname;
	private String trainingEventscode;
	private String semester1;
	private String semester2;
	private String semester3;
	private String semester4;
	private String semester5;
	private String semester6;
	private String semester7;
	private String semester8;
	private String semester9;
	private String semester10;
	private Integer totaltag;
	private Department department;
	private Set<ApplicationTrainingEvents> setApplicationTrainingEvents = new HashSet<ApplicationTrainingEvents>();
	public Set<ApplicationTrainingEvents> getSetApplicationTrainingEvents() {
		return setApplicationTrainingEvents;
	}
	public void setSetApplicationTrainingEvents(
			Set<ApplicationTrainingEvents> setApplicationTrainingEvents) {
		this.setApplicationTrainingEvents = setApplicationTrainingEvents;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Integer getTrainingEventsid() {
		return trainingEventsid;
	}
	public void setTrainingEventsid(Integer trainingEventsid) {
		this.trainingEventsid = trainingEventsid;
	}
	public String getTrainingEventsname() {
		return trainingEventsname;
	}
	public void setTrainingEventsname(String trainingEventsname) {
		this.trainingEventsname = trainingEventsname;
	}
	public String getTrainingEventscode() {
		return trainingEventscode;
	}
	public void setTrainingEventscode(String trainingEventscode) {
		this.trainingEventscode = trainingEventscode;
	}
	public String getSemester1() {
		return semester1;
	}
	public void setSemester1(String semester1) {
		this.semester1 = semester1;
	}
	public String getSemester2() {
		return semester2;
	}
	public void setSemester2(String semester2) {
		this.semester2 = semester2;
	}
	public String getSemester3() {
		return semester3;
	}
	public void setSemester3(String semester3) {
		this.semester3 = semester3;
	}
	public String getSemester4() {
		return semester4;
	}
	public void setSemester4(String semester4) {
		this.semester4 = semester4;
	}
	public String getSemester5() {
		return semester5;
	}
	public void setSemester5(String semester5) {
		this.semester5 = semester5;
	}
	public String getSemester6() {
		return semester6;
	}
	public void setSemester6(String semester6) {
		this.semester6 = semester6;
	}
	public String getSemester7() {
		return semester7;
	}
	public void setSemester7(String semester7) {
		this.semester7 = semester7;
	}
	public String getSemester8() {
		return semester8;
	}
	public void setSemester8(String semester8) {
		this.semester8 = semester8;
	}
	public String getSemester9() {
		return semester9;
	}
	public void setSemester9(String semester9) {
		this.semester9 = semester9;
	}
	public String getSemester10() {
		return semester10;
	}
	public void setSemester10(String semester10) {
		this.semester10 = semester10;
	}
	public Integer getTotaltag() {
		return totaltag;
	}
	public void setTotaltag(Integer totaltag) {
		this.totaltag = totaltag;
	}
	
}
