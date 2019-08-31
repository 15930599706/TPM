package com.tpm.dao;

import java.util.List;

import com.tpm.entity.Department;
import com.tpm.entity.MainTainOfPTTag;

public interface MainTainOfPTTagDao extends BaseDao<MainTainOfPTTag> {

	List<MainTainOfPTTag> findbydepartment(Department department);

}
