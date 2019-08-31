package com.tpm.service;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.transaction.annotation.Transactional;

import com.tpm.dao.PracticeBooks_InnerExperimentDao;
import com.tpm.dao.PracticePlanDao;
import com.tpm.entity.PracticeBooks_CourseDesign;
import com.tpm.entity.PracticeBooks_InnerExperiment;
import com.tpm.entity.PracticeLesson;

@Transactional
public class PracticeBooks_InnerExperimentService {

	private PracticeBooks_InnerExperimentDao practiceBooks_InnerExperimentDao;

	private PracticePlanDao practicePlanDao;
	public void setPracticePlanDao(PracticePlanDao practicePlanDao) {
		this.practicePlanDao = practicePlanDao;
	}

	public void setPracticeBooks_InnerExperimentDao(
			PracticeBooks_InnerExperimentDao practiceBooks_InnerExperimentDao) {
		this.practiceBooks_InnerExperimentDao = practiceBooks_InnerExperimentDao;
	}

	public List<PracticeBooks_InnerExperiment> toMateriaTheoPage(
			String syllabusId, String practiceLessonid) {
		List<PracticeBooks_InnerExperiment> practiceBooks_CourseDesignlist = practiceBooks_InnerExperimentDao.getbyPracticeBooks_CourseDesignlist(syllabusId);
		PracticeLesson newpracticeLesson = practicePlanDao.get(Integer.valueOf(practiceLessonid));
		ServletActionContext.getRequest().setAttribute("newpracticeLesson",newpracticeLesson);
		return practiceBooks_CourseDesignlist;
	}

	public void addpracticeBooks_InnerExperiment(
			PracticeBooks_InnerExperiment practiceBooks_InnerExperiment) {
		practiceBooks_InnerExperimentDao.add(practiceBooks_InnerExperiment);
		
	}

	public void updatepracticeBook(
			PracticeBooks_InnerExperiment practiceBooks_InnerExperiment) {
		practiceBooks_InnerExperimentDao.update(practiceBooks_InnerExperiment);
		
	}

	public void deletepracticeBook(
			PracticeBooks_InnerExperiment practiceBooks_InnerExperiment) {
		practiceBooks_InnerExperimentDao.delete(practiceBooks_InnerExperiment);
		
	}
	
}
