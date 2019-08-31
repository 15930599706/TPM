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
import com.tpm.entity.PracticeBook;
import com.tpm.entity.PracticeBooks_InnerExperiment;
import com.tpm.service.PracticeBooks_InnerExperimentService;

public class PracticeBooks_InnerExperimentAction extends ActionSupport implements
		ModelDriven<PracticeBooks_InnerExperiment> {

	private PracticeBooks_InnerExperiment practiceBooks_InnerExperiment = new PracticeBooks_InnerExperiment();
	public PracticeBooks_InnerExperiment getModel() {
		return practiceBooks_InnerExperiment;
	}

	private PracticeBooks_InnerExperimentService practiceBooks_InnerExperimentService;
	public void setPracticeBooks_InnerExperimentService(
			PracticeBooks_InnerExperimentService practiceBooks_InnerExperimentService) {
		this.practiceBooks_InnerExperimentService = practiceBooks_InnerExperimentService;
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
	public String toMateriaExpPage()
	{
		ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
		List<PracticeBooks_InnerExperiment> practiceBooklist=practiceBooks_InnerExperimentService.toMateriaTheoPage(syllabusId,practiceLessonid);
		if(practiceBooklist.size() ==0 ){
			HttpServletRequest request=ServletActionContext.getRequest();			
			request.setAttribute("practiceBook", "[]");
		} 
		else 
		{
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setIgnoreDefaultExcludes(false);
			jsonConfig.setExcludes(new String[]{"department","professional","curriculum"});
			JSONArray jSONArray = JSONArray.fromObject(practiceBooklist, jsonConfig);
			String practiceBookStr = jSONArray.toString();
			HttpServletRequest request=ServletActionContext.getRequest();			
			request.setAttribute("practiceBook", practiceBookStr);
		}
		return "toMateriaExpPage";
	}
	public String addexperimentBooks() throws IOException{

		practiceBooks_InnerExperiment.setSyllabus_InnerExperimentid(syllabusId);
		practiceBooks_InnerExperimentService.addpracticeBooks_InnerExperiment(practiceBooks_InnerExperiment);	
		String result = "{\"error\": false, \"content\": \"" + practiceBooks_InnerExperiment.getPracticeBooks_InnerExperimentid() + "\"}";
		HttpServletResponse response = ServletActionContext.getResponse();
	    response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8");  
		PrintWriter writer = response.getWriter();  
		writer.write(result);
		return null ;
	}
	public String updateexperimentBooks(){
		practiceBooks_InnerExperiment.setSyllabus_InnerExperimentid(syllabusId);
		practiceBooks_InnerExperimentService.updatepracticeBook(practiceBooks_InnerExperiment);	
		return "updateexperimentBooks";
	}
	public String deleteexperimentBooks(){
		practiceBooks_InnerExperimentService.deletepracticeBook(practiceBooks_InnerExperiment);	
		return "deleteexperimentBooks";
	}
}
