package com.tpm.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.tpm.entity.Ability;
import com.tpm.entity.Department;
import com.tpm.entity.PTBasicInformation;
import com.tpm.entity.User;
@SuppressWarnings("all")
public class AbilityDaoImpl extends BaseDaoImpl<Ability> implements AbilityDao {

	public List<Ability> getbydepartment(Department department) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Ability.class).add(Restrictions.eq("department", department));
		List<Ability> abilitylist = this.getHibernateTemplate().findByCriteria(criteria);
		if(abilitylist != null && abilitylist.size() != 0){
			return abilitylist;
		}else{
			return null;
		}
	}

	

	public Ability getbyabilityname(String string, Department department) {
		List<Ability> abilitielist = (List<Ability>)this.getHibernateTemplate().
				find("from Ability where abilityname=? and department=?", string,department);
		if(abilitielist !=null && abilitielist.size() != 0){
			return abilitielist.get(0);
		}else{
			return null;
		}
	}

}
