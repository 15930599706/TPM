package com.tpm.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tpm.entity.ContentGra;
import com.tpm.service.ContentGraService;

public class ContentGraAction extends ActionSupport implements ModelDriven<ContentGra> {

	private ContentGra contentGra = new ContentGra();
	public ContentGra getModel() {
		return contentGra;
	}
	
	private ContentGraService contentGraService;
	public void setContentGraService(ContentGraService contentGraService) {
		this.contentGraService = contentGraService;
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
	public String toContentGraPage()
	{
		contentGraService.toContentGraPage(syllabusId,practiceLessonid);
		return "toContentGraPage";
	}
	public String updateContentGra()
	{
		contentGraService.updateContentGra(contentGra,syllabusId,practiceLessonid);
		return "updateContentGra";
	}
}
