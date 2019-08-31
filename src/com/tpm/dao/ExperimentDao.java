package com.tpm.dao;

import java.util.List;

import com.tpm.entity.College;
import com.tpm.entity.Experiment;

public interface ExperimentDao extends BaseDao<Experiment> {

	List<Experiment> findbyCollege(College college);

}
