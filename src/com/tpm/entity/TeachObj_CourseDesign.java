package com.tpm.entity;

import java.util.HashSet;
import java.util.Set;

public class TeachObj_CourseDesign {
	public Integer teachObj_CourseDesignid;
	public String teachObjContent_CourseDesign;
	private String syllabus_CourseDesignid;
	private Set<AbilityAndTeachObj_CourseDesign> setAbilityAndTeachObj_CourseDesign = new HashSet<AbilityAndTeachObj_CourseDesign>();
	public Integer getTeachObj_CourseDesignid() {
		return teachObj_CourseDesignid;
	}
	public void setTeachObj_CourseDesignid(Integer teachObj_CourseDesignid) {
		this.teachObj_CourseDesignid = teachObj_CourseDesignid;
	}
	public String getTeachObjContent_CourseDesign() {
		return teachObjContent_CourseDesign;
	}
	public void setTeachObjContent_CourseDesign(String teachObjContent_CourseDesign) {
		this.teachObjContent_CourseDesign = teachObjContent_CourseDesign;
	}
	public String getSyllabus_CourseDesignid() {
		return syllabus_CourseDesignid;
	}
	public void setSyllabus_CourseDesignid(String syllabus_CourseDesignid) {
		this.syllabus_CourseDesignid = syllabus_CourseDesignid;
	}
	public Set<AbilityAndTeachObj_CourseDesign> getSetAbilityAndTeachObj_CourseDesign() {
		return setAbilityAndTeachObj_CourseDesign;
	}
	public void setSetAbilityAndTeachObj_CourseDesign(
			Set<AbilityAndTeachObj_CourseDesign> setAbilityAndTeachObj_CourseDesign) {
		this.setAbilityAndTeachObj_CourseDesign = setAbilityAndTeachObj_CourseDesign;
	}



	

}
