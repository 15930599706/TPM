package com.tpm.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.tpm.entity.Ability;
import com.tpm.entity.Curriculum;
import com.tpm.entity.CurriculumMatrix;
import com.tpm.entity.Professional;
import com.tpm.entity.User;

public class CurriculumMatrixDaoImpl extends BaseDaoImpl<CurriculumMatrix> implements CurriculumMatrixDao {

	//通过课程和能力查询
	public List<CurriculumMatrix> findbycuandab(Curriculum curriculum,
			Ability ability) {
		List<CurriculumMatrix> curriculumMatrixs = this.getHibernateTemplate().find("from CurriculumMatrix where curriculum=? and ability=?",curriculum,ability);
		if(curriculumMatrixs.size()!=0){
			return curriculumMatrixs;
		}else{
			return null;}
	}
	//通过课程和专业方向查询
	public List<CurriculumMatrix> findbycuandpro(Curriculum curriculum,
			Professional professional) {
		List<CurriculumMatrix> curriculumMatrixs = this.getHibernateTemplate().find("from CurriculumMatrix where curriculum=? and professional=?",curriculum,professional);
		if(curriculumMatrixs.size()!=0){
			return curriculumMatrixs;
		}else{
			return null;}
	}
	//通过课程、能力和专业方向查询
	public List<CurriculumMatrix> findbycuandab_pro(Curriculum curriculum,
			Ability ability, Professional professional) {
		List<CurriculumMatrix> curriculumMatrixs = this.getHibernateTemplate().find("from CurriculumMatrix where curriculum=? and ability=? and professional=?",curriculum,ability,professional);
		if(curriculumMatrixs.size()!=0){
			return curriculumMatrixs;
		}else{
			return null;}
	}

	public CurriculumMatrix getbyall(Curriculum curriculum, Ability ability,
			int i) {
		DetachedCriteria criteria = DetachedCriteria.forClass(CurriculumMatrix.class);
		criteria.add(Restrictions.eq("curriculum", curriculum)).add(Restrictions.eq("ability", ability)).add(Restrictions.eq("count", i));
		List<CurriculumMatrix> curriculumMatrixs = (List<CurriculumMatrix>)this.getHibernateTemplate().findByCriteria(criteria);
		if(curriculumMatrixs.size()!=0){
			return curriculumMatrixs.get(0);
		}else{
			return null;}
	}

	public CurriculumMatrix getbyall(Curriculum curriculum, Ability ability,
			int i, Professional professional) {
		DetachedCriteria criteria = DetachedCriteria.forClass(CurriculumMatrix.class);
		criteria.add(Restrictions.eq("curriculum", curriculum)).add(Restrictions.eq("ability", ability)).add(Restrictions.eq("count", i)).add(Restrictions.eq("professional",professional));
		List<CurriculumMatrix> curriculumMatrixs = (List<CurriculumMatrix>)this.getHibernateTemplate().findByCriteria(criteria);
		if(curriculumMatrixs.size()!=0){
			return curriculumMatrixs.get(0);
		}else{
			return null;}
	}





}
