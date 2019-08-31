package com.tpm.dao;

import java.util.List;

import com.tpm.entity.Ability;
import com.tpm.entity.Analysis;

public interface AnalysisDao extends BaseDao<Analysis>{

	List<Analysis> getbyability(Ability ability);

}
