package com.tpm.entity;

import java.util.HashSet;
import java.util.Set;

public class College {
	private String collegeid;
	private String collegeCname;
	private String collegeEname;
	private String collegeIntroduction;
	private Set<User> setUser = new HashSet<User>();
	private Set<Department> setDepartment = new HashSet<Department>();
	private Set<Curriculum> setCurriculum = new HashSet<Curriculum>();
	private Set<Experiment> setExperiment = new HashSet<Experiment>();
	
	
	public Set<Experiment> getSetExperiment() {
		return setExperiment;
	}
	public void setSetExperiment(Set<Experiment> setExperiment) {
		this.setExperiment = setExperiment;
	}
	public Set<Curriculum> getSetCurriculum() {
		return setCurriculum;
	}
	public void setSetCurriculum(Set<Curriculum> setCurriculum) {
		this.setCurriculum = setCurriculum;
	}
	public Set<User> getSetUser() {
		return setUser;
	}
	public void setSetUser(Set<User> setUser) {
		this.setUser = setUser;
	}
	public Set<Department> getSetDepartment() {
		return setDepartment;
	}
	public void setSetDepartment(Set<Department> setDepartment) {
		this.setDepartment = setDepartment;
	}
	public String getCollegeid() {
		return collegeid;
	}
	public void setCollegeid(String collegeid) {
		this.collegeid = collegeid;
	}
	public String getCollegeCname() {
		return collegeCname;
	}
	public void setCollegeCname(String collegeCname) {
		this.collegeCname = collegeCname;
	}
	public String getCollegeEname() {
		return collegeEname;
	}
	public void setCollegeEname(String collegeEname) {
		this.collegeEname = collegeEname;
	}
	public String getCollegeIntroduction() {
		return collegeIntroduction;
	}
	public void setCollegeIntroduction(String collegeIntroduction) {
		this.collegeIntroduction = collegeIntroduction;
	}
	
	
}
