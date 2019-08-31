package com.tpm.service;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.transaction.annotation.Transactional;

import com.tpm.dao.ApplicationSyllabusDao;
import com.tpm.dao.BaseTheoDao;
import com.tpm.dao.CurriculumDao;
import com.tpm.dao.PracticePlanDao;
import com.tpm.dao.SyllabusDao;
import com.tpm.dao.TheoreticalPlanDao;
import com.tpm.entity.ApplicationSyllabus;
import com.tpm.entity.BaseTheo;
import com.tpm.entity.Curriculum;
import com.tpm.entity.DistributeHour_Theo;
import com.tpm.entity.PracticeLesson;
import com.tpm.entity.Syllabus;
import com.tpm.entity.TheoreticalLesson;
@Transactional
public class BaseTheoService {
	private BaseTheoDao baseTheoDao;
	private CurriculumDao curriculumDao;
	private ApplicationSyllabusDao applicationSyllabusDao;
	private SyllabusDao syllabusDao;
	private TheoreticalPlanDao theoreticalPlanDao;
	
	private PracticePlanDao practicePlanDao;
	public void setPracticePlanDao(PracticePlanDao practicePlanDao) {
		this.practicePlanDao = practicePlanDao;
	}
	public void setTheoreticalPlanDao(TheoreticalPlanDao theoreticalPlanDao) {
		this.theoreticalPlanDao = theoreticalPlanDao;
	}

	public void setSyllabusDao(SyllabusDao syllabusDao) {
		this.syllabusDao = syllabusDao;
	}

	public void setApplicationSyllabusDao(
			ApplicationSyllabusDao applicationSyllabusDao) {
		this.applicationSyllabusDao = applicationSyllabusDao;
	}

	public void setCurriculumDao(CurriculumDao curriculumDao) {
		this.curriculumDao = curriculumDao;
	}

	public void setBaseTheoDao(BaseTheoDao baseTheoDao) {
		this.baseTheoDao = baseTheoDao;
	}

	public void toBaseTheo(String syllabusId, String theoreticalLessonId, String curriculumId) {
		List<ApplicationSyllabus> applicationSyllabus = applicationSyllabusDao.findCurriculum(syllabusId);//在大纲应用专业表找课程信息
		List<DistributeHour_Theo> distributeHour_Theolist = baseTheoDao.findDistributeHour_Theo(syllabusId);
		ServletActionContext.getRequest().setAttribute("distributeHour_Theolist",distributeHour_Theolist);
		
		Curriculum curriculum = applicationSyllabus.get(0).getCurriculum();
		ServletActionContext.getRequest().setAttribute("curriculum", curriculum);
		ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
		
		Curriculum newCurriculum = curriculumDao.get(curriculumId);
		if(newCurriculum.getCourseLei().equals("理论课")||newCurriculum.getCourseLei().equals("实验课"))
		{
			TheoreticalLesson newtheoreticalLesson = theoreticalPlanDao.get(Integer.valueOf(theoreticalLessonId));
			ServletActionContext.getRequest().setAttribute("newtheoreticalLesson",newtheoreticalLesson);
		}
		else
		{
			PracticeLesson newtheoreticalLesson = practicePlanDao.get(Integer.valueOf(theoreticalLessonId));
			ServletActionContext.getRequest().setAttribute("newtheoreticalLesson",newtheoreticalLesson);
		}
		
		if(applicationSyllabus != null && applicationSyllabus.size() != 0)
		{
			if(applicationSyllabus.get(0).getProfessional() != null)
			{
				ServletActionContext.getRequest().setAttribute("applicationSyllabus", applicationSyllabus);
				ServletActionContext.getRequest().setAttribute("flage", 1);
			}
				
			else{ 
				ServletActionContext.getRequest().setAttribute("applicationSyllabus", applicationSyllabus.get(0));
				ServletActionContext.getRequest().setAttribute("flage", -1);
			}
		}
		Syllabus syllabus = syllabusDao.get(Integer.valueOf(syllabusId));
		if(syllabus.getBaseTheo() != null){
			ServletActionContext.getRequest().setAttribute("BaseTheo", syllabus.getBaseTheo());
		}
	}
	public void updateBaseTheo(BaseTheo baseTheo, String syllabusId,
			String data, String theoreticalLessonId, String curriculumId) throws Exception {

		if(baseTheo.getBaseTheoid() != null){
			baseTheoDao.update(baseTheo);
		}else{
			baseTheoDao.add(baseTheo);
			
			Syllabus syllabus = syllabusDao.get(Integer.valueOf(syllabusId));
		
			syllabus.setBaseTheo(baseTheo);
			syllabusDao.update(syllabus);
		}
		Curriculum newCurriculum = curriculumDao.get(curriculumId);
		if(newCurriculum.getCourseLei().equals("理论课")||newCurriculum.getCourseLei().equals("实验课"))
		{
			TheoreticalLesson newtheoreticalLesson = theoreticalPlanDao.get(Integer.valueOf(theoreticalLessonId));
			ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
			ServletActionContext.getRequest().setAttribute("newtheoreticalLesson", newtheoreticalLesson);
		
		}
		else
		{
			PracticeLesson newtheoreticalLesson = practicePlanDao.get(Integer.valueOf(theoreticalLessonId));
			ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
			ServletActionContext.getRequest().setAttribute("newtheoreticalLesson", newtheoreticalLesson);
		}
		
		
		String result = URLDecoder.decode(data,"UTF-8");
		JSONArray jsonArray = new JSONArray(result);
		int iSize = jsonArray.length();	
		baseTheoDao.deleteDistributeHour_Theo(syllabusId);
		for (int i = 0; i < iSize; i++) 
		{
			JSONObject jsonObj = jsonArray.getJSONObject(i);
			String namecourse =jsonObj.get("namecourse").toString();
			String classhour = jsonObj.get("classhour").toString();
			String exphour = jsonObj.get("exphour").toString();
			String conhour =jsonObj.get("conhour").toString();
			DistributeHour_Theo distributeHour_Theo = new DistributeHour_Theo();
			distributeHour_Theo.setName(namecourse);
			distributeHour_Theo.setHoursOfClass(classhour);
			distributeHour_Theo.setHoursOfExp(exphour);
			distributeHour_Theo.setHoursOfCom(conhour);
			distributeHour_Theo.setSyllabusid(syllabusId);
			baseTheoDao.addDistributeHour_Theo(distributeHour_Theo);
		}
		
		String res = "{\"error\": false, \"message\":\"保存成功，请填写其他大纲内容！\"}";
		HttpServletResponse response = ServletActionContext.getResponse();
	    response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8");  
		PrintWriter writer = response.getWriter();  
		writer.write(res);
		//ServletActionContext.getRequest().setAttribute("msg", "保存成功!");
		//ServletActionContext.getRequest().setAttribute("tag", "toTheoLesPage");
	}
	
}
