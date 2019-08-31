package com.tpm.service;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.transaction.annotation.Transactional;

import com.tpm.dao.ApplicationSyllabus_CourseDesignDao;
import com.tpm.dao.BaseCourseDesignDao;
import com.tpm.dao.CurriculumDao;
import com.tpm.dao.PracticePlanDao;
import com.tpm.dao.Syllabus_CourseDesignDao;
import com.tpm.entity.ApplicationSyllabus_CourseDesign;
import com.tpm.entity.ApplicationSyllabus_FieldWork;
import com.tpm.entity.BaseCourseDesign;
import com.tpm.entity.BasePractice;
import com.tpm.entity.Curriculum;
import com.tpm.entity.DistributeHour_CourseDesign;
import com.tpm.entity.DistributeHour_Gra;
import com.tpm.entity.PracticeLesson;
import com.tpm.entity.Syllabus_CourseDesign;
import com.tpm.entity.Syllabus_FieldWork;

@Transactional
public class BaseCourseDesignService {
	private BaseCourseDesignDao baseCourseDesignDao;
	private ApplicationSyllabus_CourseDesignDao applicationSyllabus_CourseDesignDao;
	private PracticePlanDao practicePlanDao;
	private Syllabus_CourseDesignDao syllabus_CourseDesignDao;
	
	public void setPracticePlanDao(PracticePlanDao practicePlanDao) {
		this.practicePlanDao = practicePlanDao;
	}
	public void setSyllabus_CourseDesignDao(
			Syllabus_CourseDesignDao syllabus_CourseDesignDao) {
		this.syllabus_CourseDesignDao = syllabus_CourseDesignDao;
	}
	public void setApplicationSyllabus_CourseDesignDao(
			ApplicationSyllabus_CourseDesignDao applicationSyllabus_CourseDesignDao) {
		this.applicationSyllabus_CourseDesignDao = applicationSyllabus_CourseDesignDao;
	}
	public void setBaseCourseDesignDao(BaseCourseDesignDao baseCourseDesignDao) {
		this.baseCourseDesignDao = baseCourseDesignDao;
	}
	public void toBaseCouDesPage(String syllabusId, String practiceLessonid) {
	List<ApplicationSyllabus_CourseDesign> applicationSyllabus_CourseDesign = applicationSyllabus_CourseDesignDao.findCurriculum(syllabusId);//在大纲应用专业表找课程信息
		
		Curriculum curriculum = applicationSyllabus_CourseDesign.get(0).getCurriculum();
		ServletActionContext.getRequest().setAttribute("curriculum", curriculum);
		ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
		PracticeLesson newpracticeLesson = practicePlanDao.get(Integer.valueOf(practiceLessonid));
		ServletActionContext.getRequest().setAttribute("newpracticeLesson",newpracticeLesson);
		List<DistributeHour_CourseDesign> distributeHour_CourseDesignlist = baseCourseDesignDao.findDistributeHour_CourseDesign(syllabusId);
		ServletActionContext.getRequest().setAttribute("distributeHour_CourseDesignlist",distributeHour_CourseDesignlist);
		
		
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
		Syllabus_CourseDesign syllabus_CourseDesign = syllabus_CourseDesignDao.get(Integer.valueOf(syllabusId));
		if(syllabus_CourseDesign.getBaseCourseDesign() != null){
			ServletActionContext.getRequest().setAttribute("baseCourseDesign", syllabus_CourseDesign.getBaseCourseDesign());
		}
		
		
	}
	public void updateBaseCourseDesign(BaseCourseDesign baseCourseDesign, String syllabusId, String practiceLessonid, String data) throws Exception {
		
		if(baseCourseDesign.getBaseCourseDesignid() != null){
			Syllabus_CourseDesign syllabus_CourseDesign = syllabus_CourseDesignDao.get(Integer.valueOf(syllabusId));
			syllabus_CourseDesign.setBaseCourseDesign(baseCourseDesign);
			baseCourseDesignDao.update(baseCourseDesign);
		}else{
			baseCourseDesignDao.add(baseCourseDesign);
			
			Syllabus_CourseDesign syllabus_CourseDesign = syllabus_CourseDesignDao.get(Integer.valueOf(syllabusId));
		
			syllabus_CourseDesign.setBaseCourseDesign(baseCourseDesign);
			syllabus_CourseDesignDao.add(syllabus_CourseDesign);
		}
		PracticeLesson newpracticeLesson = practicePlanDao.get(Integer.valueOf(practiceLessonid));
		ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
		ServletActionContext.getRequest().setAttribute("newpracticeLesson", newpracticeLesson);
		
		String result = URLDecoder.decode(data,"UTF-8");
		JSONArray jsonArray = new JSONArray(result);
		int iSize = jsonArray.length();	
		baseCourseDesignDao.deleteDistributeHour_CourseDesign(syllabusId);
		for (int i = 0; i < iSize; i++) 
		{
			JSONObject jsonObj = jsonArray.getJSONObject(i);
			String namecourse =jsonObj.get("namecourse").toString();
			String time = jsonObj.get("time").toString();

			DistributeHour_CourseDesign distributeHour_CourseDesign = new DistributeHour_CourseDesign();
			distributeHour_CourseDesign.setName(namecourse);
			distributeHour_CourseDesign.setTime(time);
			distributeHour_CourseDesign.setSyllabusid(syllabusId);
			baseCourseDesignDao.addDistributeHour_CourseDesign(distributeHour_CourseDesign);
		}
		
		String res = "{\"error\": false, \"message\":\"保存成功，请填写其他大纲内容！\"}";
		HttpServletResponse response = ServletActionContext.getResponse();
	    response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8");  
		PrintWriter writer = response.getWriter();  
		writer.write(res);
	//	ServletActionContext.getRequest().setAttribute("msg", "保存成功!");
	//	ServletActionContext.getRequest().setAttribute("tag", "toPracLes_CourseDesignPage");
	}
	
}
