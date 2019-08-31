package com.tpm.dao;

import java.util.List;

import com.tpm.entity.ExpermentProject;

public interface ExpermentProjectDao extends BaseDao<ExpermentProject> {

	List<ExpermentProject> getbyExpermentProject(String syllabusId);

}
