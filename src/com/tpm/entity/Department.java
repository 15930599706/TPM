package com.tpm.entity;

import java.util.HashSet;
import java.util.Set;

public class Department {
	private String departmentid;
	private String departmentCname;
	private String departmentEname;
	private String learningTime;
	private String degree;
	private String level;
	private String subjectCategory;
	private String departmentidOfCountry;
	private String categoryOfSelect;
	private College college;
	private Set<User> setUser = new HashSet<User>();
	private Set<Professional> setProfessional = new HashSet<Professional>();
	private Set<PPTrainingConcept> setPPTrainingConcept = new HashSet<PPTrainingConcept>();
	private Set<PTBasicInformation> setPTBasicInformation = new HashSet<PTBasicInformation>();
	private Set<Ability> setAbility = new HashSet<Ability>();
	private Set<Topology> setTopology = new HashSet<Topology>();
	private Set<TopologyTag> setTopologyTag = new HashSet<TopologyTag>();
	private Set<MainTainOfPT> setMainTainOfPT = new HashSet<MainTainOfPT>();
	private Set<MainTainOfPTTag> setMainTainOfPTTag = new HashSet<MainTainOfPTTag>();
	private Set<ApplicationMainTainOfPT> setApplicationMainTainOfPT = new HashSet<ApplicationMainTainOfPT>();
	private Set<TrainingEvents> setTrainingEvents = new HashSet<TrainingEvents>();
	private Set<ApplicationTrainingEvents> setApplicationTrainingEvents = new HashSet<ApplicationTrainingEvents>();
	private Set<PracticeLesson> setPracticeLesson = new HashSet<PracticeLesson>();
	private Set<TheoreticalLesson> setTheoreticalLesson = new HashSet<TheoreticalLesson>();
	private Set<TheoreticalLesson> setTheoreticalLessonTeach = new HashSet<TheoreticalLesson>();
	
	private Set<TheoreticalLesson> setTheoreticalLessonTeachC = new HashSet<TheoreticalLesson>();
	
	
	private Set<PracticeLesson> setPracticeLessonTeach = new HashSet<PracticeLesson>();
	
	private Set<ScoreThreshold> setScoreThreshold = new HashSet<ScoreThreshold>();
	private Set<TrainingAnother> setTrainingAnother = new HashSet<TrainingAnother>();
	
