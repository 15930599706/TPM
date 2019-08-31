package com.tpm.entity;

import java.util.HashSet;
import java.util.Set;


public class Curriculum {
	private String curriculumid;
	private String curriculumCname;
	private String curriculumEname;
	private String credit;//学分
	private String hoursOfWeek;//周学时
	private String hoursOfALL;//总学时
	private String hoursOfClass;//上课学时
	private String hoursOfExp;//实验学时
	private String hoursOfCom;//上机学时
	private String hoursOfPractice;//实践学时
	private String courseLei;//课程类别
	private String courseCategory;//课程归属
	private NatureOfCourse natureOfCourse;//课程性质
	private String courseIntroduction;//课程简介
	private College college;//开课学院
	private String curriculumpingtai;//课程平台
	private String newcurriculum;//新添课程
	private Set<PracticeLesson> setPracticeLesson = new HashSet<PracticeLesson>();
	private Set<TheoreticalLesson> setTheoreticalLesson = new HashSet<TheoreticalLesson>();
	private Set<CurriculumMatrix> setCurriculumMatrix = new HashSet<CurriculumMatrix>();
	
	private Set<ApplicationSyllabus> setApplicationSyllabus = new HashSet<ApplicationSyllabus>();
	private Set<ApplicationSyllabus_FieldWork> setApplicationSyllabus_FieldWork = new HashSet<ApplicationSyllabus_FieldWork>();
	private Set<ApplicationSyllabus_CourseDesign> setApplicationSyllabus_CourseDesign = new HashSet<ApplicationSyllabus_CourseDesign>();
	private Set<ApplicationSyllabus_Gra> setApplicationSyllabus_Gra = new HashSet<ApplicationSyllabus_Gra>();
	private Set<ApplicationSyllabus_InnerExperiment> setApplicationSyllabus_InnerExperiment = new HashSet<ApplicationSyllabus_InnerExperiment>();
	private Set<ApplicationSyllabus_TheoInnerExperiment> setApplicationSyllabus_TheoInnerExperiment = new HashSet<ApplicationSyllabus_TheoInnerExperiment>();

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
	
	public Set<CurriculumMatrix> getSetCurriculumMatrix() {
		return setCurriculumMatrix;
	}
	public void setSetCurriculumMatrix(Set<CurriculumMatrix> setCurriculumMatrix) {
		this.setCurriculumMatrix = setCurriculumMatrix;
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
	public String getCurriculumid() {
		return curriculumid;
	}
	public void setCurriculumid(String curriculumid) {
		this.curriculumid = curriculumid;
	}
	public String getCurriculumCname() {
		return curriculumCname;
	}
	public void setCurriculumCname(String curriculumCname) {
		this.curriculumCname = curriculumCname;
	}
	public String getCurriculumEname() {
		return curriculumEname;
	}
	public void setCurriculumEname(String curriculumEname) {
		this.curriculumEname = curriculumEname;
	}
	public String getCredit() {
		return credit;
	}
	public void setCredit(String credit) {
		this.credit = credit;
	}
	public String getHoursOfWeek() {
		return hoursOfWeek;
	}
	public void setHoursOfWeek(String hoursOfWeek) {
		this.hoursOfWeek = hoursOfWeek;
	}
	public String getHoursOfALL() {
		return hoursOfALL;
	}
	public void setHoursOfALL(String hoursOfALL) {
		this.hoursOfALL = hoursOfALL;
	}
	public String getHoursOfClass() {
		return hoursOfClass;
	}
	public void setHoursOfClass(String hoursOfClass) {
		this.hoursOfClass = hoursOfClass;
	}
	public String getHoursOfExp() {
		return hoursOfExp;
	}
	public void setHoursOfExp(String hoursOfExp) {
		this.hoursOfExp = hoursOfExp;
	}
	public String getHoursOfCom() {
		return hoursOfCom;
	}
	public void setHoursOfCom(String hoursOfCom) {
		this.hoursOfCom = hoursOfCom;
	}
	public String getHoursOfPractice() {
		return hoursOfPractice;
	}
	public void setHoursOfPractice(String hoursOfPractice) {
		this.hoursOfPractice = hoursOfPractice;
	}
	public String getCourseLei() {
		return courseLei;
	}
	public void setCourseLei(String courseLei) {
		this.courseLei = courseLei;
	}
	public String getCourseCategory() {
		return courseCategory;
	}
	public void setCourseCategory(String courseCategory) {
		this.courseCategory = courseCategory;
	}
	public NatureOfCourse getNatureOfCourse() {
		return natureOfCourse;
	}
	public void setNatureOfCourse(NatureOfCourse natureOfCourse) {
		this.natureOfCourse = natureOfCourse;
	}
	public String getCourseIntroduction() {
		return courseIntroduction;
	}
	public void setCourseIntroduction(String courseIntroduction) {
		this.courseIntroduction = courseIntroduction;
	}
	public College getCollege() {
		return college;
	}
	public void setCollege(College college) {
		this.college = college;
	}
	public String getCurriculumpingtai() {
		return curriculumpingtai;
	}
	public void setCurriculumpingtai(String curriculumpingtai) {
		this.curriculumpingtai = curriculumpingtai;
	}
	public String getNewcurriculum() {
		return newcurriculum;
	}
	public void setNewcurriculum(String newcurriculum) {
		this.newcurriculum = newcurriculum;
	}
	
	
}
