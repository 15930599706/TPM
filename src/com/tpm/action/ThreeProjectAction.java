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
import com.tpm.entity.Professional;
import com.tpm.entity.ThreeProject;
import com.tpm.service.ThreeProjectService;

public class ThreeProjectAction extends ActionSupport implements ModelDriven<ThreeProject> {
	private ThreeProject threeProject = new ThreeProject();
	public ThreeProject getModel() {
		return threeProject;
	}
	private ThreeProjectService threeProjectService;
	public void setThreeProjectService(ThreeProjectService threeProjectService) {
		this.threeProjectService = threeProjectService;
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
	public String toEditThreePage()
	{
		ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
		List<ThreeProject> threeProjectlist=threeProjectService.toEditThreePage(syllabusId,theoreticalLessonid,curriculumId);	
		if(threeProjectlist.size() == 0) {
			HttpServletRequest request=ServletActionContext.getRequest();			
			request.setAttribute("threeProject", "[]");
		}
		else
		{
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setIgnoreDefaultExcludes(false);
			jsonConfig.setExcludes(new String[]{"department","professional","curriculum"});
			JSONArray jSONArray = JSONArray.fromObject(threeProjectlist, jsonConfig);
			String threeProjectStr = jSONArray.toString();
			HttpServletRequest request=ServletActionContext.getRequest();			
			request.setAttribute("threeProject", threeProjectStr);
		}
		return "toEditThreePage";
	}
	public String addThreeProject() throws Exception{
		threeProject.setSyllabusID(syllabusId);

		threeProjectService.addThreeProject(threeProject);	
		String result = "{\"error\": false, \"content\": \"" + threeProject.getThreeProjectid() + "\"}";
		HttpServletResponse response = ServletActionContext.getResponse();
	    response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8");  
		PrintWriter writer = response.getWriter();  
		writer.write(result);
		return null ;
	}
	public String updateThreeProject() {
		threeProject.setSyllabusID(syllabusId);
		threeProjectService.updateThreeProject(threeProject);
		return "updateThreeProject";
	}
	public String deleteThreeProject(){
		threeProjectService.deleteThreeProject(threeProject);
		return "deleteThreeProject";
	}
}
