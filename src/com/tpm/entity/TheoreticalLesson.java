package com.tpm.entity;

public class TheoreticalLesson {//理论课
	private Integer theoreticalLessonid;
	private Curriculum curriculum;
	private String xueqi;
	private String isxueweike;
	private String isjiaogai;
	private String isbixuan;	//是否必选
	private String remark;	//备注
	private Department department;
	private Professional professional;//专业方向
	private Department teachDepartment;//开课系
	private User teacher;//任课老师
	private String hoursOfOutExp;//课外实验学时
	private String hoursOfOutCom;//课外上机学时
	private String renzheng;//是否认证
	private Experiment experiment;//任课实验室
	private User experimenter;//任课实验员
	private Department cteachDepartment;//课内实验开课系
	private User cteacher;//课内实验任课老师
	private String isDow;//理论课可否下载
	private String isDown;//课内实验可否下载

	public String getIsDown() {
		return isDown;
	}

	public void setIsDown(String isDown) {
		this.isDown = isDown;
	}
	public String getIsDow() {
		return isDow;
	}
	public void setIsDow(String isDow) {
		this.isDow = isDow;
	}

	public Department getCteachDepartment() {
		return cteachDepartment;
	}
	public void setCteachDepartment(Department cteachDepartment) {
		this.cteachDepartment = cteachDepartment;
	}
	
	
	public User getCteacher() {
		return cteacher;
	}
	public void setCteacher(User cteacher) {
		this.cteacher = cteacher;
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
	public String getIsbixuan() {
		return isbixuan;
	}
	public void setIsbixuan(String isbixuan) {
		this.isbixuan = isbixuan;
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
	public Integer getTheoreticalLessonid() {
		return theoreticalLessonid;
	}
	public void setTheoreticalLessonid(Integer theoreticalLessonid) {
		this.theoreticalLessonid = theoreticalLessonid;
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
	public String getIsxueweike() {
		return isxueweike;
	}
	public void setIsxueweike(String isxueweike) {
		this.isxueweike = isxueweike;
	}
	public String getIsjiaogai() {
		return isjiaogai;
	}
	public void setIsjiaogai(String isjiaogai) {
		this.isjiaogai = isjiaogai;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Professional getProfessional() {
		return professional;
	}
	public void setProfessional(Professional professional) {
		this.professional = professional;
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
