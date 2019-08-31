package com.tpm.action;

import com.opensymphony.xwork2.ActionSupport;
import com.tpm.service.TrainingEventsService;

public class TrainingEventsAction extends ActionSupport {
	private TrainingEventsService trainingEventsService;

	public void setTrainingEventsService(TrainingEventsService trainingEventsService) {
		this.trainingEventsService = trainingEventsService;
	}
	
	
}
