package com.tpm.dao;

import java.util.List;

import com.tpm.entity.College;
import com.tpm.entity.Experiment;

public class ExperimentDaoImpl extends BaseDaoImpl<Experiment> implements ExperimentDao {

	public List<Experiment> findbyCollege(College college) {
		List<Experiment> experimentlist = (List<Experiment>)this.getHibernateTemplate().
				find("from Experiment where college = ?", college);
		if(experimentlist.size() != 0){
			return experimentlist;
		}else{
			return null;
		}
	}


}
