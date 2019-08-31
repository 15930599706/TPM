package com.tpm.dao;

import java.util.List;

import com.tpm.entity.PracticeBooks_CourseDesign;
import com.tpm.entity.PracticeBooks_InnerExperiment;

public class PracticeBooks_InnerExperimentDaoImpl extends BaseDaoImpl<PracticeBooks_InnerExperiment>
		implements PracticeBooks_InnerExperimentDao {

	public List<PracticeBooks_InnerExperiment> getbyPracticeBooks_CourseDesignlist(String syllabusId) {
		List<PracticeBooks_InnerExperiment> textBookslist;
		textBookslist = this.getHibernateTemplate().find("from PracticeBooks_InnerExperiment where syllabus_InnerExperimentid=?", syllabusId);
		return textBookslist;

	}

}
