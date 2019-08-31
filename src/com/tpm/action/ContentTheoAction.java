package com.tpm.action;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tpm.entity.ContentTheo;
import com.tpm.entity.Curriculum;
import com.tpm.entity.Department;
import com.tpm.entity.Professional;
import com.tpm.entity.TheoreticalLesson;
import com.tpm.service.ContentTheoService;

public class ContentTheoAction extends ActionSupport implements ModelDriven<ContentTheo>{

	private ContentTheo contentTheo = new ContentTheo();
	public ContentTheo getModel() {
		return contentTheo;
	}
	private ContentTheoService contentTheoservice;
	public ContentTheoService getContentTheoservice() {
		return contentTheoservice;
	}
	public void setContentTheoservice(ContentTheoService contentTheoservice) {
		this.contentTheoservice = contentTheoservice;
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
	private String curriculumId;
	public void setCurriculumId(String curriculumId) {
		this.curriculumId = curriculumId;
	}
	public String toContentTheoPage()
	{
		ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
		List<ContentTheo> contentTheolist=contentTheoservice.toContenTheo(syllabusId);	
		contentTheoservice.setTheo(theoreticalLessonId,curriculumId);
		if(contentTheolist.size() == 0) {
			HttpServletRequest request=ServletActionContext.getRequest();	
			request.setAttribute("contentTheo", "[]");
		}
		else
		{
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setIgnoreDefaultExcludes(false);
			JSONArray jSONArray = JSONArray.fromObject(contentTheolist, jsonConfig);
			String contentTheoStr = jSONArray.toString();
			HttpServletRequest request=ServletActionContext.getRequest();			
			request.setAttribute("contentTheo", contentTheoStr);
		}
	 return "toContentTheoPage";
	}
	public String addContenTheo() throws Exception{

		contentTheo.setSyllabusID(syllabusId);	
		contentTheoservice.addContenTheo(contentTheo);	
		String result = "{\"error\": false, \"content\": \"" + contentTheo.getContentTheoid() + "\"}";
		HttpServletResponse response = ServletActionContext.getResponse();
	    response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8");  
		PrintWriter writer = response.getWriter();  
		writer.write(result);
		return null ;
	}
	public String updataContenTheo() {

		contentTheo.setSyllabusID(syllabusId);	
		contentTheoservice.updateContenTheo(contentTheo);
		return "updatacontentTheo";
	}
	public String deleteContenTheo(){
		contentTheoservice.deleteContenTheo(contentTheo);
		return "deletecontenTheo";
	}
}
