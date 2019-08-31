package com.tpm.dao;

import java.util.List;

import com.tpm.entity.PracticeBooks_CourseDesign;

public interface PracticeBooks_CourseDesignDao extends BaseDao<PracticeBooks_CourseDesign> {

	List<PracticeBooks_CourseDesign> getbyPracticeBooks_CourseDesignlist(
			String syllabusId);

}
