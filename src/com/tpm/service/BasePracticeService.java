package com.tpm.service;

import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.transaction.annotation.Transactional;

import com.tpm.dao.ApplicationSyllabus_GraDao;
import com.tpm.dao.BasePracticeDao;
import com.tpm.dao.CurriculumDao;
import com.tpm.dao.PracticePlanDao;
import com.tpm.dao.Syllabus_GraDao;
import com.tpm.entity.ApplicationSyllabus_CourseDesign;
import com.tpm.entity.ApplicationSyllabus_Gra;
import com.tpm.entity.BasePractice;
import com.tpm.entity.ContentGra;
import com.tpm.entity.Curriculum;
import com.tpm.entity.DistributeHour_Gra;
import com.tpm.entity.DistributeHour_Theo;
import com.tpm.entity.PracticeLesson;
import com.tpm.entity.Syllabus_CourseDesign;
import com.tpm.entity.Syllabus_Gra;

@Transactional
public class BasePracticeService {
	private BasePracticeDao basePracticeDao;
	private PracticePlanDao practicePlanDao;
	private ApplicationSyllabus_GraDao applicationSyllabus_GraDao;
	private Syllabus_GraDao syllabus_GraDao;
	
	public void setPracticePlanDao(PracticePlanDao practicePlanDao) {
		this.practicePlanDao = practicePlanDao;
	}

	public void setApplicationSyllabus_GraDao(
			ApplicationSyllabus_GraDao applicationSyllabus_GraDao) {
		this.applicationSyllabus_GraDao = applicationSyllabus_GraDao;
	}

	public void setSyllabus_GraDao(Syllabus_GraDao syllabus_GraDao) {
		this.syllabus_GraDao = syllabus_GraDao;
	}

	public void setBasePracticeDao(BasePracticeDao basePracticeDao) {
		this.basePracticeDao = basePracticeDao;
	}

	public void toBaseGraPage(String syllabusId, String practiceLessonid) {
		List<ApplicationSyllabus_Gra> applicationSyllabus_CourseDesign = applicationSyllabus_GraDao.findCurriculum(syllabusId);//在大纲应用专业表找课程信息
		Curriculum curriculum = applicationSyllabus_CourseDesign.get(0).getCurriculum();
		ServletActionContext.getRequest().setAttribute("curriculum", curriculum);
		ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
		PracticeLesson newpracticeLesson = practicePlanDao.get(Integer.valueOf(practiceLessonid));
		ServletActionContext.getRequest().setAttribute("newpracticeLesson",newpracticeLesson);
		
		List<DistributeHour_Gra> distributeHour_Gralist = basePracticeDao.findDistributeHour_Theo(syllabusId);
		ServletActionContext.getRequest().setAttribute("distributeHour_Gralist",distributeHour_Gralist);
		
		if(applicationSyllabus_CourseDesign != null && applicationSyllabus_CourseDesign.size() != 0)
		{
			if(applicationSyllabus_CourseDesign.get(0).getProfessional() != null)
			{
				ServletActionContext.getRequest().setAttribute("applicationSyllabus", applicationSyllabus_CourseDesign);
				ServletActionContext.getRequest().setAttribute("flage", 1);
			}
				
			else{ 
				ServletActionContext.getRequest().setAttribute("applicationSyllabus", applicationSyllabus_CourseDesign.get(0));
				ServletActionContext.getRequest().setAttribute("flage", -1);
			}
		}
		Syllabus_Gra syllabus_Gra = syllabus_GraDao.get(Integer.valueOf(syllabusId));
		if(syllabus_Gra.getBasePractice() != null){
			ServletActionContext.getRequest().setAttribute("Basepractice", syllabus_Gra.getBasePractice());
		}
	}
	public void updateBasePractice (BasePractice basePractice, String syllabusId, String practiceLessonid, String data) throws Exception{
		if(basePractice.getBasePracticeid() != null){
			Syllabus_Gra syllabus_Gra = syllabus_GraDao.get(Integer.valueOf(syllabusId));
			syllabus_Gra.setBasePractice(basePractice);;
			basePracticeDao.update(basePractice);
		}else{
			basePracticeDao.add(basePractice);
			
			Syllabus_Gra syllabus_Gra = syllabus_GraDao.get(Integer.valueOf(syllabusId));
		
			syllabus_Gra.setBasePractice(basePractice);
			syllabus_GraDao.add(syllabus_Gra);
		}
		PracticeLesson newpracticeLesson = practicePlanDao.get(Integer.valueOf(practiceLessonid));
		ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
		ServletActionContext.getRequest().setAttribute("newpracticeLesson", newpracticeLesson);
	
		String result = URLDecoder.decode(data,"UTF-8");
		JSONArray jsonArray = new JSONArray(result);
		int iSize = jsonArray.length();	
		basePracticeDao.deleteDistributeHour_Gra(syllabusId);
		for (int i = 0; i < iSize; i++) 
		{
			JSONObject jsonObj = jsonArray.getJSONObject(i);
			String namecourse =jsonObj.get("namecourse").toString();
			String time = jsonObj.get("time").toString();

			DistributeHour_Gra distributeHour_Gra = new DistributeHour_Gra();
			distributeHour_Gra.setName(namecourse);
			distributeHour_Gra.setTime(time);
			distributeHour_Gra.setSyllabusid(syllabusId);
			basePracticeDao.addDistributeHour_Gra(distributeHour_Gra);
		}
		String res = "{\"error\": false, \"message\":\"保存成功，请填写其他大纲内容！\"}";
		HttpServletResponse response = ServletActionContext.getResponse();
	    response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8");  
		PrintWriter writer = response.getWriter();  
		writer.write(res);
	//	ServletActionContext.getRequest().setAttribute("msg", "保存成功!");
	//	ServletActionContext.getRequest().setAttribute("tag", "toPracLes_GraPage");
	}
}
