package com.tpm.service;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.transaction.annotation.Transactional;

import com.tpm.dao.CurriculumDao;
import com.tpm.dao.OtherContentDao;
import com.tpm.dao.PracticePlanDao;
import com.tpm.dao.TheoreticalPlanDao;
import com.tpm.entity.Curriculum;
import com.tpm.entity.OtherContent;
import com.tpm.entity.PracticeLesson;
import com.tpm.entity.TheoreticalLesson;

@Transactional
public class OtherContentService {
	private OtherContentDao otherContentDao;

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

	public void setOtherContentDao(OtherContentDao otherContentDao) {
		this.otherContentDao = otherContentDao;
	}

	public List<OtherContent> toEditOtherPage(String syllabusId, String theoreticalLessonid, String curriculumId) {
		List<OtherContent> OtherContentlist = otherContentDao.getbyOtherContent(syllabusId);
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
		return OtherContentlist;
	}

	public void addOtherContent(OtherContent otherContent) {
		otherContentDao.add(otherContent);
		
	}

	public void updateOtherContent(OtherContent otherContent) {
		otherContentDao.update(otherContent);
		
	}

	public void deleteOtherContent(OtherContent otherContent) {
		otherContentDao.delete(otherContent);
	}
}
