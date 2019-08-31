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
import com.tpm.entity.Professional;
import com.tpm.service.ExpermentProjectService;

public class ExpermentProjectAction extends ActionSupport implements ModelDriven<ExpermentProject>{

	private ExpermentProject expermentProject = new ExpermentProject();
	public ExpermentProject getModel() {
		return expermentProject;
	}
	private ExpermentProjectService expermentProjectService;
	public void setExpermentProjectService(
			ExpermentProjectService expermentProjectService) {
		this.expermentProjectService = expermentProjectService;
	}
	
	private String syllabusId;
	
	public String getSyllabusId() {
		return syllabusId;
	}
	public void setSyllabusId(String syllabusId) {
		this.syllabusId = syllabusId;
	}
	private String practiceLessonid;
	public String getPracticeLessonid() {
		return practiceLessonid;
	}
	public void setPracticeLessonid(String practiceLessonid) {
		this.practiceLessonid = practiceLessonid;
	}
	public String toContentExpPage()
	{
		ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
		List<ExpermentProject> textBookslist=expermentProjectService.toContentExpPage(syllabusId,practiceLessonid);
		if(textBookslist.size() ==0 ){
			HttpServletRequest request=ServletActionContext.getRequest();			
			request.setAttribute("textBooks", "[]");
		} 
		else 
		{
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setIgnoreDefaultExcludes(false);
			jsonConfig.setExcludes(new String[]{"department","professional","curriculum"});
			JSONArray jSONArray = JSONArray.fromObject(textBookslist, jsonConfig);
			String textBooksStr = jSONArray.toString();
			HttpServletRequest request=ServletActionContext.getRequest();			
			request.setAttribute("textBooks", textBooksStr);
		}
		return "toContentExpPage";
	}
	public String addExpermentProject() throws IOException{
		expermentProject.setSyllabus_InnerExperimentid(syllabusId);
		expermentProjectService.addExpermentProject(expermentProject);	
		String result = "{\"error\": false, \"content\": \"" + expermentProject.getExpermentProjectid() + "\"}";
		HttpServletResponse response = ServletActionContext.getResponse();
	    response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8");  
		PrintWriter writer = response.getWriter();  
		writer.write(result);
		return null ;
	}
	public String updateExpermentProject(){
		expermentProject.setSyllabus_InnerExperimentid(syllabusId);
		expermentProjectService.updateExpermentProject(expermentProject);	
		return "updateExpermentProject";
	}
	public String deleteExpermentProject(){
		expermentProjectService.deleteExpermentProject(expermentProject);	
		return "deleteExpermentProject";
	}
}
