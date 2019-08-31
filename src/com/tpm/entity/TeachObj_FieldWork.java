package com.tpm.entity;

import java.util.HashSet;
import java.util.Set;

public class TeachObj_FieldWork {
	private Integer teachObj_FieldWorkid;
	private String teachObjContent_FieldWork;
	private String syllabus_FieldWorkid;
	private Set<AbilityAndTeachObj_FieldWork> setAbilityAndTeachObj_FieldWork = new HashSet<AbilityAndTeachObj_FieldWork>();
	public Integer getTeachObj_FieldWorkid() {
		return teachObj_FieldWorkid;
	}
	public void setTeachObj_FieldWorkid(Integer teachObj_FieldWorkid) {
		this.teachObj_FieldWorkid = teachObj_FieldWorkid;
	}
	public String getTeachObjContent_FieldWork() {
		return teachObjContent_FieldWork;
	}
	public void setTeachObjContent_FieldWork(String teachObjContent_FieldWork) {
		this.teachObjContent_FieldWork = teachObjContent_FieldWork;
	}
	public Set<AbilityAndTeachObj_FieldWork> getSetAbilityAndTeachObj_FieldWork() {
		return setAbilityAndTeachObj_FieldWork;
	}
	public void setSetAbilityAndTeachObj_FieldWork(
			Set<AbilityAndTeachObj_FieldWork> setAbilityAndTeachObj_FieldWork) {
		this.setAbilityAndTeachObj_FieldWork = setAbilityAndTeachObj_FieldWork;
	}
	public String getSyllabus_FieldWorkid() {
		return syllabus_FieldWorkid;
	}
	public void setSyllabus_FieldWorkid(String syllabus_FieldWorkid) {
		this.syllabus_FieldWorkid = syllabus_FieldWorkid;
	}
	

}
