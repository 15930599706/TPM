package com.tpm.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tpm.entity.BaseExperiment;
import com.tpm.service.BaseExperimentService;

public class BaseExperimentAction extends ActionSupport implements ModelDriven<BaseExperiment> {

	private BaseExperiment baseExperiment = new BaseExperiment();
	public BaseExperiment getModel() {
		return baseExperiment;
	}
	private BaseExperimentService baseExperimentService;
	public void setBaseExperimentService(BaseExperimentService baseExperimentService) {
		this.baseExperimentService = baseExperimentService;
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
	public String toBaseExpPage()
	{
		baseExperimentService.toBaseExpPage(syllabusId,practiceLessonid);
		return "toBaseExpPage";
	}
	private String data;
	
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public void updateBaseExperiment() throws Exception{
		baseExperimentService.updateBaseExperiment(baseExperiment,syllabusId,practiceLessonid,data);
	//	return "updateBaseExperiment";
	}
}
