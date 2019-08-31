package com.tpm.entity;

import java.util.HashSet;
import java.util.Set;

public class WayCourseDesign {
	private Integer wayCourseDesignid;
	private String aim;
	private String assess;
	
	private Set<Syllabus_CourseDesign> setSyllabus_CourseDesign = new HashSet<Syllabus_CourseDesign>();
	
	public Set<Syllabus_CourseDesign> getSetSyllabus_CourseDesign() {
		return setSyllabus_CourseDesign;
	}
	public void setSetSyllabus_CourseDesign(
			Set<Syllabus_CourseDesign> setSyllabus_CourseDesign) {
		this.setSyllabus_CourseDesign = setSyllabus_CourseDesign;
	}
	public Integer getWayCourseDesignid() {
		return wayCourseDesignid;
	}
	public void setWayCourseDesignid(Integer wayCourseDesignid) {
		this.wayCourseDesignid = wayCourseDesignid;
	}
	public String getAim() {
		return aim;
	}
	public void setAim(String aim) {
		this.aim = aim;
	}
	public String getAssess() {
		return assess;
	}
	public void setAssess(String assess) {
		this.assess = assess;
	}

	
}
