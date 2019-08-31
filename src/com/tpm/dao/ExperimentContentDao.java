package com.tpm.dao;

import java.util.List;

import com.tpm.entity.ExperimentContent;

public interface ExperimentContentDao extends BaseDao<ExperimentContent> {

	List<ExperimentContent> getbyexperimentContent(String syllabusId);
}
