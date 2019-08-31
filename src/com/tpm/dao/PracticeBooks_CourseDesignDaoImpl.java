package com.tpm.dao;

import java.util.List;

import com.tpm.entity.PracticeBooks_CourseDesign;

public class PracticeBooks_CourseDesignDaoImpl extends BaseDaoImpl<PracticeBooks_CourseDesign> implements PracticeBooks_CourseDesignDao {


	public List<PracticeBooks_CourseDesign> getbyPracticeBooks_CourseDesignlist(String syllabusId) {
		List<PracticeBooks_CourseDesign> textBookslist;
		textBookslist = this.getHibernateTemplate().find("from PracticeBooks_CourseDesign where syllabus_CourseDesignid=?", syllabusId);
		return textBookslist;

	}
}
