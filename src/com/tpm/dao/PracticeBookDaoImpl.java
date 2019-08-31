package com.tpm.dao;

import java.util.List;

import com.tpm.entity.PracticeBook;
import com.tpm.entity.PracticeBooks_CourseDesign;
import com.tpm.entity.TextBooks;

@SuppressWarnings("all")
public class PracticeBookDaoImpl extends BaseDaoImpl<PracticeBook> implements PracticeBookDao {
	public List<PracticeBook> getbytextBooks(String syllabusId)
	{
		List<PracticeBook> practiceBooklist;
		practiceBooklist = this.getHibernateTemplate().find("from PracticeBook where syllabus_Graid=?", syllabusId);
		return practiceBooklist;
	}



}
