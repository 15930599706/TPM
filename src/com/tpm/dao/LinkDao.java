package com.tpm.dao;

import java.util.List;

import com.tpm.entity.Department;
import com.tpm.entity.Professional;

public interface LinkDao {

	//查找对应专业的专业方向
	List<Professional> findProfessional(Department department);

}
