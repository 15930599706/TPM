package com.tpm.dao;

import java.util.List;

import com.tpm.entity.TeachObj_InnerExperiment;

public interface TeachObj_InnerExperimentDao extends BaseDao<TeachObj_InnerExperiment> {

	String findAllByCDP_InnerExperiment(String syllabusId);

	List<TeachObj_InnerExperiment> findTeachObj_InnerExperimentListBySyllabusid(
			String syllabusId_Copy);

}
