package com.tpm.dao;

import java.util.List;

import com.tpm.entity.TeachObj_TheoInnerExperiment;

public interface TeachObj_TheoInnerExperimentDao extends BaseDao<TeachObj_TheoInnerExperiment> {

	String findAllByCDP_TheoInnerExperimentList(String syllabusId);

	List<TeachObj_TheoInnerExperiment> findTeachObjListBySyllabusid_TheoInnerExperiment(
			String syllabusId_Copy);

}
