package com.tpm.dao;

import java.util.List;

import com.tpm.entity.IndexSelect_TheoInnerExperiment;

public interface IndexSelect_TheoInnerExperimentDao extends BaseDao<IndexSelect_TheoInnerExperiment> {

	List<IndexSelect_TheoInnerExperiment> findIndexSelectListBySyllabusid_TheoInnerExperiment(
			String syllabusId_Copy);

}
