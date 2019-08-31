package com.tpm.entity;

import java.util.HashSet;
import java.util.Set;

public class Professional {
	private String professionalid;
	private String professionalname;
	private Department department;
	private Set<TheoreticalLesson> setTheoreticalLesson = new HashSet<TheoreticalLesson>();
	private Set<PracticeLesson> setPracticeLesson = new HashSet<PracticeLesson>();
	private Set<CurriculumMatrix> setCurriculumMatrix = new HashSet<CurriculumMatrix>();	
	private Set<ApplicationSyllabus> setApplicationSyllabus = new HashSet<ApplicationSyllabus>();
	private Set<ApplicationSyllabus_FieldWork> setApplicationSyllabus_FieldWork = new HashSet<ApplicationSyllabus_FieldWork>();
	private Set<ApplicationSyllabus_CourseDesign> setApplicationSyllabus_CourseDesign = new HashSet<ApplicationSyllabus_CourseDesign>();
	private Set<ApplicationSyllabus_Gra> setApplicationSyllabus_Gra = new HashSet<ApplicationSyllabus_Gra>();
	private Set<ApplicationSyllabus_InnerExperiment> setApplicationSyllabus_InnerExperiment = new HashSet<ApplicationSyllabus_InnerExperiment>();
	private Set<ApplicationSyllabus_TheoInnerExperiment> setApplicationSyllabus_TheoInnerExperiment = new HashSet<ApplicationSyllabus_TheoInnerExperiment>();
	private Set<ApplicationMainTainOfPT> setApplicationMainTainOfPT = new HashSet<ApplicationMainTainOfPT>();
	private Set<ApplicationTrainingEvents> setApplicationTrainingEvents = new HashSet<ApplicationTrainingEvents>();
	private Set<Topology> setTopology = new HashSet<Topology>();
	public Set<Topology> getSetTopology() {
		return setTopology;
	}
	public void setSetTopology(Set<Topology> setTopology) {
		this.setTopology = setTopology;
	}
	public Set<CurriculumMatrix> getSetCurriculumMatrix() {
		return setCurriculumMatrix;
	}
	public void setSetCurriculumMatrix(Set<CurriculumMatrix> setCurriculumMatrix) {
		this.setCurriculumMatrix = setCurriculumMatrix;
	}
	public Set<ApplicationSyllabus_TheoInnerExperiment> getSetApplicationSyllabus_TheoInnerExperiment() {
		return setApplicationSyllabus_TheoInnerExperiment;
	}
	public void setSetApplicationSyllabus_TheoInnerExperiment(
			Set<ApplicationSyllabus_TheoInnerExperiment> setApplicationSyllabus_TheoInnerExperiment) {
		this.setApplicationSyllabus_TheoInnerExperiment = setApplicationSyllabus_TheoInnerExperiment;
	}
	public Set<ApplicationSyllabus_InnerExperiment> getSetApplicationSyllabus_InnerExperiment() {
		return setApplicationSyllabus_InnerExperiment;
	}
	public void setSetApplicationSyllabus_InnerExperiment(
			Set<ApplicationSyllabus_InnerExperiment> setApplicationSyllabus_InnerExperiment) {
		this.setApplicationSyllabus_InnerExperiment = setApplicationSyllabus_InnerExperiment;
	}
	public Set<ApplicationSyllabus_Gra> getSetApplicationSyllabus_Gra() {
		return setApplicationSyllabus_Gra;
	}
	public void setSetApplicationSyllabus_Gra(
			Set<ApplicationSyllabus_Gra> setApplicationSyllabus_Gra) {
		this.setApplicationSyllabus_Gra = setApplicationSyllabus_Gra;
	}
	public Set<ApplicationSyllabus_CourseDesign> getSetApplicationSyllabus_CourseDesign() {
		return setApplicationSyllabus_CourseDesign;
	}
	public void setSetApplicationSyllabus_CourseDesign(
			Set<ApplicationSyllabus_CourseDesign> setApplicationSyllabus_CourseDesign) {
		this.setApplicationSyllabus_CourseDesign = setApplicationSyllabus_CourseDesign;
	}
	public Set<ApplicationSyllabus_FieldWork> getSetApplicationSyllabus_FieldWork() {
		return setApplicationSyllabus_FieldWork;
	}
	public void setSetApplicationSyllabus_FieldWork(
			Set<ApplicationSyllabus_FieldWork> setApplicationSyllabus_FieldWork) {
		this.setApplicationSyllabus_FieldWork = setApplicationSyllabus_FieldWork;
	}
	public Set<ApplicationSyllabus> getSetApplicationSyllabus() {
		return setApplicationSyllabus;
	}
	public void setSetApplicationSyllabus(
			Set<ApplicationSyllabus> setApplicationSyllabus) {
		this.setApplicationSyllabus = setApplicationSyllabus;
	}

	public Set<PracticeLesson> getSetPracticeLesson() {
		return setPracticeLesson;
	}
	public void setSetPracticeLesson(Set<PracticeLesson> setPracticeLesson) {
		this.setPracticeLesson = setPracticeLesson;
	}
	public String getProfessionalid() {
		return professionalid;
	}
	public void setProfessionalid(String professionalid) {
		this.professionalid = professionalid;
	}
	public String getProfessionalname() {
		return professionalname;
	}
	public void setProfessionalname(String professionalname) {
		this.professionalname = professionalname;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Set<TheoreticalLesson> getSetTheoreticalLesson() {
		return setTheoreticalLesson;
	}
	public void setSetTheoreticalLesson(Set<TheoreticalLesson> setTheoreticalLesson) {
		this.setTheoreticalLesson = setTheoreticalLesson;
	}
	public Set<ApplicationMainTainOfPT> getSetApplicationMainTainOfPT() {
		return setApplicationMainTainOfPT;
	}
	public void setSetApplicationMainTainOfPT(
			Set<ApplicationMainTainOfPT> setApplicationMainTainOfPT) {
		this.setApplicationMainTainOfPT = setApplicationMainTainOfPT;
	}
	public Set<ApplicationTrainingEvents> getSetApplicationTrainingEvents() {
		return setApplicationTrainingEvents;
	}
	public void setSetApplicationTrainingEvents(
			Set<ApplicationTrainingEvents> setApplicationTrainingEvents) {
		this.setApplicationTrainingEvents = setApplicationTrainingEvents;
	}

}
