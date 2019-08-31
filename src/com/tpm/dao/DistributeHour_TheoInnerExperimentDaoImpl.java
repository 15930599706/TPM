package com.tpm.dao;

import java.util.List;

import com.tpm.entity.DistributeHour_TheoInnerExperiment;

public class DistributeHour_TheoInnerExperimentDaoImpl extends BaseDaoImpl<DistributeHour_TheoInnerExperiment>
		implements DistributeHour_TheoInnerExperimentDao {

	public List<DistributeHour_TheoInnerExperiment> getbyDistributeHour_TheoInnerExperiment(String syllabusId_Copy) {
		List<DistributeHour_TheoInnerExperiment> distributeHour_TheoInnerExperimentlist;
		distributeHour_TheoInnerExperimentlist = this.getHibernateTemplate().find("from DistributeHour_TheoInnerExperiment where syllabusid=?", syllabusId_Copy);
		return distributeHour_TheoInnerExperimentlist;
	}

}
