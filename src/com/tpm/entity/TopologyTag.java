package com.tpm.entity;

public class TopologyTag {
	private Integer topologyTagid;
	private Department department;
	private String tag;
	public Integer getTopologyTagid() {
		return topologyTagid;
	}
	public void setTopologyTagid(Integer topologyTagid) {
		this.topologyTagid = topologyTagid;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	
}
