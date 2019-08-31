package com.tpm.dao;

import java.util.List;

import com.tpm.entity.AbilityAndTeachObj;
import com.tpm.entity.Curriculum;
import com.tpm.entity.Department;
import com.tpm.entity.Professional;
import com.tpm.entity.TeachObj;

public interface TeachObjDao extends BaseDao<TeachObj> {

	String findAllByCDP(String syllabusId);

	List<TeachObj> findTeachObjListBySyllabusid(String syllabusId_Copy);


}
