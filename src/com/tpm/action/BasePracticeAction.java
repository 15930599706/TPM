package com.tpm.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tpm.entity.BasePractice;
import com.tpm.service.BasePracticeService;

public class BasePracticeAction extends ActionSupport implements ModelDriven<BasePractice> {
	private BasePractice basePractice = new BasePractice();

	public BasePractice getModel() {
		return basePractice;
	}
	
	private BasePracticeService basePracticeService;

	public void setBasePracticeService(BasePracticeService basePracticeService) {
		this.basePracticeService = basePracticeService;
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
	public String toBaseGraPage()
	{
		basePracticeService.toBaseGraPage(syllabusId,practiceLessonid);
		return "toBaseGraPage";
	}
	public void updateBasePractice () throws Exception
	{
		basePracticeService.updateBasePractice(basePractice,syllabusId,practiceLessonid,data);
	//	return "updateBasePractice";
	}
}
