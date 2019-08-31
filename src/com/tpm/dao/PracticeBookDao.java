package com.tpm.dao;

import java.util.List;

import com.tpm.entity.PracticeBook;
import com.tpm.entity.PracticeBooks_CourseDesign;

public interface PracticeBookDao extends BaseDao<PracticeBook> {

	List<PracticeBook> getbytextBooks(String syllabusId);
}
