package com.tpm.dao;

import java.util.List;

import com.tpm.entity.IndexSelect_TheoInnerExperiment;

public class IndexSelect_TheoInnerExperimentDaoImpl extends BaseDaoImpl<IndexSelect_TheoInnerExperiment>
		implements IndexSelect_TheoInnerExperimentDao {

	public List<IndexSelect_TheoInnerExperiment> findIndexSelectListBySyllabusid_TheoInnerExperiment(String syllabusId_Copy) {
		return this.getHibernateTemplate().find("from IndexSelect_TheoInnerExperiment where syllabusid=?",syllabusId_Copy);
	}

}
