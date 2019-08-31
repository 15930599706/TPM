package com.tpm.dao;

import java.util.List;

import com.tpm.entity.ExperimentLog;

public interface ExperimentLogDao extends BaseDao<ExperimentLog> {	
	
	int findsysrzAdminCount();

	List<ExperimentLog> findsysrzAdminT(int begin, int pageSize);

}
