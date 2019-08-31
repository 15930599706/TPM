package com.tpm.entity;

import java.util.HashSet;
import java.util.Set;

public class NatureOfCourse {
	private String natureOfCourseid;
	private String natureOfCoursename;
	private String natureOfCourseeasyname;
	private String natureOfCoursefor;
	private String isForSchoolChoose;
	private String chooseOrMust;
	private Set<Curriculum> setCurriculum = new HashSet<Curriculum>();
	
	public Set<Curriculum> getSetCurriculum() {
		return setCurriculum;
	}
	public void setSetCurriculum(Set<Curriculum> setCurriculum) {
		this.setCurriculum = setCurriculum;
	}
	public String getNatureOfCourseid() {
		return natureOfCourseid;
	}
	public void setNatureOfCourseid(String natureOfCourseid) {
		this.natureOfCourseid = natureOfCourseid;
	}
	public String getNatureOfCoursename() {
		return natureOfCoursename;
	}
	public void setNatureOfCoursename(String natureOfCoursename) {
		this.natureOfCoursename = natureOfCoursename;
	}
	public String getNatureOfCourseeasyname() {
		return natureOfCourseeasyname;
	}
	public void setNatureOfCourseeasyname(String natureOfCourseeasyname) {
		this.natureOfCourseeasyname = natureOfCourseeasyname;
	}
	public String getNatureOfCoursefor() {
		return natureOfCoursefor;
	}
	public void setNatureOfCoursefor(String natureOfCoursefor) {
		this.natureOfCoursefor = natureOfCoursefor;
	}
	public String getIsForSchoolChoose() {
		return isForSchoolChoose;
	}
	public void setIsForSchoolChoose(String isForSchoolChoose) {
		this.isForSchoolChoose = isForSchoolChoose;
	}
	public String getChooseOrMust() {
		return chooseOrMust;
	}
	public void setChooseOrMust(String chooseOrMust) {
		this.chooseOrMust = chooseOrMust;
	}
	
}
