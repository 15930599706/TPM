package com.tpm.entity;

import java.util.HashSet;
import java.util.Set;

public class FieldContent {
	private Integer fieldContentid;
	private String content;
	private String request;
	private String schedule;
	private String way;
	private Set<Syllabus_FieldWork> setSyllabus_FieldWork = new HashSet<Syllabus_FieldWork>();

	public Set<Syllabus_FieldWork> getSetSyllabus_FieldWork() {
		return setSyllabus_FieldWork;
	}
	public void setSetSyllabus_FieldWork(
			Set<Syllabus_FieldWork> setSyllabus_FieldWork) {
		this.setSyllabus_FieldWork = setSyllabus_FieldWork;
	}
	public Integer getFieldContentid() {
		return fieldContentid;
	}
	public void setFieldContentid(Integer fieldContentid) {
		this.fieldContentid = fieldContentid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	public String getSchedule() {
		return schedule;
	}
	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	public String getWay() {
		return way;
	}
	public void setWay(String way) {
		this.way = way;
	}
}
