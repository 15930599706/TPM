package com.tpm.service;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.transaction.annotation.Transactional;

import com.tpm.dao.CurriculumDao;
import com.tpm.dao.ExperimentContentDao;
import com.tpm.dao.PracticePlanDao;
import com.tpm.dao.TheoreticalPlanDao;
import com.tpm.entity.ContentTheo;
import com.tpm.entity.Curriculum;
import com.tpm.entity.ExperimentContent;
import com.tpm.entity.PracticeLesson;
import com.tpm.entity.TheoreticalLesson;
@Transactional
public class ExperimentContentService {
	private ExperimentContentDao experimentContentDao;

	private TheoreticalPlanDao theoreticalPlanDao;
	private CurriculumDao curriculumDao;
	private PracticePlanDao practicePlanDao;
	
	public void setPracticePlanDao(PracticePlanDao practicePlanDao) {
		this.practicePlanDao = practicePlanDao;
	}

	public void setCurriculumDao(CurriculumDao curriculumDao) {
		this.curriculumDao = curriculumDao;
	}
	public void setTheoreticalPlanDao(TheoreticalPlanDao theoreticalPlanDao) {
		this.theoreticalPlanDao = theoreticalPlanDao;
	}

	public void setExperimentContentDao(ExperimentContentDao experimentContentDao) {
		this.experimentContentDao = experimentContentDao;
	}

	public void addExperimentContent(ExperimentContent experimentContent) {
		experimentContentDao.add(experimentContent);
	}

	public void updataExperimentContent(ExperimentContent experimentContent) {
		experimentContentDao.update(experimentContent);
		
	}

	public void deleteExperimentContent(ExperimentContent experimentContent) {
		experimentContentDao.delete(experimentContent);
	}

	public List<ExperimentContent> toexperimentContent(String syllabusId, String theoreticalLessonId, String curriculumId) {
		List<ExperimentContent> experimentContentlist = experimentContentDao.getbyexperimentContent(syllabusId);	
		Curriculum newCurriculum = curriculumDao.get(curriculumId);
		if(newCurriculum.getCourseLei().equals("理论课"))
		{
			TheoreticalLesson newtheoreticalLesson = theoreticalPlanDao.get(Integer.valueOf(theoreticalLessonId));
			ServletActionContext.getRequest().setAttribute("newtheoreticalLesson",newtheoreticalLesson);

		}
		else
		{
			PracticeLesson newPracticeLesson = practicePlanDao.get(Integer.valueOf(theoreticalLessonId));
			ServletActionContext.getRequest().setAttribute("newtheoreticalLesson",newPracticeLesson);

		}return experimentContentlist;
	}

}
