package com.tpm.service;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.transaction.annotation.Transactional;

import com.tpm.dao.ApplicationSyllabus_TheoInnerExperimentDao;
import com.tpm.dao.ExpermentProject_InnerExperimentDao;
import com.tpm.dao.TheoreticalPlanDao;
import com.tpm.entity.ApplicationSyllabus_TheoInnerExperiment;
import com.tpm.entity.Curriculum;
import com.tpm.entity.ExpermentProject_Theo;
import com.tpm.entity.TheoreticalLesson;
@Transactional
public class ExpermentProject_InnerExperimentService {

	private ExpermentProject_InnerExperimentDao expermentProject_InnerExperimentDao;
	
	private ApplicationSyllabus_TheoInnerExperimentDao applicationSyllabus_TheoInnerExperimentDao;
	private TheoreticalPlanDao theoreticalPlanDao;
	
	public void setExpermentProject_InnerExperimentDao(
			ExpermentProject_InnerExperimentDao expermentProject_InnerExperimentDao) {
		this.expermentProject_InnerExperimentDao = expermentProject_InnerExperimentDao;
	}

	public void setApplicationSyllabus_TheoInnerExperimentDao(
			ApplicationSyllabus_TheoInnerExperimentDao applicationSyllabus_TheoInnerExperimentDao) {
		this.applicationSyllabus_TheoInnerExperimentDao = applicationSyllabus_TheoInnerExperimentDao;
	}

	public void setTheoreticalPlanDao(TheoreticalPlanDao theoreticalPlanDao) {
		this.theoreticalPlanDao = theoreticalPlanDao;
	}

	public List<ExpermentProject_Theo> toTheoContentExpPage(String syllabusId, String theoreticalLessonId) {
		List<ExpermentProject_Theo> TextBookslist = expermentProject_InnerExperimentDao.getbyExpermentProject_Theo(syllabusId);
		TheoreticalLesson newtheoreticalLesson = theoreticalPlanDao.get(Integer.valueOf(theoreticalLessonId));
		ServletActionContext.getRequest().setAttribute("newtheoreticalLesson",newtheoreticalLesson);
		return TextBookslist;
	}
	
	public void addExpermentProject_Theo(ExpermentProject_Theo expermentProject_Theo) {
		expermentProject_InnerExperimentDao.add(expermentProject_Theo);
		
	}

	public void updateExpermentProject_Theo(ExpermentProject_Theo expermentProject_Theo) {
		expermentProject_InnerExperimentDao.update(expermentProject_Theo);
		
	}

	public void deleteExpermentProject_Theo(ExpermentProject_Theo expermentProject_Theo) {
		expermentProject_InnerExperimentDao.delete(expermentProject_Theo);
		
	}

	public void toTheoInnerExperimentPage(String syllabusId,
			String theoreticalLessonId) {
		List<ApplicationSyllabus_TheoInnerExperiment> applicationSyllabus_InnerExperiment = applicationSyllabus_TheoInnerExperimentDao.findCurriculum(syllabusId);//在大纲应用专业表找课程信息
		
		Curriculum curriculum = applicationSyllabus_InnerExperiment.get(0).getCurriculum();
		ServletActionContext.getRequest().setAttribute("curriculum", curriculum);
		ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
		TheoreticalLesson newTheoreticalLesson = theoreticalPlanDao.get(Integer.valueOf(theoreticalLessonId));
		ServletActionContext.getRequest().setAttribute("newTheoreticalLesson",newTheoreticalLesson);
		if(applicationSyllabus_InnerExperiment != null && applicationSyllabus_InnerExperiment.size() != 0)
		{
			if(applicationSyllabus_InnerExperiment.get(0).getProfessional() != null)
			{
				ServletActionContext.getRequest().setAttribute("applicationSyllabus", applicationSyllabus_InnerExperiment);
				ServletActionContext.getRequest().setAttribute("flage", 1);
			}
				
			else{ 
				ServletActionContext.getRequest().setAttribute("applicationSyllabus", applicationSyllabus_InnerExperiment.get(0));
				ServletActionContext.getRequest().setAttribute("flage", -1);
			}
		}
		
	}

}
