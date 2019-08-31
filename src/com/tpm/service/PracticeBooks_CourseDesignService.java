package com.tpm.service;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.transaction.annotation.Transactional;

import com.tpm.dao.PracticeBooks_CourseDesignDao;
import com.tpm.dao.PracticePlanDao;
import com.tpm.entity.PracticeBooks_CourseDesign;
import com.tpm.entity.PracticeLesson;
@Transactional
public class PracticeBooks_CourseDesignService {

	private PracticeBooks_CourseDesignDao practiceBooks_CourseDesignDao;

	private PracticePlanDao practicePlanDao;
	public void setPracticePlanDao(PracticePlanDao practicePlanDao) {
		this.practicePlanDao = practicePlanDao;
	}

	public void setPracticeBooks_CourseDesignDao(
			PracticeBooks_CourseDesignDao practiceBooks_CourseDesignDao) {
		this.practiceBooks_CourseDesignDao = practiceBooks_CourseDesignDao;
	}

	public List<PracticeBooks_CourseDesign> toMateria_CourseDesignPage(String syllabusId, String practiceLessonid) {
		List<PracticeBooks_CourseDesign> practiceBooks_CourseDesignlist = practiceBooks_CourseDesignDao.getbyPracticeBooks_CourseDesignlist(syllabusId);
		PracticeLesson newpracticeLesson = practicePlanDao.get(Integer.valueOf(practiceLessonid));
		ServletActionContext.getRequest().setAttribute("newpracticeLesson",newpracticeLesson);
		return practiceBooks_CourseDesignlist;
	}

	public void addpracticeBooks_CourseDesign(PracticeBooks_CourseDesign practiceBooks_CourseDesign) {
		practiceBooks_CourseDesignDao.add(practiceBooks_CourseDesign);
		
	}

	public void updatePracticeBooks_CourseDesign(PracticeBooks_CourseDesign practiceBooks_CourseDesign) {
		practiceBooks_CourseDesignDao.update(practiceBooks_CourseDesign);
		
	}

	public void deletePracticeBooks_CourseDesign(PracticeBooks_CourseDesign practiceBooks_CourseDesign) {
		practiceBooks_CourseDesignDao.delete(practiceBooks_CourseDesign);
		
	}
	
}
