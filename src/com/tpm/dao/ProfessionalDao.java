package com.tpm.dao;

import java.util.List;

import com.tpm.entity.Department;
import com.tpm.entity.Professional;

public interface ProfessionalDao extends BaseDao<Professional>{

	List<Professional> findbydepartment(Department department);

}
