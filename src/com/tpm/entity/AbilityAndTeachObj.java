package com.tpm.entity;

public class AbilityAndTeachObj {
	private Integer abilityAndTeachObjid;
	private Ability ability;
	private TeachObj teachObj;
	private String syllabusID;
	
	public String getSyllabusID() {
		return syllabusID;
	}
	public void setSyllabusID(String syllabusID) {
		this.syllabusID = syllabusID;
	}
	public Integer getAbilityAndTeachObjid() {
		return abilityAndTeachObjid;
	}
	public void setAbilityAndTeachObjid(Integer abilityAndTeachObjid) {
		this.abilityAndTeachObjid = abilityAndTeachObjid;
	}
	public Ability getAbility() {
		return ability;
	}
	public void setAbility(Ability ability) {
		this.ability = ability;
	}
	public TeachObj getTeachObj() {
		return teachObj;
	}
	public void setTeachObj(TeachObj teachObj) {
		this.teachObj = teachObj;
	}


}
