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
import com.tpm.entity.PracticeBooks_CourseDesign;
import com.tpm.service.PracticeBooks_CourseDesignService;

public class PracticeBook_CourseDesignAction extends ActionSupport implements
		ModelDriven<PracticeBooks_CourseDesign> {

	private PracticeBooks_CourseDesign practiceBooks_CourseDesign = new PracticeBooks_CourseDesign();
	public PracticeBooks_CourseDesign getModel() {
		return practiceBooks_CourseDesign;
	}

	private PracticeBooks_CourseDesignService practiceBooks_CourseDesignService;
	public void setPracticeBooks_CourseDesignService(
			PracticeBooks_CourseDesignService practiceBooks_CourseDesignService) {
		this.practiceBooks_CourseDesignService = practiceBooks_CourseDesignService;
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
	public String toMateriaCouDesPage()
	{
		ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
		List<PracticeBooks_CourseDesign> practiceBooks_CourseDesignlist=practiceBooks_CourseDesignService.toMateria_CourseDesignPage(syllabusId,practiceLessonid);
		if(practiceBooks_CourseDesignlist.size() ==0 ){
			HttpServletRequest request=ServletActionContext.getRequest();			
			request.setAttribute("practiceBook", "[]");
		} 
		else 
		{
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setIgnoreDefaultExcludes(false);
			JSONArray jSONArray = JSONArray.fromObject(practiceBooks_CourseDesignlist, jsonConfig);
			String practiceBookStr = jSONArray.toString();
			HttpServletRequest request=ServletActionContext.getRequest();			
			request.setAttribute("practiceBook", practiceBookStr);
		}
		return "toMateriaCouDesPage";
	}
	public String addCourseBooks() throws IOException{

		practiceBooks_CourseDesign.setSyllabus_CourseDesignid(syllabusId);
		practiceBooks_CourseDesignService.addpracticeBooks_CourseDesign(practiceBooks_CourseDesign);	
		String result = "{\"error\": false, \"content\": \"" + practiceBooks_CourseDesign.getPracticeBooks_CourseDesignid() + "\"}";
		HttpServletResponse response = ServletActionContext.getResponse();
	    response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8");  
		PrintWriter writer = response.getWriter();  
		writer.write(result);
		return null ;
	}
	public String updateCourseBooks(){
		practiceBooks_CourseDesign.setSyllabus_CourseDesignid(syllabusId);
		practiceBooks_CourseDesignService.updatePracticeBooks_CourseDesign(practiceBooks_CourseDesign);	
		return "updateCourseBooks";
	}
	public String deleteCourseBooks(){
		practiceBooks_CourseDesignService.deletePracticeBooks_CourseDesign(practiceBooks_CourseDesign);	
		return "deleteCourseBooks";
	}
	
}
