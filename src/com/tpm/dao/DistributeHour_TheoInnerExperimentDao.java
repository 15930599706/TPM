package com.tpm.dao;

import java.util.List;

import com.tpm.entity.DistributeHour_TheoInnerExperiment;

public interface DistributeHour_TheoInnerExperimentDao extends BaseDao<DistributeHour_TheoInnerExperiment> {

	List<DistributeHour_TheoInnerExperiment> getbyDistributeHour_TheoInnerExperiment(
			String syllabusId_Copy);

}
