package com.tpm.service;


import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.transaction.annotation.Transactional;

import com.tpm.dao.CurriculumDao;
import com.tpm.dao.PracticePlanDao;
import com.tpm.dao.TextBooksDao;
import com.tpm.dao.TheoreticalPlanDao;
import com.tpm.entity.Curriculum;
import com.tpm.entity.PracticeLesson;
import com.tpm.entity.TextBooks;
import com.tpm.entity.TheoreticalLesson;
@Transactional
public class TextBooksService {
	private TextBooksDao textBooksDao;

	private TheoreticalPlanDao theoreticalPlanDao;
	private PracticePlanDao practicePlanDao;
	private CurriculumDao curriculumDao;

	public void setCurriculumDao(CurriculumDao curriculumDao) {
		this.curriculumDao = curriculumDao;
	}

	public void setPracticePlanDao(PracticePlanDao practicePlanDao) {
		this.practicePlanDao = practicePlanDao;
	}
	public void setTheoreticalPlanDao(TheoreticalPlanDao theoreticalPlanDao) {
		this.theoreticalPlanDao = theoreticalPlanDao;
	}
	public TextBooksDao getTextBooksDao() {
		return textBooksDao;
	}
	public void setTextBooksDao(TextBooksDao textBooksDao) {
		this.textBooksDao = textBooksDao;
	}
	public List<TextBooks> toMateriaTheoPage(String syllabusId, String theoreticalLessonId, String curriculumId) {
		List<TextBooks> TextBookslist = textBooksDao.getbytextBooks(syllabusId);
		
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
	
		return TextBookslist;
	}
	public void addtextBooks(TextBooks textBooks) {
		textBooksDao.add(textBooks);
	}
	public void updatetextBooks(TextBooks textBooks) {
		textBooksDao.update(textBooks);
	}
	public void deletetextBooks(TextBooks textBooks) {
		textBooksDao.delete(textBooks);
	}
}
