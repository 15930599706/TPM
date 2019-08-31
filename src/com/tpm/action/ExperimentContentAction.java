package com.tpm.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tpm.entity.ContentTheo;
import com.tpm.entity.Curriculum;
import com.tpm.entity.Department;
import com.tpm.entity.ExperimentContent;
import com.tpm.entity.Professional;
import com.tpm.service.ExperimentContentService;

public class ExperimentContentAction extends ActionSupport implements ModelDriven<ExperimentContent>{
	
	private ExperimentContent experimentContent = new ExperimentContent();
	public ExperimentContent getModel() {
		return experimentContent;
	}
	private ExperimentContentService experimentContentService;
	public void setExperimentContentService(ExperimentContentService experimentContentService) {
		this.experimentContentService = experimentContentService;
	}
	
	private String syllabusId;
	
	public String getSyllabusId() {
		return syllabusId;
	}
	public void setSyllabusId(String syllabusId) {
		this.syllabusId = syllabusId;
	}
	private String theoreticalLessonid;
	public String getTheoreticalLessonid() {
		return theoreticalLessonid;
	}
	public void setTheoreticalLessonid(String theoreticalLessonid) {
		this.theoreticalLessonid = theoreticalLessonid;
	}
	
	private String curriculumId;
	public void setCurriculumId(String curriculumId) {
		this.curriculumId = curriculumId;
	}
	public String toEditTestPage()
	{
		ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
		List<ExperimentContent> experimentContentlist=experimentContentService.toexperimentContent(syllabusId,theoreticalLessonid,curriculumId);
		if(experimentContentlist.size() == 0) {
			HttpServletRequest request=ServletActionContext.getRequest();			
			request.setAttribute("experimentContent", "[]");
		}
		else
		{
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setIgnoreDefaultExcludes(false);
			JSONArray jSONArray = JSONArray.fromObject(experimentContentlist, jsonConfig);
			String experimentContentStr = jSONArray.toString();
			HttpServletRequest request=ServletActionContext.getRequest();			
			request.setAttribute("experimentContent", experimentContentStr);
		}
		return "toEditTestPage";
	}
	public String addExperimentContent() throws Exception{
		experimentContent.setSyllabusID(syllabusId);	
		experimentContentService.addExperimentContent(experimentContent);	
		String result = "{\"error\": false, \"content\": \"" + experimentContent.getExperimentContentid() + "\"}";
		HttpServletResponse response = ServletActionContext.getResponse();
	    response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8");  
		PrintWriter writer = response.getWriter();  
		writer.write(result);
		return null ;
	}
	public String updataExperimentContent() {
		experimentContent.setSyllabusID(syllabusId);	
		experimentContentService.updataExperimentContent(experimentContent);
		return "updataExperimentContent";
	}
	public String deleteExperimentContent(){
		experimentContentService.deleteExperimentContent(experimentContent);
		return "deleteExperimentContent";
	}
}
