package com.tpm.entity;

import java.util.HashSet;
import java.util.Set;

public class BaseCourseDesign {
	private Integer baseCourseDesignid;
	private String applicable;
	private String schedule;
	private Set<Syllabus_CourseDesign> setSyllabus_CourseDesign = new HashSet<Syllabus_CourseDesign>();
	
	public Set<Syllabus_CourseDesign> getSetSyllabus_CourseDesign() {
		return setSyllabus_CourseDesign;
	}
	public void setSetSyllabus_CourseDesign(
			Set<Syllabus_CourseDesign> setSyllabus_CourseDesign) {
		this.setSyllabus_CourseDesign = setSyllabus_CourseDesign;
	}
	public Integer getBaseCourseDesignid() {
		return baseCourseDesignid;
	}
	public void setBaseCourseDesignid(Integer baseCourseDesignid) {
		this.baseCourseDesignid = baseCourseDesignid;
	}
	public String getApplicable() {
		return applicable;
	}
	public void setApplicable(String applicable) {
		this.applicable = applicable;
	}
	public String getSchedule() {
		return schedule;
	}
	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	
}
