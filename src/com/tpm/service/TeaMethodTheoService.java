package com.tpm.service;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.transaction.annotation.Transactional;

import com.tpm.dao.ApplicationSyllabusDao;
import com.tpm.dao.CurriculumDao;
import com.tpm.dao.PracticePlanDao;
import com.tpm.dao.SyllabusDao;
import com.tpm.dao.TeaMethodTheoDao;
import com.tpm.dao.TheoreticalPlanDao;
import com.tpm.entity.ApplicationSyllabus;
import com.tpm.entity.Curriculum;
import com.tpm.entity.PracticeLesson;
import com.tpm.entity.Syllabus;
import com.tpm.entity.TeaMethodTheo;
import com.tpm.entity.TheoreticalLesson;

@Transactional
public class TeaMethodTheoService {
	private  TeaMethodTheoDao teaMethodTheoDao;
	private ApplicationSyllabusDao applicationSyllabusDao;
	private TheoreticalPlanDao theoreticalPlanDao;
	private SyllabusDao syllabusDao;
	private PracticePlanDao practicePlanDao;
	private CurriculumDao curriculumDao;

	public void setCurriculumDao(CurriculumDao curriculumDao) {
		this.curriculumDao = curriculumDao;
	}

	public void setPracticePlanDao(PracticePlanDao practicePlanDao) {
		this.practicePlanDao = practicePlanDao;
	}

	public void setSyllabusDao(SyllabusDao syllabusDao) {
		this.syllabusDao = syllabusDao;
	}

	public void setTheoreticalPlanDao(TheoreticalPlanDao theoreticalPlanDao) {
		this.theoreticalPlanDao = theoreticalPlanDao;
	}

	public void setApplicationSyllabusDao(
			ApplicationSyllabusDao applicationSyllabusDao) {
		this.applicationSyllabusDao = applicationSyllabusDao;
	}

	public void setTeaMethodTheoDao(TeaMethodTheoDao teaMethodTheoDao) {
		this.teaMethodTheoDao = teaMethodTheoDao;
	}


	public void toTeaMethodTheo(String syllabusId, String theoreticalLessonId, String curriculumId) {
		List<ApplicationSyllabus> applicationSyllabus = applicationSyllabusDao.findCurriculum(syllabusId);//在大纲应用专业表找课程信息
		
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
		if(syllabus.getTeaMethodTheo() != null){
			ServletActionContext.getRequest().setAttribute("TeaMethodTheo", syllabus.getTeaMethodTheo());
		}
		
	
	
	
	}

	public void updateTeaMethodTheo(TeaMethodTheo teaMethodTheo,String syllabusId, String theoreticalLessonId, String curriculumId) {

			if(teaMethodTheo.getTeaMethodTheoid() != null){
				Syllabus syllabus = syllabusDao.get(Integer.valueOf(syllabusId));
				syllabus.setTeaMethodTheo(teaMethodTheo);
				teaMethodTheoDao.update(teaMethodTheo);
			}else{
				Syllabus syllabus = syllabusDao.get(Integer.valueOf(syllabusId));
				syllabus.setTeaMethodTheo(teaMethodTheo);
				teaMethodTheoDao.add(teaMethodTheo);
			}	


			Curriculum newCurriculum = curriculumDao.get(curriculumId);
			if(newCurriculum.getCourseLei().equals("理论课"))
			{
				TheoreticalLesson newtheoreticalLesson = theoreticalPlanDao.get(Integer.valueOf(theoreticalLessonId));
				ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
				ServletActionContext.getRequest().setAttribute("newtheoreticalLesson", newtheoreticalLesson);
				ServletActionContext.getRequest().setAttribute("msg", "保存成功!");
				ServletActionContext.getRequest().setAttribute("tag", "toTheoLesPage");
			}
			else if(newCurriculum.getCourseLei().equals("实验课"))
			{
				TheoreticalLesson newtheoreticalLesson = theoreticalPlanDao.get(Integer.valueOf(theoreticalLessonId));
				ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
				ServletActionContext.getRequest().setAttribute("newtheoreticalLesson", newtheoreticalLesson);
				ServletActionContext.getRequest().setAttribute("msg", "保存成功!");
				ServletActionContext.getRequest().setAttribute("tag", "toTheoLesPage");
			}
			else
			{
				PracticeLesson newtheoreticalLesson = practicePlanDao.get(Integer.valueOf(theoreticalLessonId));
				ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
				ServletActionContext.getRequest().setAttribute("newtheoreticalLesson", newtheoreticalLesson);
				ServletActionContext.getRequest().setAttribute("msg", "保存成功!");
				ServletActionContext.getRequest().setAttribute("tag", "toTheoLesPage_other");

			}
			
			

	}
}
