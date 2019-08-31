package com.tpm.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tpm.entity.WayCourseDesign;
import com.tpm.service.WayCourseDesignService;

public class WayCourseDesignAction extends ActionSupport implements ModelDriven<WayCourseDesign> {

	private WayCourseDesign wayCourseDesign = new WayCourseDesign();
	private WayCourseDesignService wayCourseDesignService;
	
	public void setWayCourseDesignService(
			WayCourseDesignService wayCourseDesignService) {
		this.wayCourseDesignService = wayCourseDesignService;
	}

	public WayCourseDesign getModel() {
		return wayCourseDesign;
	}

	
	
	private String syllabusId;
	private String practiceLessonid;
	
	public String getSyllabusId() {
		return syllabusId;
	}
	public void setSyllabusId(String syllabusId) {
		this.syllabusId = syllabusId;
	}
	public String getPracticeLessonid() {
		return practiceLessonid;
	}
	public void setPracticeLessonid(String practiceLessonid) {
		this.practiceLessonid = practiceLessonid;
	}
	public String toTeaMethodCouDesPage()
	{
		wayCourseDesignService.toTeaMethodCouDesPage(syllabusId,practiceLessonid);
		return "toTeaMethodCouDesPage";
	}
	public String updateWayCourseDesign()
	{
		wayCourseDesignService.updateWayCourseDesign(wayCourseDesign,syllabusId,practiceLessonid);
		return "updateWayCourseDesign";
	}
}
