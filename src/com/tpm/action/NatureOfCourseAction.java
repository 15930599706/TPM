package com.tpm.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tpm.entity.NatureOfCourse;
import com.tpm.service.NatureOfCourseService;

public class NatureOfCourseAction extends ActionSupport implements ModelDriven<NatureOfCourse>{
	private NatureOfCourse natureOfCourse = new NatureOfCourse();
	public NatureOfCourse getModel() {
		return natureOfCourse;
	}
	private NatureOfCourseService natureOfCourseService;
	public void setNatureOfCourseService(NatureOfCourseService natureOfCourseService) {
		this.natureOfCourseService = natureOfCourseService;
	}
	
}
