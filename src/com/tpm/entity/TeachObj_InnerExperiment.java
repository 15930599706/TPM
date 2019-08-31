package com.tpm.entity;

import java.util.HashSet;
import java.util.Set;

public class TeachObj_InnerExperiment {
	public Integer teachObj_InnerExperimentid;
	public String teachObjContent_InnerExperiment;
	private String syllabus_InnerExperimentid;
	private Set<AbilityAndTeachObj_InnerExperiment> setAbilityAndTeachObj_InnerExperiment = new HashSet<AbilityAndTeachObj_InnerExperiment>();
	public Integer getTeachObj_InnerExperimentid() {
		return teachObj_InnerExperimentid;
	}
	public void setTeachObj_InnerExperimentid(Integer teachObj_InnerExperimentid) {
		this.teachObj_InnerExperimentid = teachObj_InnerExperimentid;
	}
	public String getTeachObjContent_InnerExperiment() {
		return teachObjContent_InnerExperiment;
	}
	public void setTeachObjContent_InnerExperiment(
			String teachObjContent_InnerExperiment) {
		this.teachObjContent_InnerExperiment = teachObjContent_InnerExperiment;
	}

	public String getSyllabus_InnerExperimentid() {
		return syllabus_InnerExperimentid;
	}
	public void setSyllabus_InnerExperimentid(String syllabus_InnerExperimentid) {
		this.syllabus_InnerExperimentid = syllabus_InnerExperimentid;
	}
	public Set<AbilityAndTeachObj_InnerExperiment> getSetAbilityAndTeachObj_InnerExperiment() {
		return setAbilityAndTeachObj_InnerExperiment;
	}
	public void setSetAbilityAndTeachObj_InnerExperiment(
			Set<AbilityAndTeachObj_InnerExperiment> setAbilityAndTeachObj_InnerExperiment) {
		this.setAbilityAndTeachObj_InnerExperiment = setAbilityAndTeachObj_InnerExperiment;
	}
	

}
