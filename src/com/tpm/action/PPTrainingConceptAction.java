package com.tpm.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tpm.entity.PPTrainingConcept;
import com.tpm.entity.PageBean;
import com.tpm.service.PPTrainingConceptService;

public class PPTrainingConceptAction extends ActionSupport implements ModelDriven<PPTrainingConcept>{
	private PPTrainingConcept ppTrainingConcept = new PPTrainingConcept();
	public PPTrainingConcept getModel() {
		return ppTrainingConcept;
	}
	private String tnum;
	private String collegeid;
	private Integer currentpage;
	public String getCollegeid() {
		return collegeid;
	}
	public void setCollegeid(String collegeid) {
		this.collegeid = collegeid;
	}
	public Integer getCurrentpage() {
		return currentpage;
	}
	public void setCurrentpage(Integer currentpage) {
		this.currentpage = currentpage;
	}
	public String getTnum() {
		return tnum;
	}
	public void setTnum(String tnum) {
		this.tnum = tnum;
	}
	private PPTrainingConceptService ppTrainingConceptService;
	public void setPpTrainingConceptService(
			PPTrainingConceptService ppTrainingConceptService) {
		this.ppTrainingConceptService = ppTrainingConceptService;
	}
	
	public String tozypyPage(){
		currentpage = getcurrentpage(currentpage);
		PageBean pageBean = ppTrainingConceptService.zypy(tnum,currentpage,collegeid);
		ServletActionContext.getRequest().setAttribute("collegeid", collegeid);
		setAttr(pageBean);
		return "tozypyPage";
	}
	public String tobjzyPage(){
		ppTrainingConceptService.bjzy(ppTrainingConcept);
		return "tobjzyPage";
	}
	public String revisetobjzyPage(){
		ppTrainingConceptService.revisebjzy(ppTrainingConcept);
		return "tobjzyPage";
	}
	public String updateppTrainingConcept(){
		ppTrainingConceptService.updateppTrainingConcept(ppTrainingConcept);
		ServletActionContext.getRequest().setAttribute("tag", "topyapPage");
	//	ServletActionContext.getRequest().setAttribute("href", "ppTrainingConcept_tozypyPage.action?collegeid=-1");
		return "updateppTrainingConcept";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	private void setAttr(PageBean pageBean) {
		ServletActionContext.getRequest().setAttribute("pageBean", pageBean);
	}
	private Integer getcurrentpage(Integer currentpage) {
		if(currentpage == null||currentpage == 0){
			currentpage=1;
		}
		return currentpage;
	}
}
