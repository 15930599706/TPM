package com.tpm.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tpm.entity.BaseCourseDesign;
import com.tpm.service.BaseCourseDesignService;

public class BaseCourseDesignAction extends ActionSupport implements ModelDriven<BaseCourseDesign> {

	private BaseCourseDesign baseCourseDesign = new BaseCourseDesign();
	public BaseCourseDesign getModel() {
		return baseCourseDesign;
	}
	private BaseCourseDesignService baseCourseDesignService;
	public void setBaseCourseDesignService(
			BaseCourseDesignService baseCourseDesignService) {
		this.baseCourseDesignService = baseCourseDesignService;
	}
	
	private String syllabusId;
	private String practiceLessonid;
	
	public String getPracticeLessonid() {
		return practiceLessonid;
	}

	public void setPracticeLessonid(String practiceLessonid) {
		this.practiceLessonid = practiceLessonid;
	}

	public String getSyllabusId() {
		return syllabusId;
	}

	public void setSyllabusId(String syllabusId) {
		this.syllabusId = syllabusId;
	}
	
	private String data;	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String toBaseCouDesPage()
	{
		baseCourseDesignService.toBaseCouDesPage(syllabusId,practiceLessonid);
		return "toBaseCouDesPage";
	}
	public void updateBaseCourseDesign() throws Exception
	{
		baseCourseDesignService.updateBaseCourseDesign(baseCourseDesign,syllabusId,practiceLessonid,data);
	//	return "updateBaseCourseDesign";
	}
	
	
}
