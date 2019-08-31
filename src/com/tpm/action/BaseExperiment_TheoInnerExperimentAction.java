package com.tpm.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tpm.entity.BaseExperiment;
import com.tpm.entity.BaseExperiment_TheoInnerExperiment;
import com.tpm.service.BaseExperimentService;
import com.tpm.service.BaseExperiment_TheoInnerExperimentService;

public class BaseExperiment_TheoInnerExperimentAction extends ActionSupport implements ModelDriven<BaseExperiment_TheoInnerExperiment> {

	private BaseExperiment_TheoInnerExperiment baseExperiment_TheoInnerExperiment = new BaseExperiment_TheoInnerExperiment();
	
	public void setBaseExperiment_TheoInnerExperiment(
			BaseExperiment_TheoInnerExperiment baseExperiment_TheoInnerExperiment) {
		this.baseExperiment_TheoInnerExperiment = baseExperiment_TheoInnerExperiment;
	}

	public BaseExperiment_TheoInnerExperiment getModel() {
		return baseExperiment_TheoInnerExperiment;
	}

	private BaseExperiment_TheoInnerExperimentService baseExperiment_TheoInnerExperimentService;
	
	public void setBaseExperiment_TheoInnerExperimentService(
			BaseExperiment_TheoInnerExperimentService baseExperiment_TheoInnerExperimentService) {
		this.baseExperiment_TheoInnerExperimentService = baseExperiment_TheoInnerExperimentService;
	}

	private String syllabusId;
	private String theoreticalLessonId;
	private String data;

	public String getSyllabusId() {
		return syllabusId;
	}

	public void setSyllabusId(String syllabusId) {
		this.syllabusId = syllabusId;
	}

	public String getTheoreticalLessonId() {
		return theoreticalLessonId;
	}

	public void setTheoreticalLessonId(String theoreticalLessonId) {
		this.theoreticalLessonId = theoreticalLessonId;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	
	public String toBaseTheoExpPage()
	{
		baseExperiment_TheoInnerExperimentService.toBaseTheoExpPage(syllabusId,theoreticalLessonId);
		return "toBaseTheoExpPage";
	}
	
	public void updateBaseTheoExperiment() throws Exception{
		baseExperiment_TheoInnerExperimentService.updateBaseTheoExperiment(baseExperiment_TheoInnerExperiment,syllabusId,theoreticalLessonId,data);
	//	return "updateBaseTheoExperiment";
	}
	
}
