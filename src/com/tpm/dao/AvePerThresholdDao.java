package com.tpm.dao;

import com.tpm.entity.AvePerThreshold;

public interface AvePerThresholdDao extends BaseDao<AvePerThreshold> {

	AvePerThreshold findAvePer();

}
