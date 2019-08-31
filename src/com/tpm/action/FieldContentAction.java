package com.tpm.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tpm.entity.FieldContent;
import com.tpm.service.FieldContentService;

public class FieldContentAction extends ActionSupport implements ModelDriven<FieldContent> {
	private FieldContent fieldContent = new FieldContent();
	public FieldContent getModel() {
		return fieldContent;
	}
	private FieldContentService fieldContentService;
	public void setFieldContentService(FieldContentService fieldContentService) {
		this.fieldContentService = fieldContentService;
	}
	
	
	private String syllabusId_FieldWorkid;
	private String practiceLessonid;
	
	public String getPracticeLessonid() {
		return practiceLessonid;
	}

	public void setPracticeLessonid(String practiceLessonid) {
		this.practiceLessonid = practiceLessonid;
	}


	public String getSyllabusId_FieldWorkid() {
		return syllabusId_FieldWorkid;
	}

	public void setSyllabusId_FieldWorkid(String syllabusId_FieldWorkid) {
		this.syllabusId_FieldWorkid = syllabusId_FieldWorkid;
	}
	public String toContentPracticePage()
	{
		fieldContentService.toContentPracticePage(syllabusId_FieldWorkid,practiceLessonid);
		return "toContentPracticePage";
	}
	public String updateFieldContent()
	{
		fieldContentService.updateFieldContent(fieldContent,syllabusId_FieldWorkid,practiceLessonid);
		return "updateFieldContent";
	}
}
