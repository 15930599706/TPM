package com.tpm.dao;

import java.util.List;

import com.tpm.entity.BaseExperiment_TheoInnerExperiment;
import com.tpm.entity.DistributeHour_InnerExperiment;
import com.tpm.entity.DistributeHour_TheoInnerExperiment;

public interface BaseExperiment_TheoInnerExperimentDao extends BaseDao<BaseExperiment_TheoInnerExperiment> {

	List<DistributeHour_TheoInnerExperiment> findDistributeHour_TheoInnerExperiment(
			String syllabusId);

	void addDistributeHour_TheoInnerExperiment(
			DistributeHour_TheoInnerExperiment distributeHour_TheoInnerExperiment);

	void deleteDistributeHour_TheoInnerExperiment(String syllabusId);

}
