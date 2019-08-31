package com.tpm.dao;

import java.util.List;

import com.tpm.entity.BaseExpTheoInnerRelateExpProjectTheo;
import com.tpm.entity.DistributeHourRelateExperProject;

public class DistributeHourRelateExperProjectDaoImpl extends BaseDaoImpl<DistributeHourRelateExperProject>
 implements DistributeHourRelateExperProjectDao{

	public List<DistributeHourRelateExperProject> findRelateByExpid(
			Integer expid) {
		return this.getHibernateTemplate().find("from DistributeHourRelateExperProject where innerExperiment.distributeHour_InnerExperimentid=?",expid);
	}





}
