package com.tpm.entity;

import java.util.HashSet;
import java.util.Set;

public class Experiment {
	private String experimentid;
	private String experimentname;
	private College college;

	private Set<TheoreticalLesson> setTheoreticalLesson = new HashSet<TheoreticalLesson>();
	private Set<User> setUser = new HashSet<User>();
	
	private Set<PracticeLesson> setPracticeLessonTeachC = new HashSet<PracticeLesson>();
	public Set<PracticeLesson> getSetPracticeLessonTeachC() {
		return setPracticeLessonTeachC;
	}
	public void setSetPracticeLessonTeachC(
			Set<PracticeLesson> setPracticeLessonTeachC) {
		this.setPracticeLessonTeachC = setPracticeLessonTeachC;
	}
	
	private Set<ExperimentLog> setExperiment_before = new HashSet<ExperimentLog>();
	private Set<ExperimentLog> setExperiment_after = new HashSet<ExperimentLog>();

	
	public Set<ExperimentLog> getSetExperiment_before() {
		return setExperiment_before;
	}
	public void setSetExperiment_before(Set<ExperimentLog> setExperiment_before) {
		this.setExperiment_before = setExperiment_before;
	}
	public Set<ExperimentLog> getSetExperiment_after() {
		return setExperiment_after;
	}
	public void setSetExperiment_after(Set<ExperimentLog> setExperiment_after) {
		this.setExperiment_after = setExperiment_after;
	}
	public Set<User> getSetUser() {
		return setUser;
	}
	public void setSetUser(Set<User> setUser) {
		this.setUser = setUser;
	}
	public Set<TheoreticalLesson> getSetTheoreticalLesson() {
		return setTheoreticalLesson;
	}
	public void setSetTheoreticalLesson(Set<TheoreticalLesson> setTheoreticalLesson) {
		this.setTheoreticalLesson = setTheoreticalLesson;
	}
	public String getExperimentid() {
		return experimentid;
	}
	public void setExperimentid(String experimentid) {
		this.experimentid = experimentid;
	}
	public String getExperimentname() {
		return experimentname;
	}
	public void setExperimentname(String experimentname) {
		this.experimentname = experimentname;
	}
	
	public College getCollege() {
		return college;
	}
	public void setCollege(College college) {
		this.college = college;
	}

}
