package com.tpm.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tpm.entity.College;
import com.tpm.entity.PageBean;
import com.tpm.service.CollegeService;

public class CollegeAction extends ActionSupport implements ModelDriven<College>{
	private College college = new College();
	
	public College getModel() {
		return college;
	}
	
	private CollegeService collegeService;
	public void setCollegeService(CollegeService collegeService) {
		this.collegeService = collegeService;
	}
	
	
}
