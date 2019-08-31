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
import com.tpm.entity.DiscussContent;
import com.tpm.entity.Professional;
import com.tpm.entity.TextBooks;
import com.tpm.service.DiscussContentService;

public class DiscussContentAction extends ActionSupport implements ModelDriven<DiscussContent> {

	private DiscussContent discussContent =new DiscussContent();
	public DiscussContent getModel() {
		return discussContent;
	}
	private DiscussContentService discussContentService;
	public void setDiscussContentService(DiscussContentService discussContentService) {
		this.discussContentService = discussContentService;
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
	public String toEditDiscussPage()
	{
		ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
		List<DiscussContent> discussContentlist=discussContentService.toEditDiscussPage(syllabusId,theoreticalLessonid,curriculumId);
		if(discussContentlist.size() ==0 ){
			HttpServletRequest request=ServletActionContext.getRequest();			
			request.setAttribute("discussContent", "[]");
		} 
		else 
		{
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setIgnoreDefaultExcludes(false);
			jsonConfig.setExcludes(new String[]{"department","professional","curriculum"});
			JSONArray jSONArray = JSONArray.fromObject(discussContentlist, jsonConfig);
			String discussContentStr = jSONArray.toString();
			HttpServletRequest request=ServletActionContext.getRequest();			
			request.setAttribute("discussContent", discussContentStr);
		}
		return "toEditDiscussPage";
	}
	public String adddiscussContent() throws IOException{
		discussContent.setSyllabusID(syllabusId);
		discussContentService.adddiscussContent(discussContent);	
		String result = "{\"error\": false, \"content\": \"" + discussContent.getDiscussContentid() + "\"}";
		HttpServletResponse response = ServletActionContext.getResponse();
	    response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8");  
		PrintWriter writer = response.getWriter();  
		writer.write(result);
		return null ;
	}
	public String updatediscussContent(){
		discussContent.setSyllabusID(syllabusId);
		discussContentService.updatediscussContent(discussContent);	
		return "updatediscussContent";
	}
	public String deletediscussContent(){
		discussContentService.deletediscussContent(discussContent);	
		return "deletediscussContent";
	}
}
