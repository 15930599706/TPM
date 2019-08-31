package com.tpm.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tpm.entity.PageBean;
import com.tpm.entity.TrainingAnother;
import com.tpm.service.PPTrainingConceptService;
import com.tpm.service.PracticePlanService;
import com.tpm.service.TrainingAnotherService;

public class TrainingAnotherAction extends ActionSupport implements
		ModelDriven<TrainingAnother> {

	private TrainingAnother trainingAnother = new TrainingAnother();
	public TrainingAnother getModel() {
		return trainingAnother;
	}
	

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

	private Integer getcurrentpage(Integer currentpage) {
		if(currentpage == null||currentpage == 0){
			currentpage=1;
		}
		return currentpage;
	}
	
	private TrainingAnotherService trainingAnotherService;
	private PracticePlanService practicePlanService;
	
	public void setPracticePlanService(PracticePlanService practicePlanService) {
		this.practicePlanService = practicePlanService;
	}
	public void setTrainingAnotherService(
			TrainingAnotherService trainingAnotherService) {
		this.trainingAnotherService = trainingAnotherService;
	}
	private PPTrainingConceptService ppTrainingConceptService;
	public void setPpTrainingConceptService(
			PPTrainingConceptService ppTrainingConceptService) {
		this.ppTrainingConceptService = ppTrainingConceptService;
	}
	
	private String tnum;	//接收登陆账户的职工号，用于查询用户级别、所在 系等	
	public void setTnum(String tnum) {
		this.tnum = tnum;
	}

	public String toanotherPage()
	{
		trainingAnotherService.toanotherPage(trainingAnother);
		return "toanotherPage";
	}
	public String revisetoanotherPage()
	{
		trainingAnotherService.revisetoanotherPage(trainingAnother);
		return "toanotherPage";
	}
	public String updataanother()
	{
		trainingAnotherService.updataanother(trainingAnother);
		ServletActionContext.getRequest().setAttribute("tag", "tollkPage");
		return "updataanother";
	}
	public String toanother(){
		currentpage = getcurrentpage(currentpage);
		PageBean pageBean = ppTrainingConceptService.zypy(tnum,currentpage,collegeid);
		ServletActionContext.getRequest().setAttribute("collegeid", collegeid);
		ServletActionContext.getRequest().setAttribute("pageBean",pageBean);
		return "toanother";
	}
}
