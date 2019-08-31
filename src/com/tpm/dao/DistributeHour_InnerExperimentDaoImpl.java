package com.tpm.dao;

import java.util.List;

import com.tpm.entity.DistributeHour_InnerExperiment;

public class DistributeHour_InnerExperimentDaoImpl extends BaseDaoImpl<DistributeHour_InnerExperiment>
		implements DistributeHour_InnerExperimentDao {

	public List<DistributeHour_InnerExperiment> getbydistributeHour_InnerExperiment(String syllabusId_Copy) {
		List<DistributeHour_InnerExperiment> distributeHour_InnerExperimentlist;
		distributeHour_InnerExperimentlist = this.getHibernateTemplate().find("from DistributeHour_InnerExperiment where syllabusid=?", syllabusId_Copy);
		return distributeHour_InnerExperimentlist;
	}

}
