package com.tpm.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.tpm.entity.ExperimentLog;
import com.tpm.entity.User;

public class ExperimentLogDaoImpl extends BaseDaoImpl<ExperimentLog> implements
		ExperimentLogDao {

	public int findsysrzAdminCount() {
		DetachedCriteria criteria = DetachedCriteria.forClass(ExperimentLog.class);
	//	criteria.add(Restrictions.eq("sign", "3"));
		List<ExperimentLog> experimentLoglist = (List<ExperimentLog>)this.getHibernateTemplate().findByCriteria(criteria);
		if(experimentLoglist != null){
			return experimentLoglist.size();
		}else{
			return 0;
		}
	}

	public List<ExperimentLog> findsysrzAdminT(int begin, int pageSize) {
		DetachedCriteria criteria = DetachedCriteria.forClass(ExperimentLog.class);
	//	criteria.add(Restrictions.eq("sign", "3"));
		List<ExperimentLog> experimentLoglist = (List<ExperimentLog>)this.getHibernateTemplate().findByCriteria(criteria);
		return (List<ExperimentLog>)this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
	}

}
