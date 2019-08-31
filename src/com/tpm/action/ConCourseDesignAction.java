package com.tpm.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tpm.entity.ConCourseDesign;
import com.tpm.service.ConCourseDesignService;

public class ConCourseDesignAction extends ActionSupport implements ModelDriven<ConCourseDesign> {

	private ConCourseDesign conCourseDesign = new ConCourseDesign();
	public ConCourseDesign getModel() {
		return conCourseDesign;
	}
	
	private ConCourseDesignService conCourseDesignService;
	public void setConCourseDesignService(
			ConCourseDesignService conCourseDesignService) {
		this.conCourseDesignService = conCourseDesignService;
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
	public String toContentCouDesPage()
	{
		conCourseDesignService.toContentCouDesPage(syllabusId,practiceLessonid);
		return "toContentCouDesPage";
	}
	public String updateConCourseDesign()
	{
		conCourseDesignService.updateConCourseDesign(conCourseDesign,syllabusId,practiceLessonid);
		return "updateConCourseDesign";
	}
}
