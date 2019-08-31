package com.tpm.dao;

import java.util.List;

import com.tpm.entity.Ability;
import com.tpm.entity.Department;

public interface AbilityDao extends BaseDao<Ability>{

	List<Ability> getbydepartment(Department department);

	Ability getbyabilityname(String string, Department department);

}
