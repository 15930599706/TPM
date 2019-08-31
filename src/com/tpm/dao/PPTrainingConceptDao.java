package com.tpm.dao;

import com.tpm.entity.Department;
import com.tpm.entity.PPTrainingConcept;

public interface PPTrainingConceptDao extends BaseDao<PPTrainingConcept>{

	PPTrainingConcept getbydepartment(Department department);


}
