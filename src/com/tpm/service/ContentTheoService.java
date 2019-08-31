package com.tpm.service;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.transaction.annotation.Transactional;

import com.tpm.dao.ContentTheoDao;
import com.tpm.dao.CurriculumDao;
import com.tpm.dao.PracticePlanDao;
import com.tpm.dao.TheoreticalPlanDao;
import com.tpm.entity.BaseTheo;
import com.tpm.entity.ContentTheo;
import com.tpm.entity.Curriculum;
import com.tpm.entity.PracticeLesson;
import com.tpm.entity.TheoreticalLesson;
@Transactional
public class ContentTheoService {

	private ContentTheoDao contentTheoDao;
	private TheoreticalPlanDao theoreticalPlanDao;
	private CurriculumDao curriculumDao;
	private PracticePlanDao practicePlanDao;
	public void setCurriculumDao(CurriculumDao curriculumDao) {
		this.curriculumDao = curriculumDao;
	}
	public void setPracticePlanDao(PracticePlanDao practicePlanDao) {
		this.practicePlanDao = practicePlanDao;
	}
	public void setTheoreticalPlanDao(TheoreticalPlanDao theoreticalPlanDao) {
		this.theoreticalPlanDao = theoreticalPlanDao;
	}
	public void setContentTheoDao(ContentTheoDao contentTheoDao) {
		this.contentTheoDao = contentTheoDao;
	}
	public void updateContenTheo(ContentTheo contentTheo) {
			contentTheoDao.update(contentTheo);
	}
	public void addContenTheo(ContentTheo contentTheo)
	{
		contentTheoDao.add(contentTheo);
	}
	public void deleteContenTheo(ContentTheo contentTheo){
		contentTheoDao.delete(contentTheo);
	} 
	public List<ContentTheo> toContenTheo(String syllabusId) {
		List<ContentTheo> contentTheolist = contentTheoDao.getbycontentTheo(syllabusId);	
		return contentTheolist;
	}
	public  void setTheo(String theoreticalLessonId, String curriculumId) {
		Curriculum newCurriculum = curriculumDao.get(curriculumId);
		if(newCurriculum.getCourseLei().equals("理论课")||newCurriculum.getCourseLei().equals("实验课"))
		{
			TheoreticalLesson newtheoreticalLesson = theoreticalPlanDao.get(Integer.valueOf(theoreticalLessonId));
			ServletActionContext.getRequest().setAttribute("newtheoreticalLesson",newtheoreticalLesson);

		}
		else
		{
			PracticeLesson newPracticeLesson = practicePlanDao.get(Integer.valueOf(theoreticalLessonId));
			ServletActionContext.getRequest().setAttribute("newtheoreticalLesson",newPracticeLesson);

		}
		
	}

}
