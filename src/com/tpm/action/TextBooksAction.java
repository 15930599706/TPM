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
import com.tpm.entity.Professional;
import com.tpm.entity.TextBooks;
import com.tpm.service.TextBooksService;

public class TextBooksAction extends ActionSupport implements ModelDriven<TextBooks> {

	private TextBooks textBooks =new TextBooks();
	private TextBooksService textBooksService;
	public TextBooksService getTextBooksService() {
		return textBooksService;
	}
	public void setTextBooksService(TextBooksService textBooksService) {
		this.textBooksService = textBooksService;
	}
	public TextBooks getModel() {
		return textBooks;
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
	/*理论课教材*/
	
	private String curriculumId;

	public void setCurriculumId(String curriculumId) {
		this.curriculumId = curriculumId;
	}
	public String toMateriaTheoPage()
	{
		ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
		List<TextBooks> textBookslist=textBooksService.toMateriaTheoPage(syllabusId,theoreticalLessonId,curriculumId);
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
		return "toMateriaTheoPage";
	}
	public String addtextBooks() throws IOException{
		textBooks.setSyllabusID(syllabusId);	
		textBooksService.addtextBooks(textBooks);	
		String result = "{\"error\": false, \"content\": \"" + textBooks.getTextBooksid() + "\"}";
		HttpServletResponse response = ServletActionContext.getResponse();
	    response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8");  
		PrintWriter writer = response.getWriter();  
		writer.write(result);
		return null ;
	}
	public String updatetextBooks(){
		textBooks.setSyllabusID(syllabusId);
		textBooksService.updatetextBooks(textBooks);	
		return "updatetextBooks";
	}
	public String deletetextBooks(){
		textBooksService.deletetextBooks(textBooks);	
		return "deletetextBooks";
	}
	
	
	
	
}
