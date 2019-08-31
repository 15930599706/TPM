package com.tpm.entity;

import java.util.List;

public class PageBean {
	private Integer currentpage;
	private Integer totalCount;
	private Integer pageSize;
	private Integer totalPage;
	private Integer begin;
	private List<College> collegelist;
	private List<Department> departmentlist;
	private List<Curriculum> curriculumlist;
	private List<Professional> professionallist;
	private List<Notice> noticelist;
	private List<NoticeFile> noticeFilelist;
	private List<User> userlist;
	private List<List<TheoreticalLesson>> theoreticalLessonlist;
	private List<List<PracticeLesson>> practiceLessonlist;
	private List<TeachCal> teachCallist;
	private List<Experiment> experimentlist;
	private List<ExperimentLog> experimentLogList;
	public List<ExperimentLog> getExperimentLogList() {
		return experimentLogList;
	}
	public void setExperimentLogList(List<ExperimentLog> experimentLogList) {
		this.experimentLogList = experimentLogList;
	}
	public List<Experiment> getExperimentlist() {
		return experimentlist;
	}
	public void setExperimentlist(List<Experiment> experimentlist) {
		this.experimentlist = experimentlist;
	}
	public List<TeachCal> getTeachCallist() {
		return teachCallist;
	}
	public void setTeachCallist(List<TeachCal> teachCallist) {
		this.teachCallist = teachCallist;
	}
	public List<List<PracticeLesson>> getPracticeLessonlist() {
		return practiceLessonlist;
	}
	public void setPracticeLessonlist(List<List<PracticeLesson>> practiceLessonlist) {
		this.practiceLessonlist = practiceLessonlist;
	}
	public List<List<TheoreticalLesson>> getTheoreticalLessonlist() {
		return theoreticalLessonlist;
	}
	public void setTheoreticalLessonlist(
			List<List<TheoreticalLesson>> theoreticalLessonlist) {
		this.theoreticalLessonlist = theoreticalLessonlist;
	}
	public List<User> getUserlist() {
		return userlist;
	}
	public void setUserlist(List<User> userlist) {
		this.userlist = userlist;
	}
	public List<NoticeFile> getNoticeFilelist() {
		return noticeFilelist;
	}
	public void setNoticeFilelist(List<NoticeFile> noticeFilelist) {
		this.noticeFilelist = noticeFilelist;
	}
	public List<Notice> getNoticelist() {
		return noticelist;
	}
	public void setNoticelist(List<Notice> noticelist) {
		this.noticelist = noticelist;
	}
	public List<Professional> getProfessionallist() {
		return professionallist;
	}
	public void setProfessionallist(List<Professional> professionallist) {
		this.professionallist = professionallist;
	}
	public List<Curriculum> getCurriculumlist() {
		return curriculumlist;
	}
	public void setCurriculumlist(List<Curriculum> curriculumlist) {
		this.curriculumlist = curriculumlist;
	}
	public List<Department> getDepartmentlist() {
		return departmentlist;
	}
	public void setDepartmentlist(List<Department> departmentlist) {
		this.departmentlist = departmentlist;
	}
	public Integer getCurrentpage() {
		return currentpage;
	}
	public void setCurrentpage(Integer currentpage) {
		this.currentpage = currentpage;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getBegin() {
		return begin;
	}
	public void setBegin(Integer begin) {
		this.begin = begin;
	}
	public List<College> getCollegelist() {
		return collegelist;
	}
	public void setCollegelist(List<College> collegelist) {
		this.collegelist = collegelist;
	}
	
}
