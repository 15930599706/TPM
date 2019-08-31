package com.tpm.entity;

import java.util.HashSet;
import java.util.Set;

public class TeachObj_Gra {
	public Integer teachObj_Graid;
	public String teachObjContent_Gra;
	private String syllabus_Graid;
	private Set<AbilityAndTeachObj_Gra> setAbilityAndTeachObj_Gra = new HashSet<AbilityAndTeachObj_Gra>();
	public Integer getTeachObj_Graid() {
		return teachObj_Graid;
	}
	public void setTeachObj_Graid(Integer teachObj_Graid) {
		this.teachObj_Graid = teachObj_Graid;
	}
	public String getTeachObjContent_Gra() {
		return teachObjContent_Gra;
	}
	public void setTeachObjContent_Gra(String teachObjContent_Gra) {
		this.teachObjContent_Gra = teachObjContent_Gra;
	}
	public String getSyllabus_Graid() {
		return syllabus_Graid;
	}
	public void setSyllabus_Graid(String syllabus_Graid) {
		this.syllabus_Graid = syllabus_Graid;
	}
	public Set<AbilityAndTeachObj_Gra> getSetAbilityAndTeachObj_Gra() {
		return setAbilityAndTeachObj_Gra;
	}
	public void setSetAbilityAndTeachObj_Gra(
			Set<AbilityAndTeachObj_Gra> setAbilityAndTeachObj_Gra) {
		this.setAbilityAndTeachObj_Gra = setAbilityAndTeachObj_Gra;
	}



	

}
