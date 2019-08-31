package com.tpm.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tpm.entity.ScoreThreshold;
import com.tpm.service.ScoreThresholdService;

public class ScoreThresholdAction extends ActionSupport implements ModelDriven<ScoreThreshold>{
	private ScoreThreshold scoreThreshold = new ScoreThreshold();
	public ScoreThreshold getModel() {
		return scoreThreshold;
	}
	private ScoreThresholdService scoreThresholdService;

	public void setScoreThresholdService(ScoreThresholdService scoreThresholdService) {
		this.scoreThresholdService = scoreThresholdService;
	}

	public String setTotalCredit(){
		scoreThresholdService.setTotalCredit(scoreThreshold);
		ServletActionContext.getRequest().setAttribute("tag", "togetInPage");
		ServletActionContext.getRequest().setAttribute("msg", "设置成功！");
		return "setTotalCredit";
	}
	
	private String weekHour;
	public String getWeekHour() {
		return weekHour;
	}

	public void setWeekHour(String weekHour) {
		this.weekHour = weekHour;
	}

	public String setAvePerThreshold(){
		scoreThresholdService.setAvePerThreshold(weekHour);
		ServletActionContext.getRequest().setAttribute("tag", "togetInPage");
		ServletActionContext.getRequest().setAttribute("msg", "设置成功！");
		return "setAvePerThreshold";
	}
}
