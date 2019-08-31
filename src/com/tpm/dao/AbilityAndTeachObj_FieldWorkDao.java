package com.tpm.dao;

import java.util.List;

import com.tpm.entity.Ability;
import com.tpm.entity.AbilityAndTeachObj_FieldWork;
import com.tpm.entity.ApplicationSyllabus_FieldWork;
import com.tpm.entity.IndexSelect_FieldWork;

public interface AbilityAndTeachObj_FieldWorkDao extends BaseDao<AbilityAndTeachObj_FieldWork> {

	ApplicationSyllabus_FieldWork findAppSyllabus_FieldWork(Integer syllabusId);

	List<IndexSelect_FieldWork> findIndexSelectByAbility_FieldWork(
			Ability ability, String syllabusId);

	void addSelectIndex_FieldWork(List<IndexSelect_FieldWork> newindexSelect_FieldWorkList,String syllabusId);

	void addAbilityAndTeachObj_FieldWork(List<AbilityAndTeachObj_FieldWork> abilityAndTeachObjSelect_FieldWork,String syllabusId);

	List<AbilityAndTeachObj_FieldWork> findAbiAndTeachListBySyllabusid(
			String syllabusId_Copy);

	List<AbilityAndTeachObj_FieldWork> findTeachByAbility_FieldWork(
			Ability ability, String syllabusId);


}
