package com.tpm.dao;

import java.util.List;

import com.tpm.entity.TextBooks_InnerExperiment;

public interface TextBooks_InnerExperimentDao extends BaseDao<TextBooks_InnerExperiment> {

	List<TextBooks_InnerExperiment> getbytextBooks_InnerExperimentlist(
			String syllabusId);

}
