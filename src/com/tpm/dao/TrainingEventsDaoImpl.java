package com.tpm.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.tpm.entity.Curriculum;
import com.tpm.entity.Department;
import com.tpm.entity.TrainingEvents;
@SuppressWarnings("all")
public class TrainingEventsDaoImpl extends BaseDaoImpl<TrainingEvents> implements TrainingEventsDao {

	public List<TrainingEvents> getbydepartment(Department department) {
		List<TrainingEvents> trainingEventslist = (List<TrainingEvents>)this.getHibernateTemplate().
				find("from TrainingEvents where department=?", department);
		if(trainingEventslist !=null && trainingEventslist.size() != 0){
			return trainingEventslist;
		}else{
			return null;
		}
	}

	public List<TrainingEvents> getbydepartmentnototal(Department department) {
		List<TrainingEvents> trainingEventslist = (List<TrainingEvents>)this.getHibernateTemplate().
				find("from TrainingEvents where department=? and totaltag=?", department,0);
		if(trainingEventslist !=null && trainingEventslist.size() != 0){
			return trainingEventslist;
		}else{
			return null;
		}
	}

	public TrainingEvents gettotal(Department department) {
		List<TrainingEvents> trainingEventslist = (List<TrainingEvents>)this.getHibernateTemplate().
				find("from TrainingEvents where department=? and totaltag=?", department,1);
		if(trainingEventslist !=null && trainingEventslist.size() != 0){
			return trainingEventslist.get(0);
		}else{
			return null;
		}
	}

	public TrainingEvents gettotalli(Department department) {
		List<TrainingEvents> trainingEventslist = (List<TrainingEvents>)this.getHibernateTemplate().
				find("from TrainingEvents where department=? and totaltag=?", department,2);
		if(trainingEventslist !=null && trainingEventslist.size() != 0){
			return trainingEventslist.get(0);
		}else{
			return null;
		}
	}


}
