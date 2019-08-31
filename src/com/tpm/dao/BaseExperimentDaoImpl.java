package com.tpm.dao;

import java.util.List;

import com.tpm.entity.BaseExperiment;
import com.tpm.entity.BasePractice;
import com.tpm.entity.DistributeHour_CourseDesign;
import com.tpm.entity.DistributeHour_InnerExperiment;

public class BaseExperimentDaoImpl extends BaseDaoImpl<BaseExperiment> implements BaseExperimentDao {

	public void deleteDistributeHour_InnerExperiment(String syllabusId) {
		List<DistributeHour_InnerExperiment> distributeHour_InnerExperimentlsit = this.getHibernateTemplate().find("from DistributeHour_InnerExperiment where syllabusid=?",syllabusId);
		for(int i=0;i<distributeHour_InnerExperimentlsit.size();i++)
		{
			this.getHibernateTemplate().delete(distributeHour_InnerExperimentlsit.get(i));
		}
		
	}

	public void addDistributeHour_InnerExperiment(
			DistributeHour_InnerExperiment distributeHour_InnerExperiment) {
		this.getHibernateTemplate().save(distributeHour_InnerExperiment);
		
	}

	public List<DistributeHour_InnerExperiment> findDistributeHour_InnerExperiment(
			String syllabusId) {
		return this.getHibernateTemplate().find("from DistributeHour_InnerExperiment where syllabusid=?",syllabusId);

	}
}
