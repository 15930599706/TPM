package com.tpm.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tpm.entity.Department;
import com.tpm.entity.Professional;
import com.tpm.service.ProfessionalService;

public class ProfessionalAction extends ActionSupport implements ModelDriven<Professional> {
	private Professional professional = new Professional();
	public Professional getModel() {
		return professional;
	}
	private ProfessionalService professionalService;
	public void setProfessionalService(ProfessionalService professionalService) {
		this.professionalService = professionalService;
	}
	//删除专业方向
	public String deleteProfessional(){
		professionalService.deleteProfessional(professional);
		return "deleteProfessional";
	}
	public String toaddProDirPage(){
		professionalService.toaddProDirPage();
		return "toaddProDirPage";
	}
	public String addProDir(){
		String tag = professionalService.addProDir(professional);
		if(tag.equals("success"))
		{
			return "addProDir_success";
		}
		else
		{
			return "addProDir_failure";
		}
	}

}