	private Set<ApplicationSyllabus> setApplicationSyllabus = new HashSet<ApplicationSyllabus>();
	private Set<ApplicationSyllabus_FieldWork> setApplicationSyllabus_FieldWork = new HashSet<ApplicationSyllabus_FieldWork>();
	private Set<ApplicationSyllabus_CourseDesign> setApplicationSyllabus_CourseDesign = new HashSet<ApplicationSyllabus_CourseDesign>();
	private Set<ApplicationSyllabus_Gra> setApplicationSyllabus_Gra = new HashSet<ApplicationSyllabus_Gra>();
	private Set<ApplicationSyllabus_InnerExperiment> setApplicationSyllabus_InnerExperiment = new HashSet<ApplicationSyllabus_InnerExperiment>();
	private Set<ApplicationSyllabus_TheoInnerExperiment> setApplicationSyllabus_TheoInnerExperiment = new HashSet<ApplicationSyllabus_TheoInnerExperiment>();

	
	public Set<TopologyTag> getSetTopologyTag() {
		return setTopologyTag;
	}
	public void setSetTopologyTag(Set<TopologyTag> setTopologyTag) {
		this.setTopologyTag = setTopologyTag;
	}
	public Set<TrainingAnother> getSetTrainingAnother() {
		return setTrainingAnother;
	}
	public void setSetTrainingAnother(Set<TrainingAnother> setTrainingAnother) {
		this.setTrainingAnother = setTrainingAnother;
	}
	public Set<ScoreThreshold> getSetScoreThreshold() {
		return setScoreThreshold;
	}
	public void setSetScoreThreshold(Set<ScoreThreshold> setScoreThreshold) {
		this.setScoreThreshold = setScoreThreshold;
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
	public Set<TheoreticalLesson> getSetTheoreticalLesson() {
		return setTheoreticalLesson;
	}
	public void setSetTheoreticalLesson(Set<TheoreticalLesson> setTheoreticalLesson) {
		this.setTheoreticalLesson = setTheoreticalLesson;
	}
	public Set<PracticeLesson> getSetPracticeLesson() {
		return setPracticeLesson;
	}
	public void setSetPracticeLesson(Set<PracticeLesson> setPracticeLesson) {
		this.setPracticeLesson = setPracticeLesson;
	}
	public Set<TrainingEvents> getSetTrainingEvents() {
		return setTrainingEvents;
	}
	public void setSetTrainingEvents(Set<TrainingEvents> setTrainingEvents) {
		this.setTrainingEvents = setTrainingEvents;
	}
	public Set<MainTainOfPT> getSetMainTainOfPT() {
		return setMainTainOfPT;
	}
	public void setSetMainTainOfPT(Set<MainTainOfPT> setMainTainOfPT) {
		this.setMainTainOfPT = setMainTainOfPT;
	}
	public Set<Topology> getSetTopology() {
		return setTopology;
	}
	public void setSetTopology(Set<Topology> setTopology) {
		this.setTopology = setTopology;
	}
	public Set<Ability> getSetAbility() {
		return setAbility;
	}
	public void setSetAbility(Set<Ability> setAbility) {
		this.setAbility = setAbility;
	}
	public Set<PTBasicInformation> getSetPTBasicInformation() {
		return setPTBasicInformation;
	}
	public void setSetPTBasicInformation(
			Set<PTBasicInformation> setPTBasicInformation) {
		this.setPTBasicInformation = setPTBasicInformation;
	}
	public Set<PPTrainingConcept> getSetPPTrainingConcept() {
		return setPPTrainingConcept;
	}
	public void setSetPPTrainingConcept(Set<PPTrainingConcept> setPPTrainingConcept) {
		this.setPPTrainingConcept = setPPTrainingConcept;
	}
	public Set<Professional> getSetProfessional() {
		return setProfessional;
	}
	public void setSetProfessional(Set<Professional> setProfessional) {
		this.setProfessional = setProfessional;
	}
	public Set<User> getSetUser() {
		return setUser;
	}
	public void setSetUser(Set<User> setUser) {
		this.setUser = setUser;
	}
	public String getDepartmentid() {
		return departmentid;
	}
	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}
	public String getDepartmentCname() {
		return departmentCname;
	}
	public void setDepartmentCname(String departmentCname) {
		this.departmentCname = departmentCname;
	}
	public String getDepartmentEname() {
		return departmentEname;
	}
	public void setDepartmentEname(String departmentEname) {
		this.departmentEname = departmentEname;
	}
	public String getLearningTime() {
		return learningTime;
	}
	public void setLearningTime(String learningTime) {
		this.learningTime = learningTime;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getSubjectCategory() {
		return subjectCategory;
	}
	public void setSubjectCategory(String subjectCategory) {
		this.subjectCategory = subjectCategory;
	}
	public String getDepartmentidOfCountry() {
		return departmentidOfCountry;
	}
	public void setDepartmentidOfCountry(String departmentidOfCountry) {
		this.departmentidOfCountry = departmentidOfCountry;
	}
	public String getCategoryOfSelect() {
		return categoryOfSelect;
	}
	public void setCategoryOfSelect(String categoryOfSelect) {
		this.categoryOfSelect = categoryOfSelect;
	}
	public College getCollege() {
		return college;
	}
	public void setCollege(College college) {
		this.college = college;
	}
	public Set<TheoreticalLesson> getSetTheoreticalLessonTeach() {
		return setTheoreticalLessonTeach;
	}
	public void setSetTheoreticalLessonTeach(
			Set<TheoreticalLesson> setTheoreticalLessonTeach) {
		this.setTheoreticalLessonTeach = setTheoreticalLessonTeach;
	}
	public Set<PracticeLesson> getSetPracticeLessonTeach() {
		return setPracticeLessonTeach;
	}
	public void setSetPracticeLessonTeach(Set<PracticeLesson> setPracticeLessonTeach) {
		this.setPracticeLessonTeach = setPracticeLessonTeach;
	}
	public Set<MainTainOfPTTag> getSetMainTainOfPTTag() {
		return setMainTainOfPTTag;
	}
	public void setSetMainTainOfPTTag(Set<MainTainOfPTTag> setMainTainOfPTTag) {
		this.setMainTainOfPTTag = setMainTainOfPTTag;
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
	
	public Set<TheoreticalLesson> getSetTheoreticalLessonTeachC() {
		return setTheoreticalLessonTeachC;
	}
	public void setSetTheoreticalLessonTeachC(
			Set<TheoreticalLesson> setTheoreticalLessonTeachC) {
		this.setTheoreticalLessonTeachC = setTheoreticalLessonTeachC;
	}
}
