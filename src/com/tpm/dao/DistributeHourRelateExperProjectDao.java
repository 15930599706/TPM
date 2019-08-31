package com.tpm.dao;

import java.util.List;

import com.tpm.entity.DistributeHourRelateExperProject;


public interface DistributeHourRelateExperProjectDao extends BaseDao<DistributeHourRelateExperProject>{

	public List<DistributeHourRelateExperProject> findRelateByExpid(Integer expid);
}
