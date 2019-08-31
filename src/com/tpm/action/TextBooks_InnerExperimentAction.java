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
import com.tpm.entity.TextBooks_InnerExperiment;
import com.tpm.service.PracticeBooks_InnerExperimentService;
import com.tpm.service.TextBooks_InnerExperimentService;

public class TextBooks_InnerExperimentAction extends ActionSupport implements
		ModelDriven<TextBooks_InnerExperiment> {

	private TextBooks_InnerExperiment textBooks_InnerExperiment = new TextBooks_InnerExperiment();
	public TextBooks_InnerExperiment getModel() {
		return textBooks_InnerExperiment;
	}

	private TextBooks_InnerExperimentService textBooks_InnerExperimentService ;
	
	public void setTextBooks_InnerExperimentService(
			TextBooks_InnerExperimentService textBooks_InnerExperimentService) {
		this.textBooks_InnerExperimentService = textBooks_InnerExperimentService;
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
	public String toTheoMateriaExpPage()
	{
		ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
		List<TextBooks_InnerExperiment> practiceBooklist=textBooks_InnerExperimentService.toTheoInnerExperimentPage(syllabusId,theoreticalLessonId);
		if(practiceBooklist.size() ==0 ){
			HttpServletRequest request=ServletActionContext.getRequest();			
			request.setAttribute("practiceBook", "[]");
		} 
		else 
		{
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setIgnoreDefaultExcludes(false);
			//jsonConfig.setExcludes(new String[]{"department","professional","curriculum"});
			JSONArray jSONArray = JSONArray.fromObject(practiceBooklist, jsonConfig);
			String practiceBookStr = jSONArray.toString();
			HttpServletRequest request=ServletActionContext.getRequest();			
			request.setAttribute("practiceBook", practiceBookStr);
		}
		return "toTheoMateriaExpPage";
	}
	public void addTextBooksInnerExperiment() throws IOException{

		textBooks_InnerExperiment.setSyllabusID(syllabusId);
		textBooks_InnerExperimentService.addpracticeBooks_InnerExperiment(textBooks_InnerExperiment);	
		String result = "{\"error\": false, \"content\": \"" + textBooks_InnerExperiment.getTextBooks_InnerExperimentid() + "\"}";
		HttpServletResponse response = ServletActionContext.getResponse();
	    response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8");  
		PrintWriter writer = response.getWriter();  
		writer.write(result);
	}
	public void updateTextBooksInnerExperimen(){
		textBooks_InnerExperiment.setSyllabusID(syllabusId);
		textBooks_InnerExperimentService.updateTextBooksInnerExperimen(textBooks_InnerExperiment);	
	}
	public void deleteTextBooksInnerExperimens(){
		textBooks_InnerExperimentService.deleteTextBooksInnerExperimen(textBooks_InnerExperiment);	
	}
}
