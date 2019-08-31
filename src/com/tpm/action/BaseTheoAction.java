package com.tpm.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tpm.entity.BaseTheo;
import com.tpm.service.BaseTheoService;

public class BaseTheoAction extends ActionSupport implements ModelDriven<BaseTheo>{
	private BaseTheo baseTheo = new BaseTheo();
	public BaseTheo getModel() {
		return baseTheo;
	}
	private BaseTheoService baseTheoService;
	public void setBaseTheoService(BaseTheoService baseTheoService) {
		this.baseTheoService = baseTheoService;
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
	
	private String data;	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	private String curriculumId;

	public void setCurriculumId(String curriculumId) {
		this.curriculumId = curriculumId;
	}
	public String toBaseTheoPage(){
		baseTheoService.toBaseTheo(syllabusId,theoreticalLessonId,curriculumId);
		return "toBaseTheoPage";
	}
	public void updateBaseTheo() throws Exception{
		baseTheoService.updateBaseTheo(baseTheo,syllabusId,data,theoreticalLessonId,curriculumId);
	//	return "updateBaseTheo";
	}
	
	

	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public String toBefourAimTheoPage(){
		return "toBefourAimTheoPage";
	}
	public String updateAimTheo(){
		return "updateAimTheo";
	}
	public String toContentTheoPage(){
		return "toContentTheoPage";
	}
	public String toEditThreePage(){
		return "toEditThreePage";
	}
	public String toEditTestPage(){
		return "toEditTestPage";
	}
	public String toEditOtherPage(){
		return "toEditOtherPage";
	}
	public String toEditDiscussPage(){
		return "toEditDiscussPage";
	}
	public String toMateriaTheoPage(){
		return "toMateriaTheoPage";
	}
	public String toBaseGraPage(){
		return "toBaseGraPage";
	}
	public String toBefourAimGraPage(){
		return "toBefourAimGraPage";
	}
	public String toContentGraPage(){
		return "toContentGraPage";
	}
	public String toMateriaGraPage(){
		return "toMateriaGraPage";
	}
	public String toBasePracticePage(){
		return "toBasePracticePage";
	}
	public String toBefourAimPracticePage(){
		return "toBefourAimPracticePage";
	}
	public String toContentPracticePage(){
		return "toContentPracticePage";
	}
	public String toBaseCouDesPage(){
		return "toBaseCouDesPage";
	}
	public String toBefourAimCouDesPage(){
		return "toBefourAimCouDesPage";
	}
	public String toContentCouDesPage(){
		return "toContentCouDesPage";
	}
	public String toTeaMethodCouDesPage(){
		return "toTeaMethodCouDesPage";
	}
	public String toBaseExpPage(){
		return "toBaseExpPage";
	}
	public String toBefourAimExpPage(){
		return "toBefourAimExpPage";
	}
	public String toContentExpPage(){
		return "toContentExpPage";
	}
	public String toLaborInstruPage(){
		return "toLaborInstruPage";
	}
	public String toMateriaExpPage(){
		return "toMateriaExpPage";
	}
	public String toMateriaCouDesPage(){
		return "toMateriaCouDesPage";
	}
	public String toAimGraPage(){
		return "toAimGraPage";
	}
	public String toTeachCalPage(){
		return "toTeachCalPage";
	}
	public String tobjjzPage(){
		return "tobjjzPage";
	}
	
}
