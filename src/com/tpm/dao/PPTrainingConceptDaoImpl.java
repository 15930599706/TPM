package com.tpm.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.tpm.entity.Department;
import com.tpm.entity.PPTrainingConcept;
@SuppressWarnings("all")
public class PPTrainingConceptDaoImpl extends BaseDaoImpl<PPTrainingConcept> implements PPTrainingConceptDao {

	public PPTrainingConcept getbydepartment(Department department) {
		DetachedCriteria criteria = DetachedCriteria.forClass(PPTrainingConcept.class).add(Restrictions.eq("department", department));
		List<PPTrainingConcept> ppTrainingConceptlist = this.getHibernateTemplate().findByCriteria(criteria);
		if(ppTrainingConceptlist != null && ppTrainingConceptlist.size() != 0){
			return ppTrainingConceptlist.get(0);
		}else{
			return null;
		}
	}

	

}
