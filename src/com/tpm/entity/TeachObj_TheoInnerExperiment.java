package com.tpm.entity;

import java.util.HashSet;
import java.util.Set;

public class TeachObj_TheoInnerExperiment {
	public Integer teachObj_TheoInnerExperimentid;
	public String teachObjContent_TheoInnerExperiment;
	private String syllabusid;
	private Set<AbilityAndTeachObj_TheoInnerExperiment> setAbilityAndTeachObj_TheoInnerExperiment = new HashSet<AbilityAndTeachObj_TheoInnerExperiment>();
	public Integer getTeachObj_TheoInnerExperimentid() {
		return teachObj_TheoInnerExperimentid;
	}
	public void setTeachObj_TheoInnerExperimentid(
			Integer teachObj_TheoInnerExperimentid) {
		this.teachObj_TheoInnerExperimentid = teachObj_TheoInnerExperimentid;
	}
	public String getTeachObjContent_TheoInnerExperiment() {
		return teachObjContent_TheoInnerExperiment;
	}
	public void setTeachObjContent_TheoInnerExperiment(
			String teachObjContent_TheoInnerExperiment) {
		this.teachObjContent_TheoInnerExperiment = teachObjContent_TheoInnerExperiment;
	}
	public String getSyllabusid() {
		return syllabusid;
	}
	public void setSyllabusid(String syllabusid) {
		this.syllabusid = syllabusid;
	}
	public Set<AbilityAndTeachObj_TheoInnerExperiment> getSetAbilityAndTeachObj_TheoInnerExperiment() {
		return setAbilityAndTeachObj_TheoInnerExperiment;
	}
	public void setSetAbilityAndTeachObj_TheoInnerExperiment(
			Set<AbilityAndTeachObj_TheoInnerExperiment> setAbilityAndTeachObj_TheoInnerExperiment) {
		this.setAbilityAndTeachObj_TheoInnerExperiment = setAbilityAndTeachObj_TheoInnerExperiment;
	}

	
}
