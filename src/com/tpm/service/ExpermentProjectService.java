package com.tpm.service;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.transaction.annotation.Transactional;

import com.tpm.dao.ExpermentProjectDao;
import com.tpm.dao.PracticePlanDao;
import com.tpm.entity.ExpermentProject;
import com.tpm.entity.PracticeLesson;
@Transactional
public class ExpermentProjectService {

	private ExpermentProjectDao expermentProjectDao;

	private PracticePlanDao practicePlanDao;
	public void setPracticePlanDao(PracticePlanDao practicePlanDao) {
		this.practicePlanDao = practicePlanDao;
	}

	public void setExpermentProjectDao(ExpermentProjectDao expermentProjectDao) {
		this.expermentProjectDao = expermentProjectDao;
	}

	public List<ExpermentProject> toContentExpPage(String syllabusId, String practiceLessonid) {
		List<ExpermentProject> TextBookslist = expermentProjectDao.getbyExpermentProject(syllabusId);
		PracticeLesson newpracticeLesson = practicePlanDao.get(Integer.valueOf(practiceLessonid));
		ServletActionContext.getRequest().setAttribute("newpracticeLesson",newpracticeLesson);
		return TextBookslist;
	}

	public void addExpermentProject(ExpermentProject expermentProject) {
		expermentProjectDao.add(expermentProject);
		
	}

	public void updateExpermentProject(ExpermentProject expermentProject) {
		expermentProjectDao.update(expermentProject);
		
	}

	public void deleteExpermentProject(ExpermentProject expermentProject) {
		expermentProjectDao.delete(expermentProject);
		
	}
	
}
