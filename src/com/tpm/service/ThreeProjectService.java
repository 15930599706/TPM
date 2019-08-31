package com.tpm.service;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.transaction.annotation.Transactional;

import com.tpm.dao.CurriculumDao;
import com.tpm.dao.PracticePlanDao;
import com.tpm.dao.TheoreticalPlanDao;
import com.tpm.dao.ThreeProjectDao;
import com.tpm.entity.Curriculum;
import com.tpm.entity.PracticeLesson;
import com.tpm.entity.TheoreticalLesson;
import com.tpm.entity.ThreeProject;
@Transactional
public class ThreeProjectService {
	private ThreeProjectDao	threeProjectDao;

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

	public void setThreeProjectDao(ThreeProjectDao threeProjectDao) {
		this.threeProjectDao = threeProjectDao;
	}

	public List<ThreeProject> toEditThreePage(String syllabusId, String theoreticalLessonid, String curriculumId) {
		List<ThreeProject> ThreeProjectlist = threeProjectDao.getbyThreeProject(syllabusId);
		Curriculum newCurriculum = curriculumDao.get(curriculumId);
		if(newCurriculum.getCourseLei().equals("理论课"))
		{
			TheoreticalLesson newtheoreticalLesson = theoreticalPlanDao.get(Integer.valueOf(theoreticalLessonid));
			ServletActionContext.getRequest().setAttribute("newtheoreticalLesson",newtheoreticalLesson);

		}
		else
		{
			PracticeLesson newPracticeLesson = practicePlanDao.get(Integer.valueOf(theoreticalLessonid));
			ServletActionContext.getRequest().setAttribute("newtheoreticalLesson",newPracticeLesson);

		}
		return ThreeProjectlist;
	}

	public void addThreeProject(ThreeProject threeProject) {
		threeProjectDao.add(threeProject);
	}

	public void updateThreeProject(ThreeProject threeProject) {
		threeProjectDao.update(threeProject);
	}

	public void deleteThreeProject(ThreeProject threeProject) {
		threeProjectDao.update(threeProject);
	}
}
