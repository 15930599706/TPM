package com.tpm.dao;

import java.util.List;

import com.tpm.entity.Ability;
import com.tpm.entity.AbilityAndTeachObj_InnerExperiment;
import com.tpm.entity.ApplicationSyllabus_InnerExperiment;
import com.tpm.entity.IndexSelect_InnerExperiment;

public interface AbilityAndTeachObj_InnerExperimentDao extends BaseDao<AbilityAndTeachObj_InnerExperiment> {

	ApplicationSyllabus_InnerExperiment findAppSyllabus_InnerExperiment(Integer syllabusId);

	List<IndexSelect_InnerExperiment> findIndexSelectByAbility_InnerExperiment(
			Ability ability, String syllabusId);

	void addSelectIndex_InnerExperiment(List<IndexSelect_InnerExperiment> newindexSelect_InnerExperimentList,String syllabusId);

	void addAbilityAndTeachObj_InnerExperiment(List<AbilityAndTeachObj_InnerExperiment> abilityAndTeachObjSelect_InnerExperiment,String syllabusId);

	List<AbilityAndTeachObj_InnerExperiment> findAbiAndTeachListBySyllabusid(
			String syllabusId_Copy);

	List<AbilityAndTeachObj_InnerExperiment> findTeachByAbility_InnerExperiment(
			Ability ability, String syllabusId);



}
