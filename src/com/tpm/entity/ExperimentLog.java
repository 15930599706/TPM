package com.tpm.entity;

public class ExperimentLog {//实验室日志
	private Integer experimentLogid;
	private User user_modify;	//修改者
	private User user_modified;	//被修改者
	private Experiment experiment_before;	//修改前所在实验室
	private Integer experimenterlevel_before;	//修改前等级
	private Experiment experiment_after;	//修改后所在实验室
	private Integer experimenterlevel_after;	//修改后等级
	private String operate;	//操作
	private String time;	//修改时间
	private String sign;	//操作标记
	public Integer getExperimentLogid() {
		return experimentLogid;
	}
	public void setExperimentLogid(Integer experimentLogid) {
		this.experimentLogid = experimentLogid;
	}
	public User getUser_modify() {
		return user_modify;
	}
	public void setUser_modify(User user_modify) {
		this.user_modify = user_modify;
	}
	public User getUser_modified() {
		return user_modified;
	}
	public void setUser_modified(User user_modified) {
		this.user_modified = user_modified;
	}
	public Experiment getExperiment_before() {
		return experiment_before;
	}
	public void setExperiment_before(Experiment experiment_before) {
		this.experiment_before = experiment_before;
	}
	public Integer getExperimenterlevel_before() {
		return experimenterlevel_before;
	}
	public void setExperimenterlevel_before(Integer experimenterlevel_before) {
		this.experimenterlevel_before = experimenterlevel_before;
	}
	public Experiment getExperiment_after() {
		return experiment_after;
	}
	public void setExperiment_after(Experiment experiment_after) {
		this.experiment_after = experiment_after;
	}
	public Integer getExperimenterlevel_after() {
		return experimenterlevel_after;
	}
	public void setExperimenterlevel_after(Integer experimenterlevel_after) {
		this.experimenterlevel_after = experimenterlevel_after;
	}
	public String getOperate() {
		return operate;
	}
	public void setOperate(String operate) {
		this.operate = operate;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}


}
