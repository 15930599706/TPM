package com.tpm.entity;

public class PracticeLesson {
	private Integer practiceLessonid;
	private Curriculum curriculum;
	private String xueqi;
	private String qizhizhou;
	private String zuzhixingshi;
	private String isxueweike;
	private String idallpracticeLesson;
	private String beizhu;
	private Department department;
	private Professional professional;//专业方向
	private Department teachDepartment;//开课系
	private User teacher;//任课老师
	private String hoursOfOutExp;//课外实验学时
	private String hoursOfOutCom;//课外上机学时
	private String renzheng;//是否认证
	
	private Experiment experiment;//任课实验室
	private User experimenter;//任课实验员
	private String workload;//实习可否下载
	private String workload1;//课程设计可否下载
	private String workload2;//实验可否下载
	private String workload3;//毕业设计可否下载
	private String workload4;//其他可否下载
	public String getWorkload4() {
		return workload4;
	}
	public void setWorkload4(String workload4) {
		this.workload4 = workload4;
	}
	public String getWorkload() {
		return workload;
	}
	public void setWorkload(String workload) {
		this.workload = workload;
	}
	public String getWorkload3() {
		return workload3;
	}
	public void setWorkload3(String workload3) {
		this.workload3 = workload3;
	}
	public String getWorkload2() {
		return workload2;
	}
	public void setWorkload2(String workload2) {
		this.workload2 = workload2;
	}
	public String getWorkload1() {
		return workload1;
	}
	public void setWorkload1(String workload1) {
		this.workload1 = workload1;
	}

	public Experiment getExperiment() {
		return experiment;
	}
	public void setExperiment(Experiment experiment) {
		this.experiment = experiment;
	}
	public User getExperimenter() {
		return experimenter;
	}
	public void setExperimenter(User experimenter) {
		this.experimenter = experimenter;
	}
	public String getRenzheng() {
		return renzheng;
	}
	public void setRenzheng(String renzheng) {
		this.renzheng = renzheng;
	}
	public String getIsxueweike() {
		return isxueweike;
	}
	public void setIsxueweike(String isxueweike) {
		this.isxueweike = isxueweike;
	}
	public String getHoursOfOutExp() {
		return hoursOfOutExp;
	}
	public void setHoursOfOutExp(String hoursOfOutExp) {
		this.hoursOfOutExp = hoursOfOutExp;
	}
	public String getHoursOfOutCom() {
		return hoursOfOutCom;
	}
	public void setHoursOfOutCom(String hoursOfOutCom) {
		this.hoursOfOutCom = hoursOfOutCom;
	}
	public Professional getProfessional() {
		return professional;
	}
	public void setProfessional(Professional professional) {
		this.professional = professional;
	}
	public Integer getPracticeLessonid() {
		return practiceLessonid;
	}
	public void setPracticeLessonid(Integer practiceLessonid) {
		this.practiceLessonid = practiceLessonid;
	}
	public Curriculum getCurriculum() {
		return curriculum;
	}
	public void setCurriculum(Curriculum curriculum) {
		this.curriculum = curriculum;
	}
	public String getXueqi() {
		return xueqi;
	}
	public void setXueqi(String xueqi) {
		this.xueqi = xueqi;
	}
	public String getQizhizhou() {
		return qizhizhou;
	}
	public void setQizhizhou(String qizhizhou) {
		this.qizhizhou = qizhizhou;
	}
	public String getZuzhixingshi() {
		return zuzhixingshi;
	}
	public void setZuzhixingshi(String zuzhixingshi) {
		this.zuzhixingshi = zuzhixingshi;
	}
	public String getIdallpracticeLesson() {
		return idallpracticeLesson;
	}
	public void setIdallpracticeLesson(String idallpracticeLesson) {
		this.idallpracticeLesson = idallpracticeLesson;
	}
	public String getBeizhu() {
		return beizhu;
	}
	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Department getTeachDepartment() {
		return teachDepartment;
	}
	public void setTeachDepartment(Department teachDepartment) {
		this.teachDepartment = teachDepartment;
	}
	public User getTeacher() {
		return teacher;
	}
	public void setTeacher(User teacher) {
		this.teacher = teacher;
	}
	
	
	
}
