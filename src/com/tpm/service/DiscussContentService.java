package com.tpm.service;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.transaction.annotation.Transactional;

import com.tpm.dao.CurriculumDao;
import com.tpm.dao.DiscussContentDao;
import com.tpm.dao.PracticePlanDao;
import com.tpm.dao.TheoreticalPlanDao;
import com.tpm.entity.Curriculum;
import com.tpm.entity.DiscussContent;
import com.tpm.entity.PracticeLesson;
import com.tpm.entity.TheoreticalLesson;
@Transactional
public class DiscussContentService {

	private DiscussContentDao discussContentDao;

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

	public void setDiscussContentDao(DiscussContentDao discussContentDao) {
		this.discussContentDao = discussContentDao;
	}

	public List<DiscussContent> toEditDiscussPage(String syllabusId, String theoreticalLessonid, String curriculumId) {
		List<DiscussContent> discussContentlist=discussContentDao.getbydiscussContent(syllabusId);
		
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
		return discussContentlist;
	}

	public void adddiscussContent(DiscussContent discussContent) {

		discussContentDao.add(discussContent);
	}

	public void updatediscussContent(DiscussContent discussContent) {

		discussContentDao.update(discussContent);
		
	}
	public void deletediscussContent(DiscussContent discussContent) {
		discussContentDao.delete(discussContent);
	}

}
