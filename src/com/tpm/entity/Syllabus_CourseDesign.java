package com.tpm.entity;

import java.util.HashSet;
import java.util.Set;

public class Syllabus_CourseDesign {
	
	private Integer syllabus_CourseDesignid;
	private BaseCourseDesign baseCourseDesign;
	private ConCourseDesign conCourseDesign;
	private WayCourseDesign wayCourseDesign;
	private Set<ApplicationSyllabus_CourseDesign> setApplicationSyllabus_CourseDesign = new HashSet<ApplicationSyllabus_CourseDesign>();

	public Integer getSyllabus_CourseDesignid() {
		return syllabus_CourseDesignid;
	}
	public void setSyllabus_CourseDesignid(Integer syllabus_CourseDesignid) {
		this.syllabus_CourseDesignid = syllabus_CourseDesignid;
	}
	public BaseCourseDesign getBaseCourseDesign() {
		return baseCourseDesign;
	}
	public void setBaseCourseDesign(BaseCourseDesign baseCourseDesign) {
		this.baseCourseDesign = baseCourseDesign;
	}
	public ConCourseDesign getConCourseDesign() {
		return conCourseDesign;
	}
	public void setConCourseDesign(ConCourseDesign conCourseDesign) {
		this.conCourseDesign = conCourseDesign;
	}
	public WayCourseDesign getWayCourseDesign() {
		return wayCourseDesign;
	}
	public void setWayCourseDesign(WayCourseDesign wayCourseDesign) {
		this.wayCourseDesign = wayCourseDesign;
	}
	public Set<ApplicationSyllabus_CourseDesign> getSetApplicationSyllabus_CourseDesign() {
		return setApplicationSyllabus_CourseDesign;
	}
	public void setSetApplicationSyllabus_CourseDesign(
			Set<ApplicationSyllabus_CourseDesign> setApplicationSyllabus_CourseDesign) {
		this.setApplicationSyllabus_CourseDesign = setApplicationSyllabus_CourseDesign;
	}
	
	
}
