package com.tpm.dao;

import java.util.List;

import com.tpm.entity.IndexSelect_InnerExperiment;

public interface IndexSelect_InnerExperimentDao extends BaseDao<IndexSelect_InnerExperiment> {

	List<IndexSelect_InnerExperiment> findIndexSelect_InnerExperimentListBySyllabusid(
			String syllabusId_Copy);

}
