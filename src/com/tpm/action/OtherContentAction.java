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
import com.tpm.entity.OtherContent;
import com.tpm.entity.Professional;
import com.tpm.service.OtherContentService;

public class OtherContentAction extends ActionSupport implements ModelDriven<OtherContent> {

	private OtherContent otherContent = new OtherContent();
	public OtherContent getModel() {
		return otherContent;
	}
	private OtherContentService otherContentService;
	public void setOtherContentService(OtherContentService otherContentService) {
		this.otherContentService = otherContentService;
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
	public String toEditOtherPage()
	{
		ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
		List<OtherContent> otherContentlist=otherContentService.toEditOtherPage(syllabusId,theoreticalLessonid,curriculumId);	
		if(otherContentlist.size() == 0) {
			HttpServletRequest request=ServletActionContext.getRequest();			
			request.setAttribute("otherContent", "[]");
		}
		else
		{
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setIgnoreDefaultExcludes(false);
			jsonConfig.setExcludes(new String[]{"department","professional","curriculum"});
			JSONArray jSONArray = JSONArray.fromObject(otherContentlist, jsonConfig);
			String otherContentStr = jSONArray.toString();
			HttpServletRequest request=ServletActionContext.getRequest();			
			request.setAttribute("otherContent", otherContentStr);
		}
		return "toEditOtherPage";
	}
	public String addOtherContent() throws Exception{
		otherContent.setSyllabusID(syllabusId);
		otherContentService.addOtherContent(otherContent);	
		String result = "{\"error\": false, \"content\": \"" + otherContent.getOtherContentid() + "\"}";
		HttpServletResponse response = ServletActionContext.getResponse();
	    response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8");  
		PrintWriter writer = response.getWriter();  
		writer.write(result);
		return null ;
	}
	public String updateOtherContent() {
		otherContent.setSyllabusID(syllabusId);
		otherContentService.updateOtherContent(otherContent);
		return "updateOtherContent";
	}
	public String deleteOtherContent(){
		otherContentService.deleteOtherContent(otherContent);
		return "deleteOtherContent";
	}
}
