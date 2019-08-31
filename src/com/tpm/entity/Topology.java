package com.tpm.entity;

public class Topology {
	private Integer topologyid;
	private String topologytime;
	private String topologypath;
	private Department department;
	private Professional professional;
	public Professional getProfessional() {
		return professional;
	}
	public void setProfessional(Professional professional) {
		this.professional = professional;
	}
	public Integer getTopologyid() {
		return topologyid;
	}
	public void setTopologyid(Integer topologyid) {
		this.topologyid = topologyid;
	}
	public String getTopologytime() {
		return topologytime;
	}
	public void setTopologytime(String topologytime) {
		this.topologytime = topologytime;
	}
	public String getTopologypath() {
		return topologypath;
	}
	public void setTopologypath(String topologypath) {
		this.topologypath = topologypath;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	
}
