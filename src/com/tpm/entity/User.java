package com.tpm.entity;

import java.util.HashSet;
import java.util.Set;

public class User {//用户（老师）
	private String tnum;//职工号
	private String password;//登录密码
	private String username;//姓名
	private String sex;//性别
	private Integer adminlevel;//管理员级别
	private College college;//学院
	private Department department;//系
	private Experiment experiment;//实验室
	private Integer experimenterlevel;//实验员级别
	
	private Set<Notice> setNotice = new HashSet<Notice>();
	private Set<NoticeFile> setNoticeFile = new HashSet<NoticeFile>();
	private Set<TheoreticalLesson> setTheoreticalLesson = new HashSet<TheoreticalLesson>();
	private Set<PracticeLesson> setPracticeLesson = new HashSet<PracticeLesson>();
	private Set<TeachCal> setTeachCal = new HashSet<TeachCal>();

	private Set<TheoreticalLesson> setTheoreticalLesson_Experiment = new HashSet<TheoreticalLesson>();
	
	private Set<TheoreticalLesson> setTheoreticalLessonC = new HashSet<TheoreticalLesson>();
	
	public Set<PracticeLesson> getSetPracticeLessonC() {
		return setPracticeLessonC;
	}
	public void setSetPracticeLessonC(Set<PracticeLesson> setPracticeLessonC) {
		this.setPracticeLessonC = setPracticeLessonC;
	}
	private Set<PracticeLesson> setPracticeLessonC = new HashSet<PracticeLesson>();
	private Set<ExperimentLog> setUser_modify = new HashSet<ExperimentLog>();
	private Set<ExperimentLog> setUser_modified = new HashSet<ExperimentLog>();
	

	public Set<ExperimentLog> getSetUser_modify() {
		return setUser_modify;
	}
	public void setSetUser_modify(Set<ExperimentLog> setUser_modify) {
		this.setUser_modify = setUser_modify;
	}
	public Set<ExperimentLog> getSetUser_modified() {
		return setUser_modified;
	}
	public void setSetUser_modified(Set<ExperimentLog> setUser_modified) {
		this.setUser_modified = setUser_modified;
	}
	public Experiment getExperiment() {
		return experiment;
	}
	public void setExperiment(Experiment experiment) {
		this.experiment = experiment;
	}
	public Set<TheoreticalLesson> getSetTheoreticalLesson_Experiment() {
		return setTheoreticalLesson_Experiment;
	}
	public void setSetTheoreticalLesson_Experiment(
			Set<TheoreticalLesson> setTheoreticalLesson_Experiment) {
		this.setTheoreticalLesson_Experiment = setTheoreticalLesson_Experiment;
	}

	public Integer getExperimenterlevel() {
		return experimenterlevel;
	}
	public void setExperimenterlevel(Integer experimenterlevel) {
		this.experimenterlevel = experimenterlevel;
	}
	public Set<TeachCal> getSetTeachCal() {
		return setTeachCal;
	}
	public void setSetTeachCal(Set<TeachCal> setTeachCal) {
		this.setTeachCal = setTeachCal;
	}
	public Set<NoticeFile> getSetNoticeFile() {
		return setNoticeFile;
	}
	public void setSetNoticeFile(Set<NoticeFile> setNoticeFile) {
		this.setNoticeFile = setNoticeFile;
	}
	public Set<Notice> getSetNotice() {
		return setNotice;
	}
	public void setSetNotice(Set<Notice> setNotice) {
		this.setNotice = setNotice;
	}
	public String getTnum() {
		return tnum;
	}
	public void setTnum(String tnum) {
		this.tnum = tnum;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getAdminlevel() {
		return adminlevel;
	}
	public void setAdminlevel(Integer adminlevel) {
		this.adminlevel = adminlevel;
	}
	public College getCollege() {
		return college;
	}
	public void setCollege(College college) {
		this.college = college;
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
	public Set<PracticeLesson> getSetPracticeLesson() {
		return setPracticeLesson;
	}
	public void setSetPracticeLesson(Set<PracticeLesson> setPracticeLesson) {
		this.setPracticeLesson = setPracticeLesson;
	}
	public Set<TheoreticalLesson> getSetTheoreticalLessonC() {
		return setTheoreticalLessonC;
	}
	public void setSetTheoreticalLessonC(
			Set<TheoreticalLesson> setTheoreticalLessonC) {
		this.setTheoreticalLessonC = setTheoreticalLessonC;
	}
	
}
