package com.tpm.service;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.transaction.annotation.Transactional;

import com.tpm.dao.TextBooks_InnerExperimentDao;
import com.tpm.dao.TheoreticalPlanDao;
import com.tpm.entity.PracticeBooks_InnerExperiment;
import com.tpm.entity.TextBooks_InnerExperiment;
import com.tpm.entity.TheoreticalLesson;
@Transactional
public class TextBooks_InnerExperimentService {

	private TextBooks_InnerExperimentDao textBooks_InnerExperimentDao;
	private TheoreticalPlanDao theoreticalPlanDao;
	public void setTheoreticalPlanDao(TheoreticalPlanDao theoreticalPlanDao) {
		this.theoreticalPlanDao = theoreticalPlanDao;
	}


	public void setTextBooks_InnerExperimentDao(
			TextBooks_InnerExperimentDao textBooks_InnerExperimentDao) {
		this.textBooks_InnerExperimentDao = textBooks_InnerExperimentDao;
	}


	public List<TextBooks_InnerExperiment> toTheoInnerExperimentPage(
			String syllabusId, String theoreticalLessonId) {
		List<TextBooks_InnerExperiment> practiceBooks_CourseDesignlist = textBooks_InnerExperimentDao.getbytextBooks_InnerExperimentlist(syllabusId);
		TheoreticalLesson newtheoreticalLesson = theoreticalPlanDao.get(Integer.valueOf(theoreticalLessonId));
		ServletActionContext.getRequest().setAttribute("newtheoreticalLesson",newtheoreticalLesson);
		return practiceBooks_CourseDesignlist;
	}


	public void addpracticeBooks_InnerExperiment(
			TextBooks_InnerExperiment textBooks_InnerExperiment) {
		textBooks_InnerExperimentDao.add(textBooks_InnerExperiment);
		
	}


	public void updateTextBooksInnerExperimen(
			TextBooks_InnerExperiment textBooks_InnerExperiment) {
		textBooks_InnerExperimentDao.update(textBooks_InnerExperiment);
		
	}

	public void deleteTextBooksInnerExperimen(
			TextBooks_InnerExperiment textBooks_InnerExperiment) {
		textBooks_InnerExperimentDao.delete(textBooks_InnerExperiment);
		
	}

}
