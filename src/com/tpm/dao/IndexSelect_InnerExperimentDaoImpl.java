package com.tpm.dao;

import java.util.List;

import com.tpm.entity.IndexSelect_InnerExperiment;

public class IndexSelect_InnerExperimentDaoImpl extends BaseDaoImpl<IndexSelect_InnerExperiment>
		implements IndexSelect_InnerExperimentDao {

	public List<IndexSelect_InnerExperiment> findIndexSelect_InnerExperimentListBySyllabusid(String syllabusId_Copy) {
		return this.getHibernateTemplate().find("from IndexSelect_InnerExperiment where syllabus_InnerExperimentid=?",syllabusId_Copy);
	}

}
