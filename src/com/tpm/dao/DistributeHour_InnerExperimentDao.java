package com.tpm.dao;

import java.util.List;

import com.tpm.entity.DistributeHour_InnerExperiment;

public interface DistributeHour_InnerExperimentDao extends BaseDao<DistributeHour_InnerExperiment> {

	List<DistributeHour_InnerExperiment> getbydistributeHour_InnerExperiment(
			String syllabusId_Copy);

}
