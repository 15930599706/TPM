package com.tpm.dao;

import java.util.List;

import com.tpm.entity.BaseExperiment;
import com.tpm.entity.DistributeHour_InnerExperiment;

public interface BaseExperimentDao extends BaseDao<BaseExperiment> {

	void deleteDistributeHour_InnerExperiment(String syllabusId);

	void addDistributeHour_InnerExperiment(
			DistributeHour_InnerExperiment distributeHour_InnerExperiment);

	List<DistributeHour_InnerExperiment> findDistributeHour_InnerExperiment(
			String syllabusId);

}
