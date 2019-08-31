package com.tpm.dao;

import java.util.List;

import com.tpm.entity.Ability;
import com.tpm.entity.AbilityAndTeachObj;
import com.tpm.entity.ApplicationSyllabus;
import com.tpm.entity.Curriculum;
import com.tpm.entity.Department;
import com.tpm.entity.IndexSelect;
import com.tpm.entity.Professional;

public interface AbilityAndTeachObjDao extends BaseDao<AbilityAndTeachObj> {

	List<IndexSelect> findIndexSelectByAbility(Ability ability, String syllabusId);
	
	//通过大纲ID查询记录
	ApplicationSyllabus findAppSyllabus(Integer syllabusId);
	
	//保存选择的指标点
	void addSelectIndex(List<IndexSelect> newindexSelectList, String syllabusId);

	//保存毕业要求与教学目标的对应关系
	void addAbilityAndTeachObj(List<AbilityAndTeachObj> abilityAndTeachObjSelect, String syllabusId);

	//通过大纲ID查询毕业要求与教学目标对应关系
	List<AbilityAndTeachObj> findAbiAndTeachListBySyllabusid(
			String syllabusId_Copy);

	//通过选择的毕业要求查询选择的教学目标
	List<AbilityAndTeachObj> findTeachByAbility(Ability ability,
			String syllabusId);

	

}
