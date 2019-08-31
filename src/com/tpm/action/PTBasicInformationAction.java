package com.tpm.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tpm.entity.PTBasicInformation;
import com.tpm.entity.PageBean;
import com.tpm.service.PPTrainingConceptService;
import com.tpm.service.PTBasicInformationService;

public class PTBasicInformationAction extends ActionSupport implements ModelDriven<PTBasicInformation>{
	private PTBasicInformation ptBasicInformation = new PTBasicInformation();
	public PTBasicInformation getModel() {
		return ptBasicInformation;
	}
	private PPTrainingConceptService ppTrainingConceptService; 
	private PTBasicInformationService ptBasicInformationService;
	private String tnum;
	private String collegeid;
	private Integer currentpage;
	public String getTnum() {
		return tnum;
	}
	public void setTnum(String tnum) {
		this.tnum = tnum;
	}
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
	public void setPpTrainingConceptService(
			PPTrainingConceptService ppTrainingConceptService) {
		this.ppTrainingConceptService = ppTrainingConceptService;
	}
	public void setPtBasicInformationService(
			PTBasicInformationService ptBasicInformationService) {
		this.ptBasicInformationService = ptBasicInformationService;
	}
	public String topyxxPage(){
		currentpage = getcurrentpage(currentpage);
		PageBean pageBean = ppTrainingConceptService.zypy(tnum,currentpage,collegeid);
		ServletActionContext.getRequest().setAttribute("collegeid", collegeid);
		setAttr(pageBean);
		return "topyxxPage";
	}
	public String tobjpyPage(){
		ptBasicInformationService.bjpy(ptBasicInformation);
		return "tobjpyPage";
	}
	public String revisetobjpyPage(){
		ptBasicInformationService.revisebjpy(ptBasicInformation);
		return "tobjpyPage";
	}
	public String updateptBasicInformation(){
		ptBasicInformationService.updateptBasicInformation(ptBasicInformation);
		return "updateptBasicInformation";
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
	
	private String pid;
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	private String cid;
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public void profession() throws Exception{
		ptBasicInformationService.profession(pid);
	}
	
	public void college() throws Exception{
		ptBasicInformationService.college(cid);
	}
	
	public void school() throws Exception{
		ptBasicInformationService.school();
	}

	
}
