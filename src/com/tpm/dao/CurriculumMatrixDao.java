package com.tpm.dao;

import java.util.List;

import com.tpm.entity.Ability;
import com.tpm.entity.Curriculum;
import com.tpm.entity.CurriculumMatrix;
import com.tpm.entity.Professional;

public interface CurriculumMatrixDao extends BaseDao<CurriculumMatrix>{

	List<CurriculumMatrix> findbycuandab(Curriculum curriculum, Ability ability);

	CurriculumMatrix getbyall(Curriculum curriculum, Ability ability, int i);

	List<CurriculumMatrix> findbycuandab_pro(Curriculum curriculum,
			Ability ability, Professional professional);

	//有专业方向的
	CurriculumMatrix getbyall(Curriculum curriculum, Ability ability, int i,
			Professional professional);

	List<CurriculumMatrix> findbycuandpro(Curriculum curriculum,
			Professional professional);

}
