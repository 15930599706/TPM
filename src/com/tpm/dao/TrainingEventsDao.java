package com.tpm.dao;

import java.util.List;

import com.tpm.entity.Department;
import com.tpm.entity.TrainingEvents;

public interface TrainingEventsDao extends BaseDao<TrainingEvents>{

	List<TrainingEvents> getbydepartment(Department department);

	List<TrainingEvents> getbydepartmentnototal(Department department);

	TrainingEvents gettotal(Department department);

	TrainingEvents gettotalli(Department department);



}
