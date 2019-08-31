package com.tpm.dao;

import java.util.List;

import com.tpm.entity.Department;
import com.tpm.entity.Professional;
import com.tpm.entity.Topology;

public interface TopologyDao extends BaseDao<Topology>{

	Topology getbydepartment(Department department);

	Integer getcountbyname(String hash_name);

	List<Topology> findbydepartment(Department department);

	Topology getbyprofessional(Professional professional);

	List<Topology> gettopologybypath(String topologypath);

}
