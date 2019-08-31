package com.tpm.dao;

import java.util.List;

import com.tpm.entity.BaseExperiment_TheoInnerExperiment;
import com.tpm.entity.DistributeHour_InnerExperiment;
import com.tpm.entity.DistributeHour_Theo;
import com.tpm.entity.DistributeHour_TheoInnerExperiment;

public class BaseExperiment_TheoInnerExperimentDaoImpl extends BaseDaoImpl<BaseExperiment_TheoInnerExperiment>
		implements BaseExperiment_TheoInnerExperimentDao {

	public List<DistributeHour_TheoInnerExperiment> findDistributeHour_TheoInnerExperiment(
			String syllabusId) {
		return this.getHibernateTemplate().find("from DistributeHour_TheoInnerExperiment where syllabusid=?",syllabusId);

	}

	public void addDistributeHour_TheoInnerExperiment(
			DistributeHour_TheoInnerExperiment distributeHour_TheoInnerExperiment) {
		this.getHibernateTemplate().save(distributeHour_TheoInnerExperiment);

	}

	public void deleteDistributeHour_TheoInnerExperiment(String syllabusId) {
		List<DistributeHour_TheoInnerExperiment> distributeHour_Theolsit = this.getHibernateTemplate().find("from DistributeHour_TheoInnerExperiment where syllabusid=?",syllabusId);
		for(int i=0;i<distributeHour_Theolsit.size();i++)
		{
			this.getHibernateTemplate().delete(distributeHour_Theolsit.get(i));
		}
	}

}
