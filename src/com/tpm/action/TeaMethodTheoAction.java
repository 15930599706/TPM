package com.tpm.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tpm.entity.TeaMethodTheo;
import com.tpm.service.TeaMethodTheoService;

public class TeaMethodTheoAction extends ActionSupport implements ModelDriven<TeaMethodTheo>{
	private TeaMethodTheo teaMethodTheo = new TeaMethodTheo();
	public TeaMethodTheo getModel() {
		return teaMethodTheo;
	}
	private TeaMethodTheoService teaMethodTheoService;
	public void setTeaMethodTheoService(TeaMethodTheoService teaMethodTheoService) {
		this.teaMethodTheoService = teaMethodTheoService;
	}
	
	private String syllabusId;
	public String getSyllabusId() {
		return syllabusId;
	}
	public void setSyllabusId(String syllabusId) {
		this.syllabusId = syllabusId;
	}
	private String theoreticalLessonId;
	public String getTheoreticalLessonId() {
		return theoreticalLessonId;
	}
	public void setTheoreticalLessonId(String theoreticalLessonId) {
		this.theoreticalLessonId = theoreticalLessonId;
	}
	
	private String curriculumId;

	public void setCurriculumId(String curriculumId) {
		this.curriculumId = curriculumId;
	}
	public String toTeaMethodTheopage(){
		teaMethodTheoService.toTeaMethodTheo(syllabusId,theoreticalLessonId,curriculumId);
		return "toTeaMethodTheopage";
	}
	public String updateTeaMethodTheo(){
		teaMethodTheoService.updateTeaMethodTheo(teaMethodTheo,syllabusId,theoreticalLessonId,curriculumId);
		return "updateTeaMethodTheo";
	}
}
