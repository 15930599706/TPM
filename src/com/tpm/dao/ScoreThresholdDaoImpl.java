package com.tpm.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.tpm.entity.AvePerThreshold;
import com.tpm.entity.Department;
import com.tpm.entity.ScoreThreshold;
import com.tpm.entity.User;

public class ScoreThresholdDaoImpl extends BaseDaoImpl<ScoreThreshold> implements ScoreThresholdDao {

	public ScoreThreshold findbydepartment(Department department) {
		List<ScoreThreshold> scoreThresholdlist = this.getHibernateTemplate().find("from ScoreThreshold where department=?",department);
		if(scoreThresholdlist.size()!=0){
			return scoreThresholdlist.get(0);
		}else{
			return null;
		}
	}

	public ScoreThreshold findbydepartmentnull() {
		DetachedCriteria criteria = DetachedCriteria.forClass(ScoreThreshold.class);
		criteria.add(Restrictions.isNull("department"));
		List<ScoreThreshold> list = this.getHibernateTemplate().findByCriteria(criteria);
		if(list.size()!=0){
			return list.get(0);
		}else{
			return null;}
	}



}
