package com.tpm.dao;

import java.util.List;

import com.tpm.entity.Ability;
import com.tpm.entity.AbilityAndTeachObj_TheoInnerExperiment;
import com.tpm.entity.ApplicationSyllabus_TheoInnerExperiment;
import com.tpm.entity.IndexSelect_TheoInnerExperiment;

public interface AbilityAndTeachObj_TheoInnerExperimentDao extends BaseDao<AbilityAndTeachObj_TheoInnerExperiment> {

	ApplicationSyllabus_TheoInnerExperiment findAppSyllabus_TheoInnerExperiment(Integer syllabusId);

	List<IndexSelect_TheoInnerExperiment> findIndexSelect_TheoInnerExperimentByAbility(
			Ability ability, String syllabusId);

	void addSelectIndex_TheoInnerExperiment(
			List<IndexSelect_TheoInnerExperiment> newindexSelect_TheoInnerExperimentList,
			String syllabusId);

	void addAbilityAndTeachObj_TheoInnerExperiment(
			List<AbilityAndTeachObj_TheoInnerExperiment> abilityAndTeachObjSelect_TheoInnerExperiment,
			String syllabusId);

	List<AbilityAndTeachObj_TheoInnerExperiment> findAbiAndTeachListBySyllabusid_TheoInnerExperiment(
			String syllabusId_Copy);

	List<AbilityAndTeachObj_TheoInnerExperiment> findTeachByAbility_TheoInnerExperiment(
			Ability ability, String syllabusId);

}
