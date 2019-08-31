package com.tpm.dao;

import java.util.List;

import com.tpm.entity.PracticeBooks_InnerExperiment;

public interface PracticeBooks_InnerExperimentDao extends BaseDao<PracticeBooks_InnerExperiment> {

	List<PracticeBooks_InnerExperiment> getbyPracticeBooks_CourseDesignlist(
			String syllabusId);

}
