package com.tpm.dao;

import java.util.List;

import com.tpm.entity.Ability;
import com.tpm.entity.AbilityAndTeachObj_Gra;
import com.tpm.entity.ApplicationSyllabus_Gra;
import com.tpm.entity.IndexSelect_Gra;

public interface AbilityAndTeachObj_GraDao extends BaseDao<AbilityAndTeachObj_Gra> {

	ApplicationSyllabus_Gra findAppSyllabus_Gra(Integer syllabusId);

	List<IndexSelect_Gra> findIndexSelectByAbility_Gra(Ability ability,String syllabusId);

	void addSelectIndex_Gra(List<IndexSelect_Gra> newindexSelect_GraList,String syllabusId);

	void addAbilityAndTeachObj_Gra(List<AbilityAndTeachObj_Gra> abilityAndTeachObjSelect_Gra,String syllabusId);

	List<AbilityAndTeachObj_Gra> findAbiAndTeachListBySyllabusid(
			String syllabusId_Copy);

	List<AbilityAndTeachObj_Gra> findTeachByAbility_Gra(Ability ability,
			String syllabusId);

}
