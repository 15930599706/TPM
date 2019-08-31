package com.tpm.entity;

import java.util.HashSet;
import java.util.Set;

public class TeachObj {
	public Integer teachObjid;
	public String teachObjContent;
	private String syllabusID;
	private Set<AbilityAndTeachObj> setAbilityAndTeachObj = new HashSet<AbilityAndTeachObj>();
	
	public String getSyllabusID() {
		return syllabusID;
	}
	public void setSyllabusID(String syllabusID) {
		this.syllabusID = syllabusID;
	}
	public Set<AbilityAndTeachObj> getSetAbilityAndTeachObj() {
		return setAbilityAndTeachObj;
	}
	public void setSetAbilityAndTeachObj(
			Set<AbilityAndTeachObj> setAbilityAndTeachObj) {
		this.setAbilityAndTeachObj = setAbilityAndTeachObj;
	}
	public Integer getTeachObjid() {
		return teachObjid;
	}
	public void setTeachObjid(Integer teachObjid) {
		this.teachObjid = teachObjid;
	}
	public String getTeachObjContent() {
		return teachObjContent;
	}
	public void setTeachObjContent(String teachObjContent) {
		this.teachObjContent = teachObjContent;
	}
	

}
