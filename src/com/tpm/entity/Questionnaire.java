package com.tpm.entity;



public class Questionnaire {
	private int ID;
	private String name;
	private String starttime;
	private String endtime;
	private int status;
	private String adminID;
	private String grade;
	//private String dofinishtime;
	public String getGrade()
		{
			return grade;
		}
		public void setGrade(String grade)
		{
			this.grade = grade;
		}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getAdminID() {
		return adminID;
	}
	public void setAdminID(String adminID) {
		this.adminID = adminID;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	 
	

}
