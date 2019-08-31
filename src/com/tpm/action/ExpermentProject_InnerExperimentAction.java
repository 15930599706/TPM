package com.tpm.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tpm.entity.Curriculum;
import com.tpm.entity.Department;
import com.tpm.entity.ExpermentProject;
import com.tpm.entity.ExpermentProject_Theo;
import com.tpm.entity.Professional;
import com.tpm.service.ExpermentProjectService;
import com.tpm.service.ExpermentProject_InnerExperimentService;

public class ExpermentProject_InnerExperimentAction extends ActionSupport implements ModelDriven<ExpermentProject_Theo>{

	private ExpermentProject_Theo expermentProject_Theo = new ExpermentProject_Theo();
	public ExpermentProject_Theo getModel() {
		return expermentProject_Theo;
	}
	private ExpermentProject_InnerExperimentService expermentProject_InnerExperimentService;

	public void setExpermentProject_InnerExperimentService(
			ExpermentProject_InnerExperimentService expermentProject_InnerExperimentService) {
		this.expermentProject_InnerExperimentService = expermentProject_InnerExperimentService;
	}
	private String syllabusId;
	
	public String getSyllabusId() {
		return syllabusId;
	}
	public void setSyllabusId(String syllabusId) {
		this.syllabusId = syllabusId;
	}
	
	private String theoreticalLessonId;
	
	public String getTheoreticalLessonId() {
		return theoreticalLessonId;
	}
	public void setTheoreticalLessonId(String theoreticalLessonId) {
		this.theoreticalLessonId = theoreticalLessonId;
	}
	public String toTheoContentExpPage()
	{
		ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
		List<ExpermentProject_Theo> textBookslist=expermentProject_InnerExperimentService.toTheoContentExpPage(syllabusId,theoreticalLessonId);
		if(textBookslist.size() ==0 ){
			HttpServletRequest request=ServletActionContext.getRequest();			
			request.setAttribute("textBooks", "[]");
		} 
		else 
		{
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setIgnoreDefaultExcludes(false);
			//jsonConfig.setExcludes(new String[]{"department","professional","curriculum"});
			JSONArray jSONArray = JSONArray.fromObject(textBookslist, jsonConfig);
			String textBooksStr = jSONArray.toString();
			HttpServletRequest request=ServletActionContext.getRequest();			
			request.setAttribute("textBooks", textBooksStr);
		}
		return "toTheoContentExpPage";
	}
	public void addTheoExpermentProject() throws IOException{
		expermentProject_Theo.setSyllabusid(syllabusId);
		expermentProject_InnerExperimentService.addExpermentProject_Theo(expermentProject_Theo);	
		String result = "{\"error\": false, \"content\": \"" + expermentProject_Theo.getExpermentProject_Theoid() + "\"}";
		HttpServletResponse response = ServletActionContext.getResponse();
	    response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8");  
		PrintWriter writer = response.getWriter();  
		writer.write(result);
	}
	public void updateTheoExpermentProject(){
		expermentProject_Theo.setSyllabusid(syllabusId);
		expermentProject_InnerExperimentService.updateExpermentProject_Theo(expermentProject_Theo);	
	}
	public void deleteTheoExpermentProject(){
		expermentProject_InnerExperimentService.deleteExpermentProject_Theo(expermentProject_Theo);	
	}
	

	public String toTheoInnerExperimentPage()
	{
		expermentProject_InnerExperimentService.toTheoInnerExperimentPage(syllabusId,theoreticalLessonId);
		return "toTheoInnerExperimentPage";
	}

	
}
