package com.tpm.dao;

import java.util.List;

import com.tpm.entity.Department;
import com.tpm.entity.TopologyTag;

public interface TopologyTagDao extends BaseDao<TopologyTag>{

	List<TopologyTag> findbydepartment(Department department);

}
