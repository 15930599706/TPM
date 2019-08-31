package com.tpm.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tpm.entity.FieldWork;
import com.tpm.service.FieldWorkService;

public class FieldWorkAction extends ActionSupport implements ModelDriven<FieldWork> {

	private FieldWork fieldWork = new FieldWork();
	public FieldWork getModel() {
		return fieldWork;
	}

	private FieldWorkService fieldWorkService;
	public void setFieldWork(FieldWork fieldWork) {
		this.fieldWork = fieldWork;
	}

	public void setFieldWorkService(FieldWorkService fieldWorkService) {
		this.fieldWorkService = fieldWorkService;
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

	public String toBasePracticePage()
	{
		fieldWorkService.toBasePracticePage(syllabusId_FieldWorkid,practiceLessonid);
		return "toBasePracticePage";
	}
	public String updateFieldWork()
	{
		fieldWorkService.updateFieldWork(fieldWork,syllabusId_FieldWorkid,practiceLessonid);
		return "updateFieldWork";
	}
}
