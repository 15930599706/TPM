package com.tpm.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tpm.entity.Experiment;
import com.tpm.service.ExperimentService;

public class ExperimentAction extends ActionSupport implements ModelDriven<Experiment> {

	private Experiment experiment = new Experiment();
	public Experiment getModel() {
		return experiment;
	}
	
	private ExperimentService experimentService;
	public void setExperimentService(ExperimentService experimentService) {
		this.experimentService = experimentService;
	}
		
	public String toaddExperimentPage()	//到添加实验室页面
	{
		experimentService.toaddExperimentPage();
		return "toaddExperimentPage";
	}
	
	public String addExp()	//添加实验室
	{
		String tag = experimentService.addExp(experiment);
		if(tag.equals("success"))
		{
			return "addExp_success";
		}
		else
		{
			return "addExp_failure";
		}
	}
	
	public String deleteExperiment(){	//删除实验室
		String tag = experimentService.deleteExperiment(experiment);
		if(tag.equals("success"))
		{
			return "deleteExperiment_success";
		}
		else
		{
			return "deleteExperiment_failure";
		}
	}

}
