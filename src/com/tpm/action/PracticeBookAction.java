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
import com.tpm.entity.PracticeBook;
import com.tpm.entity.PracticeBooks_CourseDesign;
import com.tpm.entity.Professional;
import com.tpm.entity.TextBooks;
import com.tpm.service.PracticeBookService;

public class PracticeBookAction extends ActionSupport implements ModelDriven<PracticeBook>{
	private PracticeBook practiceBook = new PracticeBook();
	public PracticeBook getModel() {
		return practiceBook;
	}
	private PracticeBookService practiceBookService;
	public void setPracticeBookService(PracticeBookService practiceBookService) {
		this.practiceBookService = practiceBookService;
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
/*****************************毕业设计************************************/
	public String toMateriaGraPage()
	{
		ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
		List<PracticeBook> practiceBooklist=practiceBookService.toMateriaTheoPage(syllabusId,practiceLessonid);
		if(practiceBooklist.size() ==0 ){
			HttpServletRequest request=ServletActionContext.getRequest();			
			request.setAttribute("practiceBook", "[]");
		} 
		else 
		{
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setIgnoreDefaultExcludes(false);
			JSONArray jSONArray = JSONArray.fromObject(practiceBooklist, jsonConfig);
			String practiceBookStr = jSONArray.toString();
			HttpServletRequest request=ServletActionContext.getRequest();			
			request.setAttribute("practiceBook", practiceBookStr);
		}
		return "toMateriaGraPage";
	}
	public String addpracticeBook() throws IOException{

		practiceBook.setSyllabus_Graid(syllabusId);
		practiceBookService.addpracticeBook(practiceBook);	
		String result = "{\"error\": false, \"content\": \"" + practiceBook.getPracticeBookid() + "\"}";
		HttpServletResponse response = ServletActionContext.getResponse();
	    response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8");  
		PrintWriter writer = response.getWriter();  
		writer.write(result);
		return null ;
	}
	public String updatepracticeBook(){
		practiceBook.setSyllabus_Graid(syllabusId);
		practiceBookService.updatepracticeBook(practiceBook);	
		return "updatepracticeBook";
	}
	public String deletepracticeBook(){
		practiceBookService.deletepracticeBook(practiceBook);	
		return "deletepracticeBook";
	}

	
}
