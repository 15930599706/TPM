package com.tpm.dao;

import java.util.List;

import com.tpm.entity.ExpermentProject_Theo;

public interface ExpermentProject_InnerExperimentDao extends BaseDao<ExpermentProject_Theo> {

	List<ExpermentProject_Theo> getbyExpermentProject_Theo(String syllabusId);

}
