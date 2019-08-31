package com.tpm.dao;

import java.util.List;

import com.tpm.entity.Department;
import com.tpm.entity.MainTainOfPT;
import com.tpm.entity.Professional;

public interface MainTainOfPTDao extends BaseDao<MainTainOfPT>{

	List<MainTainOfPT> findbydepartment(Department department);


}
