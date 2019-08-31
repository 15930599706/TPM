package com.tpm.dao;

import java.util.List;

import com.tpm.entity.Ability;
import com.tpm.entity.AbilityAndTeachObj_CourseDesign;
import com.tpm.entity.AbilityAndTeachObj_InnerExperiment;
import com.tpm.entity.ApplicationSyllabus_CourseDesign;
import com.tpm.entity.IndexSelect_CourseDesign;

public interface AbilityAndTeachObj_CourseDesignDao extends BaseDao<AbilityAndTeachObj_CourseDesign> {

	ApplicationSyllabus_CourseDesign findAppSyllabus_CourseDesign(Integer syllabusId);

	List<IndexSelect_CourseDesign> findIndexSelectByAbility_CourseDesign(Ability ability, String syllabusId);

	void addSelectIndex_CourseDesign(
			List<IndexSelect_CourseDesign> newindexSelect_CourseDesignList,
			String syllabusId);

	void addAbilityAndTeachObj_CourseDesign(List<AbilityAndTeachObj_CourseDesign> abilityAndTeachObjSelect_CourseDesign,String syllabusId);

	List<AbilityAndTeachObj_CourseDesign> findAbiAndTeachListBySyllabusid(
			String syllabusId_Copy);

	List<AbilityAndTeachObj_CourseDesign> findTeachByAbility_CourseDesign(
			Ability ability, String syllabusId);


}
