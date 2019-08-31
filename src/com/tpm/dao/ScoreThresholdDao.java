package com.tpm.dao;

import com.tpm.entity.AvePerThreshold;
import com.tpm.entity.Department;
import com.tpm.entity.ScoreThreshold;

public interface ScoreThresholdDao extends BaseDao<ScoreThreshold>{

	ScoreThreshold findbydepartment(Department department);

	ScoreThreshold findbydepartmentnull();

}
